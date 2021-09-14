//package com.rzx.framework.security.filter;
//
//import java.io.IOException;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.rzx.common.core.domain.model.AppLoginUser;
//import com.rzx.framework.web.service.AppTokenService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import com.rzx.common.core.domain.model.LoginUser;
//import com.rzx.common.utils.SecurityUtils;
//import com.rzx.common.utils.StringUtils;
//import com.rzx.framework.web.service.TokenService;
//
///**
// * token过滤器 验证token有效性
// *
// * @author zy
// */
//@Component
//public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
//    @Autowired
//    private TokenService tokenService;
//    @Autowired
//    private AppTokenService appTokenService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//        AppLoginUser appLoginUser = appTokenService.getLoginUser(request);
//        LoginUser loginUser = tokenService.getLoginUser(request);
//
//        boolean isAuthentication = StringUtils.isNull(SecurityUtils.getAuthentication());
//        boolean isLoginUser = StringUtils.isNotNull(loginUser) || StringUtils.isNotNull(appLoginUser);
//        if (isLoginUser && isAuthentication) {
//            if (StringUtils.isNotNull(loginUser)) {
//                tokenService.verifyToken(loginUser);
//                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            } else {
//                appTokenService.verifyToken(appLoginUser);
//                SmsCodeAuthenticationToken authenticationToken = new SmsCodeAuthenticationToken(appLoginUser, appLoginUser.getAuthorities());
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            }
//        }
//        chain.doFilter(request, response);
//    }
//}
