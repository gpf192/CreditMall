package com.xsdzq.mall.service.impl;

import com.xsdzq.mall.controller.PresentController;
import com.xsdzq.mall.dao.MallUserInfoRepository;
import com.xsdzq.mall.dao.MallUserRepository;
import com.xsdzq.mall.entity.MallUserEntity;
import com.xsdzq.mall.model.*;
import com.xsdzq.mall.service.CreditService;
import com.xsdzq.mall.service.OrderService;
import com.xsdzq.mall.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {
    @Resource
    private OrderService orderService;
    @Resource
    private MallUserRepository mallUserRepository;
    @Resource
    private CreditService creditService;
    @Resource
    private ProductService productService;
    @Resource
    private PresentController presentController;

    //@Test
    void getLatestExchangeRecord() {
        Map<String, Object> latestExchangeRecord = presentController.getLatestExchangeRecord();
        System.out.println(latestExchangeRecord);
    }

    //@Test
    void exchangePrize() {
        MallUserEntity user = mallUserRepository.findByClientId("1688988");
        ExchangePrizeReqDTO exchangePrizeReqDTO = new ExchangePrizeReqDTO();
        exchangePrizeReqDTO.setGoodsTypeId("card");
        exchangePrizeReqDTO.setGoodsNo("aaa");
        exchangePrizeReqDTO.setRequestTime(System.currentTimeMillis());
        exchangePrizeReqDTO.setRechargeNumber("15010658509");

        ExchangePrizeRespDTO exchangePrizeRespDTO = orderService.exchangePrize(user, exchangePrizeReqDTO);
        System.out.println(exchangePrizeRespDTO);

        exchangePrizeReqDTO.setOrderNo("51f74028def94c31830cf61fc149dbfe");
        ExchangePrizeRespDTO exchangePrizeRespDTO1 = orderService.exchangePrize(user,exchangePrizeReqDTO);
        System.out.println(exchangePrizeRespDTO1);
    }

    //@Test
    void getUserExchangeRecord() {
        MallUserEntity mallUserEntity = new MallUserEntity();
        mallUserEntity.setClientId("1688988");
        List<MyExchangeRecordRespDTO> userExchangeRecord = orderService.getUserExchangeRecord(mallUserEntity);
        System.out.println(userExchangeRecord);
    }

    //@Test
    void getUserCreditRecord(){
        MallUserEntity mallUserEntity = new MallUserEntity();
        mallUserEntity.setClientId("1688988");
        CreditRecordMap userCreditRecord = creditService.getUserCreditRecord(mallUserEntity);
        System.out.println(userCreditRecord);
    }

    //@Test
    void getHotDirectRecharge(){
        MallUserEntity user = mallUserRepository.findByClientId("1688988");
        List<DirectRechargeRespDTO> hotDirectRecharge = productService.getHotDirectRecharge(user);
        System.out.println(hotDirectRecharge);
    }

}