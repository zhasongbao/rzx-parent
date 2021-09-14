package com.rzx.common.utils.ocr;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rzx.common.enums.HttpMethod;
import com.rzx.common.utils.StringUtils;
import com.rzx.common.utils.http.HttpUtil;
import com.rzx.common.utils.ocr.vo.BankCardOcrVO;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

/**
 * OCR识别工具类
 *
 * @author zy
 * @date 2021/6/15 14:28
 */
public class OCRUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(OCRUtils.class);

    private final static String API_APPCODE = "5aa6f1f7c0b540628c73a56833e590ee";
    // private static String api_appcode =
    // Constants.getValue(Constants.ALI_OCRAPI_APPCODE);
    private final static String API_HOST_CARBABY = "https://cardpack.market.alicloudapi.com";
    private final static String API_HOST_FENMI = "https://dm-53.data.aliyun.com";
    private final static String API_VEHICLE_URL = "/rest/160601/ocr/ocr_vehicle.json";
    private final static String API_ID_CARD_URL = "/rest/160601/ocr/ocr_idcard.json";
    private final static String API_DRIVER_LICENSE_URL = "/rest/160601/ocr/ocr_driver_license.json";

    /**
     * 银行卡ocr请求地址
     */
    private final static String API_BANK_CARD_URL = "/rest/160601/ocr/ocr_bank_card.json";


    private final static String API_BUSINESS_LICENSE_URL = "/rest/160601/ocr/ocr_business_license.json";

    /**
     * 图形识别
     *
     * @param imgFile 图片地址
     * @param apiHost api端口
     * @param apiPath 地址
     * @return
     */
    public static JSONObject ocrRecognition(String imgFile, String apiHost, String apiPath) {
        // 如果文档的输入中含有inputs字段，设置为True， 否则设置为False
//        boolean isOldFormat = false;

        Map<String, String> headers = new HashMap<String, String>(6);
        // 最后在header中的格式(中间是英文空格)为Authorization:APPCODE
        // 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + API_APPCODE);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");

        Map<String, String> queryMap = new HashMap<String, String>(2);

        Map<String, String> bodys = new HashMap<String, String>(2);
        bodys.put("image", getImgBase64(imgFile));

        String result = null;
        try {
            HttpResponse response = null;
            response = HttpUtil.doPost(getApiHost(), apiPath, HttpMethod.POST.toString(), headers, queryMap, JSON.toJSONString(bodys));

            Assert.notNull(response, "图像识别失败,请上传清晰图片或者图片非法");
            result = EntityUtils.toString(response.getEntity());
            Assert.state(StringUtils.isNotEmpty(result), "图像识别失败");
        } catch (Exception e) {
            LOGGER.error("图像失败异常：{},{}", e.getMessage(), e);
        }
        JSONObject object = JSONObject.parseObject(result);
        Boolean success = object.getBoolean("success");
        Assert.state(success, "图像识别失败");
        return object;
    }

    /**
     * 获取端口
     *
     * @return
     */
    public static String getApiHost() {
        String api = "6e3ec342f205473c871cffc2fb9c1da6";
        if (api.equals(API_APPCODE)) {
            return API_HOST_FENMI;
        }
        return API_HOST_CARBABY;
    }

    /**
     * 转成base64
     *
     * @param imgFile
     * @return
     */
    public static String getImgBase64(String imgFile) {
        // 对图像进行base64编码
        InputStream is = FileUtil.getInputStream(imgFile);
        byte[] content = IoUtil.readBytes(is);
        return new String(encodeBase64(content));
    }

    /**
     * 银行卡ocr
     *
     * @param bankImgUrl 银行卡地址
     * @return
     */
    public static BankCardOcrVO bankCardOcr(String bankImgUrl) {
        JSONObject object = ocrRecognition(bankImgUrl, getApiHost(), API_BANK_CARD_URL);
        Assert.state(StringUtils.isNotEmpty(object.getString("card_num")), "银行卡识别失败,请重新上传");
        return new BankCardOcrVO(object.getString("card_num"), object.getString("bank_name"));
    }

//    public static void main(String[] args) {
//        System.out.println(bankCardOcr("https://huochebaoimg.oss-cn-shenzhen.aliyuncs.com/images/files/2020/12/31/ad2cb9c0d89247b28bf154f42e2e9549.jpg"));
//    }
}
