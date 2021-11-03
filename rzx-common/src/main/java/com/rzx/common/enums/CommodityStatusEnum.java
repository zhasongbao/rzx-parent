package com.rzx.common.enums;

/**
 * 商品状态枚举 (1上架销售中, 0下架)
 *
 * @author zhasbao
 */
public enum CommodityStatusEnum {

    SALE_END("0","下架"),
    SALE_ING("1","上架销售中"),
    ;

    private final String code;

    private final String info;

    CommodityStatusEnum(String code, String info) {
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
