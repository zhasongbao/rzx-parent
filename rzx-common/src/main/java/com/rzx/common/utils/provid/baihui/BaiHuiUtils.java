package com.rzx.common.utils.provid.baihui;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rzx.common.constant.Constants;
import com.rzx.common.core.mongodb.MongodbService;
import com.rzx.common.core.redis.RedisCache;
import com.rzx.common.utils.MD5;
import com.rzx.common.utils.Tools;
import com.rzx.common.utils.http.HttpClientUtil;
import com.rzx.common.utils.spring.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 重庆百汇商城
 * 测试环境 接口URL地址：http://t.blhapi.li91.com 	App_id：3000100071   App_key:cc3ded0b341572fd108d2911e0b8ac27
 */
public class BaiHuiUtils {

    private static final Logger logger = LoggerFactory.getLogger(BaiHuiUtils.class);
    private static RedisCache redisDaoImpl = SpringUtils.getBean(RedisCache.class);
    private static MongodbService mongodbService = SpringUtils.getBean(MongodbService.class);

    //当前环境
    private static final String ACTIVE = SpringUtils.getActiveProfiles()[0];
    //生产
    private static final String PROD = "prod";

    private static String REDIS_BH_TOKEN = "baihui:token";
    /**
     * 请求URL
     */
    private static String URL_DEV = "http://t.blhapi.li91.com";
    private static String URL_PRO = "https://blhapi.li91.com";

    public static String APPID_DEV = "3000100071";
    public static String APPID_PRO = "3000100107";
    /**
     * 云中鹤 accessToken
     */
    private static String APPKEY_DEV = "cc3ded0b341572fd108d2911e0b8ac27";
    private static String APPKEY_PRO = "72028d49f8dcedddfedfa9545dd4b7c4";

    public static String getReqUrl() {
        if (PROD.equals(ACTIVE)) {
            return URL_PRO;
        } else {
            return URL_DEV;
        }
    }

    public static String getAppId() {
        if (PROD.equals(ACTIVE)) {
            return APPID_PRO;
        } else {
            return APPID_DEV;
        }
    }

    public static String getAppKey() {
        if (PROD.equals(ACTIVE)) {
            return APPKEY_PRO;
        } else {
            return APPKEY_DEV;
        }
    }


    public static String getBaihuiToken() {
        String token = redisDaoImpl.getCacheObject(REDIS_BH_TOKEN);
        if (StringUtils.isEmpty(token)) {
            token = BaiHuiUtils.getToken();
            redisDaoImpl.setCacheObject(REDIS_BH_TOKEN, token);
            redisDaoImpl.expire(REDIS_BH_TOKEN, 86400000);
        }
        if (StringUtils.isEmpty(token)) {
            logger.error("getBaihuiToken_token获取失败");
        }
        return token;
    }

    public static void main(String[] args) throws Exception {
//		getToken(); 	//获取授权token f5315d3e05c6d3b9087e56afd3615ea8
//		getGoodsId("41d3b67a9f2a1752f0ae0db43401dc26");	//查询商品ID 2000220,2000227,2000249,2000300,2000325,2000329,2000381
        getGoodInfo("67a635f909d727f13715c0d261670a7b", "2000381");    //查询商品详情
//		getCategorys("2f156560b8b5b2b135a4dd04c73d2115"); //查询分类列表
//		getProvince("2f156560b8b5b2b135a4dd04c73d2115"); 	//查询地址库列表（省级）
//		getCity("2f156560b8b5b2b135a4dd04c73d2115","21"); 	//查询地址库列表（市级）
//		getCounty("2f156560b8b5b2b135a4dd04c73d2115","1845"); 	//查询地址库列表（区县）
//		getTown("2f156560b8b5b2b135a4dd04c73d2115","1848"); 	//查询地址库列表（街道）
//		getBhValid("2f156560b8b5b2b135a4dd04c73d2115","21","1845","1848","25611"); //校验地址有效性


//		{"addr_type":"1","cityId":"1607","confirmModel":"1","countyId":"4773","isvirtual":"1","orderId":"09141412100730240",
//		"products":[{"itemId":"2000300","number":"1"}],"provinceId":"19","sendcms":"1","shouhuo_addr":"123","shouhuo_name":"王大锤",
//		"shouhuo_phone":"17621145730","token":"67a635f909d727f13715c0d261670a7b","townId":"62121"}


        JSONObject param = new JSONObject();
        param.put("token", "67a635f909d727f13715c0d261670a7b");
        param.put("isvirtual", "1");
        param.put("orderId", "123123545541");
        param.put("shouhuo_phone", "17621145730");
        param.put("shouhuo_name", "王大锤");
        param.put("addr_type", "1");
        param.put("provinceId", "19");
        param.put("cityId", "1607");
        param.put("countyId", "4773");
        param.put("townId", "62121");
        param.put("shouhuo_addr", "水口花园东七巷");
        param.put("confirmModel", "1");
        JSONArray products = new JSONArray();
        JSONObject product = new JSONObject();
        product.put("itemId", "2000381");
        product.put("number", "1");
//			product.put("price","");
        products.add(product);
        param.put("products", products);
        param.put("sendcms", "1"); //1-出库时短信提示 0-不提示
//		JSONObject respJson = requestToBh(getReqUrl() + "/Order/createOrder", param);
    }

