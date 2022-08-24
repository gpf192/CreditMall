package com.xsdzq.mall.controller;

import com.xsdzq.mall.entity.MallUserEntity;
import com.xsdzq.mall.entity.PresentEntity;
import com.xsdzq.mall.model.*;
import com.xsdzq.mall.service.OrderService;
import com.xsdzq.mall.service.PresentService;
import com.xsdzq.mall.service.ProductService;
import com.xsdzq.mall.service.TokenService;
import com.xsdzq.mall.util.GsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping(value = "/mall/present")
public class PresentController {

    private static final Logger logger = LoggerFactory.getLogger(PresentController.class);

    @Autowired
    private PresentService presentService;
    @Resource
    private ProductService productService;
    @Resource
    private OrderService orderService;
    @Resource
    private TokenService tokenService;

    @GetMapping("/all")
    public Map<String, Object> getAllPresent() {

        List<PresentEntity> presentEntities = presentService.getPresentEntities();
        return GsonUtil.buildMap(0, "success", presentEntities);
    }


    @GetMapping("/latest")
    public Map<String, Object> getLatestPresentResult() {

        List<PresentLatestResult> resultEntities = presentService.getLatestPresentResultEntities();
        return GsonUtil.buildMap(0, "success", resultEntities);
    }


    @GetMapping("/hot")
    public Map<String, Object> getHotPresents() {

        List<PresentEntity> presentEntities = presentService.getHotPresentList();
        return GsonUtil.buildMap(0, "success", presentEntities);
    }

    @GetMapping("/category/all")
    public Map<String, Object> getAllPresentCategory() {

        List<PresentCategorys> presentCategoryEntities = presentService.getPresentCategoryEntities();
        return GsonUtil.buildMap(0, "success", presentCategoryEntities);
    }

    @GetMapping("/composite-hot")
    public Map<String, Object> getHotProduct(@RequestHeader("Authorization") String token) {
        MallUserEntity mallUserEntity = null;
        if (token != null && !"".equals(token.trim()) && !"false".equals(token) && !"0".equals(token)) {
            mallUserEntity = tokenService.getMallUserEntity(token);
        }
        HotProductRespDTO hotProduct = new HotProductRespDTO();
        List<PresentEntity> presentEntities = presentService.getHotPresentList();
        hotProduct.setCardCoupon(presentEntities);

        List<DirectRechargeRespDTO> directRechargeList = productService.getHotDirectRecharge(mallUserEntity);
        hotProduct.setDirectRecharge(directRechargeList);
        return GsonUtil.buildMap(0, "success", hotProduct);
    }

    @GetMapping("/composite-latest")
    public Map<String, Object> getLatestExchangeRecord() {
        ArrayList<ExchangeRecordRespDTO> recordCompositeList = null;
        try {
            PriorityQueue<ExchangeRecordRespDTO> recordQueue = new PriorityQueue<>(Comparator.comparing(ExchangeRecordRespDTO::getEndTime));

            List<ExchangeRecordRespDTO> recordList = orderService.getLatestExchangeRecord();
            if (!CollectionUtils.isEmpty(recordList)) {
                recordQueue.addAll(recordList);
            }

            List<PresentLatestResult> resultList = presentService.getLatestPresentResultEntities();
            if (!CollectionUtils.isEmpty(resultList)) {
                for (PresentLatestResult result : resultList) {
                    ExchangeRecordRespDTO record = new ExchangeRecordRespDTO();
                    record.setClientId(result.getClientId());
                    record.setPrizeName(result.getPrizeName());
                    record.setEndTime(result.getRecordTime());
                    recordQueue.add(record);
                }
            }

            recordCompositeList = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                ExchangeRecordRespDTO record = recordQueue.poll();
                if (record != null) {
                    recordCompositeList.add(record);
                }
            }
        } catch (Exception ignored) {
        }

        return GsonUtil.buildMap(0, "success", recordCompositeList);
    }

    @GetMapping("/category/composite-all")
    public Map<String, Object> getAllProductCategory(@RequestHeader("Authorization") String token) {
        MallUserEntity mallUserEntity = null;
        if (token != null && !"".equals(token.trim()) && !"false".equals(token) && !"0".equals(token)) {
            mallUserEntity = tokenService.getMallUserEntity(token);
        }
        AllProductRespDTO allProduct = new AllProductRespDTO();
        List<PresentCategorys> presentCategorys = presentService.getPresentCategoryEntities();
        allProduct.setCardCoupon(presentCategorys);

        List<DirectRechargeRespDTO> directRechargeList = productService.getHotDirectRecharge(mallUserEntity);
        allProduct.setDirectRecharge(directRechargeList);
        return GsonUtil.buildMap(0, "success", allProduct);
    }

}