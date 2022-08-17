package com.xsdzq.mall.service.client.chengquan;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;

@SpringBootTest
class DirectChargeServiceTest {
    @Resource
    private DirectChargeService directChargeService;

    //@Test
    void payTel() {
        PayTelReqEntity req = new PayTelReqEntity();
        req.setOrder_no("111111111");
        req.setRecharge_number("15010658509");
        req.setPrice(new BigDecimal("10000"));
        req.setTimestamp(System.currentTimeMillis());
        directChargeService.payTel(req);
    }

    //@Test
    void getOrder() {
        GetOrderReqEntity req = new GetOrderReqEntity();
        req.setOrder_no("111111111");
        directChargeService.getOrder(req);
    }
}