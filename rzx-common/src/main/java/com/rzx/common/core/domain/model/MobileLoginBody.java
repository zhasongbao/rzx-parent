package com.rzx.common.core.domain.model;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 手机验证码登录对象
 *
 * @author fm
 * @date 2021/06/04
 */
@Data
@ToString
public class MobileLoginBody {

    /**
     * 用户名
     */
    @NotBlank(message = "手机号码不能为空")
    @Size(max = 11, message = "手机号码长度只能11位")
    @Pattern(regexp = "^((11)|(12)|(13)|(14)|(15)|(16)|(17)|(18)|(19))\\d{9}$" , message = "手机号码格式错误")
    private String phoneNumber;

    /**
     * 验证码
     */
    @NotBlank(message = "手机验证码不能为空")
    @Size(max = 4, message = "验证码长度只能4位")
    private String verifyCode;
}
