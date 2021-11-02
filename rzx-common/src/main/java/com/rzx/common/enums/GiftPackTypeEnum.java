package com.rzx.common.enums;

/**
 * 礼包类型枚举 0-单品 1-多选一 2-多选二
 *
 * @author zhasbao
 */
public enum GiftPackTypeEnum {

    SINGLE("0","单品"),
    MORE_TO_ONE("1","多选一"),
    MORE_TO_THREE("2","多选二"),
    ;

    private final String code;

    private final String info;

    GiftPackTypeEnum(String code, String info) {
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
