package com.rzx.app.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rzx.common.core.controller.BaseController;
import com.rzx.common.utils.StringUtils;
import com.rzx.project.facade.vo.NotifyVerifyResult;
import com.rzx.project.service.IPayBizService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import org.apache.http.util.ByteArrayBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 支付回调 Controller
 *
 * @author zy
 * @date 2021/10/8 13:56
 */
@Api(value = "支付回调接口")
@ApiModel(value = "支付回调Controller")
@RequestMapping(value = "/payNotify")
@RestController
public class PayNotifyController extends BaseController {
    @Autowired
    private IPayBizService payBizService;

    /**
     * 异步回调
     *
     * @param payChannelCode 支付渠道编码
     * @param bizSourceCode  支付业务编码,通过业务编码找到对应的支付订单
     * @param response
     * @param request
     */
    @RequestMapping(value = "/notify/{payChannelCode}/{bizSourceCode}")
    public void notify(@PathVariable("payChannelCode") String payChannelCode, @PathVariable("bizSourceCode") String bizSourceCode, HttpServletResponse response,
                       HttpServletRequest request) {

        logger.info("进入异步支付回调方法，payChannelCode：{}, bizSourceCode：{}", payChannelCode, bizSourceCode);

        Map<String, Object> parameterMap = getParameterMap(request);
        logger.info("通道方异步通知返回结果{}", parameterMap);
        if (CollectionUtil.isEmpty(parameterMap)) {
            parameterMap = new HashMap<>(16);
            String inputStreamToString = getRequestInputStreamToString(request);
            JSONObject reqJson = JSON.parseObject(inputStreamToString);
            parameterMap.putAll(reqJson);
            parameterMap.put("bankChannelReturnMsg", inputStreamToString);
            parameterMap.put("headers", getRequestHeadToString(request));
            logger.info("通道方异步通知返回结果{}", inputStreamToString);
        }
        //验签
        NotifyVerifyResult verifyResult = payBizService.notifyVerify(parameterMap, payChannelCode);
        //验签成功
        if (verifyResult.getIsVerify()) {
            try {
                boolean isSusses = payBizService.completePay(verifyResult, parameterMap, bizSourceCode);
                logger.info("订单{} 业务处理结果为：{}", verifyResult.getOutTradeNo(), isSusses);
                if (StringUtils.isNotEmpty(verifyResult.getResponseStr()) && isSusses) {
                    logger.info("回写上游信息:{}", verifyResult.getResponseStr());
                    response.getWriter().print(verifyResult.getResponseStr());
                } else  {
                    logger.info("回写上游信息失败:{}", verifyResult.getResponseStr());
                }

            } catch (IOException e) {
                logger.error("系统异常", e);
            }
        } else {
            logger.info("第三方返回数据验签失败");
        }
    }

    /**
     * @param request
     * @return
     */
    private String getRequestInputStreamToString(HttpServletRequest request) {
        String param = null;
        try {
            ServletInputStream in = request.getInputStream();
            param = readStringFromInputStream(in);
        } catch (IOException e) {
            logger.error("get fast pay inputsteam exception :", e);
        }
        return param;
    }

    private String readStringFromInputStream(InputStream is) throws IOException {
        byte[] buf = new byte[4096];
        int len = 0;
        ByteArrayBuffer bytes = new ByteArrayBuffer(4096);

        while (true) {
            len = is.read(buf);
            if (len >= 0) {
                bytes.append(buf, 0, len);
            } else {
                break;
            }
        }
        return new String(bytes.toByteArray(), StandardCharsets.UTF_8);
    }

    /**
     * 从Header部分取
     *
     * @param request
     * @return
     */
    public String getRequestHeadToString(HttpServletRequest request) {
        // Header部分
        logger.info("getHeaderNames:" + request.getHeaderNames());
        Enumeration<?> enum1 = request.getHeaderNames();
        JSONObject object = new JSONObject();
        while (enum1.hasMoreElements()) {
            String key = (String) enum1.nextElement();
            String value = request.getHeader(key);
            object.put(key, value);
        }
        logger.info("headers:" + object);
        return object.toString();
    }

    /**
     * 将请求参数封装成一个Map
     *
     * @return
     */
    private Map<String, Object> getParameterMap(HttpServletRequest request) {

        // 参数Map
        Map<String, String[]> properties = request.getParameterMap();
        // 返回值Map
        Map<String, Object> returnMap = new HashMap<String, Object>(16);
        Iterator<Map.Entry<String, String[]>> entries = properties.entrySet().iterator();
        Map.Entry<String, String[]> entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = entries.next();
            name = entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else {
                String[] values = (String[]) valueObj;
                for (String s : values) {
                    value = s + ",";
                }
                value = value.substring(0, value.length() - 1);
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }
}
