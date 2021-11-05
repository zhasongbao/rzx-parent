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
 * 任智行 供应商地址信息对象 rzx_provid_address
 *
 * @author zy
 * @date 2021-09-28
 */
@Data
@Builder
@TableName("rzx_provid_address")
@ApiModel(value = "任智行 供应商地址信息对象", description = "任智行 供应商地址信息rzx_provid_address表")
public class ProvidAddress extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Tolerate
    public ProvidAddress() {
    }

    /** $column.columnComment */
    @ApiModelProperty(value = "${column.columnComment}")
    @TableId(value ="providaddress_id", type = IdType.ASSIGN_ID)
    private String providaddressId;
    
    /** 地址ID */
    @ApiModelProperty(value = "地址ID")
    @TableField(value = "id")
    private String id;
    
    /** 地址名称 */
    @Excel(name = "地址名称")
    @ApiModelProperty(value = "地址名称")
    @TableField(value = "name")
    private String name;
    
    /** 上级ID */
    @Excel(name = "上级ID")
    @ApiModelProperty(value = "上级ID")
    @TableField(value = "parent_id")
    private String parentId;
    
    /** 地址类型1(省),2(市), 3(区/县), 4(乡/镇) */
    @Excel(name = "地址类型1(省),2(市), 3(区/县), 4(乡/镇)")
    @ApiModelProperty(value = "地址类型1(省),2(市), 3(区/县), 4(乡/镇)")
    @TableField(value = "type")
    private String type;
    
    /** 供应商（1-云中鹤，2-百汇） */
    @Excel(name = "供应商", readConverterExp = "1=-云中鹤，2-百汇")
    @ApiModelProperty(value = "供应商（1-云中鹤，2-百汇）")
    @TableField(value = "provid")
    private String provid;
    
    /** 状态(1-有效 0-无效) */
    @Excel(name = "状态(1-有效 0-无效)")
    @ApiModelProperty(value = "状态(1-有效 0-无效)")
    @TableField(value = "status")
    private String status;
    


}
