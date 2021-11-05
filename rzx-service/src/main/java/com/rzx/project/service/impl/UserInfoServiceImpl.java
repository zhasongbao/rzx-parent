package com.rzx.project.service.impl;

import java.util.List;
import java.time.LocalDateTime;

import com.rzx.common.core.domain.entity.ChannelUserInfo;
import com.rzx.project.model.dto.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rzx.project.mapper.UserInfoMapper;
import com.rzx.project.model.domain.UserInfo;
import com.rzx.project.service.IUserInfoService;

/**
 * 任智行渠道用户信息Service业务层处理
 *
 * @author zy
 * @date 2021-09-28
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper,UserInfo> implements IUserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 查询任智行渠道用户信息
     *
     * @param userInfoId 任智行渠道用户信息ID
     * @return 任智行渠道用户信息
     */
    @Override
    public UserInfo selectUserInfoById(String userInfoId) {
        return userInfoMapper.selectUserInfoById(userInfoId);
    }

    /**
     * 查询用户信息
     *
     * @param dto 渠道用户I
     * @return 用户信息
     */
    @Override
    public ChannelUserInfo selectUserInfoByIdAndInSource(UserLoginDTO dto) {
        return userInfoMapper.selectUserInfoByIdAndInSource(dto);
    }

    /**
     * 查询任智行渠道用户信息列表
     *
     * @param userInfo 任智行渠道用户信息
     * @return 任智行渠道用户信息
     */
    @Override
    public List<UserInfo> selectUserInfoList(UserInfo userInfo) {
        return userInfoMapper.selectUserInfoList(userInfo);
    }

    /**
     * 新增任智行渠道用户信息
     *
     * @param userInfo 任智行渠道用户信息
     * @return 结果
     */
    @Override
    public int insertUserInfo(UserInfo userInfo) {
        LocalDateTime now = LocalDateTime.now();
        userInfo.setCreateTime(now);
        userInfo.setUpdateTime(now);
        return userInfoMapper.insert(userInfo);
    }

    /**
     * 修改任智行渠道用户信息
     *
     * @param userInfo 任智行渠道用户信息
     * @return 结果
     */
    @Override
    public int updateUserInfo(UserInfo userInfo) {
        userInfo.setUpdateTime(LocalDateTime.now());
        return userInfoMapper.updateUserInfo(userInfo);
    }

    /**
     * 批量删除任智行渠道用户信息
     *
     * @param userInfoIds 需要删除的任智行渠道用户信息ID
     * @return 结果
     */
    @Override
    public int deleteUserInfoByIds(String[] userInfoIds) {
        return userInfoMapper.deleteUserInfoByIds(userInfoIds);
    }

    /**
     * 删除任智行渠道用户信息信息
     *
     * @param userInfoId 任智行渠道用户信息ID
     * @return 结果
     */
    @Override
    public int deleteUserInfoById(String userInfoId) {
        return userInfoMapper.deleteUserInfoById(userInfoId);
    }
}
