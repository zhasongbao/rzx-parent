package com.rzx.project.pay.yunzhuo;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rzx.common.config.WeiXinConfig;
import com.rzx.common.constant.Constants;
import com.rzx.common.exception.CustomException;
import com.rzx.common.utils.BigDecimalUtils;
import com.rzx.common.utils.plutuspay.AesEncryptionUtil;
import com.rzx.common.utils.plutuspay.Http;
import com.rzx.common.utils.spring.SpringUtils;
import com.rzx.project.model.domain.PayChannelCode;
import com.rzx.project.facade.dto.CnpPayPreParam;
import com.rzx.project.facade.dto.CnpRefundParam;
import com.rzx.project.facade.dto.OrderQueryParam;
import com.rzx.project.facade.dto.RefundQueryParam;
import com.rzx.project.service.ICommonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 云卓支付
 *
 * @author zy
 * @date 2021/5/12 14:45
 */
@Slf4j
public class YunZhuoPayUtils {

    /**
     * 云卓小程序支付
     *
     * @param payPreParam 请求参数
     * @param channel     渠道对象
     * @return
     */
    public static Map<String, String> appPay(CnpPayPreParam payPreParam, PayChannelCode channel) {

        String sn = payPreParam.getSubMerchantNo();
        if (StringUtils.isEmpty(sn)) {
            ICommonService commonService = SpringUtils.getBean(ICommonService.class);
            sn = commonService.getYZPaySN();
            payPreParam.setSubMerchantNo(sn);
        }
        //元转成分
        Long amount = BigDecimalUtils.multiply(payPreParam.getPayAmount(), BigDecimal.valueOf(100)).longValue();
        WeiXinConfig weiXinConfig = SpringUtils.getBean(WeiXinConfig.class);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appId", weiXinConfig.getAppid());
        jsonObject.put("openId", payPreParam.getOpenId());
        jsonObject.put("outTradeId", payPreParam.getOutTrxNo());
        jsonObject.put("isMiniProgram", true);
        jsonObject.put("tradeAmount", amount);
        jsonObject.put("payTypeId", 1003);
        jsonObject.put("sn", sn);
        String notifyUrl = channel.getNotifyUrl() + payPreParam.getPayChannelCode() + "/" + payPreParam.getBizSourceCode();
        if (Constants.OUT_PAY_FLAG.equals(channel.getRemark())) {
            notifyUrl = channel.getNotifyUrl();
        }
        jsonObject.put("notifyUrl", notifyUrl);
        jsonObject.put("remark", payPreParam.getOrderName());
        log.info("云卓支付请求参数为：{}", jsonObject);

        String param = AesEncryptionUtil.encryption(jsonObject.toString());
        String str = Http.post(channel.getTradeRequestUrl(), param);

        Assert.hasText(str, "调用云卓支付返回结果异常！");

        JSONObject json = JSON.parseObject(str);

//        log.info("云卓支付返回结果：{}", json);
        String code = json.getString("code");
        Assert.state("0".equals(code), json.getString("msg"));

        String signature = json.getString("signature");
        String content = json.getString("content");
        // 验签
        boolean verify = AesEncryptionUtil.verify256(content, Base64.decode(signature));
        Assert.state(verify, "云卓验签失败！！");
        // 解密
        byte[] bb = AesEncryptionUtil.decrypt(Base64.decode(content), AesEncryptionUtil.SECRET_KEY);
        Assert.notNull(bb, "支付异常！");
        Map<String, String> map = new HashMap<>(8);
        if (StrUtil.isNotEmpty(new String(bb))) {
            JSONObject object = JSON.parseObject(new String(bb));
            if (Objects.isNull(object) || !object.containsKey("payInfo")) {
                throw new CustomException("云卓pay解析失败！！");
            }
            JSONObject payInfo = JSON.parseObject(object.getString("payInfo"));
            String appId = payInfo.getString("appId");
            String nonceStr = payInfo.getString("nonceStr");
            String timeStamp = payInfo.getString("timeStamp");
            String paySign = payInfo.getString("paySign");
            String packageStr = payInfo.getString("package");
            String signType = payInfo.getString("signType");
            map.put("appId", appId);
            map.put("nonceStr", nonceStr);
            map.put("timeStamp", timeStamp);
            map.put("paySign", paySign);
            map.put("package", packageStr);
            map.put("signType", signType);
        }
        return map;
    }

    /**
     * 订单查询
     *
     * @param orderQueryParam
     * @param payChannelCode
     * @return
     */
    public static Map<String, Object> orderQuery(OrderQueryParam orderQueryParam, PayChannelCode payChannelCode) {

        String sn = orderQueryParam.getSubMerchantNo();
        net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
        jsonObject.put("outTradeId", orderQueryParam.getOutTrxNo());
        jsonObject.put("sn", sn);
        String param = AesEncryptionUtil.encryption(jsonObject.toString());

        String str = Http.post(payChannelCode.getTradeQueryUrl(), param);
        Assert.hasText(str, "云卓支付查询返回结果异常！");
        JSONObject json = JSON.parseObject(str);

        String signature = json.getString("signature");
        String content = json.getString("content");
        // 验签
        boolean verify = AesEncryptionUtil.verify256(content, Base64.decode(signature));
        Assert.state(verify, "云卓验签失败！！");
        // 解密
        byte[] bb = AesEncryptionUtil.decrypt(Base64.decode(content), AesEncryptionUtil.SECRET_KEY);
        Assert.notNull(bb, "支付异常！");
        return JSONObject.parseObject(new String(bb));
    }

