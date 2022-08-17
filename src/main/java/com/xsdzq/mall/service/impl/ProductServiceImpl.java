package com.xsdzq.mall.service.impl;

import com.xsdzq.mall.constants.BrandStatusEnum;
import com.xsdzq.mall.dao.BrandRepository;
import com.xsdzq.mall.dao.MallUserInfoRepository;
import com.xsdzq.mall.dao.ProductRepository;
import com.xsdzq.mall.entity.MallBrandEntity;
import com.xsdzq.mall.entity.MallProductEntity;
import com.xsdzq.mall.entity.MallUserEntity;
import com.xsdzq.mall.entity.MallUserInfoEntity;
import com.xsdzq.mall.model.DirectRechargeRespDTO;
import com.xsdzq.mall.service.MallUserService;
import com.xsdzq.mall.service.ProductService;
import com.xsdzq.mall.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductRepository productRepository;
    @Resource
    private BrandRepository brandRepository;
    @Resource
    private MallUserInfoRepository mallUserInfoRepository;
    @Resource
    private MallUserService mallUserService;
    @Resource
    private ProductService productService;

    @Override
    public List<DirectRechargeRespDTO> getHotDirectRecharge(MallUserEntity mallUserEntity) {
        ArrayList<DirectRechargeRespDTO> drList = new ArrayList<>();
        List<MallBrandEntity> brandList = brandRepository.findBySellStatusOrderByCreateTimeDesc(BrandStatusEnum.ON_SHELF.getCode());
        if (!CollectionUtils.isEmpty(brandList)) {
            // 查询用户可用积分，积分金额
            Integer creditScore = null;
            Integer dayValue = null;
            if (mallUserEntity != null) {
                MallUserInfoEntity userInfo = mallUserInfoRepository.findByClientId(mallUserEntity.getClientId());
                creditScore = userInfo == null ? null : userInfo.getCreditScore();
                double currentDayValue = mallUserService.getCurrentDayValue(mallUserEntity, DateUtil.getStandardDate(new Date()));
                dayValue = Double.valueOf(currentDayValue).intValue();
            }

            for (MallBrandEntity brand : brandList) {
                DirectRechargeRespDTO dr = new DirectRechargeRespDTO();
                dr.setGoodsTypeId(brand.getGoodsTypeId());
                dr.setGoodsTypeName(brand.getGoodsTypeName());
                dr.setImage(brand.getImageUrl());
                dr.setAvailableIntegral(creditScore);
                dr.setPriceDayQuota(dayValue);

                ArrayList<DirectRechargeRespDTO.GoodsRespDTO> goodsList = new ArrayList<>();
                List<MallProductEntity> productList = productRepository.findBySellStatusOrderByOfficialPriceAsc(BrandStatusEnum.ON_SHELF.getCode());
                if (!CollectionUtils.isEmpty(productList)) {
                    productList.forEach(p -> {
                        DirectRechargeRespDTO.GoodsRespDTO goodsDTO = new DirectRechargeRespDTO.GoodsRespDTO();
                        goodsDTO.setGoodsNo(p.getGoodsNo());
                        goodsDTO.setGoodsName(p.getGoodsName());
                        goodsDTO.setSmallImage(p.getSmallImage());
                        goodsDTO.setBigImage(p.getBigImage());
                        goodsDTO.setOfficialPrice(p.getOfficialPrice());
                        goodsDTO.setGoodsIntegral(productService.getProductIntegral(p.getPrice()));
                        goodsList.add(goodsDTO);
                    });
                }
                dr.setGoodsList(goodsList);
                drList.add(dr);
            }
        }

        return drList;
    }

    @Override
    public Integer getProductIntegral(BigDecimal productPrice) {
        return productPrice.multiply(new BigDecimal("100")).intValue();
    }

}