package com.rzx.project.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author zhasbao
 * @description 礼包绑定券号生成订单入参
 * @date 2021/9/16 16:30
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PackageLinkCouponsDTO implements Serializable {

    @ApiModelProperty("任意行用户id")
    private String salemanId;

    @ApiModelProperty("商城用户ID")
    private String userInfoId;

    @NotBlank(message = "券号 couponsInfoId为空")
    @ApiModelProperty("券号ID")
    private String couponsInfoId;

    @NotBlank(message = "礼包ID giftPackageId为空")
    @ApiModelProperty("礼包ID")
    private String giftPackageId;

    @NotBlank(message = "礼包原价 initAmount不能为空")
    @ApiModelProperty("礼包原价")
    private String initAmount;

    @NotBlank(message = "礼包售价 saleAmount不能为空")
    @ApiModelProperty("礼包售价")
    private String saleAmount;

    @NotBlank(message = "礼包类型 type为空")
    @ApiModelProperty("礼包类型 0-单品 1-多选一 2-多选二")
    private String type;

    @ApiModelProperty("商品编码 commodityCode为空")
    private String commodityCode;

    @NotBlank(message = "支付方式 暂只支持他人支付")
    @ApiModelProperty("0-他人支付 1-自己支付")
    private String payType;

    @ApiModelProperty("openId")
    private String openId;

}
