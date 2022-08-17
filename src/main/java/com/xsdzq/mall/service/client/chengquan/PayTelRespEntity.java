package com.xsdzq.mall.service.client.chengquan;

import java.math.BigDecimal;

public class PayTelRespEntity {
    private String app_id;
    private String order_no;
    private String recharge_number;
    private String start_time;
    private String end_time;
    private String state;
    private BigDecimal consume_amount;

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

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

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getConsume_amount() {
        return consume_amount;
    }

    public void setConsume_amount(BigDecimal consume_amount) {
        this.consume_amount = consume_amount;
    }
}