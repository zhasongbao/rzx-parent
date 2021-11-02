package com.rzx.common.enums;

/**
 * 核销状态枚举 0-未核销 1-已核销
 *
 * @author zhasbao
 */
public enum CheckStatusEnum {

    NO_CHECK("0","未核销"),
    YES_CHECK("1","已核销"),
    ;

    private final String code;

    private final String info;

    CheckStatusEnum(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
