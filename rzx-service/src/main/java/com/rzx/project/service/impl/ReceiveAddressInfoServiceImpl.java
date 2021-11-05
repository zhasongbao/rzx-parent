package com.rzx.project.service.impl;

import java.util.List;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rzx.project.mapper.ReceiveAddressInfoMapper;
import com.rzx.project.model.domain.ReceiveAddressInfo;
import com.rzx.project.service.IReceiveAddressInfoService;

/**
 * 任智行 收货地址Service业务层处理
 *
 * @author zy
 * @date 2021-09-28
 */
@Service
public class ReceiveAddressInfoServiceImpl extends ServiceImpl<ReceiveAddressInfoMapper,ReceiveAddressInfo> implements IReceiveAddressInfoService {
    @Autowired
    private ReceiveAddressInfoMapper receiveAddressInfoMapper;

    /**
     * 查询任智行 收货地址
     *
     * @param receiveaddressId 任智行 收货地址ID
     * @return 任智行 收货地址
     */
    @Override
    public ReceiveAddressInfo selectReceiveAddressInfoById(String receiveaddressId) {
        return receiveAddressInfoMapper.selectReceiveAddressInfoById(receiveaddressId);
    }

    /**
     * 查询任智行 收货地址列表
     *
     * @param receiveAddressInfo 任智行 收货地址
     * @return 任智行 收货地址
     */
    @Override
    public List<ReceiveAddressInfo> selectReceiveAddressInfoList(ReceiveAddressInfo receiveAddressInfo) {
        return receiveAddressInfoMapper.selectReceiveAddressInfoList(receiveAddressInfo);
    }

    /**
     * 新增任智行 收货地址
     *
     * @param receiveAddressInfo 任智行 收货地址
     * @return 结果
     */
    @Override
    public int insertReceiveAddressInfo(ReceiveAddressInfo receiveAddressInfo) {
        LocalDateTime now = LocalDateTime.now();
        receiveAddressInfo.setCreateTime(now);
        receiveAddressInfo.setUpdateTime(now);
        return receiveAddressInfoMapper.insert(receiveAddressInfo);
    }

    /**
     * 修改任智行 收货地址
     *
     * @param receiveAddressInfo 任智行 收货地址
     * @return 结果
     */
    @Override
    public int updateReceiveAddressInfo(ReceiveAddressInfo receiveAddressInfo) {
        receiveAddressInfo.setUpdateTime(LocalDateTime.now());
        return receiveAddressInfoMapper.updateReceiveAddressInfo(receiveAddressInfo);
    }

    /**
     * 批量删除任智行 收货地址
     *
     * @param receiveaddressIds 需要删除的任智行 收货地址ID
     * @return 结果
     */
    @Override
    public int deleteReceiveAddressInfoByIds(String[] receiveaddressIds) {
        return receiveAddressInfoMapper.deleteReceiveAddressInfoByIds(receiveaddressIds);
    }

    /**
     * 删除任智行 收货地址信息
     *
     * @param receiveaddressId 任智行 收货地址ID
     * @return 结果
     */
    @Override
    public int deleteReceiveAddressInfoById(String receiveaddressId) {
        return receiveAddressInfoMapper.deleteReceiveAddressInfoById(receiveaddressId);
    }
}
