package com.rzx.project.service.impl;

import java.util.List;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rzx.project.mapper.ReceiveaddressInfoMapper;
import com.rzx.project.domain.ReceiveaddressInfo;
import com.rzx.project.service.IReceiveaddressInfoService;

/**
 * 任智行 收货地址Service业务层处理
 *
 * @author zy
 * @date 2021-09-15
 */
@Service
public class ReceiveaddressInfoServiceImpl extends ServiceImpl<ReceiveaddressInfoMapper,ReceiveaddressInfo> implements IReceiveaddressInfoService {
    @Autowired
    private ReceiveaddressInfoMapper receiveaddressInfoMapper;

    /**
     * 查询任智行 收货地址
     *
     * @param receiveaddressId 任智行 收货地址ID
     * @return 任智行 收货地址
     */
    @Override
    public ReceiveaddressInfo selectReceiveaddressInfoById(String receiveaddressId) {
        return receiveaddressInfoMapper.selectReceiveaddressInfoById(receiveaddressId);
    }

    /**
     * 查询任智行 收货地址列表
     *
     * @param receiveaddressInfo 任智行 收货地址
     * @return 任智行 收货地址
     */
    @Override
    public List<ReceiveaddressInfo> selectReceiveaddressInfoList(ReceiveaddressInfo receiveaddressInfo) {
        return receiveaddressInfoMapper.selectReceiveaddressInfoList(receiveaddressInfo);
    }

    /**
     * 新增任智行 收货地址
     *
     * @param receiveaddressInfo 任智行 收货地址
     * @return 结果
     */
    @Override
    public int insertReceiveaddressInfo(ReceiveaddressInfo receiveaddressInfo) {
        LocalDateTime now = LocalDateTime.now();
        receiveaddressInfo.setCreateTime(now);
        receiveaddressInfo.setUpdateTime(now);
        return receiveaddressInfoMapper.insert(receiveaddressInfo);
    }

    /**
     * 修改任智行 收货地址
     *
     * @param receiveaddressInfo 任智行 收货地址
     * @return 结果
     */
    @Override
    public int updateReceiveaddressInfo(ReceiveaddressInfo receiveaddressInfo) {
        receiveaddressInfo.setUpdateTime(LocalDateTime.now());
        return receiveaddressInfoMapper.updateReceiveaddressInfo(receiveaddressInfo);
    }

    /**
     * 批量删除任智行 收货地址
     *
     * @param receiveaddressIds 需要删除的任智行 收货地址ID
     * @return 结果
     */
    @Override
    public int deleteReceiveaddressInfoByIds(String[] receiveaddressIds) {
        return receiveaddressInfoMapper.deleteReceiveaddressInfoByIds(receiveaddressIds);
    }

    /**
     * 删除任智行 收货地址信息
     *
     * @param receiveaddressId 任智行 收货地址ID
     * @return 结果
     */
    @Override
    public int deleteReceiveaddressInfoById(String receiveaddressId) {
        return receiveaddressInfoMapper.deleteReceiveaddressInfoById(receiveaddressId);
    }
}
