package com.rzx.common.utils.jt;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.rzx.common.core.mongodb.MongodbUtils;
import com.rzx.common.core.redis.RedisCache;
import com.rzx.common.utils.spring.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 广西-捷通小车发行接口API工具类
 *
 * @author hk
 * @date: 2021-09-14
 */
@Component
public class JTApiUtil {
    private static final Logger logger = LoggerFactory.getLogger(JTApiUtil.class);

    private static RedisCache redisDaoImpl = SpringUtils.getBean(RedisCache.class);


    private static final String MONGODB_JT_TABLE = "rtx_jt_trolley_requestApiLog";

    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(50);
    //当前环境
    private static final String THIS_ACTIVE = SpringUtils.getActiveProfiles()[0];
    //生产
    private static final String ACTIVE_PROD_NAME = "prod";
    //发行相关地址
    private static final String URL_DEV = "https://posrefactor.gxetc.com.cn/etcBusinessInterface";
    private static final String URL_PRO = "https://pos.gxetc.com.cn/etcBusinessInterface";
    //资金相关地址
    private static final String CAPITAL_URL_DEV = "https://payapitest.gxetc.com.cn/";
    private static final String CAPITAL_URL_PRO = "https://payapi.gxetc.com.cn/";
    //员工号
    private static final String OPERATOR_NAME_DEV = "FMGS002";
    private static final String OPERATOR_NAME_PRO = "FM0002";
    //员工号密码
    private static final String OPERATOR_PASSWORD_DEV = "FMGS002";
    private static final String OPERATOR_PASSWORD_PRO = "FM123456";
    //终端ID
    private static final String TERMINAL_ID_DEV = "000020201127";
    private static final String TERMINAL_ID_PRO = "000020210129";
    //厂商ID
    private static final String MANUFACTURER_ID_DEV = "1999";
    private static final String MANUFACTURER_ID_PRO = "1999";
    //银行ID
    private static final String BANK_ID_DEV = "FMGS";
    private static final String BANK_ID_PRO = "FMGS";
    //银行网点ID
    private static final String NET_NODE_ID_DEV = "2020112701";
    private static final String NET_NODE_ID_PRO = "2020112700";
    //版本号
    private static final String SOFT_VERSION_DEV = "V0.0.0";
    private static final String SOFT_VERSION_PRO = "V0.0.0";
    //圈存终端号
    private static final String CARD_READERTERM_NO_DEV = "000020201127";
    private static final String CARD_READERTERM_NO_PRO = "000020210130";

    private static final String ETCMARKETMASTID_DEV = "100000100000452";
    private static final String ETCMARKETMASTID_PRO = "100000620";

    private static final String ETCMARKETMASTID_ZX_DEV = "100000100000452";
    private static final String ETCMARKETMASTID_ZX_PRO = "100000653";

    private static final String OBUMARKETMASTID_DEV = "100000100000332";
    private static final String OBUMARKETMASTID_PRO = "100000621";

    private static final String OBUMARKETMASTID_ZX_DEV = "100000100000332";
    private static final String OBUMARKETMASTID_ZX_PRO = "100000654";

    private static final String RETURN_CODE_NAME = "ajaxTrage";

    private static final String RETURN_MSG_NAME = "ajaxMsg";

    private static final String RETURN_SUCCESS_NAME = "true";

    private static final String RETURN_FAIN_NAME = "false";

    private static final String RETURN_JT_TOKEN_NAME = "authToken";

    private static final String REDIS_JT_TOKEN = "JT_TOKEN";

    //捷通token重试次数
    private static final String JT_TOKEN_RETRIES_COUNT = "jtTokenRetriesCount";


