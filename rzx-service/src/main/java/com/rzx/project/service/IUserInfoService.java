package com.rzx.project.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rzx.common.constant.CacheConstants;
import com.rzx.common.core.domain.entity.ChannelUserInfo;
import com.rzx.common.core.domain.model.AppLoginUser;
import com.rzx.common.core.text.Convert;
import com.rzx.common.exception.CustomException;
import com.rzx.common.utils.StringUtils;
import com.rzx.project.domain.UserInfo;
import com.rzx.project.domain.dto.UserLoginDTO;
import org.springframework.security.core.Authentication;

/**
 * 用户信息Service接口
 *
 * @author zy
 * @date 2021-09-16
 */
public interface IUserInfoService extends IService<UserInfo> {

    /**
     * 查询用户信息
     *
     * @param userInfoId 用户信息ID
     * @return 用户信息
     */
    UserInfo selectUserInfoById(String userInfoId);

    /**
     * 查询用户信息
     *
     * @return 用户信息
     */
    ChannelUserInfo selectUserInfoByIdAndInSource(UserLoginDTO dtoe);

    /**
     * 查询用户信息列表
     *
     * @param userInfo 用户信息
     * @return 用户信息集合
     */
    List<UserInfo> selectUserInfoList(UserInfo userInfo);

    /**
     * 新增用户信息
     *
     * @param userInfo 用户信息
     * @return 结果
     */
    int insertUserInfo(UserInfo userInfo);

    /**
     * 修改用户信息
     *
     * @param userInfo 用户信息
     * @return 结果
     */
    int updateUserInfo(UserInfo userInfo);

    /**
     * 批量删除用户信息
     *
     * @param userInfoIds 需要删除的用户信息ID
     * @return 结果
     */
    int deleteUserInfoByIds(String[] userInfoIds);

    /**
     * 删除用户信息信息
     *
     * @param userInfoId 用户信息ID
     * @return 结果
     */
    int deleteUserInfoById(String userInfoId);

}