    private static void saveLog(JSONObject paraJson, JSONObject result, String orderId, String urlPath) {
        // 保存日志
        final Map<String, Object> paraPd = new HashMap<>();
        paraPd.put("result", result == null ? "" : result);
        paraPd.put("paraJson", paraJson == null ? "" : paraJson);
        paraPd.put("orderId", orderId);
        paraPd.put("urlPath", urlPath);
        new Thread(() -> {
            paraPd.put("a_id", IdUtil.simpleUUID());
            paraPd.put("a_createTime", DateUtil.now());
            paraPd.put("paraJson", paraJson);
            mongodbService.save(paraPd, Constants.BAILIHUI_REQUESTAPILOG);
        }).start();
    }

    /**
     * 请求百汇方法
     * 测试http 生产https
     *
     * @param url
     * @param param
     * @return
     */
    public static JSONObject requestToBh(String url, JSONObject param) {
        JSONObject resp = new JSONObject();
        try {
            if (PROD.equals(ACTIVE)) {
                // 生产环境调用https
                resp = JSON.parseObject(HttpClientUtil.doPostToBaiHuiPro(url, JSONObject.toJSONString(param), "utf-8"));
            } else {
                // 测试环境调用http
                resp = HttpClientUtil.doPostToBaiHui(url, param);
            }
        } catch (Exception e) {
            logger.error("BaiHuiUtil_requestToBh_e=", e);
        }
        return resp;
    }

    /**
     * 获取授权token  844df9e75e56b305959be50725493746
     */
    public static String getToken() {
        String token = "";
        try {
            String tamptimes = Tools.date2Str(new Date());
            JSONObject param = new JSONObject();
            param.put("app_id", getAppId());
            param.put("app_key", getAppKey());
            param.put("tamptimes", tamptimes);
            String sign = MD5.md5("app_id=" + getAppId() + ":app_key=" + getAppKey() + ":tamptimes=" + tamptimes).toUpperCase();
            param.put("sign", sign);
            JSONObject respJson = requestToBh(getReqUrl() + "/Index/getToken", param);
            if (!respJson.isEmpty() && respJson.getBoolean("result")) {
                JSONObject data = (JSONObject) respJson.get("data");
                if (!data.isEmpty()) {
                    token = data.getString("token");
                }
            }
        } catch (Exception e) {
            logger.error("BaiHuiUtil_getToken_e=", e);
        }
        return token;
    }


    /**
     * 创建订单
     *
     * @param token   授权口令
     * @param order   订单信息
     * @param address 地址信息
     */
    public static JSONObject createOrder(String token, JSONObject order, JSONObject address) {
        JSONObject respJson = new JSONObject();
        try {
            JSONObject param = new JSONObject();
            param.put("token", token);
            param.put("isvirtual", order.getString("PROVID_SOURCE"));
            param.put("orderId", order.getString("ORDER_ID"));
            param.put("shouhuo_phone", address.getString("RECEIVE_PHONE"));
            param.put("shouhuo_name", address.getString("RECEIVE_NAME"));
            param.put("addr_type", "1");//TODO
            param.put("provinceId", address.getString("PROVINCE"));
            param.put("cityId", address.getString("CITY"));
            param.put("countyId", address.getString("COUNTY"));
            param.put("townId", address.getString("TOWN"));
            param.put("shouhuo_addr", address.getString("RECEIVE_ADDRESS"));
            param.put("confirmModel", "1");
            JSONArray products = new JSONArray();
            JSONObject product = new JSONObject();
            product.put("itemId", order.getString("COMMODITY_CODE"));
            product.put("number", "1");
//			product.put("price",""); TODO
            products.add(product);
            param.put("products", products);
            param.put("sendcms", "1"); //1-出库时短信提示 0-不提示
            respJson = requestToBh(getReqUrl() + "/Order/createOrder", param);
            saveLog(param, respJson, order.getString("ORDER_ID"), "/Order/createOrder");
        } catch (Exception e) {
            logger.error("BaiHuiUtil_getGoodsId_e", e);
        }
        return respJson;
    }

