package com.rzx.common.enums;

/**
 * 支付方式 (0-云卓支付)
 *
 * @author zhasbao
 */
public enum PayWayEnum {

    YZ("0","云卓支付"),
    ;

    private final String code;

    private final String info;

    PayWayEnum(String code, String info) {
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
