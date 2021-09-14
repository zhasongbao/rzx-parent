package com.rzx.project.service;

/**
 * 短信发送service
 *
 * @author zy
 * @date 2021/6/7 14:26
 */
public interface ISmsCodeService {

    /**
     * 获取短信验证码
     *
     * @param phoneNumber 手机号
     * @return
     */
    String getVeriCode(String phoneNumber);
}