    /**
     * 取消订单
     *
     * @param token    授权口令
     * @param order_sn 订单号
     */
    public static JSONObject cancelOrder(String token, String order_sn) {
        JSONObject respJson = new JSONObject();
        try {
            JSONObject param = new JSONObject();
            param.put("token", token);
            param.put("order_sn", order_sn);
            respJson = requestToBh(getReqUrl() + "/Order/createOrder", param);
        } catch (Exception e) {
            logger.error("BaiHuiUtil_getGoodsId_e", e);
        }
        return respJson;
    }

    /**
     * 查询商品ID
     *
     * @param token 授权口令
     */
    public static JSONObject getGoodsId(String token) {
        JSONObject respJson = new JSONObject();
        try {
            JSONObject param = new JSONObject();
            param.put("token", token);
            respJson = requestToBh(getReqUrl() + "/Product/getGoodsId", param);
            saveLog(param, respJson, null, "/Product/getGoodsId");
        } catch (Exception e) {
            logger.error("BaiHuiUtil_getGoodsId_e", e);
        }
        return respJson;
    }

    /**
     * 查询商品详情
     *
     * @param token  授权口令
     * @param itemId 商品ID
     */
    public static JSONObject getGoodInfo(String token, String itemId) {
        JSONObject respJson = new JSONObject();
        try {
            JSONObject param = new JSONObject();
            param.put("token", token);
            param.put("itemId", itemId);
            respJson = requestToBh(getReqUrl() + "/Product/getGoodInfo", param);
            saveLog(param, respJson, itemId, "/Product/getGoodInfo");
        } catch (Exception e) {
            logger.error("BaiHuiUtil_getGoodInfo_e", e);
        }
        return respJson;
    }

    /**
     * 查询分类列表
     *
     * @param token 授权口令
     * @return
     */
    public static JSONObject getCategorys(String token) {
        JSONObject respJson = new JSONObject();
        try {
            JSONObject param = new JSONObject();
            param.put("token", token);
            respJson = requestToBh(getReqUrl() + "/Product/getCategorys", param);
            saveLog(param, respJson, null, "/Product/getCategorys");
        } catch (Exception e) {
            logger.error("BaiHuiUtil_getCategorys_e=", e);
        }
        return respJson;
    }

    /**
     * 查询地址库列表（省级）
     *
     * @param token 授权口令
     * @return
     */
    public static JSONObject getProvince(String token) {
        JSONObject respJson = new JSONObject();
        try {
            JSONObject param = new JSONObject();
            param.put("token", token);
            respJson = requestToBh(getReqUrl() + "/Area/getProvince", param);
            saveLog(param, respJson, null, "/Area/getProvince");
        } catch (Exception e) {
            logger.error("BaiHuiUtil_getProvince_e", e);
        }
        return respJson;
    }

    /**
     * 查询地址库列表（市级）
     *
     * @param token  授权口令
     * @param areaId 省级区域ID
     * @return
     */
    public static JSONObject getCity(String token, String areaId) {
        JSONObject respJson = new JSONObject();
        try {
            JSONObject param = new JSONObject();
            param.put("token", token);
            param.put("area_id", areaId);
            respJson = requestToBh(getReqUrl() + "/Area/getCity", param);
            saveLog(param, respJson, null, "/Area/getCity");
        } catch (Exception e) {
            logger.error("BaiHuiUtil_getCity_e", e);
        }
        return respJson;
    }

    /**
     * 查询地址库列表（区县）
     *
     * @param token  授权口令
     * @param cityId 省级区域ID
     * @return
     */
    public static JSONObject getCounty(String token, String cityId) {
        JSONObject respJson = new JSONObject();
        try {
            JSONObject param = new JSONObject();
            param.put("token", token);
            param.put("area_id", cityId);
            respJson = requestToBh(getReqUrl() + "/Area/getCounty", param);
            saveLog(param, respJson, null, "/Area/getCounty");
        } catch (Exception e) {
            logger.error("BaiHuiUtil_getCounty_e", e);
        }
        return respJson;
    }

    /**
     * 查询地址库列表（街道）
     *
     * @param token    授权口令
     * @param countyId 省级区域ID
     * @return
     */
    public static JSONObject getTown(String token, String countyId) {
        JSONObject respJson = new JSONObject();
        try {
            JSONObject param = new JSONObject();
            param.put("token", token);
            param.put("area_id", countyId);
            respJson = requestToBh(getReqUrl() + "/Area/getTown", param);
            saveLog(param, respJson, null, "/Area/getTown");
        } catch (Exception e) {
            logger.error("BaiHuiUtil_getTown_e", e);
        }
        return respJson;
    }

