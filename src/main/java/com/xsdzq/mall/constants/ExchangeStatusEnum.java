package com.xsdzq.mall.constants;

public enum ExchangeStatusEnum {
    FAILURE(0, "失败"),
    SUCCESS(1, "成功"),
    PROCESSING(2, "处理中"),

    ;

    private final Integer code;
    private final String desc;

    ExchangeStatusEnum(Integer code, String desc) {
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
