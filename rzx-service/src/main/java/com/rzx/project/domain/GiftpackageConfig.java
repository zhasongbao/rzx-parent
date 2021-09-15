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
 * 任智行 礼包配置对象 rzx_giftpackage_config
 *
 * @author zy
 * @date 2021-09-15
 */
@Data
@ToString
@TableName("rzx_giftpackage_config")
@ApiModel(value = "任智行 礼包配置对象", description = "任智行 礼包配置rzx_giftpackage_config表")
@EqualsAndHashCode(callSuper = true)
public class GiftpackageConfig extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @ApiModelProperty(value = "${column.columnComment}")
    @TableId(value ="GIFTPACKAGE_ID", type = IdType.ASSIGN_ID)
    private String giftpackageId;
    
    /** 礼包名称 */
    @Excel(name = "礼包名称")
    @ApiModelProperty(value = "礼包名称")
    @TableField(value = "NAME")
    private String name;
    
    /** 礼包描述 */
    @Excel(name = "礼包描述")
    @ApiModelProperty(value = "礼包描述")
    @TableField(value = "EXPLAIN")
    private String explain;
    
    /** 礼包原价 */
    @Excel(name = "礼包原价")
    @ApiModelProperty(value = "礼包原价")
    @TableField(value = "INIT_AMOUNT")
    private String initAmount;
    
    /** 礼包售价 */
    @Excel(name = "礼包售价")
    @ApiModelProperty(value = "礼包售价")
    @TableField(value = "SALE_AMOUNT")
    private String saleAmount;
    
    /** 礼包类型（0-单品 1-多选一） */
    @Excel(name = "礼包类型", readConverterExp = "0=-单品,1=-多选一")
    @ApiModelProperty(value = "礼包类型（0-单品 1-多选一）")
    @TableField(value = "TYPE")
    private String type;
    
    /** 状态(1-有效 0-无效) */
    @Excel(name = "状态(1-有效 0-无效)")
    @ApiModelProperty(value = "状态(1-有效 0-无效)")
    @TableField(value = "STATUS")
    private String status;
    
    /** 已售份数 */
    @Excel(name = "已售份数")
    @ApiModelProperty(value = "已售份数")
    @TableField(value = "SALLOUT_COUNT")
    private String salloutCount;
    
    /** 礼包排序 */
    @Excel(name = "礼包排序")
    @ApiModelProperty(value = "礼包排序")
    @TableField(value = "PRODUCT_ORDER")
    private Integer productOrder;
    
    /** 礼包封面 */
    @Excel(name = "礼包封面")
    @ApiModelProperty(value = "礼包封面")
    @TableField(value = "PRODUCT_LOGO")
    private String productLogo;
    
    /** 一级分配积分 */
    @Excel(name = "一级分配积分")
    @ApiModelProperty(value = "一级分配积分")
    @TableField(value = "ONE_LEVEL_INTEGRAL")
    private String oneLevelIntegral;
    
    /** 二级分配积分 */
    @Excel(name = "二级分配积分")
    @ApiModelProperty(value = "二级分配积分")
    @TableField(value = "TWO_LEVEL_INTEGRAL")
    private String twoLevelIntegral;
    
    /** 三级分配积分 */
    @Excel(name = "三级分配积分")
    @ApiModelProperty(value = "三级分配积分")
    @TableField(value = "THREE_LEVEL_INTEGRAL")
    private String threeLevelIntegral;
    
    /** 平台补贴佣金总比例 */
    @Excel(name = "平台补贴佣金总比例")
    @ApiModelProperty(value = "平台补贴佣金总比例")
    @TableField(value = "PLATFORM_SUBSIDY_AMT")
    private String platformSubsidyAmt;
    
    /** 费用政策 */
    @Excel(name = "费用政策")
    @ApiModelProperty(value = "费用政策")
    @TableField(value = "EXPENSE_POLICY")
    private String expensePolicy;
    
    /** 市场费用率 */
    @Excel(name = "市场费用率")
    @ApiModelProperty(value = "市场费用率")
    @TableField(value = "MART_RATE")
    private String martRate;
    
    /** 推广费用率 */
    @Excel(name = "推广费用率")
    @ApiModelProperty(value = "推广费用率")
    @TableField(value = "PROMOTE_RATE")
    private String promoteRate;
    
    /** 管理费用率 */
    @Excel(name = "管理费用率")
    @ApiModelProperty(value = "管理费用率")
    @TableField(value = "MANAGE_RATE")
    private String manageRate;
    
    /** 可分润推广费用率 */
    @Excel(name = "可分润推广费用率")
    @ApiModelProperty(value = "可分润推广费用率")
    @TableField(value = "SHARE_RATE")
    private String shareRate;
    
    /** 技术服务费用率 */
    @Excel(name = "技术服务费用率")
    @ApiModelProperty(value = "技术服务费用率")
    @TableField(value = "IT_RATE")
    private String itRate;
    
    /** 甲方分润占比 */
    @Excel(name = "甲方分润占比")
    @ApiModelProperty(value = "甲方分润占比")
    @TableField(value = "A_SHARE_RATE")
    private String aShareRate;
    
    /** 已方分润占比 */
    @Excel(name = "已方分润占比")
    @ApiModelProperty(value = "已方分润占比")
    @TableField(value = "B_SHARE_RATE")
    private String bShareRate;
    
    /** 市场费类型 0-百分比 1-绝对值 */
    @Excel(name = "市场费类型 0-百分比 1-绝对值")
    @ApiModelProperty(value = "市场费类型 0-百分比 1-绝对值")
    @TableField(value = "MART_TYPE")
    private String martType;
    
    /** 产品税率 */
    @Excel(name = "产品税率")
    @ApiModelProperty(value = "产品税率")
    @TableField(value = "PRODUCT_TAX_RATE")
    private String productTaxRate;
    
    /** 市场费绝对值 */
    @Excel(name = "市场费绝对值")
    @ApiModelProperty(value = "市场费绝对值")
    @TableField(value = "MART_VALUE")
    private String martValue;
    
    /** 所属合作商(0-广西交通厅) */
    @Excel(name = "所属合作商(0-广西交通厅)")
    @ApiModelProperty(value = "所属合作商(0-广西交通厅)")
    @TableField(value = "PARTNER")
    private String partner;

}
