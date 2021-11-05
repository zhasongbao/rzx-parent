package com.rzx.app.controller;

import com.rzx.common.constant.Constants;
import com.rzx.common.core.domain.AjaxResult;
import com.rzx.framework.web.service.ChannelUserLoginService;
import com.rzx.project.model.dto.UserLoginDTO;
import com.rzx.project.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 任智行登录接口
 *
 * @author zhasbao
 * @date 2021-06-04
 */
@Api(value = "通过渠道和渠道用户id登录控制器")
@ApiModel(value = "登录Controller")
@RestController
public class AppLoginController {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private ChannelUserLoginService smsCodeLoginService;


//    @PostMapping(value = "/login")
//    @ApiOperation(value = "app登录接口")
//    public AjaxResult<Map<String, String>> appLogin(@RequestBody MobileLoginBody loginBody) {
//        String token = smsCodeLoginService.smsCodeLogin(loginBody.getPhoneNumber(), loginBody.getVerifyCode());
//        Map<String, String> map = new HashMap<>(2);
//        map.put(Constants.TOKEN, token);
//        return AjaxResult.success(map);
//    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "app登录接口")
    public AjaxResult<Map<String, String>> appLogin(@RequestBody UserLoginDTO dto) {
        String token = smsCodeLoginService.userLogin(dto);
        Map<String, String> map = new HashMap<>(2);
        map.put(Constants.TOKEN, token);
        return AjaxResult.success(map);
    }

}
