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
 * 任智行 商品分类对象 rzx_commodity_class
 *
 * @author zy
 * @date 2021-09-15
 */
@Data
@ToString
@TableName("rzx_commodity_class")
@ApiModel(value = "任智行 商品分类对象", description = "任智行 商品分类rzx_commodity_class表")
@EqualsAndHashCode(callSuper = true)
public class CommodityClass extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @ApiModelProperty(value = "${column.columnComment}")
    @TableId(value ="COMMODITYCLASS_ID", type = IdType.ASSIGN_ID)
    private String commodityclassId;
    
    /** 分类ID */
    @Excel(name = "分类ID")
    @ApiModelProperty(value = "分类ID")
    @TableField(value = "CODE")
    private String code;
    
    /** 分类名称 */
    @Excel(name = "分类名称")
    @ApiModelProperty(value = "分类名称")
    @TableField(value = "NAME")
    private String name;
    
    /** 层级 */
    @Excel(name = "层级")
    @ApiModelProperty(value = "层级")
    @TableField(value = "LEVEL")
    private String level;
    
    /** 提供方（1-云中鹤） */
    @Excel(name = "提供方", readConverterExp = "1=-云中鹤")
    @ApiModelProperty(value = "提供方（1-云中鹤）")
    @TableField(value = "PROVID")
    private String provid;
    
    /** 分类父ID */
    @Excel(name = "分类父ID")
    @ApiModelProperty(value = "分类父ID")
    @TableField(value = "PARENTID")
    private String parentid;
    
    /** 状态(1-有效 0-无效) */
    @Excel(name = "状态(1-有效 0-无效)")
    @ApiModelProperty(value = "状态(1-有效 0-无效)")
    @TableField(value = "STATUS")
    private String status;
    


}
