package com.xsdzq.mall.constants;

public enum OrderStatusEnum {
    INIT(0, "初始状态"),
    PROCESSING(1, "处理中"),
    SUCCESS(2, "成功"),
    FAILURE(3, "失败"),
    NULL(-1, "null"),

    ;

    private final Integer code;
    private final String desc;

    OrderStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static OrderStatusEnum match(Integer orderStatus) {
        for (OrderStatusEnum os : OrderStatusEnum.values()) {
            if (os.getCode().equals(orderStatus)) {
                return os;
            }
        }
        return NULL;
    }
}
