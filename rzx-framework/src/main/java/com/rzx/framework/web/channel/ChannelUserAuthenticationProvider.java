package com.rzx.framework.web.channel;

import com.rzx.framework.web.service.ChannelUserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 渠道用户登录身份认证组件
 *
 * @author zy
 * @date 2021/6/4 16:33
 */
@Slf4j
public class ChannelUserAuthenticationProvider implements AuthenticationProvider {

    private ChannelUserDetailsServiceImpl userDetailsService;

//    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userId = (String) authentication.getPrincipal();
        //根据userId加载用户
        UserDetails user = userDetailsService.loadUserByUsername(userId);

        // 已认证的Token
        ChannelUserAuthenticationToken authenticationToken = new ChannelUserAuthenticationToken(user, user.getAuthorities());

        // 复制之前的请求信息到认证后的Token中
        authenticationToken.setDetails(authenticationToken.getDetails());
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        //如果是SmsCodeAuthenticationToken该类型，则在该处理器做登录校验
        return ChannelUserAuthenticationToken.class.isAssignableFrom(aClass);
    }


    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(ChannelUserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
