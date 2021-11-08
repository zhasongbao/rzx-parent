package com.rzx.project.facade.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 支付订单查询
 *
 * @author zy
 * @date 2021/10/9 17:38
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderQueryParam implements Serializable {

    private static final long serialVersionUID = -5855582765388730053L;

    /**
     * 支付接口编码（非空）
     **/
    @NotBlank(message = "支付接口编码不能为空")
    private String payChannelCode;

    /**
     * 平台支付流水号 与 transactionNo 二选一
     */
    @NotBlank(message = "平台支付流水号不能为空")
    private String outTrxNo;

    /**
     * 第三方订单号 与 outTrxNo 二选一
     */
    @NotBlank(message = "第三方订单号不能为空")
    private String transactionNo;

    /**
     * 商户银行通道附属信息
     */
    private String bankDesc;

    /**
     * 渠道分配子商户号
     **/
    @NotBlank(message = "渠道分配子商户号不能为空")
    private String subMerchantNo;

    /**
     * 渠道分配子商户paykey
     **/
    private String subMerchantPayKey;
}
