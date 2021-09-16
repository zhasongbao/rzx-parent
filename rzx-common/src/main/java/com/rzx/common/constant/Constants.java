package com.rzx.common.constant;

import org.springframework.beans.factory.annotation.Value;

/**
 * 通用常量信息
 *
 * @author zy
 */
public class Constants {

    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * http请求
     */
    public static final String HTTP = "http://";

    /**
     * https请求
     */
    public static final String HTTPS = "https://";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "rzx_captcha_codes:";

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "rzx_login_tokens:";

    /**
     * app登录用户 redis key
     */
    public static final String APP_LOGIN_TOKEN_KEY = "rzx_app_login_tokens:";

    /**
     * 防重提交 redis key
     */
    public static final String REPEAT_SUBMIT_KEY = "rzx_repeat_submit:";

    /**
     * 验证码有效期（分钟）
     */
    public static final Long CAPTCHA_EXPIRATION = 2L;

    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    public static final String LOGIN_USER_KEY = "login_user_key";

    /**
     * app令牌前缀
     */
    public static final String APP_LOGIN_USER_KEY = "app_login_user_key";

    /**
     * 用户ID
     */
    public static final String JWT_USERID = "userid";

    /**
     * 用户名称
     */
    public static final String JWT_USERNAME = "sub";

    /**
     * 用户头像
     */
    public static final String JWT_AVATAR = "avatar";

    /**
     * 创建时间
     */
    public static final String JWT_CREATED = "created";

    /**
     * 用户权限
     */
    public static final String JWT_AUTHORITIES = "authorities";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";

    /**成功状态码*/
    public final static String SUCCESS_CODE = "1";
    /** 接口返回值 失败状态值 */
    public static final String FAILURE_CODE = "0";

    /** 通用标志-是 */
    public static final String YES_FLAG = "1";
    /** 通用标志-否 */
    public static final String NO_FLAG = "0";
    public static final String SUCCESS_MSG = "请求成功!";
    public static final String SERVER_FAILURE_MSG = "服务器异常!";
    public static final String FAILURE_MSG = "请求失败!";

    public static final String YUNZHONGHE_REQUESTAPILOG = "YUNZHONGHE_REQUESTAPILOG";//请求云中鹤日志及结果
    public static final String BAILIHUI_REQUESTAPILOG = "BAILIHUI_REQUESTAPILOG";//请求云中鹤日志及结果

}
