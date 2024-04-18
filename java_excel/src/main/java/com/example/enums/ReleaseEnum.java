package com.example.enums;

/**
 * @author: cp
 * @date: 2023-09-25 13:36
 */
public enum ReleaseEnum {

    RELEASE_NORMAL(10, "正常放币"),
    RELEASE_AHEAD(20, "提前放币"),
    RELEASE_TRD(100, "其他"),
    ;

    private Integer value;
    private String name;

    ReleaseEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static ReleaseEnum parseOf(Integer value) {
        for (ReleaseEnum statusEnum : ReleaseEnum.values()) {
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
