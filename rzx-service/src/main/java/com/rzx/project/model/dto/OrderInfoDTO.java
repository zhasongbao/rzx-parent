package com.rzx.project.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author zhasbao
 * @description
 * @date 2021/9/16 16:30
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoDTO implements Serializable {

//    @NotBlank(message = "用户id不能为空")
    @ApiModelProperty("用户openId")
    private String userId;
}
