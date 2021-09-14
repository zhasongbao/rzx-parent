package com.rzx.common.core.domain;

import com.rzx.common.core.emums.HttpCodeEnum;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 操作消息提醒
 *
 * @author zy
 */
@Data
@ToString
public class AjaxResult<T> implements Serializable {

    private static final long serialVersionUID = -2894405969787154814L;

    /** 状态码 */
    public static final String CODE_TAG = "code";

    /** 返回内容 */
    public static final String MSG_TAG = "msg";

    /** 数据对象 */
    public static final String DATA_TAG = "data";

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回内容
     */
    private String msg;

    /**
     * 数据对象
     */
    private T data;

    /**
     * 返回结果
     */
    private boolean success;

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public AjaxResult() {
    }

    public AjaxResult(T data) {
        this.data = data;
    }

    public AjaxResult(Integer code, String msg, boolean success) {
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    public AjaxResult(Integer code, String msg, T data, boolean success) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.success = success;
    }

    /**
     * 追加2个方法 hmm
     *
     * @param codeEnum
     * @param data
     * @param success
     */
    public AjaxResult(HttpCodeEnum codeEnum, T data, boolean success) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMessage();
        this.data = data;
        this.success = success;
    }

    public AjaxResult(HttpCodeEnum codeEnum, boolean success) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMessage();
        this.success = success;
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static <T> AjaxResult<T> success() {
        return new AjaxResult<T>(HttpCodeEnum.OK.getCode(), HttpCodeEnum.OK.getMessage(), null, true);
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static <T> AjaxResult<T> success(T data) {
        return new AjaxResult<T>(HttpCodeEnum.OK.getCode(), HttpCodeEnum.OK.getMessage(), data, true);
    }

    /**
     * 成功请求（有消息）
     *
     * @param msg
     * @return
     */
    public static <T> AjaxResult<T> successMsg(String msg) {
        return new AjaxResult<T>(HttpCodeEnum.OK.getCode(), msg, null, true);
    }

    /**
     * 操作失败
     *
     * @return
     */
    public static <T> AjaxResult<T> fail() {
        return new <T>AjaxResult<T>(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMessage(), false);
    }

    /**
     * 操作失败
     *
     * @return
     */
    public static <T> AjaxResult<T> fail(T data) {
        return new AjaxResult<T>(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMessage(), data, false);
    }

    /**
     * 服务器错误
     *
     * @return
     */
    public static <T> AjaxResult<T> error() {
        return new AjaxResult<T>(HttpCodeEnum.INTERNAL_SERVER_ERROR.getCode(), HttpCodeEnum.INTERNAL_SERVER_ERROR.getMessage(), false);
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static <T> AjaxResult<T> error(String msg) {
        return new AjaxResult<T>(HttpCodeEnum.INTERNAL_SERVER_ERROR.getCode(), msg, null,false);
    }

    /**
     * 服务器错误
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> AjaxResult<T> error(String msg, T data) {
        return new AjaxResult<T>(HttpCodeEnum.INTERNAL_SERVER_ERROR.getCode(), msg, data, false);
    }

    /**
     * 服务器错误
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> AjaxResult<T> error(Integer code, String msg) {
        return new AjaxResult<T>(code, msg, null,false);
    }

    /**
     * 服务器错误
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> AjaxResult<T> error(Integer code, String msg, T data) {
        return new AjaxResult<T>(code, msg, data, false);
    }

    /**
     * 操作失败
     *
     * @param httpCodeEnum
     * @return
     */
    public static <T> AjaxResult<T> error(HttpCodeEnum httpCodeEnum) {
        return new AjaxResult<T>(httpCodeEnum, null,false);
    }

    /**
     * 参数错误
     *
     * @return
     */
    public static <T> AjaxResult<T> paramError() {
        return new AjaxResult<T>(HttpCodeEnum.INVALID_REQUEST.getCode(), HttpCodeEnum.INVALID_REQUEST.getMessage(), null,false);
    }

    /**
     * 参数错误
     *
     * @param data
     * @return
     */
    public static <T> AjaxResult<T> paramError(T data) {
        return new AjaxResult<T>(HttpCodeEnum.INVALID_REQUEST.getCode(), HttpCodeEnum.INVALID_REQUEST.getMessage(), data, false);
    }

    public static <T> AjaxResult<T> paramError(String msg) {
        return new AjaxResult<T>(HttpCodeEnum.INVALID_REQUEST.getCode(), msg, null,false);
    }

    /**
     * 没有权限
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> AjaxResult<T> errorToken(String msg) {
        return new AjaxResult<T>(HttpCodeEnum.UNAUTHORIZED.getCode(), msg, null,false);
    }

    /**
     * 没有权限
     *
     * @param data
     * @return
     */
    public static <T> AjaxResult<T> unAuthorized(T data) {
        return new AjaxResult<T>(HttpCodeEnum.UNAUTHORIZED.getCode(), HttpCodeEnum.UNAUTHORIZED.getMessage(), data, false);
    }

    /**
     * 禁止访问
     *
     * @return
     */
    public static <T> AjaxResult<T> errorAuth(String msg) {
        return new AjaxResult<T>(HttpCodeEnum.FORBIDDEN.getCode(), msg, null,false);
    }

}
