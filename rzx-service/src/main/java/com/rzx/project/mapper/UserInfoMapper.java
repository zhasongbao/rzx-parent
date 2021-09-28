package com.rzx.project.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rzx.common.core.domain.entity.ChannelUserInfo;
import com.rzx.project.domain.UserInfo;
import com.rzx.project.domain.dto.UserLoginDTO;

/**
 * 任智行渠道用户信息Mapper接口
 *
 * @author zy
 * @date 2021-09-28
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /**
     * 查询任智行渠道用户信息
     *
     * @param userInfoId 任智行渠道用户信息ID
     * @return 任智行渠道用户信息
     */
    UserInfo selectUserInfoById(String userInfoId);

    ChannelUserInfo selectUserInfoByIdAndInSource(UserLoginDTO dto);

    /**
     * 查询任智行渠道用户信息列表
     *
     * @param userInfo 任智行渠道用户信息
     * @return 任智行渠道用户信息集合
     */
    List<UserInfo> selectUserInfoList(UserInfo userInfo);


    /**
     * 修改任智行渠道用户信息
     *
     * @param userInfo 任智行渠道用户信息
     * @return 结果
     */
    int updateUserInfo(UserInfo userInfo);

    /**
     * 删除任智行渠道用户信息
     *
     * @param userInfoId 任智行渠道用户信息ID
     * @return 结果
     */
    int deleteUserInfoById(String userInfoId);

    /**
     * 批量删除任智行渠道用户信息
     *
     * @param userInfoIds 需要删除的数据ID
     * @return 结果
     */
    int deleteUserInfoByIds(String[] userInfoIds);
}
