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
 * @description 获取用户信息入参
 * @date 2021/11/03 10:40
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChannelUserDTO implements Serializable {

    @NotBlank(message = "渠道用户id不能为空!")
    @ApiModelProperty("渠道用户id")
    private String userId;

    @NotBlank(message = "渠道来源不能为空!")
    @ApiModelProperty("渠道来源(1-任货行 2-任意行 3-任通行 4-广西商城)")
    private String source;

    @NotBlank(message = "渠道用户类型不能为空!")
    @ApiModelProperty("渠道用户类型(0-渠道登录用户 1-渠道游客用户 )")
    private String userType;

}
