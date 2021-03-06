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
 * 任智行 商品分类对象 rzx_commodity_class
 *
 * @author zy
 * @date 2021-09-28
 */
@Data
@Builder
@TableName("rzx_commodity_class")
@ApiModel(value = "任智行 商品分类对象", description = "任智行 商品分类rzx_commodity_class表")
public class CommodityClass extends BaseEntity {
    
    private static final long serialVersionUID = 1L;

    @Tolerate
    public CommodityClass() {
    }
    
    /** $column.columnComment */
    @ApiModelProperty(value = "${column.columnComment}")
    @TableId(value ="commodityclass_id", type = IdType.ASSIGN_ID)
    private String commodityclassId;
    
    /** 分类ID */
    @Excel(name = "分类ID")
    @ApiModelProperty(value = "分类ID")
    @TableField(value = "code")
    private String code;
    
    /** 分类名称 */
    @Excel(name = "分类名称")
    @ApiModelProperty(value = "分类名称")
    @TableField(value = "name")
    private String name;
    
    /** 层级 */
    @Excel(name = "层级")
    @ApiModelProperty(value = "层级")
    @TableField(value = "level")
    private String level;
    
    /** 提供方（1-云中鹤） */
    @Excel(name = "提供方", readConverterExp = "1=-云中鹤")
    @ApiModelProperty(value = "提供方（1-云中鹤）")
    @TableField(value = "provid")
    private String provid;
    
    /** 分类父ID */
    @Excel(name = "分类父ID")
    @ApiModelProperty(value = "分类父ID")
    @TableField(value = "parentid")
    private String parentid;
    
    /** 状态(1-有效 0-无效) */
    @Excel(name = "状态(1-有效 0-无效)")
    @ApiModelProperty(value = "状态(1-有效 0-无效)")
    @TableField(value = "status")
    private String status;
    


}
