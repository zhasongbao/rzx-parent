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
 * 券信息对象 rzx_coupons_info
 *
 * @author zy
 * @date 2021-09-16
 */
@Data
@ToString
@TableName("rzx_coupons_info")
@ApiModel(value = "券信息对象", description = "券信息rzx_coupons_info表")
@EqualsAndHashCode(callSuper = true)
public class CouponsInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @ApiModelProperty(value = "${column.columnComment}")
    @TableId(value ="couponsinfo_id", type = IdType.ASSIGN_ID)
    private String couponsinfoId;
    
    /** 券批次号 */
    @Excel(name = "券批次号")
    @ApiModelProperty(value = "券批次号")
    @TableField(value = "batch_no")
    private String batchNo;
    
    /** 密码 */
    @Excel(name = "密码")
    @ApiModelProperty(value = "密码")
    @TableField(value = "coupons_pwd")
    private String couponsPwd;
    
    /** 销售状态(0-未销售 1-已销售) */
    @Excel(name = "销售状态(0-未销售 1-已销售)")
    @ApiModelProperty(value = "销售状态(0-未销售 1-已销售)")
    @TableField(value = "sale_status")
    private String saleStatus;
    
    /** 核销状态(0-未核销 1-已核销) */
    @Excel(name = "核销状态(0-未核销 1-已核销)")
    @ApiModelProperty(value = "核销状态(0-未核销 1-已核销)")
    @TableField(value = "check_status")
    private String checkStatus;
    
    /** 核销时间 */
    @Excel(name = "核销时间")
    @ApiModelProperty(value = "核销时间")
    @TableField(value = "check_time")
    private String checkTime;
    
    /** 状态(1-有效 0-无效) */
    @Excel(name = "状态(1-有效 0-无效)")
    @ApiModelProperty(value = "状态(1-有效 0-无效)")
    @TableField(value = "status")
    private String status;
    
    /** 券号二维码 */
    @Excel(name = "券号二维码")
    @ApiModelProperty(value = "券号二维码")
    @TableField(value = "coupons_qrcode")
    private String couponsQrcode;
    
    /** 来源(1-任货行 2-任意行 3-任通行) */
    @Excel(name = "来源(1-任货行 2-任意行 3-任通行)")
    @ApiModelProperty(value = "来源(1-任货行 2-任意行 3-任通行)")
    @TableField(value = "in_source")
    private String inSource;
    
    /** 1-虚拟 2-实物 */
    @Excel(name = "1-虚拟 2-实物")
    @ApiModelProperty(value = "1-虚拟 2-实物")
    @TableField(value = "type")
    private String type;

    private String giftpackageId;
    private String orderId;
    private String initAmount;
    private String saleAmount;

}
