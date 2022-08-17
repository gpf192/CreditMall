package com.xsdzq.mall.service.client.chengquan;

import java.math.BigDecimal;

public class PayTelReqEntity extends AbstractHeadReqEntity {
    private String order_no;
    private String recharge_number;
    private BigDecimal price;

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getRecharge_number() {
        return recharge_number;
    }

    public void setRecharge_number(String recharge_number) {
        this.recharge_number = recharge_number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
