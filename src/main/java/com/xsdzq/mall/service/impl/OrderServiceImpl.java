package com.xsdzq.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.xsdzq.mall.constants.*;
import com.xsdzq.mall.dao.BrandRepository;
import com.xsdzq.mall.dao.MallUserInfoRepository;
import com.xsdzq.mall.dao.OrderRepository;
import com.xsdzq.mall.dao.ProductRepository;
import com.xsdzq.mall.entity.*;
import com.xsdzq.mall.exception.BusinessException;
import com.xsdzq.mall.manager.OrderManager;
import com.xsdzq.mall.model.ExchangePrizeReqDTO;
import com.xsdzq.mall.model.ExchangePrizeRespDTO;
import com.xsdzq.mall.model.ExchangeRecordRespDTO;
import com.xsdzq.mall.model.MyExchangeRecordRespDTO;
import com.xsdzq.mall.service.MallUserService;
import com.xsdzq.mall.service.OrderService;
import com.xsdzq.mall.service.ProductService;
import com.xsdzq.mall.service.client.chengquan.CommonRespEntity;
import com.xsdzq.mall.service.client.chengquan.DirectChargeService;
import com.xsdzq.mall.service.client.chengquan.PayTelReqEntity;
import com.xsdzq.mall.service.client.chengquan.PayTelRespEntity;
import com.xsdzq.mall.util.DateUtil;
import com.xsdzq.mall.util.PresentUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private ProductRepository productRepository;
    @Resource
    private BrandRepository brandRepository;
    @Resource
    private MallUserInfoRepository mallUserInfoRepository;
    @Resource
    private ProductService productService;
    @Resource
    private DirectChargeService directChargeService;
    @Resource
    private OrderManager orderManager;
    @Resource
    private MallUserService mallUserService;

    @Override
    public List<ExchangeRecordRespDTO> getLatestExchangeRecord() {
        PageRequest pageable = PageRequest.of(0, 3);
        Page<MallOrderEntity> resultPage = orderRepository.findByOrderStatusOrderByEndTimeDesc(OrderStatusEnum.SUCCESS.getCode(), pageable);
        List<ExchangeRecordRespDTO> recordList = new ArrayList<>();
        List<MallOrderEntity> orderList = resultPage.getContent();
        if (!CollectionUtils.isEmpty(orderList)) {
            orderList.forEach(t -> {
                ExchangeRecordRespDTO record = new ExchangeRecordRespDTO();
                record.setClientId(PresentUtil.getInstance().getSecretName(t.getClientId()));
                record.setPrizeName(t.getGoodsName());
                record.setEndTime(t.getEndTime());
                record.setExchangeType(ExchangeTypeEnum.RECHARGE.getCode());
                recordList.add(record);
            });
        }

        return recordList;
    }

    @Override
    public ExchangePrizeRespDTO exchangePrize(MallUserEntity user, ExchangePrizeReqDTO epReqDTO) {
        ExchangePrizeRespDTO epRespDTO = new ExchangePrizeRespDTO();
        if (!StringUtils.isEmpty(epReqDTO.getOrderNo())) {
            MallOrderEntity order = orderRepository.findByOrderNo(epReqDTO.getOrderNo());
            if (order != null) {
                epRespDTO.setStatus(getExchangeStatus(order.getOrderStatus()));
                //epRespDTO.setFailureReason();
                epRespDTO.setOrderNo(order.getOrderNo());
                epRespDTO.setUseIntegral(order.getUseIntegral());
                epRespDTO.setRechargeAmount(order.getRechargeAmount());
                MallUserInfoEntity currentUserInfo = mallUserInfoRepository.findByClientId(user.getClientId());
                epRespDTO.setAvailableIntegral(currentUserInfo.getCreditScore());
            }
            return epRespDTO;
        }

        MallBrandEntity brand = brandRepository.findByGoodsTypeId(epReqDTO.getGoodsTypeId());
        if (brand == null) {
            throw new BusinessException("品牌不存在");
        } else if (ProductStatusEnum.OFF_SHELF.getCode().equals(brand.getSellStatus())) {
            throw new BusinessException("品牌已下架");
        }

        MallProductEntity product = productRepository.findByGoodsNoAndGoodsTypeId(epReqDTO.getGoodsNo(), epReqDTO.getGoodsTypeId());
        if (product == null) {
            throw new BusinessException("商品不存在");
        } else if (ProductStatusEnum.OFF_SHELF.getCode().equals(product.getSellStatus())) {
            throw new BusinessException("商品已下架");
        }

        MallUserInfoEntity userInfo = mallUserInfoRepository.findByClientId(user.getClientId());
        if (userInfo == null) {
            throw new BusinessException("用户没有积分");
        } else if (userInfo.getCreditScore() < productService.getProductIntegral(product.getPrice())) {
            throw new BusinessException("用户积分不足");
        }

        double currentDayValue = mallUserService.getCurrentDayValue(user, DateUtil.getStandardDate(new Date()));
        int dayValue = Double.valueOf(currentDayValue).intValue();
        if(product.getOfficialPrice().compareTo(new BigDecimal(dayValue)) > 0){
            throw new BusinessException("超过单日额度");
        }

        // 保存订单，冻结用户积分
        MallUserInfoEntity updateUserInfo = new MallUserInfoEntity();
        updateUserInfo.setClientId(user.getClientId());
        updateUserInfo.setFrozenIntegral(productService.getProductIntegral(product.getPrice()));
        MallOrderEntity order = buildOrder(user, epReqDTO, brand, product);
        try {
            orderManager.save(order, updateUserInfo);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("请求被防重");
        }

        // 调用橙券话费直充接口
        MallOrderEntity updateOrder = callPayTel(order);
        CreditRecordEntity creditRecord = buildCreditRecord(updateOrder, user);
        // 更新订单状态，解冻用户积分，记录积分使用记录
        try {
            orderManager.update(updateOrder, updateUserInfo, creditRecord);
        } catch (Exception e) {
            logger.error("更新订单状态异常updateOrder:{},updateUserInfo:{}", JSON.toJSON(updateOrder), JSON.toJSON(updateUserInfo), e);
            updateOrder.setOrderStatus(OrderStatusEnum.PROCESSING.getCode());
        }

        epRespDTO.setOrderNo(order.getOrderNo());
        epRespDTO.setUseIntegral(order.getUseIntegral());
        int availableIntegral;
        if (OrderStatusEnum.FAILURE.getCode().equals(updateOrder.getOrderStatus())) {
            availableIntegral = userInfo.getCreditScore();
        } else {
            availableIntegral = userInfo.getCreditScore() - productService.getProductIntegral(product.getPrice());
        }
        epRespDTO.setAvailableIntegral(availableIntegral);
        epRespDTO.setStatus(getExchangeStatus(updateOrder.getOrderStatus()));
        epRespDTO.setRechargeAmount(order.getRechargeAmount());
        epRespDTO.setFailureReason(order.getRechargeMessage());
        return epRespDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MyExchangeRecordRespDTO> getUserExchangeRecord(MallUserEntity mallUserEntity) {
        List<MyExchangeRecordRespDTO> myExchangeRecordList = Collections.emptyList();
        PageRequest pageable = PageRequest.of(0, 5000);
        Page<MallOrderEntity> resultPage = orderRepository.findByClientIdOrderByCreateTimeDesc(mallUserEntity.getClientId(), pageable);
        List<MallOrderEntity> orderEntityList = resultPage.getContent();
        if (!CollectionUtils.isEmpty(orderEntityList)) {
            myExchangeRecordList = new ArrayList<>(orderEntityList.size());
            for (MallOrderEntity order : orderEntityList) {
                MyExchangeRecordRespDTO mer = new MyExchangeRecordRespDTO();
                String tradeDate = order.getTradeDate().toString();
                mer.setGroupTime(tradeDate.substring(0, 4) + "-" + tradeDate.substring(4, 6));
                mer.setCreateTime(order.getCreateTime());
                mer.setCompleteTime(order.getEndTime());
                mer.setGoodsName(order.getGoodsName());
                mer.setRechargeAmount(order.getRechargeAmount());
                mer.setUseIntegral(order.getUseIntegral());
                mer.setRechargeNumber(order.getRechargeNumber());

                Optional<MallProductEntity> optional = productRepository.findById(order.getProductId());
                if (optional.isPresent()) {
                    MallProductEntity product = optional.get();
                    mer.setSmallImage(product.getSmallImage());
                    mer.setBigImage(product.getBigImage());
                    mer.setExchangePrice(product.getPrice().multiply(new BigDecimal(order.getQuantity())));
                }
                mer.setStatus(getExchangeStatus(order.getOrderStatus()));
                mer.setFailureReason(order.getRechargeMessage());
                myExchangeRecordList.add(mer);
            }
        }
        return myExchangeRecordList;
    }

    private MallOrderEntity callPayTel(MallOrderEntity order) {
        PayTelReqEntity payTelReq = buildPayTelReq(order);
        CommonRespEntity<PayTelRespEntity> commonResp = null;
        try {
            commonResp = directChargeService.payTel(payTelReq);
        } catch (Exception e) {
            logger.error("话费下单接口请求异常payTelReq:{}", JSON.toJSON(payTelReq), e);
            commonResp = new CommonRespEntity<>();
            commonResp.setCode(-1);
            commonResp.setMessage("话费下单接口请求异常");
        }
        MallOrderEntity updateOrder = new MallOrderEntity();
        BeanUtils.copyProperties(order, updateOrder);
        updateOrder.setRechargeCode(commonResp.getCode());
        updateOrder.setRechargeMessage(commonResp.getMessage());
        updateOrder.setUpdateTime(null);
        // 请求通过
        if (commonResp.getCode() == 7000) {
            PayTelRespEntity data = commonResp.getData();
            updateOrder.setOrderStatus(getOrderStatus(data.getState()));
            updateOrder.setStartTime(DateUtil.strToDate(data.getStart_time()));
            updateOrder.setEndTime(DateUtil.strToDate(data.getEnd_time()));
            updateOrder.setRechargeStatus(data.getState());
            updateOrder.setConsumeAmount(data.getConsume_amount());
            // 系统异常
        } else if (commonResp.getCode() == 7777 || commonResp.getCode() == -1) {
            updateOrder.setOrderStatus(OrderStatusEnum.PROCESSING.getCode());
            updateOrder.setRechargeStatus(ChengQuanOrderStatusEnum.RECHARGE.getCode());
            // 失败
        } else {
            updateOrder.setOrderStatus(OrderStatusEnum.FAILURE.getCode());
            updateOrder.setRechargeStatus(ChengQuanOrderStatusEnum.FAILURE.getCode());
        }
        return updateOrder;
    }

    private PayTelReqEntity buildPayTelReq(MallOrderEntity order) {
        PayTelReqEntity payTelReq = new PayTelReqEntity();
        payTelReq.setOrder_no(order.getOrderNo());
        payTelReq.setRecharge_number(order.getRechargeNumber());
        // 充值面值(单位：分)
        payTelReq.setPrice(order.getRechargeAmount().multiply(new BigDecimal("100")));
        payTelReq.setTimestamp(order.getCreateTime().getTime());
        return payTelReq;
    }

    private MallOrderEntity buildOrder(MallUserEntity user, ExchangePrizeReqDTO epReqDTO, MallBrandEntity brand, MallProductEntity product) {
        MallOrderEntity order = new MallOrderEntity();
        order.setClientName(user.getClientName());
        order.setClientId(user.getClientId());
        order.setTradeDate(DateUtil.getIntegerTime(new Date()));
        order.setRequestTime(epReqDTO.getRequestTime());
        order.setDepartmentCode(user.getDepartmentCode());
        order.setDepartmentName(user.getDepartmentName());
        order.setOrderNo(UUID.randomUUID().toString().replaceAll("-", ""));
        order.setBrandId(brand.getId());
        order.setGoodsTypeId(brand.getGoodsTypeId());
        order.setGoodsTypeName(brand.getGoodsTypeName());
        order.setProductId(product.getId());
        order.setGoodsNo(product.getGoodsNo());
        order.setGoodsName(product.getGoodsName());
        order.setQuantity(1);
        order.setRechargeNumber(epReqDTO.getRechargeNumber());
        order.setRechargeUserName("");
        order.setRechargeAmount(product.getOfficialPrice());
        order.setUseIntegral(productService.getProductIntegral(product.getPrice()));
        order.setOrderStatus(OrderStatusEnum.INIT.getCode());
        order.setAdjustmentType(AdjustmentTypeEnum.DEFAULT.getCode());
        return order;
    }


    private Integer getOrderStatus(String chengQuanOrderStatus) {
        Integer orderStatus;
        switch (ChengQuanOrderStatusEnum.match(chengQuanOrderStatus)) {
            case SUCCESS:
                orderStatus = OrderStatusEnum.SUCCESS.getCode();
                break;
            case FAILURE:
                orderStatus = OrderStatusEnum.FAILURE.getCode();
                break;
            case RECHARGE:
            default:
                orderStatus = OrderStatusEnum.PROCESSING.getCode();
        }

        return orderStatus;
    }

    private Integer getExchangeStatus(Integer orderStatus) {
        Integer exchangeStatus;
        switch (OrderStatusEnum.match(orderStatus)) {
            case SUCCESS:
                exchangeStatus = ExchangeStatusEnum.SUCCESS.getCode();
                break;
            case FAILURE:
                exchangeStatus = ExchangeStatusEnum.FAILURE.getCode();
                break;
            case PROCESSING:
            case INIT:
            default:
                exchangeStatus = ExchangeStatusEnum.PROCESSING.getCode();
        }

        return exchangeStatus;
    }

    private CreditRecordEntity buildCreditRecord(MallOrderEntity order, MallUserEntity user) {
        CreditRecordEntity credit = new CreditRecordEntity();
        credit.setType(CreditRecordConst.REDUCESCORE);
        credit.setReasonCode(CreditRecordConst.EXCHANGECARD);
        credit.setReason(CreditRecordConst.PREPAIDREFILLREASON);
        credit.setItem(order.getGoodsName());
        credit.setIntegralNumber(order.getUseIntegral());
        String tradeDate = order.getTradeDate().toString();
        credit.setDateFlag(tradeDate.substring(0, 4) + "-" + tradeDate.substring(4, 6) + "-" + tradeDate.substring(6, 8));
        credit.setGroupTime(tradeDate.substring(0, 4) + "-" + tradeDate.substring(4, 6));
        credit.setChangeType(CreditRecordConst.CHANGETYPE_COMPLETE);
        credit.setRemindNumer(0);
        credit.setRecordTime(order.getEndTime());
        credit.setMallUserEntity(user);
        return credit;
    }

}
