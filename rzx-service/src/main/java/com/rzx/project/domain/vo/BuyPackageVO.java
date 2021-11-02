package com.rzx.project.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author zhasbao
 * @description 购买礼包出参
 * @date 2021/9/16 16:30
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuyPackageVO implements Serializable {

    @ApiModelProperty("订单号")
    private String orderId;

    @ApiModelProperty("订单金额")
    private String saleAmount;

    @ApiModelProperty("支付方式")
    private String payWay;

    @ApiModelProperty("回调地址")
    private String notifyUrl;

}
