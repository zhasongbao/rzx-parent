package com.rzx.project.facade.vo;

import com.rzx.common.enums.RefundStatusEnum;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 无卡退款退款
 *
 * @author zy
 * @date 2021/10/25 11:09
 */
@Data
@ToString
public class CnpRefundResult implements Serializable {

    private static final long serialVersionUID = -3648254825533591009L;

    /**
     * 银行渠道返回信息
     */
    private String bankReturnMsg;

    /**
     * 渠道方退款单号
     */
    private String refundTrxNo;

    /**
     * 商户退款单号
     */
    private String outRefundNo;

    /**
     * 支付渠道编码
     */
    private String payChannelCode;

    /**
     * 退款状态
     */
    private RefundStatusEnum refundStatusEnum;

    /**
     * 渠道方分配的商户号
     **/
    private String subMerchantNo;
}
