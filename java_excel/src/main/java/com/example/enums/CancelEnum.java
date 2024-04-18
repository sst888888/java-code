package com.example.enums;

/**
 * @author: cp
 * @date: 2023-09-24 22:38
 */
public enum CancelEnum {

    PAY_TIMEOUT(10, "超时付款，我未付款"),
    ABNORMAL(20, "异常订单，取消交易"),
    OTHER_REASONS(100, "其他原因");

    private Integer value;
    private String name;

    CancelEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static CancelEnum parseOf(Integer value) {
        for (CancelEnum statusEnum : CancelEnum.values()) {
            if (statusEnum.getValue().equals(value)) {
                return statusEnum;
            }
        }
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
