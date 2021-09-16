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
 * 收货地址对象 rzx_receiveaddress_info
 *
 * @author zy
 * @date 2021-09-16
 */
@Data
@ToString
@TableName("rzx_receiveaddress_info")
@ApiModel(value = "收货地址对象", description = "收货地址rzx_receiveaddress_info表")
@EqualsAndHashCode(callSuper = true)
public class ReceiveaddressInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @ApiModelProperty(value = "${column.columnComment}")
    @TableId(value ="receiveaddress_id", type = IdType.ASSIGN_ID)
    private String receiveaddressId;
    
    /** 用户id */
    @Excel(name = "用户id")
    @ApiModelProperty(value = "用户id")
    @TableField(value = "saleman_id")
    private String salemanId;
    
    /** 省, 区域信息ID */
    @Excel(name = "省, 区域信息ID")
    @ApiModelProperty(value = "省, 区域信息ID")
    @TableField(value = "province")
    private String province;
    
    /** 省 */
    @Excel(name = "省")
    @ApiModelProperty(value = "省")
    @TableField(value = "province_name")
    private String provinceName;
    
    /** 市, 区域信息ID */
    @Excel(name = "市, 区域信息ID")
    @ApiModelProperty(value = "市, 区域信息ID")
    @TableField(value = "city")
    private String city;
    
    /** 市 */
    @Excel(name = "市")
    @ApiModelProperty(value = "市")
    @TableField(value = "city_name")
    private String cityName;
    
    /** 区/县, 区域信息ID */
    @Excel(name = "区/县, 区域信息ID")
    @ApiModelProperty(value = "区/县, 区域信息ID")
    @TableField(value = "county")
    private String county;
    
    /** 区/县 */
    @Excel(name = "区/县")
    @ApiModelProperty(value = "区/县")
    @TableField(value = "county_name")
    private String countyName;
    
    /** 乡/镇, 区域信息ID */
    @Excel(name = "乡/镇, 区域信息ID")
    @ApiModelProperty(value = "乡/镇, 区域信息ID")
    @TableField(value = "town")
    private String town;
    
    /** 乡/镇 */
    @Excel(name = "乡/镇")
    @ApiModelProperty(value = "乡/镇")
    @TableField(value = "town_name")
    private String townName;
    
    /** 收货人姓名 */
    @Excel(name = "收货人姓名")
    @ApiModelProperty(value = "收货人姓名")
    @TableField(value = "receive_name")
    private String receiveName;
    
    /** 收货人手机 */
    @Excel(name = "收货人手机")
    @ApiModelProperty(value = "收货人手机")
    @TableField(value = "receive_phone")
    private String receivePhone;
    
    /** 收货地址 */
    @Excel(name = "收货地址")
    @ApiModelProperty(value = "收货地址")
    @TableField(value = "receive_address")
    private String receiveAddress;
    
    /** 状态(1-有效 0-无效) */
    @Excel(name = "状态(1-有效 0-无效)")
    @ApiModelProperty(value = "状态(1-有效 0-无效)")
    @TableField(value = "status")
    private String status;
    
    /** 提供方（1-云中鹤，2-百汇） */
    @Excel(name = "提供方", readConverterExp = "1=-云中鹤，2-百汇")
    @ApiModelProperty(value = "提供方（1-云中鹤，2-百汇）")
    @TableField(value = "provid")
    private String provid;
    


}
