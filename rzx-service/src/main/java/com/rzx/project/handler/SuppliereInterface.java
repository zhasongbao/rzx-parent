package com.rzx.project.handler;


import com.alibaba.fastjson.JSONObject;
import com.rzx.common.utils.PageData;

/**
 * @author zhasbao
 * @version 1.0
 * @description 供应商交互接口
 * @company 深圳分米互联科技有限公司
 * @date 2021/8/20 10:38
 */
public interface SuppliereInterface {

    /**
     * 创建订单
     * @param order 订单信息
     * @param address 地址信息
     * @return
     */
    PageData createOrder(PageData order, PageData address);

    /**
     * 获取地址列表
     * @param type
     * @return
     */
    PageData getAddress(String type,String code);

    /**
     * 校验地址有效性
     * @param provinceId
     * @param cityId
     * @param countyId
     * @param townId
     * @return ture 有效
     */
    boolean getValid(String provinceId,String cityId,String countyId,String townId);

    /**
     * 查询商品可售状态
     * @param commodityCode
     * @return ture 可售
     */
    boolean saleStatus(String commodityCode);

    /**
     * 查询物流信息
     * @param orderId 任意行订单号
     * @return
     */
    JSONObject orderTrack(String orderId);

    /**
     * 同步供应商商品信息
     * @param commodityCode
     * @return
     */
    void detial(PageData commodityCode);

}
