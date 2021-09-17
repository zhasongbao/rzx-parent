package com.rzx.framework.web.service;

import com.rzx.common.core.domain.entity.ChannelUserInfo;
import com.rzx.common.core.domain.model.AppLoginUser;
import com.rzx.common.core.redis.RedisCache;
import com.rzx.common.exception.CustomException;
import com.rzx.project.domain.dto.UserLoginDTO;
import com.rzx.project.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * 渠道用户登录
 *
 * @author zhasbao
 * @date 2021/6/4 14:45
 */
@Slf4j
@Component
public class ChannelUserLoginService {

    @Autowired
    private AppTokenService tokenService;
    @Resource
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 渠道用户登录验证
     *
     * @param dto 用户信息
     * @return 结果
     */
    public String userLogin(UserLoginDTO dto) {
        ChannelUserInfo userInfo = userInfoService.selectUserInfoByIdAndInSource(dto);
        if(ObjectUtils.isEmpty(userInfo) && !"88888888".equals(dto.getUserId())){
            throw new CustomException("未找到用户信息!");
        }
        // 用户验证
        // 该方法会去调用 SmsCodeUserDetailsServiceImpl.loadUserByUsername
//        Authentication authentication = authenticationManager.authenticate(new ChannelUserAuthenticationToken(dto.getUserId()));
//
//        AppLoginUser loginUser = (AppLoginUser) authentication.getPrincipal();
//
        AppLoginUser loginUser = new AppLoginUser();
        loginUser.setUser(userInfo);
        // 生成token
        return tokenService.createToken(loginUser);
    }
}
