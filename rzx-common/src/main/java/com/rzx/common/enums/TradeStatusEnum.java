package com.rzx.common.enums;

import java.math.BigDecimal;

/**
 * <b>功能说明:订单状态枚举类</b>
 *
 * @author zy
 * @date 2021/05/28
 */
public enum TradeStatusEnum {

    /**
     * 交易成功
     */
    SUCCESS("交易成功"),

    /**
     * 失败
     */
    FAIL("失败"),

    /**
     * 订单已取消
     */
    CANCELED("订单已取消"),

    /**
     * 等待支付
     */
    WAITING("等待支付"),

    /**
     * 未知结果
     */
    UNKNOWN("未知结果");

    /**
     * 描述
     */
    private String desc;

    private TradeStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
