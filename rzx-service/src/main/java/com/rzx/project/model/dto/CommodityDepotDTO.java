package com.rzx.project.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author zhasbao
 * @description 商品库列表入参
 * @date 2021/11/03 10:40
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommodityDepotDTO implements Serializable {

    @ApiModelProperty("选品库类型（默认查询商城选品库） 0-广西捷通商城")
    private String type;

}
