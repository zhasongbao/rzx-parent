package com.rzx.project.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rzx.common.core.domain.entity.ChannelUserInfo;
import com.rzx.project.model.domain.UserInfo;
import com.rzx.project.model.dto.UserLoginDTO;

/**
 * 任智行渠道用户信息Service接口
 *
 * @author zy
 * @date 2021-09-28
 */
public interface IUserInfoService extends IService<UserInfo> {

    /**
     * 查询任智行渠道用户信息
     *
     * @param userInfoId 任智行渠道用户信息ID
     * @return 任智行渠道用户信息
     */
    UserInfo selectUserInfoById(String userInfoId);

    /**
     * 查询用户信息
     *
     * @return 用户信息
     */
    ChannelUserInfo selectUserInfoByIdAndInSource(UserLoginDTO dtoe);

    /**
     * 查询任智行渠道用户信息列表
     *
     * @param userInfo 任智行渠道用户信息
     * @return 任智行渠道用户信息集合
     */
    List<UserInfo> selectUserInfoList(UserInfo userInfo);

    /**
     * 新增任智行渠道用户信息
     *
     * @param userInfo 任智行渠道用户信息
     * @return 结果
     */
    int insertUserInfo(UserInfo userInfo);

    /**
     * 修改任智行渠道用户信息
     *
     * @param userInfo 任智行渠道用户信息
     * @return 结果
     */
    int updateUserInfo(UserInfo userInfo);

    /**
     * 批量删除任智行渠道用户信息
     *
     * @param userInfoIds 需要删除的任智行渠道用户信息ID
     * @return 结果
     */
    int deleteUserInfoByIds(String[] userInfoIds);

    /**
     * 删除任智行渠道用户信息信息
     *
     * @param userInfoId 任智行渠道用户信息ID
     * @return 结果
     */
    int deleteUserInfoById(String userInfoId);
}