    public static void main(String[] args) throws Exception {
//        //新增绑定关系
//        PageData data = new PageData();
//        data.put("frontServerAccessNo", UuidUtil.get32UUID());
//        data.put("tradeNo", data.getString("frontServerAccessNo"));
//        data.put("bindingSerialNo", JTApiUtil.generateBindingSerialNo());
//        data.put("bankId", getBankId());
//        data.put("netNodeId", getNetNodeId());

//        //银行卡号
//        data.put("BIND_MEDIUM", "6228480409589272577");
//        data.put("bankCardType", "1");
//        data.put("operateTime", Tools.date2Str(new Date()));
//        //车牌号
//        data.put("CAR_NUM", "苏EBK113");
//        //车牌颜色
//        data.put("VEHICLECOLOR", "1");
//        data.put("custIdTypeCode", "101");
//        //身份证号
//        data.put("IDCODE", "340621198207064415");
//        data.put("bindingType", "3");
//        data.put("interAgency", "0");
//        data.put("payOrgId", "CCB");
//        JSONObject result=bindingReq(data, "");
        //成功的话直接返回true


//        查询客户信息
//        JSONObject result = searchCust1("340621198207064415", "");
        //成功 {"ajaxTrage":true,"resultList":[{"custMobile":"13824904702","operateTime":1607582145000,"operator":1110003849,"invoiceType":"1","indentFund":0,"netno":"4501","mailAddress":"安徽省溪县四铺镇大曹村小郭家庄511号","custIdTypeCodeStr":"身份证","version":2,"branchCode":1011104273,"custFund":0,"invoiceTypeStr":"预存时领取","custType":"0","custUserPassword":"d90d0241599d547eab75e6dec01cc108","custMastId":2861316,"custIdNo":"340621198207064415","status":"1","custFrozenFund":0,"registeredType":2,"updateDate":1607582145000,"statusStr":"正常","branchCodeStr":"分米发行网点","custBriefName":"高飞","custUserName":"340621198207064415","custBankNameStr":"","delFlag":"0","custSalesAccount":0,"updateBy":1110003849,"createDate":1607582145000,"custTypeStr":"个人","custName":"高飞","custCode":"3584819","organizationStr":"","custIdTypeCode":"0","createBy":1110003849,"masterCustId":2861316,"custAccountFlagStr":"","custContact":"高飞","operatorStr":"分米公司"}]}
        //    {"ajaxTrage":false,"ajaxMsg":"-3001,查询不到客户信息！"}


        //创建客户
//        PageData data = new PageData();
//        data.put("CARD_HOLDER", "高飞");
//        data.put("IDCODE", "340621198207064415");
//        data.put("PHONE", "13824904702");
//        data.put("ID_ADDRESS", "安徽省溪县四铺镇大曹村小郭家庄511号");
//        JSONObject insUserObj = JTApiUtil.addCust(data.getString("CARD_HOLDER"), data.getString("IDCODE"), data.getString("PHONE"), data.getString("ID_ADDRESS"), "");
//        结果 {"ajaxTrage":true,"custInfo":{"custMobile":"%^&*b866879b541d59b8d4354ddadc3868cb","operateTime":1607582145145,"vatInvoiceName":"","operator":1110003849,"organizationId":"","invoiceType":"1","indentFund":0,"custIdTypeFront":"","netno":"4501","mailAddress":"安徽省溪县四铺镇大曹村小郭家庄511号","custIdTypeCodeStr":"身份证","version":1,"branchCode":1011104273,"vatInvoiceNo":"","custFund":0,"invoiceTypeStr":"预存时领取","custEmail":"","custType":"0","contactIdTypeFront":"","custUserPassword":"d90d0241599d547eab75e6dec01cc108","custMastId":2861316,"custIdNo":"!@#$4aab860414e1d60bb12973afc0a8f9e229cbcae1f2bb4d8d5d5e3b6d8b584b1b","status":"1","custFrozenFund":0,"registeredType":2,"updateDate":1607582145145,"vatInvoiceAddressAndTel":"","invoiceName":"test","statusStr":"正常","branchCodeStr":"分米发行网点","custBriefName":"高飞","custUserName":"340621198207064415","faceFeatureCode":"","remark":"","custBankNameStr":"","delFlag":"0","custSalesAccount":0,"updateBy":1110003849,"createDate":1607582145145,"invoiceInfo":"","contactIdTypeBack":"","custTypeStr":"个人","custIdTypeBack":"","custName":"高飞","custCode":"3584819","organizationStr":"","custIdTypeCode":"0","custTel":"","createBy":1110003849,"faceFeatureVersion":"","custAccountFlagStr":"","vatInvoiceBankNo":"","custContact":"高飞","operatorStr":"分米公司"}}
        //多次{"ajaxTrage":false,"ajaxMsg":"新建客户信息失败，失败原因：已存在相同证件类型和证件号码的用户，请核对证件信息是否正确!"}

        //车牌校验
//        JSONObject object=JTApiUtil.carUniqueResult("苏DJR675","2","1","");

        //根据卡号获取卡片信息      45012026230300003395
//        JTApiUtil.getCardInfoByNo("45012026230300003393","");

        //获取营销方案，
//        JTApiUtil.getCardMarketMastList("1","3","");
        //100000100000452
        // 结果{"ajaxTrage":true,"cardMarketMastList":[{"checkBy":0,"createDeptId":0,"checkDeptId":0,"delFlag":"0","marketType":"","checkStatus":"","marketCharges":[],"carType":"","updateBy":0,"activityDetailPrint":"","marketFilePath":"","limitDeptId":0,"issueLimitCount":0,"cardType":"","isTrunk":"","version":0,"createBy":0,"marketName":"货车后付费记账卡(广西捷通高速科技股份有限公司)","marketRegulations":[],"parentMarketMastId":0,"custType":"","marketMastId":100000100000452,"custMastId":0,"activityType":"","marketDetail":""}]}

        //获取营销方案，
        //100000654
//        JTApiUtil.getObuMarketMastList("3","3","");
//
//        JSONObject data=new JSONObject();
//        data.put("custMastId","2861321");
//        data.put("cardNo","45012026230300003395");
//        data.put("marketMastId","100000100000452");
//        data.put("carStyle","0");
//        data.put("cardClass","2");
//        data.put("carNo","云F87008");
//        data.put("carColor","1");
//        data.put("isTrunk","1");
//        data.put("carType","2");
//        data.put("gxCarType","4");
//        data.put("carModel","解放牌CA5180CCYP62K1L4");
//        data.put("carSeatNum","3");
//        data.put("vin","LFNAHULM3LFA32859");
//        data.put("permittedWeight","9900");
//        data.put("axleCount","2");
//        data.put("vehicleType","重型仓栅式货车");
//        data.put("totalMass","18000");
//        data.put("maintenanceMass","7905");
//        data.put("wheelCount","4");
//        data.put("engineNum","60572007");
//        data.put("carLong","9000");
//        data.put("carWide","2550");
//        data.put("carHigh","3900");
//        data.put("permittedTowWeight","");
//        data.put("needTicket","0");
//        data.put("cardType","23");
//        data.put("issueType","20");
//        data.put("isPos","2");
//        data.put("drivingLicencePath", "");
//        //车辆图片
//        data.put("drivingLicenceBackPath", "");
//        data.put("thirdPayOrderNo", "");
//        //预售记录ID号
//        data.put("orderDetailInfoId", "");
//        data.put("roadTransportPath", "");
//        JTApiUtil.addCardReleaseRequest(data,"");

//        System.out.println();
//        //
//        reUploadPhotoResult(Base64Util.getBase64("https://huochebaoimg.oss-cn-shenzhen.aliyuncs.com/images/files/2020/12/14/962652d82d10485aaff973ddd1eaa946.jpg"),"custIdTypeFront","cust","123","");


        //写卡0015
//        getMacFor0015FileResult("release",Tools.getRandomStr(4),"780531","");
//        {"mac2":"98","ajaxTrage":true,"fileData":"04D695141B2020121520301215D4C64638373030380000000000010c"}
        //写卡0016
//        getMacFor0016FileResult("release", Tools.getRandomStr(4), "780531", "");
//        {"mac2":"98","ajaxTrage":true,"fileData":"04D6960239C5EDB9E2D5FD0000000000000000000000000000353332313232313938393032313832383135000000000000000000000000000000"}

        //写卡001B
//        getMacFor001BFileResult("release",Tools.getRandomStr(8),"780531","");
        //{"mac2":"98","ajaxTrage":true,"fileData":"451C0002000000000000000000000000000000000000000000000000005B"}


        //获取OBU营销方案
//        getObuMarketMastList("2", "1", "");
        //100000100000332
        //结果{"ajaxTrage":true,"obuMarketMastList":[{"checkBy":0,"createDeptId":0,"checkDeptId":0,"delFlag":"0","marketType":"","checkStatus":"","marketCharges":[],"carType":"","updateBy":0,"activityDetailPrint":"","marketFilePath":"","limitDeptId":0,"issueLimitCount":0,"cardType":"","isTrunk":"","version":0,"createBy":0,"marketName":"货车记账卡发行（OBU）(广西捷通高速科技股份有限公司)","marketRegulations":[],"parentMarketMastId":0,"custType":"","marketMastId":100000100000332,"custMastId":0,"activityType":"","marketDetail":""}]}


//        List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
//
//        //排序方法
//        Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
//            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//                return (o1.getKey()).toString().compareTo(o2.getKey());
//            }
//        });
//
//        addCardFreeChangeRequest("4501192905026823", "4501192905026832", "");

//        JTApiUtil.reUploadPhoto("mjxLJQ43THP6d38TSfxXCHgHqLTvkQD9VD4Fzzs7LgH6pqI7EmfgNFt8STH6FFmf017", "custIdTypeBack", "obu", "4501192905026914", "80ea4ad2ba164c38a7ad0256da4d82de");
//        searchRelationship("闽D8TW71","0","");
    }


