package com.rzx.project.handler;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rzx.common.constant.Constants;
import com.rzx.common.core.controller.BaseController;
import com.rzx.common.utils.BigDecimalUtils;
import com.rzx.common.utils.Tools;
import com.rzx.common.utils.provid.baihui.BaiHuiUtils;
import com.rzx.project.domain.CommodityConfig;
import com.rzx.project.domain.OrderInfo;
import com.rzx.project.service.ICommodityConfigService;
import com.rzx.project.service.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhasbao
 * @version 1.0
 * @description 百礼汇业务处理实现类
 * @company 深圳分米互联科技有限公司
 * @date 2021/9/6 19:04
 */
@Service("supplierBhImpl")
public class SuppliereBhImpl extends BaseController implements SuppliereInterface {

    @Autowired
    private ICommodityConfigService commodityConfigService;

    @Autowired
    private ICommonService commonService;

    @Override
    public JSONObject createOrder(JSONObject order,JSONObject address) {
        JSONObject resp = new JSONObject();
        String token = commonService.getBaihuiToken();
        if(StringUtils.isEmpty(token)){
            resp.put("RC", Constants.FAILURE_CODE);
            resp.put("RM", "获取token失败");
            return resp;
        }
        JSONObject respJson = BaiHuiUtils.createOrder(token,order,address);
        if (!ObjectUtil.isEmpty(respJson) && respJson.getBoolean("result") && !ObjectUtil.isEmpty(respJson.getJSONObject("data"))) {
            JSONObject data = respJson.getJSONObject("data");
            // 成功
            resp.put("RC", Constants.SUCCESS_CODE);
            resp.put("ORDER_KEY", data.getString("order_sn"));
        }else{
            // 失败
            resp.put("RC", Constants.FAILURE_CODE);
            resp.put("RM", "调用百礼汇生成订单失败，请稍后再试！");
        }
        return resp;
    }

    @Override
    public JSONObject getAddress(String type,String code) {
        JSONObject resp = new JSONObject();
        String token = commonService.getBaihuiToken();
        if(StringUtils.isEmpty(token)){
            resp.put("RC", Constants.FAILURE_CODE);
            resp.put("RM", "获取token失败");
            return resp;
        }
        JSONObject respJson = new JSONObject();
        JSONArray rows = new JSONArray();
        try{
            if ("1".equals(type)) {
                respJson = BaiHuiUtils.getProvince(token);
            } else if ("2".equals(type)) {
                respJson = BaiHuiUtils.getCity(token, code);
            } else if ("3".equals(type)) {
                respJson = BaiHuiUtils.getCounty(token, code);
            } else if ("4".equals(type)) {
                respJson = BaiHuiUtils.getTown(token, code);
            } else {
                resp.put("RC", Constants.FAILURE_CODE);
                resp.put("RM", "type类型错误");
                return resp;
            }
            if (!ObjectUtils.isEmpty(respJson) && respJson.getBoolean("result") && !ObjectUtils.isEmpty(respJson.getJSONObject("data"))
                    && !ObjectUtils.isEmpty(respJson.getJSONObject("data").getJSONArray("list"))) {
                for (int i = 0; i < respJson.getJSONObject("data").getJSONArray("list").size(); i++) {
                    JSONObject o = (JSONObject) respJson.getJSONObject("data").getJSONArray("list").get(i);
                    o.put("code",o.getString("id"));
                    rows.add(o);
                }
                resp.put("RC", Constants.SUCCESS_CODE);
                resp.put("rows",rows);
            }else{
                resp.put("RC", Constants.FAILURE_CODE);
                resp.put("RM", "获取供应商(百汇)地址失败");
            }
        }catch (Exception e){
            logger.error(e.toString(), e);
            resp.put("RC", Constants.FAILURE_CODE);
            resp.put("RM", "获取供应商(百汇)地址异常");
        }
        return resp;
    }

