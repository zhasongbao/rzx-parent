package com.rzx.project.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author zhasbao
 * @version 1.0
 * @title
 * @description
 * @company 深圳分米互联科技有限公司
 * @date 2021/9/16 16:30
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO implements Serializable {

    @NotBlank(message = "用户openId不能为空")
    @ApiModelProperty("用户openId")
    private String userId;

    @NotBlank(message = "渠道用户类型不能为空")
    @ApiModelProperty("渠道用户类型(0-代理分销用户 1-普通用户 )")
    private String userType;

    @NotBlank(message = "渠道来源不能为空")
    @ApiModelProperty("渠道来源(1-任货行 2-任意行 3-任通行)")
    private String source;
}
