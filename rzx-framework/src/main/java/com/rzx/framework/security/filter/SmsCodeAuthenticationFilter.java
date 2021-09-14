//package com.rzx.framework.security.filter;
//
//import com.rzx.common.enums.HttpMethod;
//import com.rzx.common.utils.StringUtils;
//import com.rzx.framework.web.sms.SmsCodeAuthenticationToken;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.util.Assert;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 短信验证码过滤器
// * 参照{@link org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter}
// *
// * @author zy
// * @date 2021/6/4 16:05
// */
//public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
//
//    public static final String MOBILE_KEY = "mobile";
//
//    private String mobileParameter = MOBILE_KEY;
//
//    private boolean postOnly = true;
//
//    public SmsCodeAuthenticationFilter() {
//        super(new AntPathRequestMatcher("/login/mobile" , HttpMethod.POST.toString()));
//    }
//
//    public SmsCodeAuthenticationFilter(AuthenticationManager authManager) {
//        super(new AntPathRequestMatcher("/mobile/login" , HttpMethod.POST.toString()));
//        setAuthenticationManager(authManager);
//        setApplicationEventPublisher(eventPublisher);
//    }
//
//
//    public SmsCodeAuthenticationFilter(AuthenticationManager authManager,
//                                       AuthenticationSuccessHandler successHandler,
//                                       AuthenticationFailureHandler failureHandler,
//                                       ApplicationEventPublisher eventPublisher) {
//        super(new AntPathRequestMatcher("/mobile/login" , HttpMethod.POST.toString()));
//        setAuthenticationManager(authManager);
//        setAuthenticationSuccessHandler(successHandler);
//        setAuthenticationFailureHandler(failureHandler);
//        setApplicationEventPublisher(eventPublisher);
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
//
//        if (postOnly && !HttpMethod.POST.toString().equals(httpServletRequest.getMethod())) {
//            throw new AuthenticationServiceException("Authentication method not supported: " + httpServletRequest.getMethod());
//        }
//
//        String mobile = StringUtils.trimToEmpty(httpServletRequest.getParameter(MOBILE_KEY));
//        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);
//
//        // Allow subclasses to set the "details" property
//        setDetails(httpServletRequest, authRequest);
//
//        //手机号和验证码 传递到认证管理ProviderManager
//        return this.getAuthenticationManager().authenticate(authRequest);
//    }
//
//    /**
//     * Provided so that subclasses may configure what is put into the
//     * authentication request's details property.
//     *
//     * @param request     that an authentication request is being created for
//     * @param authRequest the authentication request object that should have its
//     *                    details set
//     */
//    protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
//        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
//    }
//
//    /**
//     * Sets the parameter name which will be used to obtain the mobile from the
//     * login request.
//     *
//     * @param mobileParameter the parameter name. Defaults to "mobile".
//     */
//    public void setMobileParameter(String mobileParameter) {
//        Assert.hasText(mobileParameter, "mobile parameter must not be empty or null");
//        this.mobileParameter = mobileParameter;
//    }
//
//    /**
//     * Defines whether only HTTP POST requests will be allowed by this filter.
//     * If set to true, and an authentication request is received which is not a
//     * POST request, an exception will be raised immediately and authentication
//     * will not be attempted. The <tt>unsuccessfulAuthentication()</tt> method
//     * will be called as if handling a failed authentication.
//     * <p>
//     * Defaults to <tt>true</tt> but may be overridden by subclasses.
//     */
//    public void setPostOnly(boolean postOnly) {
//        this.postOnly = postOnly;
//    }
//
//    public final String getMobileParameter() {
//        return mobileParameter;
//    }
//
//}
