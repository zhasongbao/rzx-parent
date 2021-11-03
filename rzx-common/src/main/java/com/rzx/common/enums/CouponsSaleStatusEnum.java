package com.rzx.common.enums;

/**
 * 销售状态枚举 (0-未销售 1-已销售)
 *
 * @author zhasbao
 */
public enum CouponsSaleStatusEnum {

    NO_SALE("0","未销售"),
    YES_SALE("1","已销售"),
    ;

    private final String code;

    private final String info;

    CouponsSaleStatusEnum(String code, String info) {
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
