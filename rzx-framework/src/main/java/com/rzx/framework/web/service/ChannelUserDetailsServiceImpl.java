package com.rzx.framework.web.service;


import com.rzx.common.constant.Constants;
import com.rzx.common.core.domain.entity.ChannelUserInfo;
import com.rzx.common.core.domain.model.AppLoginUser;
import com.rzx.common.enums.UserStatus;
import com.rzx.common.exception.BaseException;
import com.rzx.framework.remote.hcb.RemoteTruckUserWallet;
import com.rzx.project.domain.dto.UserLoginDTO;
import com.rzx.project.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 渠道用户登录处理
 *
 * @author zy
 * @date 2021/6/4 17:47
 */
@Slf4j
@Service
public class ChannelUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RemoteTruckUserWallet remoteTruckUserWallet;
    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 查询用户信息
     *
     * @param userId 手机号码
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserLoginDTO dto = UserLoginDTO.builder().userId(userId).build();
        ChannelUserInfo userInfo = userInfoService.selectUserInfoByIdAndInSource(dto);
        if (Objects.isNull(userInfo)) {
            log.info("登录用户：{} 用户不存在.", userId);
            throw new BaseException("登录用户：{} 用户不存在!!", userId);
        } else {
            String userStatus = userInfo.getStatus();
            if (Constants.NO_FLAG.equals(userStatus)) {
                log.info("登录用户无效", userId);
                throw new BaseException("对不起，您的账号：" + userId + " 无效");
            }
        }
        return createLoginUser(userInfo);
    }

    public UserDetails createLoginUser(ChannelUserInfo user) {
        return new AppLoginUser(user);
    }
}
