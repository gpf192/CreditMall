package com.xsdzq.mall.constants;

public enum ChengQuanOrderStatusEnum {
    RECHARGE("RECHARGE", "充值中"),
    SUCCESS("SUCCESS", "成功"),
    FAILURE("FAILURE", "失败"),
    NULL("-1", "null"),

    ;

    private final String code;
    private final String desc;

    ChengQuanOrderStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ChengQuanOrderStatusEnum match(String orderStatus) {
        for (ChengQuanOrderStatusEnum os : ChengQuanOrderStatusEnum.values()) {
            if (os.getCode().equals(orderStatus)) {
                return os;
            }
        }
        return NULL;
    }

}