package com.rzx.common.enums;

/**
 * 商品状态枚举 (1上架销售中, 0下架)
 *
 * @author zhasbao
 */
public enum CommodityProvidEnum {

    YUNZHONGHE("1","云中鹤", "supplierYzhImpl"),
    BAIHUI("2","百礼汇", "supplierBhImpl"),
    ;

    private final String code;
    private final String info;
    private final String implClass;

    CommodityProvidEnum(String code, String info, String implClass) {
        this.code = code;
        this.info = info;
        this.implClass = implClass;
    }

    public String getCode() {
        return code;
    }
    public String getInfo() {
        return info;
    }
    public String getImplClass() {
        return implClass;
    }

    /**
     * 根据 code 获取 implClass
     * @param value
     * @return
     */
    public static String getImplClassByValue(String value) {
        for (CommodityProvidEnum e : CommodityProvidEnum.values()) {
            if (e.code.equals(value)) {
                return e.implClass;
            }
        }
        return null;
    }
}
