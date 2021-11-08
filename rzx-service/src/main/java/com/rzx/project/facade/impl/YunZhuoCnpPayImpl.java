package com.rzx.project.facade.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.alibaba.fastjson.JSON;
import com.rzx.common.enums.PayLinkNetWorkStatusEnum;
import com.rzx.common.enums.PayWayEnum;
import com.rzx.common.enums.RefundStatusEnum;
import com.rzx.common.enums.TradeStatusEnum;
import com.rzx.common.utils.BigDecimalUtils;
import com.rzx.common.utils.plutuspay.AesEncryptionUtil;
import com.rzx.project.model.domain.PayChannelCode;
import com.rzx.project.facade.*;
import com.rzx.project.facade.dto.*;
import com.rzx.project.facade.vo.*;
import com.rzx.project.pay.yunzhuo.YunZhuoPayUtils;
import com.rzx.project.service.IPayChannelCodeService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

/**
 * 云卓小程序支付
 *
 * @author zy
 * @date 2021/9/30 16:03
 */
@Slf4j
@Service("YUN_ZHUO_PAY")
public class YunZhuoCnpPayImpl implements CnpPayFacade, CnpPayVerifyFacade, OrderQueryFacade, CnpRefundFacade, RefundQueryFacade {
    @Autowired
    private IPayChannelCodeService payChannelCodeService;

    /**
     * 异步回调方法
     *
     * @param notifyVerifyParam
     * @return
     */
    @Override
    public NotifyVerifyResult cnpPayVerify(NotifyVerifyParam notifyVerifyParam) {
        NotifyVerifyResult notifyVerifyResult = new NotifyVerifyResult();
        Map<String, Object> paramMap = notifyVerifyParam.getParamMap();
        String payChannelCode = notifyVerifyParam.getPayChannelCode();
        String content = paramMap.get("content").toString();
        boolean verify = YunZhuoPayUtils.signVerify(paramMap);
        if (!verify) {
            //验签失败
            log.error("云卓支付验签失败!");
            notifyVerifyResult.setIsVerify(false);
            notifyVerifyResult.setTradeStatusEnum(TradeStatusEnum.FAIL);
            return notifyVerifyResult;
        }
        // 解密
        byte[] bytes = AesEncryptionUtil.decrypt(Base64.decode(content), AesEncryptionUtil.SECRET_KEY);
        if (Objects.isNull(bytes)) {
            log.error("云卓支付回调解密失败!");
            //云卓解密失败
            notifyVerifyResult.setIsVerify(true);
            notifyVerifyResult.setResponseStr("FAIL");
            notifyVerifyResult.setTradeStatusEnum(TradeStatusEnum.FAIL);
            return notifyVerifyResult;
        }
        String contentStr = new String(bytes);
        JSONObject json = JSONObject.fromObject(contentStr);
        String tradeTime = json.getString("tradeTime");
        String outTransId = json.getString("outTransId");
        String tradeAmount = json.getString("tradeAmount");
        String tradeId = json.getString("tradeId");
        //交易状态，1开始交易、2交易成功、3交易失败、5未确定
        String tradeStatus = json.getString("tradeStatus");
        TradeStatusEnum tradeStatusEnum;
        switch (tradeStatus) {
            case "1":
                tradeStatusEnum = TradeStatusEnum.WAITING;
                break;
            case "2":
                tradeStatusEnum = TradeStatusEnum.SUCCESS;
                break;
            case "3":
                tradeStatusEnum = TradeStatusEnum.FAIL;
                break;
            default:
                tradeStatusEnum = TradeStatusEnum.UNKNOWN;
                break;
        }
        //验签通过
        notifyVerifyResult.setIsVerify(true);
        notifyVerifyResult.setPayChannelCode(payChannelCode);
        notifyVerifyResult.setPayAmount(new BigDecimal(tradeAmount));
        notifyVerifyResult.setResponseStr("SUCCESS");
        notifyVerifyResult.setSuccessTime(LocalDateTimeUtil.parse(tradeTime, DatePattern.NORM_DATETIME_PATTERN));
        notifyVerifyResult.setOutTradeNo(outTransId);
        notifyVerifyResult.setTransactionNo(tradeId);
        notifyVerifyResult.setTradeStatusEnum(tradeStatusEnum);
        notifyVerifyResult.setDecryptReturnParam(contentStr);
        return notifyVerifyResult;
    }

    /**
     * 小程序支付
     *
     * @param param
     * @return
     */
    @Override
    public CnpPayResult cnpPay(CnpPayPreParam param) {
        log.info("进入云卓小程序支付,请求参数：{}", JSON.toJSONString(param));
        String payChannelCode = param.getPayChannelCode();
        PayChannelCode channelCode = payChannelCodeService.getDataByChannelCode(payChannelCode);
        Assert.notNull(channelCode, "支付渠道未配置");
        Map<String, String> map = YunZhuoPayUtils.appPay(param, channelCode);

        CnpPayResult result = new CnpPayResult();

        if (CollectionUtil.isNotEmpty(map)) {
            result.setPayMessage(JSON.toJSONString(map));
            result.setPayLinkNetWorkStatusEnum(PayLinkNetWorkStatusEnum.SUCCESS);
            result.setBankReturnMsg("请求支付成功");
            result.setPayChannelCode(payChannelCode);
            result.setSubMerchantNo(param.getSubMerchantNo());
        } else {
            result.setPayLinkNetWorkStatusEnum(PayLinkNetWorkStatusEnum.FAIL);
            result.setBankReturnMsg("请求支付失败");
            result.setPayChannelCode(payChannelCode);
        }
        return result;
    }

