package com.rzx.project.service;

import com.rzx.project.facade.dto.*;
import com.rzx.project.facade.vo.*;

import java.util.Map;

/**
 * 业务支付接口
 *
 * @author zy
 * @date 2021/9/30 14:56
 */
public interface IPayBizService {

    /**
     * 无卡支付、支持小程序支付、扫码、h5支付
     *
     * @param param 参数
     * @return
     */
    CnpPayResult cnpPay(CnpPayPreParam param);

    /**
     * 回调验签方法
     *
     * @param parameterMap   回调支付map
     * @param payChannelCode 支付渠道编码
     * @return
     */
    NotifyVerifyResult notifyVerify(Map<String, Object> parameterMap, String payChannelCode);

    /**
     * 订单查询
     *
     * @param orderQueryParam 支付结果对象
     * @return
     */
    OrderQueryResult orderQuery(OrderQueryParam orderQueryParam);

    /**
     * 支付完成后业务处理
     *
     * @param notifyVerifyResult 支付回调返回结果
     * @param bankReturnMsg      第三方返回的map
     * @param bizSourceCode      支付来源编码 BizSourceCodeEnum枚举类
     * @return
     */
    boolean completePay(NotifyVerifyResult notifyVerifyResult, Map<String, Object> bankReturnMsg, String bizSourceCode);

//    /**
//     * 银行卡快捷支付
//     *
//     * @param quickPayParam
//     * @return
//     */
//    QuickPayResult quickPay(QuickPayParam quickPayParam);

    /**
     * 无卡退款
     *
     * @param param
     * @return
     */
    CnpRefundResult refund(CnpRefundParam param);

    /**
     * 退款查询
     *
     * @param param
     * @return
     */
    RefundQueryResult refundQuery(RefundQueryParam param);
}
