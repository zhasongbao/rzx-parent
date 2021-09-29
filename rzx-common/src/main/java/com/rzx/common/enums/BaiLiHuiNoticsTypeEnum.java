package com.rzx.common.enums;

/**
 * 百礼汇消息通知类型
 * type 1-商品新增  2-商品基础信息更新 3-商品上架状态更新 4-商品市场价更新  5-商品结算价更新 10-实物订单出库 11-直充订单出库 12-卡券订单出库  99-订单关闭
 * @author zhasbao
 */
public enum BaiLiHuiNoticsTypeEnum {

    ADD("1","商品新增"),
    UPDATE_BASE("2","商品基础信息更新"),
    UPDATE_STATUS("3","商品上架状态更新"),
    UPDATE_SCJ("4","商品市场价更新"),
    UPDATE_JSJ("5","商品结算价更新"),
    ORDER_OUT_SW("10","实物订单出库"),
    ORDER_OUT_ZC("11","直充订单出库"),
    ORDER_OUT_KQ("12","卡券订单出库"),
    ORDER_CLOSE("99","订单关闭"),
    ;


    private final String code;

    private final String info;

    BaiLiHuiNoticsTypeEnum(String code, String info) {
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
