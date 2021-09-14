//package com.rzx.app.controller;
//
//import com.rzx.common.core.domain.AjaxResult;
//import com.rzx.project.service.ISmsCodeService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
///**
// * 短信验证码controller
// *
// * @author zy
// * @date 2021/6/7 14:25
// */
//@Api(value = "手机验证码登录控制器")
//@ApiModel(value = "手机验证码登录Controller")
//@RestController
//@RequestMapping(value = "/code")
//public class SmsCodeController {
//    @Autowired
//    private ISmsCodeService smsCodeService;
//
//    /**
//     * 获取短信验证码
//     *
//     * @param phoneNumber 手机号码s
//     * @return
//     */
//    @GetMapping(value = "/getVeriCode")
//    @ApiOperation(value = "获取短信验证码", httpMethod = "GET")
//    public AjaxResult<String> getVeriCode(@RequestParam String phoneNumber) {
//        return AjaxResult.successMsg(smsCodeService.getVeriCode(phoneNumber));
//    }
//}
