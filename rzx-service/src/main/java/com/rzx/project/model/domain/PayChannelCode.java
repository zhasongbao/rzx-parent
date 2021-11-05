package com.rzx.project.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.rzx.common.annotation.Excel;
import com.rzx.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 支付渠道编码对象 rzx_pay_channel_code
 *
 * @author zy
 * @date 2021-09-30
 */
@Data
@ToString
@TableName("rzx_pay_channel_code")
@ApiModel(value = "支付渠道编码对象", description = "支付渠道编码rzx_pay_channel_code表")
@EqualsAndHashCode(callSuper = true)
public class PayChannelCode extends BaseEntity {

    private static final long serialVersionUID = 8724707891190563842L;

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * 渠道方商户号
     */
    @Excel(name = "渠道方商户号")
    @ApiModelProperty(value = "渠道方商户号")
    @TableField(value = "channel_merchant_no")
    private String channelMerchantNo;

    /**
     * 渠道方应用id
     */
    @Excel(name = "渠道方应用id")
    @ApiModelProperty(value = "渠道方应用id")
    @TableField(value = "channel_merchant_app_id")
    private String channelMerchantAppId;

    /**
     * 支付渠道编码
     */
    @Excel(name = "支付渠道编码")
    @ApiModelProperty(value = "支付渠道编码")
    @TableField(value = "pay_channel_code")
    private String payChannelCode;

    /**
     * 支付渠道编码名称
     */
    @Excel(name = "支付渠道编码名称")
    @ApiModelProperty(value = "支付渠道编码名称")
    @TableField(value = "bank_channel_name")
    private String bankChannelName;

    /**
     * 交易请求地址
     */
    @Excel(name = "交易请求地址")
    @ApiModelProperty(value = "交易请求地址")
    @TableField(value = "trade_request_url")
    private String tradeRequestUrl;

    /**
     * 交易查询地址
     */
    @Excel(name = "交易查询地址")
    @ApiModelProperty(value = "交易查询地址")
    @TableField(value = "trade_query_url")
    private String tradeQueryUrl;

    /**
     * 退款请求地址
     */
    @Excel(name = "退款请求地址")
    @ApiModelProperty(value = "退款请求地址")
    @TableField(value = "refund_url")
    private String refundUrl;

    /**
     * 退款查询地址
     */
    @Excel(name = "退款查询地址")
    @ApiModelProperty(value = "退款查询地址")
    @TableField(value = "refund_query_url")
    private String refundQueryUrl;

    /**
     * 异步回调地址
     */
    @Excel(name = "异步回调地址")
    @ApiModelProperty(value = "异步回调地址")
    @TableField(value = "notify_url")
    private String notifyUrl;

    /**
     * 费率%1：0.01表示
     */
    @Excel(name = "费率%1：0.01表示")
    @ApiModelProperty(value = "费率%1：0.01表示")
    @TableField(value = "rate")
    private BigDecimal rate;


}
