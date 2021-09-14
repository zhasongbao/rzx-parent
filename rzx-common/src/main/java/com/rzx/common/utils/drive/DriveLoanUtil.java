//package com.loan.common.utils.drive;
//
//import cn.hutool.core.date.DatePattern;
//import cn.hutool.core.date.LocalDateTimeUtil;
//import cn.hutool.core.util.CharsetUtil;
//import cn.hutool.crypto.SecureUtil;
//import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
//import cn.hutool.crypto.symmetric.SymmetricCrypto;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.loan.common.configuration.DriveLoanConfiguration;
//import com.loan.common.utils.http.HttpUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import java.time.LocalDateTime;
//
///**
// * 司机贷工具类
// *
// * @author zy
// * @date 2021/05/12
// */
//@Component
//public class DriveLoanUtil {
//    private final static Logger LOGGER = LoggerFactory.getLogger(DriveLoanUtil.class);
//
//    @Autowired
//    private DriveLoanConfiguration driveLoanConfiguration;
//
//    //测试地址
//    public static final String openApiUrl="https://2923423q20.goho.co/openapi/rest";
////    public static final String openApiUrl="https://59.41.197.99";
//    public static final String appid = "284402970861375488";
//    public static final String session = "aff2c1ce387446178341584f77719a29";
//    public static final String secretkey = "7a55d6448913431b";
//    public static final String FORMAT = "json";
//    public static final String VERSION = "2.0";
//    public static final String OPEN_API_URL = "https://59.41.197.99:7385/openapi/rest";
//    //接入配置
////    public static final String APPID = "261980336098004992";
////    public static final String SESSION = "11e7d141a5674502b051acf3b37fbd71";
////    public static final String SECRET_KEY = "947a46ed894946e0";
//
//    public static String getDateToString(LocalDateTime now) {
//        return LocalDateTimeUtil.format(now, DatePattern.NORM_DATETIME_MINUTE_PATTERN);
//    }
//
//    /**
//     * 司机贷接口数据获取
//     *
//     * @param data data
//     * @return 成功返回案例：
//     * {"ret":0,"data":{"acctStatus":"UN_OPEN","reqNo":"20210324094342940","respNo":"YM20331026085058015000000016435"},"success":true,"message":"成功"}
//     * 失败返回案例：
//     * {"ret":17,"message":"参数[idNo]企业名称不能为空"}
//     * {"ret":-1,"success":false,"message":"程序处理错误,请稍后重试或联系客服人员"}
//     * 确定成功返回判断：json里有success字段且值为true
//     */
//    public String request(JSONObject data, String methodName) {
//        String secretKey = driveLoanConfiguration.getSecretKey();
//        String session = driveLoanConfiguration.getSession();
//        String appId = driveLoanConfiguration.getAppId();
//
//        //构建
//        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, secretKey.getBytes());
//        //加密
//        String encryptData = aes.decryptStr(aes.encrypt(JSON.toJSONString(data)), CharsetUtil.CHARSET_UTF_8);
//        String timestamp = getDateToString(LocalDateTime.now());
//        //待签名字符串
//        String sing = secretKey + appId + encryptData + FORMAT + methodName + session + timestamp + VERSION + secretKey;
//
//        String signResult = SecureUtil.md5(sing);
//
//        JSONObject requestObj = new JSONObject();
//        requestObj.put("appid", appid);
//        requestObj.put("method", methodName);
//        requestObj.put("session", DriveLoanUtil.session);
//        requestObj.put("format", FORMAT);
//        requestObj.put("data", encryptData);
//        requestObj.put("v", VERSION);
//        requestObj.put("timestamp", timestamp);
//        requestObj.put("sign", signResult);
//        return HttpUtils.sendSSLPost(openApiUrl, requestObj.toJSONString());
//    }
//
//}
