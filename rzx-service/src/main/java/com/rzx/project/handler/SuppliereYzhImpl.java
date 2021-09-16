package com.rzx.project.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rzx.common.constant.Constants;
import com.rzx.common.core.controller.BaseController;
import com.rzx.common.utils.PageData;
import com.rzx.common.utils.Tools;
import com.rzx.common.utils.provid.yunzhonghe.YunZhongHeUtils;
import com.rzx.project.service.ICommodityConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;

/**
 * @author zhasbao
 * @version 1.0
 * @description 云中鹤业务处理实现类
 * @company 深圳分米互联科技有限公司
 * @date 2021/9/6 19:04
 */
@Service("supplierYzhImpl")
public class SuppliereYzhImpl extends BaseController implements SuppliereInterface {

    @Autowired
    private ICommodityConfigService commodityconfigService;

    @Override
    public PageData createOrder(PageData order, PageData address) {
        PageData resp = new PageData();
        try{
            JSONObject respJson = YunZhongHeUtils.createOrder(order, address);
            if(!ObjectUtils.isEmpty(respJson)){
                if(respJson.getBoolean("RESPONSE_STATUS")){
                    JSONObject data = respJson.getJSONObject("RESULT_DATA");
                    // 成功
                    resp.put("RC", Constants.SUCCESS_CODE);
                    resp.put("ORDER_KEY", data.getString("order_key"));
                    resp.put("ORDER_SHIPMENT_FEE", data.getDoubleValue("order_shipment_fee"));
                }else{
                    // 失败
                    resp.put("RC", respJson.getString("ERROR_CODE"));
                    resp.put("RM", respJson.getString("ERROR_MESSAGE"));
                }
            }else{
                resp.put("RC", Constants.FAILURE_CODE);
                resp.put("RM", "调用云中鹤生成订单失败，请稍后再试！");
            }
        }catch (Exception e){
            logger.error(e.toString(), e);
            resp.put("RC", Constants.FAILURE_CODE);
            resp.put("RM", "调用云中鹤生成订单异常，请稍后再试！");
        }
        return resp;
    }

    @Override
    public PageData getAddress(String type,String code) {
        PageData resp = new PageData();
        JSONArray rows = new JSONArray();
        JSONObject respJson = new JSONObject();
        try{
            if ("1".equals(type)) {
                respJson = YunZhongHeUtils.getProvince();
            } else if ("2".equals(type)) {
                respJson = YunZhongHeUtils.getCity(code);
            } else if ("3".equals(type)) {
                respJson = YunZhongHeUtils.getCounty(code);
            } else if ("4".equals(type)) {
                respJson = YunZhongHeUtils.getTown(code);
            } else {
                resp.put("RC", Constants.FAILURE_CODE);
                resp.put("RM", respJson.getString("type类型错误"));
                return resp;
            }
            if (!ObjectUtils.isEmpty(respJson) && respJson.getBoolean("RESPONSE_STATUS")) {
                // 请求成功
                resp.put("RC", Constants.SUCCESS_CODE);
                rows = respJson.getJSONArray("RESULT_DATA");
                resp.put("rows",rows);
            }else{
                resp.put("RC", Constants.FAILURE_CODE);
                resp.put("RM", "获取供应商(云中鹤)地址失败");
            }
        }catch (Exception e){
            logger.error(e.toString(), e);
            resp.put("RC", Constants.FAILURE_CODE);
            resp.put("RM", "获取供应商(云中鹤)地址异常");
        }
        return resp;
    }

    @Override
    public boolean getValid(String provinceId,String cityId,String countyId,String townId) {
        boolean resp = false;
        JSONObject respCheckJson = YunZhongHeUtils.checkArea(provinceId, cityId, StringUtils.isEmpty(countyId) ? "0" : countyId,
                StringUtils.isEmpty(townId) ? "0" : townId);
        if(!ObjectUtils.isEmpty(respCheckJson) && !ObjectUtils.isEmpty(respCheckJson.getJSONObject("RESULT_DATA"))
                && respCheckJson.getJSONObject("RESULT_DATA").getBoolean("success")){
            resp = true;
        }
        return resp;
    }

