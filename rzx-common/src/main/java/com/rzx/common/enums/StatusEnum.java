package com.rzx.common.enums;

/**
 * 状态枚举
 *
 * @author zhasbao
 */
public enum StatusEnum {

    /**
     * 有效
     */
    VALID("1" , "有效"),

    /**
     * 无效
     */
    INVALID("0" , "无效"),
    ;


    private final String code;

    private final String info;

    StatusEnum(String code, String info) {
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
