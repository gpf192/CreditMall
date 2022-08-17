package com.xsdzq.mall.constants;

public enum ProductStatusEnum {
    ON_SHELF(0, "上架"),
    OFF_SHELF(1, "下架"),

    ;

    private final Integer code;
    private final String desc;

    ProductStatusEnum(Integer code, String desc) {
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
