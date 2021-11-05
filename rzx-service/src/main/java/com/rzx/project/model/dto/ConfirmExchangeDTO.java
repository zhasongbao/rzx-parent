package com.rzx.project.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author zhasbao
 * @description 确认兑换接口入参
 * @date 2021/11/03 10:40
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmExchangeDTO implements Serializable {

    @NotBlank(message = "订单唯一ID不能为空")
    @ApiModelProperty("订单唯一ID")
    private String salesOrderId;

    @NotBlank(message = "商品编码不能为空")
    @ApiModelProperty("商品编码")
    private String commodityCode;

    @NotBlank(message = "商品供应商不能为空")
    @ApiModelProperty("商品供应商")
    private String provid;

    @NotBlank(message = "礼包原价不能为空")
    @ApiModelProperty("礼包初始总金额")
    private String initTotalAmount;

    @NotBlank(message = "礼包售价不能为空")
    @ApiModelProperty("礼包总售价")
    private String saleTotalAmount;

    @NotBlank(message = "省id不能为空")
    @ApiModelProperty("省id")
    private String province;

    @NotBlank(message = "省名称不能为空")
    @ApiModelProperty("省名称")
    private String provinceName;

    @NotBlank(message = "市id不能为空")
    @ApiModelProperty("市id")
    private String city;

    @NotBlank(message = "市名称不能为空")
    @ApiModelProperty("市名称")
    private String cityName;

    @NotBlank(message = "区/县id不能为空")
    @ApiModelProperty("区/县id")
    private String county;

    @NotBlank(message = "区/县名称不能为空")
    @ApiModelProperty("区/县名称")
    private String countyName;

    @ApiModelProperty("乡/镇id")
    private String town;

    @ApiModelProperty("乡/镇名称")
    private String townName;

    @NotBlank(message = "收货人地址不能为空")
    @ApiModelProperty("收货人地址")
    private String receiveAddress;

    @NotBlank(message = "收货人手机不能为空")
    @ApiModelProperty("收货人手机")
    private String receivePhone;

    @NotBlank(message = "收货人姓名不能为空")
    @ApiModelProperty("收货人姓名")
    private String receiveName;







}
