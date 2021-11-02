package com.rzx.common.enums;

/**
 * 订单状态枚举
 *
 * @author zhasbao
 */
public enum SalesOrderStatusEnum {

    NO_PAY("0","未支付"),
    YES_PAY("1","已支付"),
    FAIL_PAY("2","支付失败"),
    PAY_ING("3","支付中"),
    CANCEL("4","订单取消"),
    FAIL_CREATE("9","订单创建失败"),
    ;


    private final String code;

    private final String info;

    SalesOrderStatusEnum(String code, String info) {
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
