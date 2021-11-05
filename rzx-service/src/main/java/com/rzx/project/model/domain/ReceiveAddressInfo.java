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
import lombok.experimental.Tolerate;

/**
 * 任智行 收货地址对象 rzx_receive_address_info
 *
 * @author zy
 * @date 2021-09-28
 */
@Data
@Builder
@TableName("rzx_receive_address_info")
@ApiModel(value = "任智行 收货地址对象", description = "任智行 收货地址rzx_receive_address_info表")
public class ReceiveAddressInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Tolerate
    public ReceiveAddressInfo() {
    }

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
    


}
