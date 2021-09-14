package com.rzx.common.exception.user;

/**
 * 验证码错误异常类
 *
 * @author fm
 */
public class CaptchaException extends UserException {
    private static final long serialVersionUID = -7713424313741937443L;

    public CaptchaException() {
        super("user.jcaptcha.error", null);
    }
}