    /**
     * 登录接口
     * 2.1.1
     *
     * @return
     * @throws Exception
     */
    public static JSONObject login() throws Exception {
        JSONObject result = new JSONObject();

        String token = redisDaoImpl.getCacheObject(REDIS_JT_TOKEN);
        if (StringUtils.isEmpty(token)) {
            JSONObject data = new JSONObject();
            data.put("operatorName", getOperatorName());
            data.put("operatorPassword", getOperatorPassword());
            data.put("softVersion", getSoftVersion());
            JSONObject object = request(data, "/login.do?method=login", "", true, "登录接口");
            if (null == object || RETURN_FAIN_NAME.equals(object.getString(RETURN_CODE_NAME))) {
                result.put("ajaxMsg", "获取token错误!");
                result.put("ajaxTrage", false);
                return result;
            }
            redisDaoImpl.setCacheObject(REDIS_JT_TOKEN, object.getString(RETURN_JT_TOKEN_NAME));
            redisDaoImpl.expire(REDIS_JT_TOKEN, 86400000);
            result.put("token", object.getString(RETURN_JT_TOKEN_NAME));
            result.put(RETURN_MSG_NAME, object.getString(RETURN_MSG_NAME));
            result.put(RETURN_CODE_NAME, object.getString(RETURN_CODE_NAME));
        } else {
            result.put(REDIS_JT_TOKEN, token);
            result.put(RETURN_CODE_NAME, true);
            result.put(RETURN_MSG_NAME, "获取成功!");
        }
        return result;
    }

    /**
     * 获取软件版本号
     * 2.1.4
     *
     * @return
     * @throws Exception
     */
    public static JSONObject getSoftVersionOne() throws Exception {
        JSONObject result = new JSONObject();
        String version = redisDaoImpl.getCacheObject("JT_VERSION");
        if (StringUtils.isEmpty(version)) {
            JSONObject object = request(result, "/admin.do?method=getSoftVersionInfo", "", true, "获取软件版本号信息接口");
            if (null == object || "false".equals(object.getString("ajaxTrage"))) {
                result.put("ajaxMsg", "获取软件版本号错误!");
                result.put("ajaxTrage", false);
                return result;
            }
            version = object.getString("currentVersion");
            //如果不一致则需要同步软件版本号
            if (!object.getString("currentVersion").equals(object.getString("nextVersion"))) {
                object = updateSoftVersion(version);
                if (null == object || "false".equals(object.getString("ajaxTrage"))) {
                    result.put("ajaxMsg", "因软件版本不一致,同步最新版本错误!");
                    result.put("ajaxTrage", false);
                    return result;
                }
                version = object.getString("currentVersion");
            }
            redisDaoImpl.setCacheObject("JT_VERSION", version);
            redisDaoImpl.expire("JT_VERSION", 86400000);
            result.put("version", version);
            result.put("ajaxTrage", true);
            result.put("ajaxMsg", "获取成功!");
        } else {
            result.put("version", version);
            result.put("ajaxTrage", true);
            result.put("ajaxMsg", "获取成功!");
        }
        return result;
    }

    /**
     * 同步软件版本号
     * 2.1.5
     *
     * @param currentVersion
     * @return
     * @throws Exception
     */
    public static JSONObject updateSoftVersion(String currentVersion) throws Exception {
        JSONObject param = new JSONObject();
        JSONObject result = new JSONObject();
        param.put("currentVersion", currentVersion);
        result = request(param, "/admin.do?method=updateSoftVersion", "", true, "同步软件版本号");
        return result;
    }

    /**
     * 车牌唯一性校验
     * 2.3.20
     *
     * @param carNo    车牌号
     * @param carColor 车牌颜色
     * @param isTrunk  客货标识
     * @return
     * @throws Exception
     */
    public static JSONObject carUniqueResult(String carNo, String carColor, String isTrunk, String orderId) throws Exception {
        JSONObject param = new JSONObject();
        param.put("carNo", carNo);
        param.put("carColor", carColor);
        param.put("isTrunk", isTrunk);
        JSONObject result = request(param, "/cardInterface.do?method=carUniqueResult", orderId, true, "车牌唯一性校验");
        return result;
    }