    @Override
    public boolean getValid(String provinceId,String cityId,String countyId,String townId) {
        String token = commonService.getBaihuiToken();
        if(StringUtils.isEmpty(token)){
            return false;
        }
        JSONObject resp = BaiHuiUtils.getBhValid(token,provinceId,cityId,countyId,townId);
        if (!ObjectUtils.isEmpty(resp) && resp.getBoolean("result") && !ObjectUtils.isEmpty(resp.getJSONObject("data")) &&
                resp.getJSONObject("data").getBoolean("res")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean saleStatus(String commodityCode) {
        return true;
    }

    @Override
    public JSONObject orderTrack(OrderInfo order) {
        JSONObject data = new JSONObject();
        String token = commonService.getBaihuiToken();
        if(StringUtils.isEmpty(token)){
            return data;
        }
//        JSONObject resp = BaiHuiUtils.getOrderShipment(token, order.getString("ORDER_KEY"));
//        if (!ObjectUtils.isEmpty(resp) && resp.getBoolean("result") && !ObjectUtils.isEmpty(resp.getJSONObject("data")) &&
//                !ObjectUtil.isEmpty(resp.getJSONObject("data").getJSONArray("express"))) {
//            JSONArray express = resp.getJSONObject("data").getJSONArray("express");
//            JSONObject json = (JSONObject) express.get(0);
//            data.put("third_order", order.getString("ORDER_ID"));
//            data.put("shipment_name", json.getString("express_name"));
//            data.put("shipment_order", json.getString("express_code"));
//            String status = json.getString("express_status");
//            if("0".equals(status)){
//                status = "receive";
//            }else if("3".equals(status)){
//                status = "signed";
//            }
//            data.put("status", status);
//            List<JSONObject> contents = new ArrayList<>();
//            JSONArray express_track = json.getJSONArray("express_track");
//            for (Object o : express_track) {
//                JSONObject da = (JSONObject) o;
//                JSONObject track = new JSONObject();
//                track.put("time", da.getString("time"));
//                track.put("description", da.getString("status"));
//                contents.add(track);
//            }
//            data.put("contents", contents);
//        }
        if(!StringUtils.isEmpty(order.getExpressCode())){
            JSONObject resp = BaiHuiUtils.getOrderExpress(token, order.getOrderKey(), order.getExpressCode());
            if (!ObjectUtils.isEmpty(resp) && resp.getBoolean("result") && !ObjectUtils.isEmpty(resp.getJSONObject("data"))) {
                data.put("third_order", order.getOrderId());
                data.put("shipment_name", resp.getJSONObject("data").getString("expName"));
                data.put("shipment_order", resp.getJSONObject("data").getString("expCode"));
                String status = resp.getJSONObject("data").getString("expStatus");
                if("0".equals(status)){
                    status = "receive";
                }else if("3".equals(status)){
                    status = "signed";
                }
                data.put("status", status);
                List<JSONObject> contents = new ArrayList<>();
                JSONArray express_track = resp.getJSONObject("data").getJSONArray("expTrack");
                for (Object o : express_track) {
                    JSONObject da = (JSONObject) o;
                    JSONObject track = new JSONObject();
                    track.put("time", da.getString("time"));
                    track.put("description", da.getString("status"));
                    contents.add(track);
                }
                data.put("contents", contents);
            }
        }
        return data;
    }

    @Override
    public CommodityConfig detial(CommodityConfig commodity) {
        try {
            String token = commonService.getBaihuiToken();
            if(StringUtils.isEmpty(token)){
                return null;
            }
            JSONObject infoResp = BaiHuiUtils.getGoodInfo(token,commodity.getCommodityCode());
            if(!ObjectUtil.isEmpty(infoResp) && infoResp.getBoolean("result") && !ObjectUtil.isEmpty(infoResp.getJSONObject("data"))
                    && !ObjectUtil.isEmpty(infoResp.getJSONObject("data").get("product"))){
                JSONObject product = infoResp.getJSONObject("data").getJSONObject("product");
                commodity.setUpdateTime(LocalDateTime.now());
                dowith(commodity, product);
                commodityConfigService.updateCommodityConfig(commodity);
            }
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return commodity;
    }

    @Override
    public JSONObject cancelOrder(OrderInfo order) {
        JSONObject resp = new JSONObject();
        String token = commonService.getBaihuiToken();
        if(StringUtils.isEmpty(token)){
            resp.put("RC", Constants.FAILURE_CODE);
            resp.put("RM", "获取token失败");
            return resp;
        }
        JSONObject infoResp = BaiHuiUtils.cancelOrder(token,order.getOrderKey());
        if(!ObjectUtil.isEmpty(infoResp) && infoResp.getBoolean("result")){
            resp.put("RC", Constants.SUCCESS_CODE);
            resp.put("RM", "获取token失败");
            return resp;
        }else{
            resp.put("RC", Constants.FAILURE_CODE);
            resp.put("RM", "调用百礼汇取消订单失败！");
            return resp;
        }
    }

    @Override
    public List<JSONObject> stockBatch(List<Object> list, String province, String city, String county, String town) {
        List<JSONObject> respList = new ArrayList<>();
        String token = commonService.getBaihuiToken();
        if(StringUtils.isEmpty(token)){
            for (int i = 0; i < list.size(); i++) {
                JSONObject data = (JSONObject) list.get(i);
                data.put("provid","2");
                data.put("state","0");
                data.put("detail_msg","获取token失败");
                respList.add(data);
            }
            return respList;
        }
//        // 根据商品属性获取库存
//        List<JSONObject> jdList = new ArrayList<>();
//        List<JSONObject> notJdList = new ArrayList<>();
//        for (int i = 0; i < list.size(); i++) {
//            JSONObject data = (JSONObject) list.get(i);
//            if("3".equals(data.getString("typeId"))){
//                jdList.add(data);
//            }else{
//                notJdList.add(data);
//            }
//        }
        // 查询区域库存
        JSONObject json = BaiHuiUtils.getStoreAsNum(token, list, province, city, county, town);
        if(!ObjectUtil.isEmpty(json) && json.getBoolean("result") && !ObjectUtil.isEmpty(json.getJSONObject("data"))){
            for (Object o : json.getJSONObject("data").keySet()) {
                JSONObject detail = (JSONObject) json.getJSONObject("data").get(o);
                detail.put("provid","2");
                respList.add(detail);
            }
        }else{
            for (int i = 0; i < list.size(); i++) {
                JSONObject data = (JSONObject) list.get(i);
                data.put("provid","2");
                data.put("state","0");
                data.put("detail_msg","获取百汇区域库存失败");
                respList.add(data);
            }
        }
//        if(CollectionUtils.isEmpty(notJdList)){
//            // 查询商品区域库存
//            String itemIds = "";
//            for (int i = 0; i < notJdList.size(); i++) {
//                if(StringUtils.isEmpty(itemIds)){
//                    itemIds = notJdList.get(i).getString("itemId");
//                }else{
//                    itemIds = "," + notJdList.get(i).getString("itemId");
//                }
//            }
//            JSONObject json = BaiHuiUtils.getRegionStore(token,itemIds, province, city, county, town);
//            if(!ObjectUtil.isEmpty(json) && json.getBoolean("result") && !ObjectUtil.isEmpty(json.getJSONObject("data"))){
//                for (Object o : json.getJSONObject("data").keySet()) {
//                    JSONObject detail = (JSONObject) json.getJSONObject("data").get(o);
//                    detail.put("provid","2");
//                    respList.add(detail);
//                }
//            }
//        }
        return respList;
    }

    /**
     * 参数处理
     * @param pd
     * @param product
     */
    public void dowith(CommodityConfig pd, JSONObject product) {
        try {
            pd.setTypeId(product.getString("typeid"));
            pd.setCommodityName(StringUtils.isEmpty(product.getString("product_name")) ? "" : URLDecoder.decode(product.getString("product_name")));
            pd.setProvidSource(product.getString("product_group"));
            pd.setExplain(StringUtils.isEmpty(product.getString("sell_point")) ? "" : URLDecoder.decode(product.getString("sell_point")));
            pd.setProductCate(product.getString("category_id"));
            pd.setBrand(StringUtils.isEmpty(product.getString("brand")) ? "" : URLDecoder.decode(product.getString("brand")));
            pd.setMarketPrice(product.getString("market_price"));
            pd.setRetailAmount(product.getString("settlement"));
            String amount = BigDecimalUtils.multiply(product.getString("settlement"),"1.1",2);
            pd.setAmount(amount);
            pd.setSaleStatus(product.getString("state"));
            pd.setThumbnailImage(product.getString("product_images"));
            pd.setImageUrls(product.getString("images"));
            if("0".equals(product.getString("state"))){
                // 下架商品挪出礼包/商品库
                pd.setGiftpackageId("");
                pd.setChooseFlag(Constants.NO_FLAG);
            }
            pd.setImage(StringUtils.isEmpty(product.getString("app_introduce")) ? "" : URLDecoder.decode(product.getString("app_introduce")));
        } catch (Exception e) {
            logger.error("forCommodityBhTask_dowith_e=" + e);
        }
    }

}
