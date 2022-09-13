package com.xsdzq.mall.constants;

public enum ExchangeTypeEnum {
    EXCHANGE(0, "兑换"),
    RECHARGE(1, "充值"),

    ;

    private final Integer code;
    private final String desc;

    ExchangeTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
