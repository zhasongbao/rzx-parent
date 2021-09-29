package com.rzx.common.enums;

/**
 * 供应商枚举
 *
 * @author zhasbao
 */
public enum ProvidEnum {

    /**
     * 云中鹤
     */
    YZH("1" , "云中鹤"),

    /**
     * 百礼汇
     */
    BLH("2" , "百礼汇"),
    ;


    private final String code;

    private final String info;

    ProvidEnum(String code, String info) {
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
