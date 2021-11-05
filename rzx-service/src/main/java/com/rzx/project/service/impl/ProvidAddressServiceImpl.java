package com.rzx.project.service.impl;

import java.util.List;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rzx.project.mapper.ProvidAddressMapper;
import com.rzx.project.model.domain.ProvidAddress;
import com.rzx.project.service.IProvidAddressService;

/**
 * 任智行 供应商地址信息Service业务层处理
 *
 * @author zy
 * @date 2021-09-28
 */
@Service
public class ProvidAddressServiceImpl extends ServiceImpl<ProvidAddressMapper,ProvidAddress> implements IProvidAddressService {
    @Autowired
    private ProvidAddressMapper providAddressMapper;

    /**
     * 查询任智行 供应商地址信息
     *
     * @param providaddressId 任智行 供应商地址信息ID
     * @return 任智行 供应商地址信息
     */
    @Override
    public ProvidAddress selectProvidAddressById(String providaddressId) {
        return providAddressMapper.selectProvidAddressById(providaddressId);
    }

    /**
     * 查询任智行 供应商地址信息列表
     *
     * @param providAddress 任智行 供应商地址信息
     * @return 任智行 供应商地址信息
     */
    @Override
    public List<ProvidAddress> selectProvidAddressList(ProvidAddress providAddress) {
        return providAddressMapper.selectProvidAddressList(providAddress);
    }

    /**
     * 新增任智行 供应商地址信息
     *
     * @param providAddress 任智行 供应商地址信息
     * @return 结果
     */
    @Override
    public int insertProvidAddress(ProvidAddress providAddress) {
        LocalDateTime now = LocalDateTime.now();
        providAddress.setCreateTime(now);
        providAddress.setUpdateTime(now);
        return providAddressMapper.insert(providAddress);
    }

    /**
     * 修改任智行 供应商地址信息
     *
     * @param providAddress 任智行 供应商地址信息
     * @return 结果
     */
    @Override
    public int updateProvidAddress(ProvidAddress providAddress) {
        providAddress.setUpdateTime(LocalDateTime.now());
        return providAddressMapper.updateProvidAddress(providAddress);
    }

    /**
     * 批量删除任智行 供应商地址信息
     *
     * @param providaddressIds 需要删除的任智行 供应商地址信息ID
     * @return 结果
     */
    @Override
    public int deleteProvidAddressByIds(String[] providaddressIds) {
        return providAddressMapper.deleteProvidAddressByIds(providaddressIds);
    }

    /**
     * 删除任智行 供应商地址信息信息
     *
     * @param providaddressId 任智行 供应商地址信息ID
     * @return 结果
     */
    @Override
    public int deleteProvidAddressById(String providaddressId) {
        return providAddressMapper.deleteProvidAddressById(providaddressId);
    }

    /**
     * 批量插入/更新
     *
     * @param list
     */
    @Override
    public int batchInsertOrUpdate(List<ProvidAddress> list) {
        return providAddressMapper.batchInsertOrUpdate(list);
    }
}
