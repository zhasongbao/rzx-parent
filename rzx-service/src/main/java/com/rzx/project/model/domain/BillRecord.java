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
 * 任智行 开票信息对象 rzx_bill_record
 *
 * @author zy
 * @date 2021-09-28
 */
@Data
@Builder
@TableName("rzx_bill_record")
@ApiModel(value = "任智行 开票信息对象", description = "任智行 开票信息rzx_bill_record表")
public class BillRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Tolerate
    public BillRecord() {
    }

    /** $column.columnComment */
    @ApiModelProperty(value = "${column.columnComment}")
    @TableId(value ="bill_record_id", type = IdType.ASSIGN_ID)
    private String billRecordId;
    
    /** 是否已开票(0-否 1-是) */
    @Excel(name = "是否已开票(0-否 1-是)")
    @ApiModelProperty(value = "是否已开票(0-否 1-是)")
    @TableField(value = "bill_status")
    private String billStatus;
    
    /** 开票类型（0-个人 1-企业） */
    @Excel(name = "开票类型", readConverterExp = "0=-个人,1=-企业")
    @ApiModelProperty(value = "开票类型（0-个人 1-企业）")
    @TableField(value = "type")
    private String type;
    
    /** 抬头名称 */
    @Excel(name = "抬头名称")
    @ApiModelProperty(value = "抬头名称")
    @TableField(value = "up_name")
    private String upName;
    
    /** 邮箱地址 */
    @Excel(name = "邮箱地址")
    @ApiModelProperty(value = "邮箱地址")
    @TableField(value = "email")
    private String email;
    
    /** 税号 */
    @Excel(name = "税号")
    @ApiModelProperty(value = "税号")
    @TableField(value = "tax_number")
    private String taxNumber;
    
    /** 单位地址 */
    @Excel(name = "单位地址")
    @ApiModelProperty(value = "单位地址")
    @TableField(value = "company_address")
    private String companyAddress;
    
    /** 公司电话号码 */
    @Excel(name = "公司电话号码")
    @ApiModelProperty(value = "公司电话号码")
    @TableField(value = "company_phone")
    private String companyPhone;
    
    /** 开户银行 */
    @Excel(name = "开户银行")
    @ApiModelProperty(value = "开户银行")
    @TableField(value = "open_bank")
    private String openBank;
    
    /** 银行账户 */
    @Excel(name = "银行账户")
    @ApiModelProperty(value = "银行账户")
    @TableField(value = "account_bank")
    private String accountBank;
    


}
