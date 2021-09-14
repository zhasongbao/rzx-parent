//package com.rzx.app.controller;
//
//import com.rzx.common.constant.Constants;
//import com.rzx.common.core.domain.AjaxResult;
//import com.rzx.common.core.domain.model.MobileLoginBody;
//import com.rzx.framework.web.service.SmsCodeLoginService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 手机验证码登录
// *
// * @author zy
// * @date 2021-06-04
// */
//@Api(value = "手机验证码登录控制器")
//@ApiModel(value = "手机验证码登录Controller")
//@RestController
//public class AppLoginController {
//
//    @Autowired
//    private SmsCodeLoginService smsCodeLoginService;
//
//    @PostMapping(value = "/login")
//    @ApiOperation(value = "app登录接口")
//    public AjaxResult<Map<String, String>> appLogin(@RequestBody MobileLoginBody loginBody) {
//        String token = smsCodeLoginService.smsCodeLogin(loginBody.getPhoneNumber(), loginBody.getVerifyCode());
//        Map<String, String> map = new HashMap<>(2);
//        map.put(Constants.TOKEN, token);
//        return AjaxResult.success(map);
//    }
//
//}
