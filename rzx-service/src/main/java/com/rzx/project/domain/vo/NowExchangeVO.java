package com.rzx.project.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author zhasbao
 * @description 马上兑换出参
 * @date 2021/11/03 11:28
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NowExchangeVO implements Serializable {

    @ApiModelProperty("订单唯一ID")
    private String salesOrderId;

    @ApiModelProperty("礼包ID")
    private String giftPackageId;

    @ApiModelProperty("礼包原价")
    private String initTotalAmount;

    @ApiModelProperty("礼包售价")
    private String saleTotalAmount;

    @ApiModelProperty("礼包类型(0-单品 1-多选一)")
    private String type;

    @ApiModelProperty("商品编码 单品有值")
    private String commodityCode;

}