    /**
     * 新增绑定申请
     * 2.11.1
     *
     * @param orderId
     * @return
     * @throws Exception
     */
    public static JSONObject bindingReq(JSONObject data, String orderId) throws Exception {
        JSONObject param = new JSONObject();
        param.put("frontServerAccessNo", data.getString("frontServerAccessNo"));
        param.put("tradeNo", data.getString("tradeNo"));
        param.put("bankId", getBankId());
        param.put("netNodeId", getNetNodeId());
        param.put("bindingSerialNo", data.getString("bindingSerialNo"));
        param.put("bankCardNo", data.getString("BIND_MEDIUM"));
        param.put("bankCardType", "1");
        param.put("operateTime", DateUtil.now());
        param.put("carNo", data.getString("CAR_NUM"));
        param.put("carColor", data.getString("VEHICLECOLOR"));
        param.put("custIdTypeCode", "101");
        param.put("custIdNo", data.getString("IDCODE"));
        param.put("bindingType", "3");
        param.put("interAgency", "0");
        param.put("payOrgId", "CCB");
        JSONObject result = request(param, "/binding.do?method=bindingReq", orderId, true, "新增绑定申请");
        return result;
    }

    /**
     * 查询客户
     * 2.2.4
     *
     * @param custIdNo 证件号码
     * @param orderId
     * @return
     * @throws Exception
     */
    public static JSONObject searchCust1(String custIdNo, String orderId) throws Exception {
        JSONObject param = new JSONObject();
        param.put("custType", "0");
        param.put("custIdType", "0");
        param.put("custIdNo", custIdNo);
        JSONObject result = request(param, "/custInterface.do?method=searchCust1", orderId, true, "查询客户");
        return result;
    }

    /**
     * 创建普通用户
     * 2.2.1
     *
     * @param custName    客户名称
     * @param custIdNo    证件号码
     * @param custMobile  联系人手机号码
     * @param mailAddress 身份证地址
     * @param orderId
     * @return
     * @throws Exception
     */
    public static JSONObject addCust(String custName, String custIdNo,
                                     String custMobile, String mailAddress, String orderId) throws Exception {
        JSONObject param = new JSONObject();
        param.put("custName", custName);
        param.put("custBriefName", custName);
        param.put("custType", "0");
        param.put("custIdTypeCode", "0");
        param.put("custIdNo", custIdNo);
        param.put("custContact", custName);
        param.put("custMobile", custMobile);
        param.put("mailAddress", mailAddress);
        param.put("registeredType", "2");
        param.put("custFlag", "1");

        //发票抬头
        param.put("invoiceName", custName);
        //领取方式
        param.put("invoiceType", "0");
        JSONObject result = request(param, "/custInterface.do?method=addCust", orderId, true, "创建普通用户");
        return result;
    }

    /**
     * 根据卡号获取卡片信息接口
     * 2.3.1
     *
     * @param cardNo
     * @param orderId
     * @return
     * @throws Exception
     */
    public static JSONObject getCardInfoByNo(String cardNo, String orderId) throws Exception {
        JSONObject param = new JSONObject();
        param.put("cardNo", cardNo);
        JSONObject result = request(param, "/cardInterface.do?method=getCardInfoByNo", orderId, true, "根据卡号获取卡片信息接口");
        return result;
    }

    /**
     * 获取卡片营销方案列表接口
     * 2.7.1
     *
     * @param carType
     * @param isTrunk
     * @param orderId
     * @return
     * @throws Exception
     */
    public static JSONObject getCardMarketMastList(String carType, String isTrunk, String orderId) throws Exception {
        JSONObject param = new JSONObject();
        param.put("carType", carType);
        param.put("isTrunk", isTrunk);
        param.put("cardType", "23");
        JSONObject result = request(param, "/marketMastInterface.do?method=getCardMarketMastList", orderId, true, "获取卡片营销方案列表");
        return result;
    }

    /**
     * 卡片发行申请接口
     * 2.3.4
     *
     * @param param
     * @param orderId
     * @return
     * @throws Exception
     */
    public static JSONObject addCardReleaseRequest(JSONObject param, String orderId) throws Exception {
        //转账
        param.put("isPos", "2");
        param.put("needTicket", "0");
        //后付费绑定记账卡
        param.put("gxCarType", "4");
        //记账卡
        param.put("cardType", "23");
        //营销方案ID  目前通过获取营销方案接口拿到ID 获取一次后直接写死 接口提供方说明
        if ("1".equals(param.getString("isTrunk"))) {
            param.put("marketMastId", getEtcMarketMastId());
        } else {
            param.put("marketMastId", getEtcMarketMastZxId());
        }

        JSONObject result = request(param, "/cardInterface.do?method=addCardReleaseRequest", orderId, true, "卡片发行申请");
        return result;
    }

    /**
     * 写卡片0015文件 2.6.1接口
     *
     * @param operationType        写卡片0015文件的业务操作类型。release:发行
     * @param initStr              初始化向量，4个字节
     * @param cardReleaseRequestId 卡片申请ID
     * @param orderId              订单id
     * @return
     * @throws Exception
     */
    public static JSONObject getMacFor0015File(String operationType, String initStr, String cardReleaseRequestId, String orderId) throws Exception {
        JSONObject param = new JSONObject();
        param.put("operationType", operationType);
        param.put("initStr", initStr);
        param.put("cardReleaseRequestId", cardReleaseRequestId);
        return request(param, "/macInterface.do?method=getMacFor0015File", orderId, true, "广西捷通写卡片0015文件,2.6.1接口");
    }


    /**
     * 2.6.2写卡片0016文件 2.6.2接口
     *
     * @param operationType        写卡片0016文件的业务操作类型。release:发行
     * @param initStr              初始化向量，4个字节
     * @param cardReleaseRequestId 卡片申请ID
     * @param orderId              订单id
     * @return
     * @throws Exception
     */
    public static JSONObject getMacFor0016File(String operationType, String initStr, String cardReleaseRequestId, String orderId) throws Exception {
        JSONObject param = new JSONObject();
        param.put("operationType", operationType);
        param.put("initStr", initStr);
        param.put("cardReleaseRequestId", cardReleaseRequestId);
        return request(param, "/macInterface.do?method=getMacFor0016File", orderId, true, "广西捷通写卡片0016文件,2.6.2接口");
    }

