package com.rzx.project.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rzx.common.constant.Constants;
import com.rzx.common.core.controller.BaseController;
import com.rzx.common.utils.BigDecimalUtils;
import com.rzx.common.utils.provid.yunzhonghe.YunZhongHeUtils;
import com.rzx.project.domain.CommodityConfig;
import com.rzx.project.domain.OrderInfo;
import com.rzx.project.service.ICommodityConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public JSONObject createOrder(JSONObject order, JSONObject address) {
        JSONObject resp = new JSONObject();
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
            logger.error("SuppliereYzhImpl_createOrder_e={}",e);
            resp.put("RC", Constants.FAILURE_CODE);
            resp.put("RM", "调用云中鹤生成订单异常，请稍后再试！");
        }
        return resp;
    }

    @Override
    public JSONObject getAddress(String type,String code) {
        JSONObject resp = new JSONObject();
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
            logger.error("SuppliereYzhImpl_getAddress_e={}",e);
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
    public JSONObject orderTrack(OrderInfo order) {
        JSONObject data = new JSONObject();
        JSONObject respJson = YunZhongHeUtils.orderTrack(order.getOrderId());
        if (!ObjectUtils.isEmpty(respJson) && respJson.getBoolean("RESPONSE_STATUS")) {
            // 请求成功
            data = respJson.getJSONObject("RESULT_DATA");
        }
        return data;
    }

    @Override
    public CommodityConfig detial(CommodityConfig commodity) {
        try {
            JSONObject infoResp = YunZhongHeUtils.detial(commodity.getCommodityCode());
            commodity.setUpdateTime(LocalDateTime.now());
            if(!ObjectUtils.isEmpty(infoResp) && !ObjectUtils.isEmpty(infoResp.getJSONObject("RESULT_DATA"))){
                JSONObject iResultData = infoResp.getJSONObject("RESULT_DATA");
                dowith(commodity, iResultData);
                commodityconfigService.updateCommodityConfig(commodity);
            }else{
                commodity.setSaleStatus(Constants.NO_FLAG);
                commodityconfigService.updateCommodityConfig(commodity);
            }
        } catch (Exception e) {
            logger.error("SuppliereYzhImpl_detial_e={}",e);
        }
        return commodity;
    }

    @Override
    public JSONObject cancelOrder(OrderInfo order) {
        JSONObject resp = new JSONObject();
        try {
            JSONObject respJson = YunZhongHeUtils.cancelByOrderKey((JSONObject) JSONObject.toJSON(order));
            if(!ObjectUtils.isEmpty(respJson)){
                if(respJson.getBoolean("RESPONSE_STATUS")){
                    JSONObject data = respJson.getJSONObject("RESULT_DATA");
                    // 成功
                    resp.put("RC", Constants.SUCCESS_CODE);
                }else{
                    // 失败
                    resp.put("RC", Constants.FAILURE_CODE);
                    resp.put("RM", respJson.getString("ERROR_MESSAGE"));
                }
            }else{
                // 失败
                resp.put("RC", Constants.FAILURE_CODE);
                resp.put("RM", "调用云中鹤取消订单失败");
            }
        } catch (Exception e) {
            logger.error("SuppliereYzhImpl_cancelOrder_e={}",e);
        }
        return resp;
    }

    @Override
    public List<JSONObject> stockBatch(List<Object> list, String province, String city, String county, String town) {
        List<JSONObject> resp = new ArrayList<>();
        try {
            String pidNums = "";
            for (int i = 0; i < list.size(); i++) {
                JSONObject data = (JSONObject) list.get(i);
                if(StringUtils.isEmpty(pidNums)){
                    pidNums = data.getString("itemId")+"_1";
                }else{
                    pidNums = "," + data.getString("itemId")+"_1";
                }
            }
            String address = province + "_" + city + "_" + county;
            JSONObject respJson = YunZhongHeUtils.stockBatch(pidNums,address);
            if(!ObjectUtils.isEmpty(respJson) && respJson.getBoolean("RESPONSE_STATUS") &&
                    !ObjectUtils.isEmpty(respJson.getJSONArray("RESULT_DATA"))){
                JSONArray respList = respJson.getJSONArray("RESULT_DATA");
                for (int i = 0; i < respList.size(); i++) {
                    JSONObject data = (JSONObject) respList.get(i);
                    data.put("provid", "1");
                    data.put("itemId", data.getString("product_id"));
                    if(data.getBoolean("stock_status")){
                        data.put("state","1");
                    }else{
                        data.put("state","0");
                    }
                    resp.add(data);
                }
            }else{
                // 失败
                logger.error("SuppliereYzhImpl_stockBatch_查询云中鹤获取区域库存失败_respJson=" + respJson);
                for (int i = 0; i < list.size(); i++) {
                    JSONObject data = (JSONObject) list.get(i);
                    data.put("provid", "1");
                    data.put("state","0");
                    resp.add(data);
                }
            }
        } catch (Exception e) {
            logger.error("SuppliereYzhImpl_stockBatch_e={}",e);
        }
        return resp;
    }

    /**
     * 参数处理
     * @param pd
     * @param iResultData
     */
    public void dowith(CommodityConfig pd, JSONObject iResultData) {
        if(!ObjectUtils.isEmpty(iResultData.getJSONObject("PRODUCT_DATA"))){
            pd.setCommodityName(iResultData.getJSONObject("PRODUCT_DATA").getString("name"));
            pd.setBrand(iResultData.getJSONObject("PRODUCT_DATA").getString("brand"));
            pd.setProductCate(iResultData.getJSONObject("PRODUCT_DATA").getString("productCate"));
            pd.setModel(iResultData.getJSONObject("PRODUCT_DATA").getString("productCode"));
            pd.setThumbnailImage(iResultData.getJSONObject("PRODUCT_DATA").getString("thumbnailImage"));
            if("selling".equals(iResultData.getJSONObject("PRODUCT_DATA").getString("status"))){
                pd.setSaleStatus("1");
            }else{
                pd.setSaleStatus("0");
            }
            String PROVID_SOURCE = iResultData.getJSONObject("PRODUCT_DATA").getString("type");
            if ("system".equals(PROVID_SOURCE)) {
                PROVID_SOURCE = "1";
            } else if ("provider".equals(PROVID_SOURCE)) {
                PROVID_SOURCE = "21";
            } else if ("jindong".equals(PROVID_SOURCE) || "xinfutong".equals(PROVID_SOURCE)) {
                PROVID_SOURCE = "4";
            } else if ("wangyi".equals(PROVID_SOURCE)) {
                PROVID_SOURCE = "5";
            }
            pd.setProvidSource(PROVID_SOURCE);
            if("undercarriage".equals(iResultData.getJSONObject("PRODUCT_DATA").getString("status"))){
                // 下架商品挪出礼包
                pd.setGiftpackageId("");
            }
            pd.setMarketPrice(iResultData.getJSONObject("PRODUCT_DATA").getString("marketPrice"));
            pd.setRetailAmount(iResultData.getJSONObject("PRODUCT_DATA").getString("retailPrice"));
            String amount = BigDecimalUtils.multiply(iResultData.getJSONObject("PRODUCT_DATA").getString("retailPrice"),"1.1",2);
            pd.setAmount(amount);
            pd.setProductPlace(iResultData.getJSONObject("PRODUCT_DATA").getString("productPlace"));
            pd.setIsToReturn(iResultData.getJSONObject("PRODUCT_DATA").getString("is7ToReturn"));
//            pd.put("EXPLAIN", iResultData.getJSONObject("PRODUCT_DATA").getString("features"));
        }
        if(!org.springframework.util.StringUtils.isEmpty(iResultData.getString("PRODUCT_DESCRIPTION"))){
            pd.setImage(iResultData.getString("PRODUCT_DESCRIPTION").replace("\"",""));
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
            pd.setImageUrls(IMAGE_URLS);
        }
    }
}
