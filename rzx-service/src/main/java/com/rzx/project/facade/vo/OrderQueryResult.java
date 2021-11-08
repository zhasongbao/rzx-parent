package com.rzx.project.facade.vo;

import com.rzx.common.enums.TradeStatusEnum;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zy
 * @date 2021/9/30 16:37
 */
@Data
@ToString
public class OrderQueryResult implements Serializable {

    private static final long serialVersionUID = -766401159496286820L;

    /**
     * 代付接口（非空）
     **/
    private String payChannelCode;

    /**
     * 第三方订单号
     */
    private String transactionId;

    /**
     * 支付金额,单位：元
     */
    private BigDecimal payAmount;

    /**
     * 支付状态
     */
    private TradeStatusEnum tradeStatusEnum;

    /**
     * 成功时间
     */
    private LocalDateTime successTime;

    /**
     * 平台订单号
     **/
    private String outTradeNo;

    /**
     * 描述
     */
    private String desc;
}
