package com.rzx.common.enums;

/**
 * 订单类型枚举 1-礼包 2-商品订单
 *
 * @author zhasbao
 */
public enum OrderTypeEnum {

    GIFT_PACK("1","礼包订单"),
    COMMODITY("2","商品订单"),
    ;

    private final String code;

    private final String info;

    OrderTypeEnum(String code, String info) {
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
