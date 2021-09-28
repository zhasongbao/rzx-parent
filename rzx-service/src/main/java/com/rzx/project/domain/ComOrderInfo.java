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
 * 任智行 主订单对象 rzx_com_order_info
 *
 * @author zy
 * @date 2021-09-28
 */
@Data
@ToString
@TableName("rzx_com_order_info")
@ApiModel(value = "任智行 主订单对象", description = "任智行 主订单rzx_com_order_info表")
@EqualsAndHashCode(callSuper = true)
public class ComOrderInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @ApiModelProperty(value = "${column.columnComment}")
    @TableId(value ="comsalesorder_id", type = IdType.ASSIGN_ID)
    private String comsalesorderId;
    
    /** 主订单号 */
    @Excel(name = "主订单号")
    @ApiModelProperty(value = "主订单号")
    @TableField(value = "com_order_id")
    private String comOrderId;
    
    /** 用户id */
    @Excel(name = "用户id")
    @ApiModelProperty(value = "用户id")
    @TableField(value = "user_info_id")
    private String userInfoId;
    
    /** 订单总额 */
    @Excel(name = "订单总额")
    @ApiModelProperty(value = "订单总额")
    @TableField(value = "total_amount")
    private String totalAmount;
    
    /** 商品明细 */
    @Excel(name = "商品明细")
    @ApiModelProperty(value = "商品明细")
    @TableField(value = "products")
    private String products;
    
    /** 状态(1-有效 0-无效) */
    @Excel(name = "状态(1-有效 0-无效)")
    @ApiModelProperty(value = "状态(1-有效 0-无效)")
    @TableField(value = "status")
    private String status;
    
    /** 子订单处理结果 */
    @Excel(name = "子订单处理结果")
    @ApiModelProperty(value = "子订单处理结果")
    @TableField(value = "order_nums")
    private String orderNums;
    


}
