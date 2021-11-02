package com.rzx.project.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author zhasbao
 * @description 购买礼包入参
 * @date 2021/9/16 16:30
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuyPackageDTO implements Serializable {

    @NotBlank(message = "商城用户ID userInfoId为空")
    @ApiModelProperty("商城用户ID")
    private String userInfoId;

    @NotBlank(message = "礼包ID giftPackageId为空")
    @ApiModelProperty("礼包ID")
    private String giftPackageId;

    @NotBlank(message = "礼包类型 type为空")
    @ApiModelProperty("礼包类型")
    private String type;

    @NotBlank(message = "礼包原价不能为空")
    @ApiModelProperty("礼包原价 initAmount为空")
    private String initAmount;

    @NotBlank(message = "礼包售价不能为空")
    @ApiModelProperty("礼包售价 saleAmount为空")
    private String saleAmount;

}