    /**
     * 订单查询
     *
     * @param orderQueryParam
     * @return
     */
    @Override
    public OrderQueryResult orderQuery(OrderQueryParam orderQueryParam) {
        log.info("云卓支付查询订单,params:" + orderQueryParam.toString());
        String payChannelCode = orderQueryParam.getPayChannelCode();
        PayChannelCode channelCode = payChannelCodeService.getDataByChannelCode(payChannelCode);
        OrderQueryResult result = new OrderQueryResult();

        Map<String, Object> resultMap = YunZhuoPayUtils.orderQuery(orderQueryParam, channelCode);
        // 为平台订单号,对商户来说是渠道订单号，对上游来说是商户订单号
        result.setOutTradeNo(orderQueryParam.getOutTrxNo());
        result.setTradeStatusEnum(TradeStatusEnum.UNKNOWN);
        result.setSuccessTime(LocalDateTime.now());

        //交易状态：0-未知 1-支付成功 2-支付失败
        String status = resultMap.get("status").toString();
        //交易金额（分）
        long tradeAmount = Convert.toLong(resultMap.get("tradeAmount"));
        //云卓支付流水号
        String tradeId = resultMap.get("tradeId").toString();
        //格式：2020-09-24 10:15:03
        String payTime = resultMap.get("payTime").toString();
        String remark = resultMap.get("remark").toString();

        if ("1".equals(status)) {
            result.setTradeStatusEnum(TradeStatusEnum.SUCCESS);
            result.setTransactionId(tradeId);
            result.setSuccessTime(LocalDateTimeUtil.parse(payTime.replace(" ", "T")));
            //分转为元
            result.setPayAmount(BigDecimalUtils.divide(BigDecimal.valueOf(tradeAmount), BigDecimal.valueOf(100)));
            result.setDesc(remark);
        } else if ("0".equals(status)) {
            log.info("云卓支付查询订单未知结果,status：{}", status);
            result.setTradeStatusEnum(TradeStatusEnum.UNKNOWN);
        } else if ("2".equals(status)) {
            log.info("云卓支付查询订单支付失败,status：{}", status);
            result.setTradeStatusEnum(TradeStatusEnum.FAIL);
        }
        result.setPayChannelCode(PayWayEnum.YUN_ZHUO_PAY.getPayChannelCode());
        return result;
    }

    /**
     * 无卡退款
     *
     * @param param
     * @return
     */
    @Override
    public CnpRefundResult refund(CnpRefundParam param) {
        log.info("云卓支付退款发起,params:" + param.toString());
        String payChannelCode = param.getPayChannelCode();
        PayChannelCode channelCode = payChannelCodeService.getDataByChannelCode(payChannelCode);

        CnpRefundResult result = new CnpRefundResult();
        Map<String, Object> resultMap = YunZhuoPayUtils.refund(param, channelCode);
        //交易状态：0-退款中 1-退款成功 2-退款失败
        String status = resultMap.get("status").toString();
        //第三方退款、退货流水号，不长于30位，第三方全局唯一
        String outRefundId = resultMap.get("outRefundId").toString();
        //退款流水号
        String refundId = resultMap.get("refundId").toString();

        if ("1".equals(status)) {
            result.setBankReturnMsg(JSON.toJSONString(result));
            result.setOutRefundNo(outRefundId);
            result.setSubMerchantNo(param.getSubMerchantNo());
            result.setRefundTrxNo(refundId);
            result.setRefundStatusEnum(RefundStatusEnum.SUCCESS);
            result.setPayChannelCode(payChannelCode);
        } else if ("2".equals(status)) {
            result.setBankReturnMsg(JSON.toJSONString(result));
            result.setSubMerchantNo(param.getSubMerchantNo());
            result.setRefundStatusEnum(RefundStatusEnum.FILE);
            result.setPayChannelCode(payChannelCode);
        } else {
            result.setSubMerchantNo(param.getSubMerchantNo());
            result.setRefundStatusEnum(RefundStatusEnum.PROCESS_ING);
            result.setPayChannelCode(payChannelCode);
        }
        return result;
    }

    /**
     * 退款查询
     *
     * @param param
     * @return
     */
    @Override
    public RefundQueryResult refundQuery(RefundQueryParam param) {

        log.info("云卓支付退款查询,params:" + param.toString());
        String payChannelCode = param.getPayChannelCode();
        PayChannelCode channelCode = payChannelCodeService.getDataByChannelCode(payChannelCode);

        RefundQueryResult result = new RefundQueryResult();
        Map<String, Object> resultMap = YunZhuoPayUtils.refundQuery(param, channelCode);
        //交易状态：0-退款中 1-退款成功 2-退款失败
        String status = resultMap.get("status").toString();
        String remark = resultMap.get("remark").toString();
        String tradeId = resultMap.get("tradeId").toString();
        String outRefundId = resultMap.get("outRefundId").toString();
        //撤销金额（分）
        Integer refundAmount = Convert.toInt(resultMap.get("refundAmount"));

        result.setPayChannelCode(payChannelCode);
        if ("0".equals(status)) {
            result.setRefundStatusEnum(RefundStatusEnum.PROCESS_ING);
            result.setDesc(remark);
            result.setOutTradeNo(outRefundId);
        } else if ("1".equals(status)) {
            result.setRefundStatusEnum(RefundStatusEnum.SUCCESS);
            result.setDesc(remark);
            result.setTransactionId(tradeId);
            result.setOutTradeNo(outRefundId);
            result.setRefundAmount(BigDecimalUtils.centToYuan(BigDecimal.valueOf(refundAmount)));
        } else {
            result.setRefundStatusEnum(RefundStatusEnum.FILE);
            result.setOutTradeNo(outRefundId);
        }
        return result;
    }
}
