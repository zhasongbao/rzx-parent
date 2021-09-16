package com.rzx.project.domain;

    import com.rzx.common.core.domain.BaseEntity;
    import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.rzx.common.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.ToString;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * 销售订单对象 rzx_order_info
 *
 * @author zy
 * @date 2021-09-16
 */
@Data
@ToString
@TableName("rzx_order_info")
@ApiModel(value = "销售订单对象", description = "销售订单rzx_order_info表")
@EqualsAndHashCode(callSuper = true)
public class OrderInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @ApiModelProperty(value = "${column.columnComment}")
    @TableId(value ="salesorder_id", type = IdType.ASSIGN_ID)
    private String salesorderId;
    
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
    
    /** 出售价格 */
    @Excel(name = "出售价格")
    @ApiModelProperty(value = "出售价格")
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
    
    /** 订单状态(0-未支付 1-已支付 2-支付失败 3-支付中) */
    @Excel(name = "订单状态(0-未支付 1-已支付 2-支付失败 3-支付中)")
    @ApiModelProperty(value = "订单状态(0-未支付 1-已支付 2-支付失败 3-支付中)")
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
    @TableField(value = "billrecord_id")
    private String billrecordId;
    
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
    
    /** 父订单ID */
    @Excel(name = "父订单ID")
    @ApiModelProperty(value = "父订单ID")
    @TableField(value = "parent_id")
    private String parentId;
    


}
