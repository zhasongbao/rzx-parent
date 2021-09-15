package com.rzx.project.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rzx.project.domain.ReceiveaddressInfo;

/**
 * 任智行 收货地址Service接口
 *
 * @author zy
 * @date 2021-09-15
 */
public interface IReceiveaddressInfoService extends IService<ReceiveaddressInfo> {

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
     * 新增任智行 收货地址
     *
     * @param receiveaddressInfo 任智行 收货地址
     * @return 结果
     */
    int insertReceiveaddressInfo(ReceiveaddressInfo receiveaddressInfo);

    /**
     * 修改任智行 收货地址
     *
     * @param receiveaddressInfo 任智行 收货地址
     * @return 结果
     */
    int updateReceiveaddressInfo(ReceiveaddressInfo receiveaddressInfo);

    /**
     * 批量删除任智行 收货地址
     *
     * @param receiveaddressIds 需要删除的任智行 收货地址ID
     * @return 结果
     */
    int deleteReceiveaddressInfoByIds(String[] receiveaddressIds);

    /**
     * 删除任智行 收货地址信息
     *
     * @param receiveaddressId 任智行 收货地址ID
     * @return 结果
     */
    int deleteReceiveaddressInfoById(String receiveaddressId);
}
