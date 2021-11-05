package com.rzx.project.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rzx.project.model.domain.ReceiveAddressInfo;

/**
 * 任智行 收货地址Mapper接口
 *
 * @author zy
 * @date 2021-09-28
 */
public interface ReceiveAddressInfoMapper extends BaseMapper<ReceiveAddressInfo> {

    /**
     * 查询任智行 收货地址
     *
     * @param receiveaddressId 任智行 收货地址ID
     * @return 任智行 收货地址
     */
    ReceiveAddressInfo selectReceiveAddressInfoById(String receiveaddressId);

    /**
     * 查询任智行 收货地址列表
     *
     * @param receiveAddressInfo 任智行 收货地址
     * @return 任智行 收货地址集合
     */
    List<ReceiveAddressInfo> selectReceiveAddressInfoList(ReceiveAddressInfo receiveAddressInfo);


    /**
     * 修改任智行 收货地址
     *
     * @param receiveAddressInfo 任智行 收货地址
     * @return 结果
     */
    int updateReceiveAddressInfo(ReceiveAddressInfo receiveAddressInfo);

    /**
     * 删除任智行 收货地址
     *
     * @param receiveaddressId 任智行 收货地址ID
     * @return 结果
     */
    int deleteReceiveAddressInfoById(String receiveaddressId);

    /**
     * 批量删除任智行 收货地址
     *
     * @param receiveaddressIds 需要删除的数据ID
     * @return 结果
     */
    int deleteReceiveAddressInfoByIds(String[] receiveaddressIds);
}
