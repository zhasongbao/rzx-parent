package com.rzx.project.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rzx.project.domain.ProvidAddress;

/**
 * 任智行 供应商地址信息Service接口
 *
 * @author zy
 * @date 2021-09-28
 */
public interface IProvidAddressService extends IService<ProvidAddress> {

    /**
     * 查询任智行 供应商地址信息
     *
     * @param providaddressId 任智行 供应商地址信息ID
     * @return 任智行 供应商地址信息
     */
    ProvidAddress selectProvidAddressById(String providaddressId);

    /**
     * 查询任智行 供应商地址信息列表
     *
     * @param providAddress 任智行 供应商地址信息
     * @return 任智行 供应商地址信息集合
     */
    List<ProvidAddress> selectProvidAddressList(ProvidAddress providAddress);

    /**
     * 新增任智行 供应商地址信息
     *
     * @param providAddress 任智行 供应商地址信息
     * @return 结果
     */
    int insertProvidAddress(ProvidAddress providAddress);

    /**
     * 修改任智行 供应商地址信息
     *
     * @param providAddress 任智行 供应商地址信息
     * @return 结果
     */
    int updateProvidAddress(ProvidAddress providAddress);

    /**
     * 批量删除任智行 供应商地址信息
     *
     * @param providaddressIds 需要删除的任智行 供应商地址信息ID
     * @return 结果
     */
    int deleteProvidAddressByIds(String[] providaddressIds);

    /**
     * 删除任智行 供应商地址信息信息
     *
     * @param providaddressId 任智行 供应商地址信息ID
     * @return 结果
     */
    int deleteProvidAddressById(String providaddressId);

    /**
     * 批量插入/更新
     * @param list
     * @throws Exception
     */
    int batchInsertOrUpdate(List<ProvidAddress> list);
}