    @Override
    public boolean saleStatus(String commodityCode) {
        boolean resp = false;
        JSONObject respCanJson = YunZhongHeUtils.getSaleStatus(commodityCode);
        if ((!ObjectUtils.isEmpty(respCanJson) && !ObjectUtils.isEmpty(respCanJson.getJSONObject("RESULT_DATA"))
                && respCanJson.getJSONObject("RESULT_DATA").getBoolean("status"))) {
            resp = true;
        }
        return resp;
    }

    @Override
    public JSONObject orderTrack(String orderId) {
        JSONObject data = new JSONObject();
        JSONObject respJson = YunZhongHeUtils.orderTrack(orderId);
        if (!ObjectUtils.isEmpty(respJson) && respJson.getBoolean("RESPONSE_STATUS")) {
            // 请求成功
            data = respJson.getJSONObject("RESULT_DATA");
        }
        return data;
    }

    @Override
    public void detial(PageData commodity) {
        try {
            JSONObject infoResp = YunZhongHeUtils.detial(commodity.getString("COMMODITY_CODE"));
            if(!ObjectUtils.isEmpty(infoResp) && !ObjectUtils.isEmpty(infoResp.getJSONObject("RESULT_DATA"))){
                JSONObject iResultData = infoResp.getJSONObject("RESULT_DATA");
                commodity.put("UPDATE_TIME", Tools.date2Str(new Date()));
                dowith(commodity, iResultData);
//                commodityconfigService.edit(commodity);
            }
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
    }

    /**
     * 参数处理
     * @param pd
     * @param iResultData
     */
    public void dowith(PageData pd, JSONObject iResultData) {
        if(!ObjectUtils.isEmpty(iResultData.getJSONObject("PRODUCT_DATA"))){
            pd.put("COMMODITY_NAME", iResultData.getJSONObject("PRODUCT_DATA").getString("name"));
            pd.put("BRAND", iResultData.getJSONObject("PRODUCT_DATA").getString("brand"));
            pd.put("PRODUCT_CATE", iResultData.getJSONObject("PRODUCT_DATA").getString("productCate"));
            pd.put("MODEL", iResultData.getJSONObject("PRODUCT_DATA").getString("productCode"));
            pd.put("THUMBNAIL_IMAGE", iResultData.getJSONObject("PRODUCT_DATA").getString("thumbnailImage"));
            pd.put("SALE_STATUS", iResultData.getJSONObject("PRODUCT_DATA").getString("status"));
            if("undercarriage".equals(iResultData.getJSONObject("PRODUCT_DATA").getString("status"))){
                // 下架商品挪出礼包
                pd.put("GIFTPACKAGE_ID", "");
            }
            pd.put("MARKET_PRICE", iResultData.getJSONObject("PRODUCT_DATA").getString("marketPrice"));
            pd.put("RETAIL_AMOUNT", iResultData.getJSONObject("PRODUCT_DATA").getString("retailPrice"));
            pd.put("PRODUCT_PLACE", iResultData.getJSONObject("PRODUCT_DATA").getString("productPlace"));
            pd.put("IS_TO_RETURN", iResultData.getJSONObject("PRODUCT_DATA").getString("is7ToReturn"));
//            pd.put("EXPLAIN", iResultData.getJSONObject("PRODUCT_DATA").getString("features"));
        }
        if(!org.springframework.util.StringUtils.isEmpty(iResultData.getString("PRODUCT_DESCRIPTION"))){
            pd.put("IMAGE", iResultData.getString("PRODUCT_DESCRIPTION").replace("\"",""));
        }
        if(!ObjectUtils.isEmpty(iResultData.getJSONArray("PRODUCT_IMAGE"))){
            JSONArray PRODUCT_IMAGE = iResultData.getJSONArray("PRODUCT_IMAGE");
            String IMAGE_URLS = "";
            JSONObject pi = null;
            for (int j = 0; j < PRODUCT_IMAGE.size(); j++) {
                pi = (JSONObject) PRODUCT_IMAGE.get(j);
                if(org.springframework.util.StringUtils.isEmpty(IMAGE_URLS)){
                    IMAGE_URLS = pi.getString("imageUrl");
                }else{
                    IMAGE_URLS = IMAGE_URLS + "," + pi.getString("imageUrl");
                }
            }
            pd.put("IMAGE_URLS", IMAGE_URLS);
        }
    }
}
