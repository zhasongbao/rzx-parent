package com.rzx.common.enums;

/**
 * 支付方式 (0-云卓支付)
 *
 * @author zhasbao
 */
public enum PayWayEnum {

    YUN_ZHUO_PAY("0","云卓支付", "YUN_ZHUO_PAY"),
    ;

    private final String code;

    private final String info;
    /**
     * 支付渠道编码
     */
    private final String payChannelCode;

    PayWayEnum(String code, String info, String payChannelCode) {
        this.code = code;
        this.info = info;
        this.payChannelCode = payChannelCode;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public String getPayChannelCode() {
        return payChannelCode;
    }

    /**
     * 通过code获取枚举
     *
     * @param code 编码
     * @return
     */
    public static PayWayEnum getPayWayEnumByCode(String code) {
        for (PayWayEnum payWayEnum : PayWayEnum.values()) {
            if (code.equals(payWayEnum.getCode())) {
                return payWayEnum;
            }
        }
        return null;
    }

    /**
     * 通过name获取枚举
     *
     * @param name 编码
     * @return
     */
    public static PayWayEnum getPayWayByName(String name) {
        for (PayWayEnum payWayEnum : PayWayEnum.values()) {
            if (name.equals(payWayEnum.name())) {
                return payWayEnum;
            }
        }
        return null;
    }

    /**
     * 通过支付渠道编码获取枚举
     *
     * @param payChannelCode 编码
     * @return
     */
    public static PayWayEnum getPayWayEnumByPayChannelCode(String payChannelCode) {
        for (PayWayEnum payWayEnum : PayWayEnum.values()) {
            if (payChannelCode.equals(payWayEnum.getPayChannelCode())) {
                return payWayEnum;
            }
        }
        return null;
    }

}
