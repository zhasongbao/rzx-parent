package com.rzx.project.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhasbao
 * @description 商品库列表出参
 * @date 2021/11/03 10:40
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommodityDepotVO implements Serializable {

    @ApiModelProperty("商品唯一id")
    private String commodityConfigId;

    @ApiModelProperty("商品编码")
    private String commodityCode;

    @ApiModelProperty("商品名称")
    private String commodityName;

    @ApiModelProperty("商品缩略图")
    private String thumbnailImage;

    @ApiModelProperty("商品详情")
    private String image;

    @ApiModelProperty("商品轮播图片")
    private String imageUrls;

    @ApiModelProperty("市场价")
    private String marketPrice;

    @ApiModelProperty("售价金额")
    private String amount;

    @ApiModelProperty("供应商（1-云中鹤，2-百汇）")
    private String provid;

    @ApiModelProperty("百礼汇商品属性 0-自营实物 1-电子卡券 2-直充话费 3-京东电商")
    private String typeId;

    @ApiModelProperty("供应商商品来源 1：自营，2：卡券，3：直充，4：京东，5：严选，6：天猫 21:供应商")
    private String providSource;

    @ApiModelProperty("商品状态 1：上架销售中 ，0：下架")
    private String saleStatus;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

}
