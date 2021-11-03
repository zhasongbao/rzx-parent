package com.rzx.project.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author zhasbao
 * @description 马上兑换接口入参
 * @date 2021/11/03 10:40
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NowExchangeDTO implements Serializable {

    @NotBlank(message = "券号ID不能为空")
    @ApiModelProperty("券号ID")
    private String couponsInfoId;

    @NotBlank(message = "兑换码不能为空")
    @ApiModelProperty("兑换码")
    private String couponsPwd;

}
