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
 * 任智行 商品配置对象 rzx_commodity_config
 *
 * @author zy
 * @date 2021-09-15
 */
@Data
@ToString
@TableName("rzx_commodity_config")
@ApiModel(value = "任智行 商品配置对象", description = "任智行 商品配置rzx_commodity_config表")
@EqualsAndHashCode(callSuper = true)
public class CommodityConfig extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @ApiModelProperty(value = "${column.columnComment}")
    @TableId(value ="COMMODITYCONFIG_ID", type = IdType.ASSIGN_ID)
    private String commodityconfigId;
    
    /** 商品编码 */
    @Excel(name = "商品编码")
    @ApiModelProperty(value = "商品编码")
    @TableField(value = "COMMODITY_CODE")
    private String commodityCode;
    
    /** 商品名称 */
    @Excel(name = "商品名称")
    @ApiModelProperty(value = "商品名称")
    @TableField(value = "COMMODITY_NAME")
    private String commodityName;
    
    /** 品牌 */
    @Excel(name = "品牌")
    @ApiModelProperty(value = "品牌")
    @TableField(value = "BRAND")
    private String brand;
    
    /** 商品型号 */
    @Excel(name = "商品型号")
    @ApiModelProperty(value = "商品型号")
    @TableField(value = "MODEL")
    private String model;
    
    /** 商品列表缩略图 */
    @Excel(name = "商品列表缩略图")
    @ApiModelProperty(value = "商品列表缩略图")
    @TableField(value = "THUMBNAIL_IMAGE")
    private String thumbnailImage;
    
    /** 商品详细图片 */
    @Excel(name = "商品详细图片")
    @ApiModelProperty(value = "商品详细图片")
    @TableField(value = "IMAGE")
    private String image;
    
    /** 商品轮播图片 */
    @Excel(name = "商品轮播图片")
    @ApiModelProperty(value = "商品轮播图片")
    @TableField(value = "IMAGE_URLS")
    private String imageUrls;
    
    /** 商品描述 */
    @Excel(name = "商品描述")
    @ApiModelProperty(value = "商品描述")
    @TableField(value = "EXPLAIN")
    private String explain;
    
    /** 商品价格 */
    @Excel(name = "商品价格")
    @ApiModelProperty(value = "商品价格")
    @TableField(value = "AMOUNT")
    private String amount;
    
    /** 提供方（1-云中鹤，2-百汇） */
    @Excel(name = "提供方", readConverterExp = "1=-云中鹤，2-百汇")
    @ApiModelProperty(value = "提供方（1-云中鹤，2-百汇）")
    @TableField(value = "PROVID")
    private String provid;
    
    /** 状态(1-有效 0-无效) */
    @Excel(name = "状态(1-有效 0-无效)")
    @ApiModelProperty(value = "状态(1-有效 0-无效)")
    @TableField(value = "STATUS")
    private String status;
    
    /** 商品分类 */
    @Excel(name = "商品分类")
    @ApiModelProperty(value = "商品分类")
    @TableField(value = "PRODUCT_CATE")
    private String productCate;
    
    /** 商品产地 */
    @Excel(name = "商品产地")
    @ApiModelProperty(value = "商品产地")
    @TableField(value = "PRODUCT_PLACE")
    private String productPlace;
    
    /** 商品状态 (selling: 上架销售中, undercarriage: 下架) */
    @Excel(name = "商品状态 (selling: 上架销售中, undercarriage: 下架)")
    @ApiModelProperty(value = "商品状态 (selling: 上架销售中, undercarriage: 下架)")
    @TableField(value = "SALE_STATUS")
    private String saleStatus;
    
    /** 商品市场价 */
    @Excel(name = "商品市场价")
    @ApiModelProperty(value = "商品市场价")
    @TableField(value = "MARKET_PRICE")
    private String marketPrice;
    
    /** 协议价格 */
    @Excel(name = "协议价格")
    @ApiModelProperty(value = "协议价格")
    @TableField(value = "RETAIL_AMOUNT")
    private String retailAmount;
    
    /** 是否支持7天无理由退货 */
    @Excel(name = "是否支持7天无理由退货")
    @ApiModelProperty(value = "是否支持7天无理由退货")
    @TableField(value = "IS_TO_RETURN")
    private String isToReturn;
    
    /** 所属礼包ID */
    @Excel(name = "所属礼包ID")
    @ApiModelProperty(value = "所属礼包ID")
    @TableField(value = "GIFTPACKAGE_ID")
    private String giftpackageId;
    
    /** 是否选品库商品(1-是) */
    @Excel(name = "是否选品库商品(1-是)")
    @ApiModelProperty(value = "是否选品库商品(1-是)")
    @TableField(value = "CHOOSE_FLAG")
    private String chooseFlag;
    
    /** 商品重量 */
    @Excel(name = "商品重量")
    @ApiModelProperty(value = "商品重量")
    @TableField(value = "WEIGHT")
    private String weight;
    
    /** 商品尺寸 */
    @Excel(name = "商品尺寸")
    @ApiModelProperty(value = "商品尺寸")
    @TableField(value = "SIZE")
    private String size;
    
    /** 商品属性 0-自营实物 1-电子卡券 2-直充话费 3-京东电商 */
    @Excel(name = "商品属性 0-自营实物 1-电子卡券 2-直充话费 3-京东电商")
    @ApiModelProperty(value = "商品属性 0-自营实物 1-电子卡券 2-直充话费 3-京东电商")
    @TableField(value = "TYPE_ID")
    private String typeId;
    
    /** 提供方商品来源 (system: 系统, provider:供应商,jindong:京东, xinfutong：京东2,wangyi:网易严选) (2：卡券，3：直充，6：天猫) */
    @Excel(name = "提供方商品来源 (system: 系统, provider:供应商,jindong:京东, xinfutong：京东2,wangyi:网易严选) (2：卡券，3：直充，6：天猫)")
    @ApiModelProperty(value = "提供方商品来源 (system: 系统, provider:供应商,jindong:京东, xinfutong：京东2,wangyi:网易严选) (2：卡券，3：直充，6：天猫)")
    @TableField(value = "PROVID_SOURCE")
    private String providSource;
    


}