    /**
     * 订单退款
     *
     * @param cnpRefundParam 退款请求参数
     * @param payChannelCode 退款渠道配置
     * @return
     */
    public static Map<String, Object> refund(CnpRefundParam cnpRefundParam, PayChannelCode payChannelCode) {
        String sn = cnpRefundParam.getSubMerchantNo();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("outTradeId", cnpRefundParam.getOutTrxNo());
        jsonObject.put("outRefundId", cnpRefundParam.getRefundOutTrxNo());
        jsonObject.put("refundAmount", Convert.toInt(BigDecimalUtils.yuanToCent(cnpRefundParam.getPayAmount())));
        jsonObject.put("sn", sn);

        String param = AesEncryptionUtil.encryption(jsonObject.toString());

        String str = Http.post(payChannelCode.getRefundUrl(), param);
        Assert.hasText(str, "云卓退款发起返回结果异常！");
        JSONObject json = JSON.parseObject(str);
        log.info("云卓退款发起返回结果：{}", json);

        String signature = json.getString("signature");
        String content = json.getString("content");
        // 验签
        boolean verify = AesEncryptionUtil.verify256(content, Base64.decode(signature));
        Assert.state(verify, "云卓退款验签失败！！");
        // 解密
        byte[] bb = AesEncryptionUtil.decrypt(Base64.decode(content), AesEncryptionUtil.SECRET_KEY);
        Assert.notNull(bb, "退款异常！");
        return JSONObject.parseObject(new String(bb));
    }

    /**
     * 订单查询
     *
     * @param refundQueryParam 退款请求参数
     * @param payChannelCode 退款渠道配置
     * @return
     */
    public static Map<String, Object> refundQuery(RefundQueryParam refundQueryParam, PayChannelCode payChannelCode) {
        String sn = refundQueryParam.getSubMerchantNo();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("outRefundId", refundQueryParam.getOutRefundNo());
        jsonObject.put("sn", sn);

        String param = AesEncryptionUtil.encryption(jsonObject.toString());

        String str = Http.post(payChannelCode.getRefundQueryUrl(), param);
        Assert.hasText(str, "云卓退款查询返回结果异常！");
        JSONObject json = JSON.parseObject(str);
        log.info("云卓退款查询返回结果：{}", json);

        String signature = json.getString("signature");
        String content = json.getString("content");
        // 验签
        boolean verify = AesEncryptionUtil.verify256(content, Base64.decode(signature));
        Assert.state(verify, "云卓退款查询验签失败！！");
        // 解密
        byte[] bb = AesEncryptionUtil.decrypt(Base64.decode(content), AesEncryptionUtil.SECRET_KEY);
        Assert.notNull(bb, "退款查询异常！");
        return JSONObject.parseObject(new String(bb));
    }

    /**
     * 请求订单二维码  C扫B支付
     * @param orderQueryParam
     * @param channel
     * @return
     * @throws Exception
     */
    public static Map<String, String> preCreate(CnpPayPreParam orderQueryParam, PayChannelCode channel) {
        String sn = orderQueryParam.getSubMerchantNo();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sn", sn);
        jsonObject.put("outTradeId", orderQueryParam.getOutTrxNo());
        jsonObject.put("tradeAmount", orderQueryParam.getPayAmount());
        jsonObject.put("cashierType", 2);
        // 支付方式1003微信、1004支付宝、1010银联扫码
        jsonObject.put("payTypeId", 1003);
        jsonObject.put("notifyUrl", channel.getNotifyUrl());
        jsonObject.put("remark", channel.getRemark());
        System.out.println(jsonObject);
        String param = AesEncryptionUtil.encryption(jsonObject.toString());

        System.out.println(param);
        String str = Http.post(channel.getTradeRequestUrl(), param);
        System.out.println(str);
        JSONObject json = JSON.parseObject(str);
        String signature = json.getString("signature");
        String content = json.getString("content");
        // 验签
        Boolean verify = AesEncryptionUtil.verify256(content, Base64.decode(signature));
        // 验签成功
        if (verify) {
            // 解密
            byte[] bb = AesEncryptionUtil.decrypt(Base64.decode(content), AesEncryptionUtil.SECRET_KEY);
            // 服务器返回内容
            System.out.println("业务数据:" + new String(bb));
            if (org.apache.commons.lang.StringUtils.isNotEmpty(new String(bb))) {
                JSONObject ifjson = JSON.parseObject(new String(bb));
                if (! ObjectUtils.isEmpty(ifjson)) {
                    Map<String, String> returnMap = new HashMap<String, String>();
                    returnMap.put("status", ifjson.getString("status"));
                    returnMap.put("error", ifjson.getString("error"));
                    returnMap.put("tradeNO", ifjson.getString("tradeNO"));
                    returnMap.put("payTypeId", ifjson.getString("payTypeId"));
                    returnMap.put("qrCode", ifjson.getString("qrCode"));
                    return returnMap;
                }
            }
        }
        return null;
    }

    /**
     * 验签
     *
     * @param paramMap
     * @return
     */
    public static boolean signVerify(Map<String, Object> paramMap) {
        log.info(">>>>>>进入云卓验签方法,未处理参数：{}", paramMap);
        String signature = paramMap.get("signature").toString();
        String content = paramMap.get("content").toString();
        // 验签
        return AesEncryptionUtil.verify256(content, Base64.decode(signature));
    }
}
