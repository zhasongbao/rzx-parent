package com.rzx.project.facade.vo;

import com.rzx.common.enums.TradeStatusEnum;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 异步回调返回参数
 *
 * @author zy
 * @date 2021/09/30
 */
@Data
@ToString
public class NotifyVerifyResult implements Serializable {

    private static final long serialVersionUID = 8958114231716798470L;

    /**
     * 支付接口（非空）
     */
    private String payChannelCode;

    /**
     * 是否验签通过
     */
    private Boolean isVerify;

    /**
     * 支付金额,单位：元
     */
    private BigDecimal payAmount;

    /**
     * 支付状态
     */
    private TradeStatusEnum tradeStatusEnum;

    /**
     * 交易成功时间
     */
    private LocalDateTime successTime;

    /**
     * 第三方订单号
     **/
    private String transactionNo;

    /**
     * 成功后回写字符串
     **/
    private String responseStr;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 第三方返回的解密参数
     */
    private String decryptReturnParam;
}
