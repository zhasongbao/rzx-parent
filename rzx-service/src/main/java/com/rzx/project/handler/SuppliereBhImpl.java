package com.rzx.project.handler;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rzx.common.constant.Constants;
import com.rzx.common.core.controller.BaseController;
import com.rzx.common.utils.PageData;
import com.rzx.common.utils.Tools;
import com.rzx.common.utils.provid.baihui.BaiHuiUtils;
import com.rzx.project.service.ICommodityConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.util.Date;

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
    private ICommodityConfigService commodityconfigService;

    @Override
    public PageData createOrder(PageData order, PageData address) {
        PageData resp = new PageData();
        String token = BaiHuiUtils.getBaihuiToken();
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
    public PageData getAddress(String type,String code) {
        PageData resp = new PageData();
        String token = BaiHuiUtils.getBaihuiToken();
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
                JSONObject o;
                for (int i = 0; i < respJson.getJSONObject("data").getJSONArray("list").size(); i++) {
                    o = (JSONObject) respJson.getJSONObject("data").getJSONArray("list").get(i);
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
        String token = BaiHuiUtils.getBaihuiToken();
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
    public JSONObject orderTrack(String orderId) {
        String token = BaiHuiUtils.getBaihuiToken();
        return null;
    }

    @Override
    public void detial(PageData commodity) {
        try {
            String token = BaiHuiUtils.getBaihuiToken();
            if(StringUtils.isEmpty(token)){
                return;
            }
            JSONObject infoResp = BaiHuiUtils.getGoodInfo(token,commodity.getString("COMMODITY_CODE"));
            if(!ObjectUtil.isEmpty(infoResp) && infoResp.getBoolean("result") && !ObjectUtil.isEmpty(infoResp.getJSONObject("data"))
                    && !ObjectUtil.isEmpty(infoResp.getJSONObject("data").get("product"))){
                JSONObject product = infoResp.getJSONObject("data").getJSONObject("product");
                commodity.put("UPDATE_TIME", Tools.date2Str(new Date()));
                dowith(commodity, product);
//                commodityconfigService.updateCommodityConfig(commodity);
            }
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
    }

    /**
     * 参数处理
     * @param pd
     * @param product
     */
    public void dowith(PageData pd, JSONObject product) {
        try {
            pd.put("TYPE_ID", product.getString("typeid"));
            pd.put("PROVID_SOURCE", product.getString("typeid"));
            pd.put("COMMODITY_NAME", StringUtils.isEmpty(product.getString("product_name")) ? "" : URLDecoder.decode(product.getString("product_name")));
            String PROVID_SOURCE = product.getString("product_group");
            if ("1".equals(PROVID_SOURCE)) {
                PROVID_SOURCE = "system";
            } else if ("4".equals(PROVID_SOURCE)) {
                PROVID_SOURCE = "jindong";
            } else if ("5".equals(PROVID_SOURCE)) {
                PROVID_SOURCE = "wangyi";
            }
            pd.put("PROVID_SOURCE", PROVID_SOURCE);
            pd.put("EXPLAIN", StringUtils.isEmpty(product.getString("sell_point")) ? "" : URLDecoder.decode(product.getString("sell_point")));
            pd.put("PRODUCT_CATE", product.getString("category_id"));
            pd.put("BRAND", StringUtils.isEmpty(product.getString("brand")) ? "" : URLDecoder.decode(product.getString("brand")));
            pd.put("MARKET_PRICE", product.getString("market_price"));
            pd.put("RETAIL_AMOUNT", product.getString("settlement"));
            pd.put("SALE_STATUS", product.getString("state").equals("1") ? "selling" : "undercarriage");
            pd.put("THUMBNAIL_IMAGE", product.getString("product_images"));
            pd.put("IMAGE_URLS", product.getString("images"));
            if("0".equals(product.getString("state"))){
                // 下架商品挪出礼包/商品库
                pd.put("GIFTPACKAGE_ID", "");
                pd.put("CHOOSE_FLAG", Constants.NO_FLAG);
            }
            pd.put("IMAGE", StringUtils.isEmpty(product.getString("app_introduce")) ? "" : URLDecoder.decode(product.getString("app_introduce")));
        } catch (Exception e) {
            logger.error("forCommodityBhTask_dowith_e=" + e);
        }
    }

}
