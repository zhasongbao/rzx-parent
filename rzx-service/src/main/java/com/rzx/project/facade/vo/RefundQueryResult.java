package com.rzx.project.facade.vo;

import com.rzx.common.enums.RefundStatusEnum;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 退款查询
 *
 * @author zy
 * @date 2021/9/30 16:37
 */
@Data
@ToString
public class RefundQueryResult implements Serializable {

    private static final long serialVersionUID = -950063176123663380L;

    /**
     * 代付接口（非空）
     **/
    private String payChannelCode;

    /**
     * 第三方支付订单号
     */
    private String transactionId;

    /**
     * 退款金额,单位：元
     */
    private BigDecimal refundAmount;

    /**
     * 退款状态状态
     */
    private RefundStatusEnum refundStatusEnum;

    /**
     * 平台支付订单号
     **/
    private String outTradeNo;

    /**
     * 平台退款单号
     */
    private String outRefundNo;

    /**
     * 描述
     */
    private String desc;
}
