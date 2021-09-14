package com.rzx.common.core.emums;

/**
 * @author zy
 * @data 2020/8/21 15:55
 * http 状态码
 * ----------------------------------------------------------------------------
 * 200 OK - [GET]：服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）。
 * 400 INVALID REQUEST - [POST/PUT/PATCH]：用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。
 * 401 Unauthorized - [*]：表示用户没有权限（令牌、用户名、密码错误）。
 * 403 Forbidden - [*] 表示用户得到授权（与401错误相对），但是访问是被禁止的。
 * 404 NOT FOUND - [*]：用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的。
 * 406 Not Acceptable - [GET]：用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）。
 * 410 Gone -[GET]：用户请求的资源被永久删除，且不会再得到的。
 * 422 Unprocesable dto - [POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误。
 * 500 INTERNAL SERVER ERROR - [*]：服务器发生错误，用户将无法判断发出的请求是否成功。
 * 600 UN_KNOW_ERROR - 未知错误
 * ----------------------------------------------------------------------------
 */
public enum HttpCodeEnum {

    /**
     * 操作成功
     */
    OK(200, "操作成功"),

    /**
     * 参数错误
     */
    INVALID_REQUEST(400, "参数异常！"),

    /**
     * 没有权限
     */
    UNAUTHORIZED(401, "没有权限"),

    /**
     * 禁止访问
     */
    FORBIDDEN(403, "禁止访问"),

    /**
     * 资源不存在
     */
    NOT_FOUND(404, "资源不存在"),

    /**
     * 请求的格式不正确
     */
    NOT_ACCEPTABLE(406, "请求的格式不正确"),

    /**
     * 数据被删除
     */
    GONE(410, "数据被删除"),

    /**
     * 参数验证错误
     */
    UNPROCESSABLE_ENTITY(422, "参数验证错误"),

    /**
     * 请设定正确的客户端类型(clientType:pc|h5)
     */
    CLIENT_TYPE_ERROR(423, "请设定正确的客户端类型(clientType:pc|h5)"),

    /**
     * 缺少必需的请求正文,请设置正确的请求体,例:(application/json)
     */
    REQUIRED_REQUEST_BODY_IS_MISSING(424, "缺少必需的请求正文,请设置正确的请求体,例:(application/json)"),

    /**
     * 方法参数类型不匹配异常,请确认请求路径、请求方式是否正确
     */
    METHOD_ARGUMENT_TYPE_MISMATCH(425, "方法参数类型不匹配异常,请确认请求路径、请求方式是否正确"),

    /**
     * 服务器繁忙
     */
    INTERNAL_SERVER_ERROR(500, "服务器繁忙"),

    /**
     * 操作失败
     */
    FAIL(501, "操作失败"),

    /**
     * 调用feign失败
     */
    FEIGN_FAIL(502, "调用feign失败"),

    /**
     * excel导入失败
     */
    EXCEL_IN_FAIL(503, "excel导入失败"),

    /**
     * 模型不存在
     */
    MODEL_NOT_EXIST(1000, "模型不存在"),

    /**
     * 业务逻辑验证未通过
     */
    VERIFICATION_FAILED(1001, "业务逻辑验证未通过"),

    /**
     * 用户未登录或token已失效
     */
    USERNAME_OR_PASSWORD_ERR(2000, "用户未登录或token已失效"),

    /**
     * 默认头像不可删除
     */
    DELETE_DEFAULT_PHOTO_ERR(2001, "默认头像不可删除"),

    /**
     * 认证到期
     */
    AUTH_EXPIRED(3000, "认证到期"),

    /**
     * token无效
     */
    TOKEN_ERR(3001, "token无效");

    private final int code;
    private final String message;

    HttpCodeEnum(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

}