    /**
     * 查询地址有效性
     *
     * @param token      授权口令
     * @param provinceId
     * @param cityId
     * @param countyId
     * @param townId
     * @return
     */
    public static JSONObject getBhValid(String token, String provinceId, String cityId, String countyId, String townId) {
        JSONObject respJson = new JSONObject();
        StringBuilder area_ids = new StringBuilder(provinceId);
        if (!StringUtils.isEmpty(cityId)) {
            area_ids.append(",").append(cityId);
            if (!StringUtils.isEmpty(countyId)) {
                area_ids.append(",").append(countyId);
                if (!StringUtils.isEmpty(townId)) {
                    area_ids.append(",").append(townId);
                }
            }
        }
        try {
            JSONObject param = new JSONObject();
            param.put("token", token);
            param.put("area_ids", area_ids.toString());
            respJson = requestToBh(getReqUrl() + "/Area/getValid", param);
            saveLog(param, respJson, null, "/Area/getValid");
        } catch (Exception e) {
            logger.error("BaiHuiUtil_getBhValid_e", e);
        }
        return respJson;
    }

    /**
     * 查询京东商品区域库存（附带商品数量）
     *
     * @param token 授权口令
     * @return
     */
    public static JSONObject getStoreAsNum(String token, List<Object> list, String provinceId, String cityId, String countyId, String townId) {
        JSONObject respJson = new JSONObject();
        try {
            JSONObject param = new JSONObject();
            param.put("token", token);
            param.put("products", list);
            param.put("province", provinceId);
            param.put("city", cityId);
            param.put("county", countyId);
            param.put("town", townId);
            respJson = requestToBh(getReqUrl() + "/Store/getStoreAsNum", param);
        } catch (Exception e) {
            logger.error("BaiHuiUtil_getStoreAsNum_e", e);
        }
        return respJson;
    }

    /**
     * 查询实物订单包裹信息
     *
     * @param token    授权口令
     * @param order_sn 百汇订单号
     * @param nums     运单号
     * @return
     */
    public static JSONObject getOrderExpress(String token, String order_sn, String nums) {
        JSONObject respJson = new JSONObject();
        try {
            JSONObject param = new JSONObject();
            param.put("token", token);
            param.put("order_sn", order_sn);
            param.put("nums", nums);
            respJson = requestToBh(getReqUrl() + "/Special/getOrderExpress", param);
        } catch (Exception e) {
            logger.error("BaiHuiUtil_getOrderShipment_e", e);
        }
        return respJson;
    }

    /**
     * 查询实物订单配送信息
     *
     * @param token 授权口令
     * @return
     */
    public static JSONObject getOrderShipment(String token, String order_sn) {
        JSONObject respJson = new JSONObject();
        try {
            JSONObject param = new JSONObject();
            param.put("token", token);
            param.put("order_sn", order_sn);
            respJson = requestToBh(getReqUrl() + "/Order/getOrderShipment", param);
        } catch (Exception e) {
            logger.error("BaiHuiUtil_getOrderShipment_e", e);
        }
        return respJson;
    }

    /**
     * 查询商品区域库存状态
     *
     * @param token 授权口令
     * @return
     */
    public static JSONObject getRegionStore(String token, String itemIds, String provinceId, String cityId, String countyId, String townId) {
        JSONObject respJson = new JSONObject();
        try {
            JSONObject param = new JSONObject();
            param.put("token", token);
            param.put("itemIds", itemIds);
            param.put("province", provinceId);
            param.put("city", cityId);
            param.put("county", countyId);
            param.put("town", townId);
            respJson = requestToBh(getReqUrl() + "/Store/getRegionStore", param);
        } catch (Exception e) {
            logger.error("BaiHuiUtil_getRegionStore_e", e);
        }
        return respJson;
    }

    /**
     * 查询通知
     *
     * @param token 授权口令
     * @return
     */
    public static JSONObject getNotics(String token) {
        JSONObject respJson = new JSONObject();
        try {
            JSONObject param = new JSONObject();
            param.put("token", token);
            respJson = requestToBh(getReqUrl() + "/Notic/getNotics", param);
        } catch (Exception e) {
            logger.error("BaiHuiUtil_getNotics_e", e);
        }
        return respJson;
    }

    /**
     * 删除通知消息
     *
     * @param token 授权口令
     * @return
     */
    public static JSONObject doNotics(String token, String notice_ids) {
        JSONObject respJson = new JSONObject();
        try {
            JSONObject param = new JSONObject();
            param.put("token", token);
            param.put("notice_ids", notice_ids);
            respJson = requestToBh(getReqUrl() + "/Notic/doNotics", param);
        } catch (Exception e) {
            logger.error("BaiHuiUtil_doNotics_e", e);
        }
        return respJson;
    }

}
