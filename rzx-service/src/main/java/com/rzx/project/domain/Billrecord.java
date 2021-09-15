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
 * 任智行 开票信息对象 rzx_billrecord
 *
 * @author zy
 * @date 2021-09-15
 */
@Data
@ToString
@TableName("rzx_billrecord")
@ApiModel(value = "任智行 开票信息对象", description = "任智行 开票信息rzx_billrecord表")
@EqualsAndHashCode(callSuper = true)
public class Billrecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @ApiModelProperty(value = "${column.columnComment}")
    @TableId(value ="BILLRECORD_ID", type = IdType.ASSIGN_ID)
    private String billrecordId;
    
    /** 是否已开票(0-否 1-是) */
    @Excel(name = "是否已开票(0-否 1-是)")
    @ApiModelProperty(value = "是否已开票(0-否 1-是)")
    @TableField(value = "BILL_STATUS")
    private String billStatus;
    
    /** 开票类型（0-个人 1-企业） */
    @Excel(name = "开票类型", readConverterExp = "0=-个人,1=-企业")
    @ApiModelProperty(value = "开票类型（0-个人 1-企业）")
    @TableField(value = "TYPE")
    private String type;
    
    /** 抬头名称 */
    @Excel(name = "抬头名称")
    @ApiModelProperty(value = "抬头名称")
    @TableField(value = "UP_NAME")
    private String upName;
    
    /** 邮箱地址 */
    @Excel(name = "邮箱地址")
    @ApiModelProperty(value = "邮箱地址")
    @TableField(value = "EMAIL")
    private String email;
    
    /** 税号 */
    @Excel(name = "税号")
    @ApiModelProperty(value = "税号")
    @TableField(value = "TAX_NUMBER")
    private String taxNumber;
    
    /** 单位地址 */
    @Excel(name = "单位地址")
    @ApiModelProperty(value = "单位地址")
    @TableField(value = "COMPANY_ADDRESS")
    private String companyAddress;
    
    /** 公司电话号码 */
    @Excel(name = "公司电话号码")
    @ApiModelProperty(value = "公司电话号码")
    @TableField(value = "COMPANY_PHONE")
    private String companyPhone;
    
    /** 开户银行 */
    @Excel(name = "开户银行")
    @ApiModelProperty(value = "开户银行")
    @TableField(value = "OPEN_BANK")
    private String openBank;
    
    /** 银行账户 */
    @Excel(name = "银行账户")
    @ApiModelProperty(value = "银行账户")
    @TableField(value = "ACCOUNT_BANK")
    private String accountBank;
    


}
