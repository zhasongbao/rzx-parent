package com.rzx.project.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rzx.common.annotation.Excel;
import com.rzx.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Tolerate;

/**
 * 任智行 销售订单对象 rzx_order_info
 *
 * @author zy
 * @date 2021-09-28
 */
@Data
@Builder
@TableName("rzx_order_info")
@ApiModel(value = "任智行 销售订单对象", description = "任智行 销售订单rzx_order_info表")
@EqualsAndHashCode(callSuper = true)
public class OrderInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Tolerate
    public OrderInfo() {
    }

    /** $column.columnComment */
    @ApiModelProperty(value = "${column.columnComment}")
    @TableId(value ="salesorder_id", type = IdType.ASSIGN_ID)
    private String salesorderId;
    
    /** 商城订单号 */
    @Excel(name = "商城订单号")
    @ApiModelProperty(value = "商城订单号")
    @TableField(value = "order_num")
    private String orderNum;
    
    /** 销售订单号 */
    @Excel(name = "销售订单号")
    @ApiModelProperty(value = "销售订单号")
    @TableField(value = "order_id")
    private String orderId;
    
    /** 外部订单号 */
    @Excel(name = "外部订单号")
    @ApiModelProperty(value = "外部订单号")
    @TableField(value = "order_key")
    private String orderKey;
    
    /** 快递费用 */
    @Excel(name = "快递费用")
    @ApiModelProperty(value = "快递费用")
    @TableField(value = "order_shipment_fee")
    private String orderShipmentFee;
    
    /** 用户id */
    @Excel(name = "用户id")
    @ApiModelProperty(value = "用户id")
    @TableField(value = "saleman_id")
    private String salemanId;
    
    /** 任智行渠道用户id */
    @Excel(name = "任智行渠道用户id")
    @ApiModelProperty(value = "任智行渠道用户id")
    @TableField(value = "user_info_id")
    private String userInfoId;
    
    /** 礼包ID */
    @Excel(name = "礼包ID")
    @ApiModelProperty(value = "礼包ID")
    @TableField(value = "giftpackage_id")
    private String giftpackageId;
    
    /** 商品编码 */
    @Excel(name = "商品编码")
    @ApiModelProperty(value = "商品编码")
    @TableField(value = "commodity_code")
    private String commodityCode;
    
    /** 券号 */
    @Excel(name = "券号")
    @ApiModelProperty(value = "券号")
    @TableField(value = "couponsinfo_id")
    private String couponsinfoId;
    
    /** 初始价格 */
    @Excel(name = "初始价格")
    @ApiModelProperty(value = "初始价格")
    @TableField(value = "init_total_amount")
    private String initTotalAmount;
    
    /** 出售价格（售价） */
    @Excel(name = "出售价格", readConverterExp = "售=价")
    @ApiModelProperty(value = "出售价格（售价）")
    @TableField(value = "sale_total_amount")
    private String saleTotalAmount;
    
    /** 支付方式(0-云卓微信支付) */
    @Excel(name = "支付方式(0-云卓微信支付)")
    @ApiModelProperty(value = "支付方式(0-云卓微信支付)")
    @TableField(value = "pay_way")
    private String payWay;
    
    /** 支付外部流水号 */
    @Excel(name = "支付外部流水号")
    @ApiModelProperty(value = "支付外部流水号")
    @TableField(value = "out_trade_id")
    private String outTradeId;
    
    /** 支付响应内容 */
    @Excel(name = "支付响应内容")
    @ApiModelProperty(value = "支付响应内容")
    @TableField(value = "notifyjson")
    private String notifyjson;
    
    /** C扫B订单支付二维码 */
    @Excel(name = "C扫B订单支付二维码")
    @ApiModelProperty(value = "C扫B订单支付二维码")
    @TableField(value = "qr_code")
    private String qrCode;
    
    /** 订单状态(0-未支付 1-已支付 2-支付失败 3-支付中 4-订单取消 9-订单创建失败) */
    @Excel(name = "订单状态(0-未支付 1-已支付 2-支付失败 3-支付中 4-订单取消 9-订单创建失败)")
    @ApiModelProperty(value = "订单状态(0-未支付 1-已支付 2-支付失败 3-支付中 4-订单取消 9-订单创建失败)")
    @TableField(value = "order_status")
    private String orderStatus;
    
    /** 支付时间 */
    @Excel(name = "支付时间")
    @ApiModelProperty(value = "支付时间")
    @TableField(value = "sale_time")
    private String saleTime;
    
    /** 收货地址唯一ID */
    @Excel(name = "收货地址唯一ID")
    @ApiModelProperty(value = "收货地址唯一ID")
    @TableField(value = "receiveaddress_id")
    private String receiveaddressId;


    /** 订单备注信息 */
    @Excel(name = "供应商", readConverterExp = "订单备注信息")
    @ApiModelProperty(value = "订单备注信息")
    @TableField(value = "remark")
    private String remark;

    /** 供应商（1-云中鹤，2-百汇） */
    @Excel(name = "供应商", readConverterExp = "1=-云中鹤，2-百汇")
    @ApiModelProperty(value = "供应商（1-云中鹤，2-百汇）")
    @TableField(value = "order_provid")
    private String orderProvid;
    
    /** 供应商错误码 */
    @Excel(name = "供应商错误码")
    @ApiModelProperty(value = "供应商错误码")
    @TableField(value = "error_code")
    private String errorCode;
    
    /** 供应商错误信息 */
    @Excel(name = "供应商错误信息")
    @ApiModelProperty(value = "供应商错误信息")
    @TableField(value = "error_message")
    private String errorMessage;
    
    /** 状态(1-有效 0-无效) */
    @Excel(name = "状态(1-有效 0-无效)")
    @ApiModelProperty(value = "状态(1-有效 0-无效)")
    @TableField(value = "status")
    private String status;
    
    /** 佣金 */
    @Excel(name = "佣金")
    @ApiModelProperty(value = "佣金")
    @TableField(value = "brokerage")
    private String brokerage;
    
    /** 发票获取二维码 */
    @Excel(name = "发票获取二维码")
    @ApiModelProperty(value = "发票获取二维码")
    @TableField(value = "qr_bill_url")
    private String qrBillUrl;
    
    /** 开票信息ID */
    @Excel(name = "开票信息ID")
    @ApiModelProperty(value = "开票信息ID")
    @TableField(value = "bill_record_id")
    private String billRecordId;
    
    /** 支付有效截至时间 */
    @Excel(name = "支付有效截至时间")
    @ApiModelProperty(value = "支付有效截至时间")
    @TableField(value = "pay_end_time")
    private String payEndTime;
    
    /** 来源(1-任货行 2-任意行 3-任通行) */
    @Excel(name = "来源(1-任货行 2-任意行 3-任通行)")
    @ApiModelProperty(value = "来源(1-任货行 2-任意行 3-任通行)")
    @TableField(value = "in_source")
    private String inSource;
    
    /** 订单类型 1-礼包 2-商品订单 */
    @Excel(name = "订单类型 1-礼包 2-商品订单")
    @ApiModelProperty(value = "订单类型 1-礼包 2-商品订单")
    @TableField(value = "order_type")
    private String orderType;
    
    /** 主订单号 */
    @Excel(name = "主订单号")
    @ApiModelProperty(value = "主订单号")
    @TableField(value = "com_order_id")
    private String comOrderId;
    
    /** 结算方式1-现金 2-现金+积分 3-积分 */
    @Excel(name = "结算方式1-现金 2-现金+积分 3-积分")
    @ApiModelProperty(value = "结算方式1-现金 2-现金+积分 3-积分")
    @TableField(value = "pay_type")
    private String payType;
    
    /** 支付积分 */
    @Excel(name = "支付积分")
    @ApiModelProperty(value = "支付积分")
    @TableField(value = "pay_score")
    private String payScore;
    
    /** 运单号 */
    @Excel(name = "运单号")
    @ApiModelProperty(value = "运单号")
    @TableField(value = "express_code")
    private String expressCode;
    
    /** 商品单价 */
    @Excel(name = "商品单价")
    @ApiModelProperty(value = "商品单价")
    @TableField(value = "com_price")
    private String comPrice;
    
    /** 商品数量 */
    @Excel(name = "商品数量")
    @ApiModelProperty(value = "商品数量")
    @TableField(value = "com_count")
    private String comCount;

    /** 商城订单同步结果 */
    @Excel(name = "商城订单同步结果")
    @ApiModelProperty(value = "商城订单同步结果 0-同步失败 1同步成功")
    @TableField(value = "notics_status")
    private String noticsStatus;

    /** 核销状态 */
    @Excel(name = "核销状态")
    @ApiModelProperty(value = "核销状态(0-未核销 1-已核销)")
    @TableField(value = "check_status")
    private String checkStatus;

    /** 供应商订单确认状态 */
    @Excel(name = "供应商订单确认状态")
    @ApiModelProperty(value = "百汇使用 供应商订单确认状态（0-未确认 1-已确认）")
    @TableField(value = "order_confirm")
    private String orderConfirm;

    private String name;
    private String giftExplain;
    private String initAmount;
    private String saleAmount;
    private String productLogo;
    private String billStatus;
    private String receivePhone;
}