    /**
     * 2.6.3写卡片 001B 文件
     *
     * @param operationType        写卡片001B文件的业务操作类型。release:发行
     * @param initStr              初始化向量，8个字节
     * @param cardReleaseRequestId 卡片申请ID
     * @param orderId              订单id
     * @return
     * @throws Exception
     */
    public static JSONObject getMacFor001BFile(String operationType, String initStr, String cardReleaseRequestId, String orderId) throws Exception {
        JSONObject param = new JSONObject();
        param.put("operationType", operationType);
        param.put("initStr", initStr);
        param.put("cardReleaseRequestId", cardReleaseRequestId);
        return request(param, "/macInterface.do?method=getMacFor001BFile", orderId, true, "广西捷通写卡片001B文件,2.6.3接口");
    }

    /**
     * 2.6.4记账卡初始化卡片钱包文件（发行）
     *
     * @param operationType        写卡片钱包文件的业务操作类型。release:发行
     * @param cardSern             不可为空，卡交易序号，从外置读卡器读卡获取
     * @param challenge            不可为空，伪随机数，从外置读卡器读卡获取，4字节
     * @param mac1                 不可为空，4字节，从外置读卡器读卡获取
     * @param cardReleaseRequestId 卡片申请ID
     * @param orderId              订单id
     * @return
     * @throws Exception
     */
    public static JSONObject initCreditCardAmount(String operationType, String cardSern, String challenge,
                                                  String mac1, String cardReleaseRequestId, String loadTime, String orderId) throws Exception {
        JSONObject param = new JSONObject();
        param.put("operationType", operationType);
        param.put("cardSern", cardSern);
        param.put("cardBeforeBalance", "0");
        param.put("loadAmount", "2100000000");
        param.put("tranType", "02");
        param.put("challenge", challenge);
        param.put("mac1", mac1);
        param.put("loadTime", loadTime);
        param.put("cardReaderTermNo", getCardReadertermNo());
        param.put("cardReleaseRequestId", cardReleaseRequestId);
        return request(param, "/macInterface.do?method=initCreditCardAmount", orderId, true, "记账卡初始化卡片钱包文件发行");
    }

    /**
     * 2.3.3卡片发行确认接口
     *
     * @param cardNo               不能为空，卡号
     * @param cardReleaseRequestId 不能为空，卡片发行申请ID
     * @param orderId              订单id
     * @return
     * @throws Exception
     */
    public static JSONObject cardRelease(String cardNo, String cardReleaseRequestId, String orderId) throws Exception {
        JSONObject param = new JSONObject();
        param.put("cardNo", cardNo);
        param.put("cardReleaseRequestId", cardReleaseRequestId);
        return request(param, "/cardInterface.do?method=cardRelease", orderId, true, "卡片发行确认接口");
    }

    /**
     * 2.4.1根据OBU序列号获取OBU信息
     *
     * @param contractNo
     * @param orderId
     * @return
     * @throws Exception
     */
    public static JSONObject getObuMastByContractNo(String contractNo, String orderId) throws Exception {
        JSONObject param = new JSONObject();
        param.put("contractNo", contractNo);
        return request(param, "/obuInterface.do?method=getObuMastByContractNo", orderId, true, "根据OBU序列号获取OBU信息");
    }

    /**
     * 2.7.2 获取OBU营销方案列表接口
     *
     * @param carType 不能为空，车型，详见附录
     * @param isTrunk 不能为空，客货标识，详见附录
     * @param orderId 订单id
     * @return
     * @throws Exception
     */
    public static JSONObject getObuMarketMastList(String carType, String isTrunk, String orderId) throws Exception {
        JSONObject param = new JSONObject();
        param.put("carType", carType);
        param.put("isTrunk", isTrunk);
        return request(param, "/marketMastInterface.do?method=getObuMarketMastList", orderId, true, "获取OBU营销方案列表接口");
    }

    /**
     * 2.4.3OBU发行申请接口
     *
     * @param obuContractNo OBU合同序列号
     * @param cardNo        卡号
     * @param orderId
     * @return
     * @throws Exception
     */
    public static JSONObject addObuReleaseRequest(String obuContractNo, String cardNo, String orderId, String vehicleUserType) throws Exception {
        JSONObject param = new JSONObject();
        param.put("obuContractNo", obuContractNo);
        param.put("cardNo", cardNo);
        param.put("issueType", "20");
        param.put("isPos", "2");
        param.put("needTicket", "N");
        if (vehicleUserType.contains("专项")) {
            param.put("obuMarketMastId", getObuMarketMastZxId());
        } else {
            param.put("obuMarketMastId", getObuMarketMastId());
        }

        param.put("thirdPayOrderNo", "");
        param.put("orderDetailInfoId", "");
        return request(param, "/obuInterface.do?method=addObuReleaseRequest", orderId, true, "OBU发行申请接口");
    }

    /**
     * 2.6.5 写OBU的MF_EF01的有效期信息
     *
     * @param operationType       写卡片MFEF01文件的业务操作类型。release:发行
     * @param initStr             初始化随机数，8字节
     * @param obuReleaseRequestId Obu发行申请ID
     * @param orderId             订单id
     * @return
     * @throws Exception
     */
    public static JSONObject getMacForMFEF01File(String operationType, String initStr, String obuReleaseRequestId, String orderId) throws Exception {
        JSONObject param = new JSONObject();
        param.put("operationType", operationType);
        param.put("initStr", initStr);
        param.put("obuReleaseRequestId", obuReleaseRequestId);
        return request(param, "/macInterface.do?method=getMacForMFEF01File", orderId, true, "写OBU的MF_EF01的有效期信息");
    }

    public static JSONObject getMacForDF01EF01File(String operationType, String initStr, String obuReleaseRequestId, String orderId) throws Exception {
        JSONObject param = new JSONObject();
        param.put("operationType", operationType);
        param.put("initStr", initStr);
        param.put("obuReleaseRequestId", obuReleaseRequestId);
        return request(param, "/macInterface.do?method=getMacForDF01EF01File", orderId, true, "2.6.6写OBU的DF01_EF01文件");
    }

    /**
     * 2.4.4 OBU发行确认接口
     *
     * @param contractNo          不可为空,OBU合同序列号
     * @param obuReleaseRequestId 不可为空,obu发行申请ID
     * @param orderId             订单id
     * @return
     * @throws Exception
     */
    public static JSONObject obuRelease(String contractNo, String obuReleaseRequestId, String orderId) throws Exception {
        JSONObject param = new JSONObject();
        param.put("contractNo", contractNo);
        param.put("obuReleaseRequestId", obuReleaseRequestId);
        return request(param, "/obuInterface.do?method=obuRelease", orderId, true, "OBU发行确认接口");
    }

