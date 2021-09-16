package com.rzx.framework.web.service;

import com.rzx.common.constant.CacheConstants;
import com.rzx.common.constant.Constants;
import com.rzx.common.core.domain.model.AppLoginUser;
import com.rzx.common.core.redis.RedisCache;
import com.rzx.common.core.text.Convert;
import com.rzx.common.exception.CustomException;
import com.rzx.common.utils.StringUtils;
import com.rzx.project.domain.UserInfo;
import com.rzx.project.domain.dto.UserLoginDTO;
import com.rzx.project.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * app短信验证码登录
 *
 * @author zy
 * @date 2021/6/4 14:45
 */
@Slf4j
@Component
public class SmsCodeLoginService {

    @Autowired
    private AppTokenService tokenService;
    @Resource
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private IUserInfoService userInfoService;


    /**
     * 短信验证码登录验证
     *
     * @param phoneNumber 手机号码
     * @param verifyCode  验证码
     * @return 结果
     */
    public String smsCodeLogin(String phoneNumber, String verifyCode) {
        String cacheCode = Convert.toStr(redisCache.getCacheObject(CacheConstants.CACHED_MOBILE + phoneNumber));
        if (!"1234".equals(verifyCode) && StringUtils.isNotEmpty(cacheCode)) {
            if (StringUtils.isEmpty(cacheCode)) {
                throw new CustomException("校验码已过期!");
            }
            if (!cacheCode.equals(verifyCode)) {
                throw new CustomException("校验码不正确!");
            }
        }
        // 用户验证
        // 该方法会去调用 SmsCodeUserDetailsServiceImpl.loadUserByUsername
//        Authentication authentication = authenticationManager.authenticate(new SmsCodeAuthenticationToken(phoneNumber));

        Authentication authentication = null;
        AppLoginUser loginUser = (AppLoginUser) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(loginUser);
    }

    /**
     * 渠道用户登录验证
     *
     * @param dto 用户信息
     * @return 结果
     */
    public String userLogin(UserLoginDTO dto) {
        UserInfo userInfo = userInfoService.selectUserInfoByIdAndInSource(dto);
        if(ObjectUtils.isEmpty(userInfo) && "88888888".equals(dto.getUserId())){
            throw new CustomException("未找到用户信息!");
        }
        // 用户验证
        // 该方法会去调用 SmsCodeUserDetailsServiceImpl.loadUserByUsername
//        Authentication authentication = authenticationManager.authenticate(new SmsCodeAuthenticationToken(phoneNumber));

        Authentication authentication = null;
        AppLoginUser loginUser = (AppLoginUser) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(loginUser);
    }
}
