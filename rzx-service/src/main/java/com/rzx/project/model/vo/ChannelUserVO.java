package com.rzx.project.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhasbao
 * @description 获取用户信息出参
 * @date 2021/11/03 10:40
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChannelUserVO implements Serializable {

    @ApiModelProperty("任智行用户主键")
    private String userInfoId;

    @ApiModelProperty("渠道用户id")
    private String userId;

    @ApiModelProperty("渠道来源(1-任货行 2-任意行 3-任通行 4-广西商城)")
    private String source;

    @ApiModelProperty("用户积分")
    private Integer score;

    @ApiModelProperty("渠道用户类型(0-渠道登录用户 1-渠道游客用户 )")
    private String userType;

    @ApiModelProperty("用户创建时间")
    private LocalDateTime createTime;


}
