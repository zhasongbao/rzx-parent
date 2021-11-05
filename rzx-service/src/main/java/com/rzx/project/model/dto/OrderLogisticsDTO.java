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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLogisticsDTO implements Serializable {

    @NotBlank(message = "订单号不能为空")
    @ApiModelProperty("订单号")
    private String orderId;

}