    /**
     * 2.5.2 图片补传接口
     *
     * @param base64     不可为空，图片Base64编码字符串。不包括头、CSS
     * @param fileType   不可为空，图片类型，参照“4.1图片类型”定义。具体到各张图片的类型,与“与上传图片接口”有区别。
     * @param recordType 记录类型：cust（创建客户、修改客户），card（发行卡片），obu（OBU激活）
     * @param recordId   cust：使用返回对象的 custMastId；card：使用返回对象的cardMastId,obu：使用返回对象的obuContractNo
     * @param orderId    订单id
     * @return
     * @throws Exception
     */
    public static JSONObject reUploadPhoto(String base64, String fileType, String recordType, String recordId, String orderId) throws Exception {
        JSONObject param = new JSONObject();
        param.put("base64", base64);
        param.put("fileType", fileType);
        param.put("recordType", recordType);
        param.put("recordId", recordId);
        return request(param, "/photoInterface.do?method=reUploadPhoto", orderId, true, "图片补传接口");
    }

    /**
     * 2.6.8 解密OBU车辆信息接口
     *
     * @param obuContractNo Obu序列号
     * @param data          密文数据
     * @param orderId       订单id
     * @return
     * @throws Exception
     */
    public static JSONObject decodeObuDF01EF01(String obuContractNo, String data, String orderId) throws Exception {
        JSONObject param = new JSONObject();
        param.put("obuContractNo", obuContractNo);
        param.put("data", data);
        return request(param, "/macInterface.do?method=decodeObuDF01EF01", orderId, true, "解密OBU车辆信息接口");
    }

    /**
     * 2.6.7 获取激活OBU密文接口
     *
     * @param obuContractNo Obu序列号
     * @param initStr       初始化随机数,4字节
     * @param orderId
     * @return
     * @throws Exception
     */
    public static JSONObject getMacForActivating(String obuContractNo, String initStr, String orderId) throws Exception {
        JSONObject param = new JSONObject();
        param.put("obuContractNo", obuContractNo);
        param.put("initStr", initStr);
        return request(param, "/macInterface.do?method=getMacForActivating", orderId, true, "获取激活OBU密文接口");
    }

    /**
     * 2.9.1 OBU激活确认接口
     *
     * @param obuContractNo 不能为空，Obu序列号
     * @param carNo         不能为空，车牌号码
     * @param carNoColor    不能为空，车牌颜色
     * @param obuMac        不能为空，obuMacc
     * @param orderId       订单id
     * @return
     * @throws Exception
     */
    public static JSONObject obuActivationConfirm(String obuContractNo, String carNo, String carNoColor, String obuMac, String orderId) throws Exception {
        JSONObject param = new JSONObject();
        param.put("obuContractNo", obuContractNo);
        param.put("carNo", carNo);
        param.put("carNoColor", carNoColor);
        param.put("obuMac", obuMac);
        param.put("activeTime", DateUtil.now());
        param.put("activeFlag", "0");
        param.put("picCL", "");
        param.put("picXSZ", "");
        param.put("picSFZZ", "");
        param.put("picSFZF", "");
        return request(param, "/obuActivation.do?method=obuActivationConfirm", orderId, true, "OBU激活确认接口");
    }

    /**
     * * 卡片免费更换申请接口
     * * 2.3.6
     *
     * @param newCardNo 新卡号
     * @param srcCardNo 旧卡号
     * @param orderId
     * @return
     * @throws Exception
     */
    public static JSONObject addCardFreeChangeRequest(String newCardNo, String srcCardNo, String orderId) throws Exception {
        JSONObject object = new JSONObject();
        object.put("newCardNo", newCardNo);
        object.put("srcCardNo", srcCardNo);
        JSONObject result = request(object, "/cardChangeInterface.do?method=addCardFreeChangeRequest", orderId, true, "卡片免费更换申请接口");
        return result;
    }

    /**
     * * 卡片免费更换确认接口
     * * 2.3.8
     *
     * @param newCardNo 新卡号
     * @param requestId 免费更换申请ID
     * @param orderId
     * @return
     * @throws Exception
     */
    public static JSONObject cardFreeChangeConfirm(String newCardNo, String requestId, String orderId) throws Exception {
        JSONObject object = new JSONObject();
        object.put("newCardNo", newCardNo);
        object.put("requestId", requestId);
        JSONObject result = request(object, "/cardChangeInterface.do?method=cardFreeChangeConfirm", orderId, true, "卡片免费更换确认接口");
        return result;
    }


    /**
     * * 2.4.6 OBU免费更换申请
     * *
     *
     * @param newObuNo 新卡号
     * @param srcObuNo 旧卡号
     * @param orderId
     * @return
     * @throws Exception
     */
    public static JSONObject obuFreeChangeRequest(String newObuNo, String srcObuNo, String orderId) throws Exception {
        JSONObject object = new JSONObject();
        object.put("newObuNo", newObuNo);
        object.put("srcObuNo", srcObuNo);
        JSONObject result = request(object, "/obuChangeInterface.do?method=obuFreeChangeRequest", orderId, true, "OBU免费更换申请");
        return result;
    }

    /**
     * * 2.4.8 OBU免费更换确认
     * *
     *
     * @param newObuNo  新OBU号
     * @param requestId 免费更换申请ID
     * @param orderId
     * @return
     * @throws Exception
     */
    public static JSONObject obuFreeChangeConfirm(String newObuNo, String requestId, String orderId) throws Exception {
        JSONObject object = new JSONObject();
        object.put("newObuNo", newObuNo);
        object.put("requestId", requestId);
        JSONObject result = request(object, "/obuChangeInterface.do?method=obuFreeChangeConfirm", orderId, true, "OBU免费更换确认");
        return result;
    }


