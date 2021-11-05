package com.rzx.project.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rzx.project.model.domain.ReceiveAddressInfo;

/**
 * 任智行 收货地址Service接口
 *
 * @author zy
 * @date 2021-09-28
 */
public interface IReceiveAddressInfoService extends IService<ReceiveAddressInfo> {

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
     * 新增任智行 收货地址
     *
     * @param receiveAddressInfo 任智行 收货地址
     * @return 结果
     */
    int insertReceiveAddressInfo(ReceiveAddressInfo receiveAddressInfo);

    /**
     * 修改任智行 收货地址
     *
     * @param receiveAddressInfo 任智行 收货地址
     * @return 结果
     */
    int updateReceiveAddressInfo(ReceiveAddressInfo receiveAddressInfo);

    /**
     * 批量删除任智行 收货地址
     *
     * @param receiveaddressIds 需要删除的任智行 收货地址ID
     * @return 结果
     */
    int deleteReceiveAddressInfoByIds(String[] receiveaddressIds);

    /**
     * 删除任智行 收货地址信息
     *
     * @param receiveaddressId 任智行 收货地址ID
     * @return 结果
     */
    int deleteReceiveAddressInfoById(String receiveaddressId);
}
