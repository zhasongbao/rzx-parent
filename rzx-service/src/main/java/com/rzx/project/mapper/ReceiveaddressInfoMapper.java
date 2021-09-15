package com.rzx.project.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rzx.project.domain.ReceiveaddressInfo;

/**
 * 任智行 收货地址Mapper接口
 *
 * @author zy
 * @date 2021-09-15
 */
public interface ReceiveaddressInfoMapper extends BaseMapper<ReceiveaddressInfo> {

    /**
     * 查询任智行 收货地址
     *
     * @param receiveaddressId 任智行 收货地址ID
     * @return 任智行 收货地址
     */
    ReceiveaddressInfo selectReceiveaddressInfoById(String receiveaddressId);

    /**
     * 查询任智行 收货地址列表
     *
     * @param receiveaddressInfo 任智行 收货地址
     * @return 任智行 收货地址集合
     */
    List<ReceiveaddressInfo> selectReceiveaddressInfoList(ReceiveaddressInfo receiveaddressInfo);


    /**
     * 修改任智行 收货地址
     *
     * @param receiveaddressInfo 任智行 收货地址
     * @return 结果
     */
    int updateReceiveaddressInfo(ReceiveaddressInfo receiveaddressInfo);

    /**
     * 删除任智行 收货地址
     *
     * @param receiveaddressId 任智行 收货地址ID
     * @return 结果
     */
    int deleteReceiveaddressInfoById(String receiveaddressId);

    /**
     * 批量删除任智行 收货地址
     *
     * @param receiveaddressIds 需要删除的数据ID
     * @return 结果
     */
    int deleteReceiveaddressInfoByIds(String[] receiveaddressIds);
}
