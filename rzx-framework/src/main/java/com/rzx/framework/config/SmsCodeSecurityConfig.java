//package com.rzx.framework.config;
//
//import com.rzx.framework.security.filter.SmsCodeAuthenticationFilter;
//import com.rzx.framework.security.handle.MyAuthenticationSuccessHandler;
//import com.rzx.framework.web.sms.SmsCodeAuthenticationProvider;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.DefaultSecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//
///**
// * 短信验证码安全配置，串联自定义短信验证码验证流程
// *
// * @author zy
// * @date 2021/6/4 18:59
// */
//@Slf4j
//@Component
//public class SmsCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
//
//    @Autowired
//    private SmsCodeUserDetailsServiceImpl userDetailsService;
//
//    @Autowired
//    private MyAuthenticationSuccessHandler successHandler;
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        // 此处采用new的方式，而不是@Component和@Autowired结合，目的为了方便安装和卸载，可重用可移植性强
//        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
//
//        // 设置AuthenticationManager
//        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
//        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
//
//        // 短信验证码认证Provider类
//        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
//        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);
//
//        // 设置短信验证码认证Provider类到AuthenticationManager管理集合中
//        http.authenticationProvider(smsCodeAuthenticationProvider)
//                // 设置短信验证码在用户名密码验证过滤器之后验证
//                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//}
