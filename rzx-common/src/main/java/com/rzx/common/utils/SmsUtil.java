package com.rzx.common.utils;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.rzx.common.exception.CustomException;
import com.rzx.common.utils.uuid.IdUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


/**
 * 发送短信工具类
 *
 * @author lhc
 */
@Slf4j
public class SmsUtil {

    /**
     * 短信内容前缀
     **/
    public final static String SMSPrefix_LOAD = "司机贷";

    /**
     * 至臻短信发送地址
     */
    private final static String ZZDX_SENDURL = "http://115.28.112.245:8082/SendMT/SendMessage";

    /**
     * 至臻短信帐号
     */
    private final static String ZZDX_USERNAME = "jdbyz";

    /**
     * 至臻短信密码
     */
    private final static String ZZDX_PASSWORD = "jdbyz321";

    /**
     * 至臻短信扩展号
     */
    private final static String ZZDX_SUBID = "67449";

    public static Map<String, String> sendSms(String mobile, String smsContentTemplate, String smsPrefix, String... smsContents) {
        String content = MessageFormat.format(smsContentTemplate, (Object) smsContents);
        try {
            return sendZZHLSMS2(content, mobile, smsPrefix);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
            throw new CustomException("短信发送失败！！");
        }
    }

    /**
     * 发送短信接口--至臻互联短信
     *
     * @param smsContent
     * @param mobile     手机号码
     * @param smsPrefix  内容前缀
     * @throws UnsupportedEncodingException
     */
    public static Map<String, String> sendZZHLSMS2(final String smsContent, final String mobile, String smsPrefix) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(smsPrefix)) {
            smsPrefix = SMSPrefix_LOAD;
        }
        String content = smsPrefix + smsContent;

        String message = URLEncoder.encode(content, "UTF-8");
        String resultMessage = httpGetSend(ZZDX_SENDURL + "?" + "UserName=" + ZZDX_USERNAME + "&UserPass=" + ZZDX_PASSWORD + "&Content=" + message + "&Mobile=" + mobile + "&Subid=" + ZZDX_SUBID, "UTF-8");
        log.error("sendSMS res:" + resultMessage);
        if (!StringUtils.isEmpty(resultMessage)) {
            String[] str = resultMessage.split(",");
            if ("00".equals(str[0]) || "03".equals(str[0])) {
                log.info("sendSMS success!");
            } else {
                log.error("sendSMS error:[00:多个手机号请求发送成功,02:IP限制,03:单个手机号请求发送成功,"
                        + "04:用户名错误,05:密码错误,06:编码错误,08:参数错误,09:手机号码有误,10:扩展号码有误,"
                        + "11:余额不足,-1:服务器内部异常]:" + "错误码=" + str[0]);
            }
            // 保存发送短信记录
            Map<String, String> params = new HashMap<String, String>(8);
            params.put("id", IdUtils.fastSimpleUUID());
            params.put("content", content);
            params.put("mobile", mobile);
            params.put("createTime", LocalDateTimeUtil.format(LocalDateTime.now(), DateUtils.YYYY_MM_DD_HH_MM_SS));
            params.put("status", str[0]);
            return params;
        }
        return null;
    }

    public static String httpGetSend(String snedUrl, String encoded) {
        System.out.println(snedUrl);
        StringBuilder sbf = new StringBuilder("");
        BufferedReader reader = null;
        HttpURLConnection uc = null;

        try {
            URL url = new URL(snedUrl);
            uc = (HttpURLConnection) url.openConnection();
            uc.setConnectTimeout(30000);
            uc.setReadTimeout(30000);
            uc.setRequestMethod("GET");
            uc.setDoOutput(true);
            uc.setDoInput(true);

            uc.connect();
            // 读取服务器响应信息
            reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                sbf.append(line);
            }
            reader.close();
            uc.disconnect();
            return sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

//    public static void main(String[] arg) {
//        sendSms("15697360503","尊敬的用户,验证码为:5608,有效时间为:10分钟", "", "【司机贷】");
//    }
}
