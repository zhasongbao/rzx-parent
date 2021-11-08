package com.rzx.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付状态枚举类
 *
 * @author zy
 * @date 2021/9/12 14:16
 */
public enum PayLinkNetWorkStatusEnum {

    /**
     * 交易成功
     */
    SUCCESS("交易成功"),

    /**
     * 支付进行中
     */
    PAYING("支付进行中"),

    /**
     * 交易失败
     */
    FAIL("交易失败"),

    /**
     * 未支付
     */
    UN_PAY("未支付"),
    ;

    /**
     * 描述
     */
    private final String desc;

    private PayLinkNetWorkStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public static Map<String, Map<String, Object>> toMap() {
        PayLinkNetWorkStatusEnum[] ary = PayLinkNetWorkStatusEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
        for (PayLinkNetWorkStatusEnum payLinkNetWorkStatusEnum : ary) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = payLinkNetWorkStatusEnum.name();
            map.put("desc", payLinkNetWorkStatusEnum.getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

}
