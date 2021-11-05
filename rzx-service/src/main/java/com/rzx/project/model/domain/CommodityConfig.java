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
 * 任智行 商品配置对象 rzx_commodity_config
 *
 * @author zy
 * @date 2021-09-28
 */
@Data
@Builder
@TableName("rzx_commodity_config")
@ApiModel(value = "任智行 商品配置对象", description = "任智行 商品配置rzx_commodity_config表")
public class CommodityConfig extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Tolerate
    public CommodityConfig() {
    }

    /** $column.columnComment */
    @ApiModelProperty(value = "${column.columnComment}")
    @TableId(value ="commodityconfig_id", type = IdType.ASSIGN_ID)
    private String commodityconfigId;
    
    /** 商品编码 */
    @Excel(name = "商品编码")
    @ApiModelProperty(value = "商品编码")
    @TableField(value = "commodity_code")
    private String commodityCode;
    
    /** 商品名称 */
    @Excel(name = "商品名称")
    @ApiModelProperty(value = "商品名称")
    @TableField(value = "commodity_name")
    private String commodityName;
    
    /** 品牌 */
    @Excel(name = "品牌")
    @ApiModelProperty(value = "品牌")
    @TableField(value = "brand")
    private String brand;
    
    /** 商品型号 */
    @Excel(name = "商品型号")
    @ApiModelProperty(value = "商品型号")
    @TableField(value = "model")
    private String model;
    
    /** 商品列表缩略图 */
    @Excel(name = "商品列表缩略图")
    @ApiModelProperty(value = "商品列表缩略图")
    @TableField(value = "thumbnail_image")
    private String thumbnailImage;
    
    /** 商品详细图片 */
    @Excel(name = "商品详细图片")
    @ApiModelProperty(value = "商品详细图片")
    @TableField(value = "image")
    private String image;
    
    /** 商品轮播图片 */
    @Excel(name = "商品轮播图片")
    @ApiModelProperty(value = "商品轮播图片")
    @TableField(value = "image_urls")
    private String imageUrls;
    
    /** 商品描述 */
    @Excel(name = "商品描述")
    @ApiModelProperty(value = "商品描述")
    @TableField(value = "commodity_explain")
    private String commodityExplain;
    
    /** 商品价格 (=成本价+成本价*溢价比例) */
    @Excel(name = "商品价格 (=成本价+成本价*溢价比例)")
    @ApiModelProperty(value = "商品价格 (=成本价+成本价*溢价比例)")
    @TableField(value = "amount")
    private String amount;
    
    /** 提供方（1-云中鹤，2-百汇） */
    @Excel(name = "提供方", readConverterExp = "1=-云中鹤，2-百汇")
    @ApiModelProperty(value = "提供方（1-云中鹤，2-百汇）")
    @TableField(value = "provid")
    private String provid;
    
    /** 状态(1-有效 0-无效) */
    @Excel(name = "状态(1-有效 0-无效)")
    @ApiModelProperty(value = "状态(1-有效 0-无效)")
    @TableField(value = "status")
    private String status;
    
    /** 商品分类 */
    @Excel(name = "商品分类")
    @ApiModelProperty(value = "商品分类")
    @TableField(value = "product_cate")
    private String productCate;
    
    /** 商品产地 */
    @Excel(name = "商品产地")
    @ApiModelProperty(value = "商品产地")
    @TableField(value = "product_place")
    private String productPlace;
    
    /** 商品状态 1上架销售中, 0下架) */
    @Excel(name = "商品状态 1上架销售中, 0下架)")
    @ApiModelProperty(value = "商品状态 1上架销售中, 0下架)")
    @TableField(value = "sale_status")
    private String saleStatus;
    
    /** 商品市场价 */
    @Excel(name = "商品市场价")
    @ApiModelProperty(value = "商品市场价")
    @TableField(value = "market_price")
    private String marketPrice;
    
    /** 协议价格 */
    @Excel(name = "协议价格")
    @ApiModelProperty(value = "协议价格")
    @TableField(value = "retail_amount")
    private String retailAmount;
    
    /** 是否支持7天无理由退货 */
    @Excel(name = "是否支持7天无理由退货")
    @ApiModelProperty(value = "是否支持7天无理由退货")
    @TableField(value = "is_to_return")
    private String isToReturn;
    
    /** 所属礼包ID */
    @Excel(name = "所属礼包ID")
    @ApiModelProperty(value = "所属礼包ID")
    @TableField(value = "giftpackage_id")
    private String giftpackageId;
    
    /** 是否选品库商品(1-是) */
    @Excel(name = "是否选品库商品(1-是)")
    @ApiModelProperty(value = "是否选品库商品(1-是)")
    @TableField(value = "choose_flag")
    private String chooseFlag;
    
    /** 商品重量 */
    @Excel(name = "商品重量")
    @ApiModelProperty(value = "商品重量")
    @TableField(value = "weight")
    private String weight;
    
    /** 商品尺寸 */
    @Excel(name = "商品尺寸")
    @ApiModelProperty(value = "商品尺寸")
    @TableField(value = "size")
    private String size;
    
    /** 商品属性 0-自营实物 1-电子卡券 2-直充话费 3-京东电商 */
    @Excel(name = "商品属性 0-自营实物 1-电子卡券 2-直充话费 3-京东电商")
    @ApiModelProperty(value = "商品属性 0-自营实物 1-电子卡券 2-直充话费 3-京东电商")
    @TableField(value = "type_id")
    private String typeId;
    
    /** 供应商商品来源 1：自营，2：卡券，3：直充，4：京东，5：严选，6：天猫   21:供应商 */
    @Excel(name = "供应商商品来源 1：自营，2：卡券，3：直充，4：京东，5：严选，6：天猫   21:供应商")
    @ApiModelProperty(value = "供应商商品来源 1：自营，2：卡券，3：直充，4：京东，5：严选，6：天猫   21:供应商")
    @TableField(value = "provid_source")
    private String providSource;
    
    /** 溢价比例% */
    @Excel(name = "溢价比例%")
    @ApiModelProperty(value = "溢价比例%")
    @TableField(value = "rate")
    private String rate;
    


}