    /**
     * * 2.11.5办理特殊业务通知
     * *
     *
     * @param orderId
     * @return
     * @throws Exception
     */
    public static JSONObject businessVoucher(String frontServerAccessNo, String cardNo, String businessStatus, String orderId) throws Exception {
        JSONObject object = new JSONObject();
        object.put("frontServerAccessNo", frontServerAccessNo);
        object.put("tradeNo", frontServerAccessNo);
        object.put("bankId", getBankId());
        object.put("netNodeId", getNetNodeId());
        object.put("operateTime", DateUtil.now());
        object.put("cardNo", cardNo);
        object.put("businessType", "2");
        object.put("businessStatus", businessStatus);
        JSONObject result = request(object, "/binding.do?method=businessVoucher", orderId, true, "办理特殊业务通知-注销");
        return result;
    }

    /**
     * * 2.3.12 卡片注销接口
     * *
     *
     * @param orderId
     * @return
     * @throws Exception
     */
    public static JSONObject cardCancel(String cardNo, String orderId) throws Exception {
        JSONObject object = new JSONObject();
        object.put("cardNo", cardNo);
        object.put("hasCard", "0");
        JSONObject result = request(object, "/cardInterface.do?method=cardCancel", orderId, true, "ETC-注销");
        return result;
    }

    /**
     * * 2.4.12 Obu注销
     * *
     *
     * @param orderId
     * @return
     * @throws Exception
     */
    public static JSONObject obuCancel(String contractNo, String orderId) throws Exception {
        JSONObject object = new JSONObject();
        object.put("contractNo", contractNo);
        object.put("hasCard", "0");
        JSONObject result = request(object, "/obuInterface.do?method=obuCancel", orderId, true, "OBU-注销");
        return result;
    }

    /**
     * 2.11.4卡片停用/启用申请
     *
     * @param frontServerAccessNo 交易流水号
     * @param bindingSerialNo     绑定流水号
     * @param optCode             1-停用 2-启用
     * @param cardNo              卡号
     * @param stopReason          原因
     * @return
     * @throws Exception
     */
    public static JSONObject cardStop(String frontServerAccessNo, String bindingSerialNo, String optCode, String cardNo, String stopReason, String orderId) throws Exception {
        JSONObject object = new JSONObject();
        object.put("frontServerAccessNo", frontServerAccessNo);
        object.put("tradeNo", frontServerAccessNo);
        object.put("bankId", getBankId());
        object.put("netNodeId", getNetNodeId());
        object.put("bindingSerialNo", bindingSerialNo);
        object.put("operateTime", DateUtil.now());
        object.put("optCode", optCode);
        object.put("cardNo", cardNo);
        object.put("stopReason", stopReason);
        JSONObject result = request(object, "/binding.do?method=cardStop", orderId, true, "卡片停用/启用");
        return result;
    }

    /**
     * 查询绑定关系
     *
     * @return
     * @throws Exception
     */
    public static JSONObject searchRelationship(String carNo, String carColor, String orderId) throws Exception {
        JSONObject object = new JSONObject();
        object.put("carNo", carNo);
        object.put("carColor", carColor);
        object.put("frontServerAccessNo", IdUtil.simpleUUID());
        object.put("tradeNo", object.getString("frontServerAccessNo"));
        object.put("bankId", getBankId());
        object.put("netNodeId", getNetNodeId());
        JSONObject result = request(object, "/binding.do?method=searchRelationship", orderId, true, "查询绑定关系");
        return result;
    }

    /**
     * * 2.11.2 解绑申请
     * *
     *
     * @param orderId
     * @return
     * @throws Exception
     */
    public static JSONObject bindingCancel(String bindingSerialNo, String orderId) throws Exception {
        JSONObject object = new JSONObject();
        object.put("frontServerAccessNo", IdUtil.simpleUUID());
        object.put("tradeNo", object.getString("frontServerAccessNo"));
        object.put("bankId", getBankId());
        object.put("netNodeId", getNetNodeId());
        object.put("bindingSerialNo", bindingSerialNo);
        object.put("operateTime", DateUtil.now());
        JSONObject result = request(object, "/binding.do?method=bindingCancel", orderId, true, "绑定关系-解绑申请");
        return result;
    }

    public static JSONObject request(JSONObject paraJson, String urlPath, String orderId, boolean hasImg, String name) throws Exception {
        paraJson.put("operatorName", getOperatorName());
        paraJson.put("terminalId", getTerminalId());
        paraJson.put("manufacturerId", getManufacturerId());
        if (!isNeedToken(urlPath)) {
//            paraJson.put("authToken", "FM0002-20210419110238364068");
            JSONObject object = login();
            if ("true".equals(object.getString("ajaxTrage"))) {
                paraJson.put("authToken", object.getString("token"));
            } else {
                paraJson.put("ajaxMsg", object.getString("ajaxMsg"));
                paraJson.put("ajaxTrage", object.getString("ajaxTrage"));
                return paraJson;
            }
        }

        String url = "";
        if (urlPath.contains("IssueReactor")) {
            url = getCapitalUrl() + urlPath;
        } else {
            url = getUrl() + urlPath;
        }
        if (!urlPath.contains("reUploadPhoto")) {
            logger.error("请求url:" + url);
            logger.error("请求参数:" + paraJson);
        }

        JSONObject result = HttpsUtils.doSslPost(url, paraJson, "UTF-8");
        logger.error("请求结果:" + result);
        if (null != result && RETURN_FAIN_NAME.equals(result.getString(RETURN_CODE_NAME))) {
            //如果是登录失效
            if (result.getString(RETURN_MSG_NAME).contains("-1001")) {

                //重试三次
                Long jtRetriesCount = redisDaoImpl.increment(JT_TOKEN_RETRIES_COUNT, 1);
                if (jtRetriesCount == 1) {
                    redisDaoImpl.expire(JT_TOKEN_RETRIES_COUNT, 1000 * 60 * 60 * 24);
                }
                if (jtRetriesCount > 3) {
                    paraJson.put("ajaxMsg", "捷通获取Token失败,请联系客服处理!");
                    paraJson.put("ajaxTrage", false);
                    return result;
                }

                redisDaoImpl.deleteObject("JT_TOKEN");
                result = request(paraJson, urlPath, orderId, hasImg, name);
            }
        }
        paraJson.put("url", url);
        paraJson.put("orderId", orderId);
        saveJTRequestApiLog(paraJson, result, orderId, urlPath, hasImg, name);
        return result;
    }

