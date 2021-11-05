package com.rzx.project.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
public class ExchangeRecordListDTO implements Serializable {

    @ApiModelProperty("商城用户ID")
    private String userInfoId;

    @ApiModelProperty("收货人手机")
    private String receivePhone;

}
