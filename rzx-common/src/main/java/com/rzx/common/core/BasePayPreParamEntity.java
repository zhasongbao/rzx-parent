package com.rzx.common.core;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 支付基础类
 *
 * @author zy
 * @date 2021/5/12 14:05
 */
@Data
@ToString
public class BasePayPreParamEntity implements Serializable {

    private static final long serialVersionUID = -3980173711967756322L;

    /**
     * 支付接口编码（非空）
     */
    @NotBlank(message = "支付接口编码不能为空")
    private String payChannelCode;

    /**
     * 业务来源编码（非空）,哪个业务模块下单的
     */
    @NotBlank(message = "业务来源编码不能为空")
    private String bizSourceCode;

    /**
     * 平台支付流水号
     */
    @NotBlank(message = "平台支付流水号不能为空")
    private String outTrxNo;

    /**
     * 交易金额，单位：元
     **/
    @NotNull(message = "交易金额不能为空")
    private BigDecimal payAmount;

    /**
     * 币种
     */
    private String currency;

    /**
     * 订单名称
     */
    private String orderName;

    /**
     * 下单日期
     */
    private LocalDateTime orderDate;

    /**
     * 客户IP
     */
    private String payerIp;
}