    public static Boolean isNeedToken(String url) {
        if ("/login.do?method=login".equals(url) || "/admin.do?method=modifyUserPwd".equals(url)) {
            return true;
        } else {
            return false;
        }

    }

    private static void saveJTRequestApiLog(Map<String, Object> paraJson, JSONObject result,
                                            String orderId, String urlPath, boolean hasImg, String name) {
        // 保存日志
        final Map<String, Object> paraPd = new HashMap<>();
        paraPd.put("result", result == null ? "" : result);
        paraPd.put("paraJson", paraJson == null ? "" : paraJson);
        paraPd.put("orderId", orderId);
        paraPd.put("urlPath", urlPath);
        paraPd.put("name", name);
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    if (hasImg) {
                        if (paraJson != null) {
                            paraJson.remove("image");
                            paraJson.remove("sign");
                            paraJson.remove("base64");

                            paraPd.put("paraJson", paraJson);
                        }
                    }
                    paraPd.put("a_id", IdUtil.simpleUUID());
                    paraPd.put("a_createTime", DateUtil.now());
                    MongodbUtils.save(paraJson, MONGODB_JT_TABLE);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }

            }
        });
    }


    /**
     * 生成流水号
     *
     * @return
     */
    public static String generateBindingSerialNo() {
        String date = DateUtil.format(new Date(), "YYYYMMdd");

        String bindingSerialNo = "025" + date + RandomUtil.randomInt(10);
        return bindingSerialNo;
    }

//    private static JSONObject checkReturnContent(JSONObject result) {
//        String msg = result.getString("ajaxMsg");
//        if (!(msg.contains("-1001") || msg.contains("-1002") || msg.contains("-1003"))) {
//            return null;
//        }
//        JSONObject checkResult = new JSONObject();
//        RedisDao redisDaoImpl = (RedisDao) ServiceHelper.getService("redisDaoImpl");
//        Integer i = Integer.parseInt(redisDaoImpl.get("FALSE_COUNT"));
//        if (null == i) {
//            redisDaoImpl.addString("FALSE_COUNT", "1");
//            redisDaoImpl.expire("FALSE_COUNT", 60 * 60 * 1000);
//        } else {
//            if (i > 3) {
//                checkResult.put("ajaxTrage", false);
//                return checkResult;
//            }
//            redisDaoImpl.increment("FALSE_COUNT", 1);
//        }
//        checkResult.put("ajaxTrage", true);
//        redisDaoImpl.delete("JT_TOKEN");
//        if (msg.contains("-1002") || msg.contains("-1003")) {
//            redisDaoImpl.delete("JT_VERSION");
//        }
//        return checkResult;
//    }

    private static String getUrl() {
        if (THIS_ACTIVE.equals(ACTIVE_PROD_NAME)) {
            return URL_PRO;
        }
        return URL_DEV;
    }

    private static String getOperatorName() {
        if (THIS_ACTIVE.equals(ACTIVE_PROD_NAME)) {
            return OPERATOR_NAME_PRO;
        }
        return OPERATOR_NAME_DEV;
    }

    private static String getOperatorPassword() {
        if (THIS_ACTIVE.equals(ACTIVE_PROD_NAME)) {
            return OPERATOR_PASSWORD_PRO;
        }
        return OPERATOR_PASSWORD_DEV;
    }

    private static String getTerminalId() {
        if (THIS_ACTIVE.equals(ACTIVE_PROD_NAME)) {
            return TERMINAL_ID_PRO;
        }
        return TERMINAL_ID_DEV;
    }

    private static String getManufacturerId() {
        if (THIS_ACTIVE.equals(ACTIVE_PROD_NAME)) {
            return MANUFACTURER_ID_PRO;
        }
        return MANUFACTURER_ID_DEV;
    }

    private static String getBankId() {
        if (THIS_ACTIVE.equals(ACTIVE_PROD_NAME)) {
            return BANK_ID_PRO;
        }
        return BANK_ID_DEV;
    }

    private static String getNetNodeId() {
        if (THIS_ACTIVE.equals(ACTIVE_PROD_NAME)) {
            return NET_NODE_ID_PRO;
        }
        return NET_NODE_ID_DEV;
    }

    private static String getSoftVersion() {
        if (THIS_ACTIVE.equals(ACTIVE_PROD_NAME)) {
            return SOFT_VERSION_PRO;
        }
        return SOFT_VERSION_DEV;
    }

    private static String getCardReadertermNo() {
        if (THIS_ACTIVE.equals(ACTIVE_PROD_NAME)) {
            return CARD_READERTERM_NO_PRO;
        }
        return CARD_READERTERM_NO_DEV;
    }

    private static String getCapitalUrl() {
        if (THIS_ACTIVE.equals(ACTIVE_PROD_NAME)) {
            return CAPITAL_URL_PRO;
        }
        return CAPITAL_URL_DEV;
    }

    private static String getEtcMarketMastId() {
        if (THIS_ACTIVE.equals(ACTIVE_PROD_NAME)) {
            return ETCMARKETMASTID_PRO;
        }
        return ETCMARKETMASTID_DEV;
    }

    private static String getEtcMarketMastZxId() {
        if (THIS_ACTIVE.equals(ACTIVE_PROD_NAME)) {
            return ETCMARKETMASTID_ZX_PRO;
        }
        return ETCMARKETMASTID_ZX_DEV;
    }

    private static String getObuMarketMastId() {
        if (THIS_ACTIVE.equals(ACTIVE_PROD_NAME)) {
            return OBUMARKETMASTID_PRO;
        }
        return OBUMARKETMASTID_DEV;
    }

    private static String getObuMarketMastZxId() {
        if (THIS_ACTIVE.equals(ACTIVE_PROD_NAME)) {
            return OBUMARKETMASTID_ZX_PRO;
        }
        return OBUMARKETMASTID_ZX_DEV;
    }

}
