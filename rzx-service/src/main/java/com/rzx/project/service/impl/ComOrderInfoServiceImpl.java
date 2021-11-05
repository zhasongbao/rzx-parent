package com.rzx.project.service.impl;

import java.util.List;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rzx.project.mapper.ComOrderInfoMapper;
import com.rzx.project.model.domain.ComOrderInfo;
import com.rzx.project.service.IComOrderInfoService;

/**
 * 任智行 主订单Service业务层处理
 *
 * @author zy
 * @date 2021-09-28
 */
@Service
public class ComOrderInfoServiceImpl extends ServiceImpl<ComOrderInfoMapper,ComOrderInfo> implements IComOrderInfoService {
    @Autowired
    private ComOrderInfoMapper comOrderInfoMapper;

    /**
     * 查询任智行 主订单
     *
     * @param comsalesorderId 任智行 主订单ID
     * @return 任智行 主订单
     */
    @Override
    public ComOrderInfo selectComOrderInfoById(String comsalesorderId) {
        return comOrderInfoMapper.selectComOrderInfoById(comsalesorderId);
    }

    /**
     * 查询任智行 主订单列表
     *
     * @param comOrderInfo 任智行 主订单
     * @return 任智行 主订单
     */
    @Override
    public List<ComOrderInfo> selectComOrderInfoList(ComOrderInfo comOrderInfo) {
        return comOrderInfoMapper.selectComOrderInfoList(comOrderInfo);
    }

    /**
     * 新增任智行 主订单
     *
     * @param comOrderInfo 任智行 主订单
     * @return 结果
     */
    @Override
    public int insertComOrderInfo(ComOrderInfo comOrderInfo) {
        LocalDateTime now = LocalDateTime.now();
        comOrderInfo.setCreateTime(now);
        comOrderInfo.setUpdateTime(now);
        return comOrderInfoMapper.insert(comOrderInfo);
    }

    /**
     * 修改任智行 主订单
     *
     * @param comOrderInfo 任智行 主订单
     * @return 结果
     */
    @Override
    public int updateComOrderInfo(ComOrderInfo comOrderInfo) {
        comOrderInfo.setUpdateTime(LocalDateTime.now());
        return comOrderInfoMapper.updateComOrderInfo(comOrderInfo);
    }

    /**
     * 批量删除任智行 主订单
     *
     * @param comsalesorderIds 需要删除的任智行 主订单ID
     * @return 结果
     */
    @Override
    public int deleteComOrderInfoByIds(String[] comsalesorderIds) {
        return comOrderInfoMapper.deleteComOrderInfoByIds(comsalesorderIds);
    }

    /**
     * 删除任智行 主订单信息
     *
     * @param comsalesorderId 任智行 主订单ID
     * @return 结果
     */
    @Override
    public int deleteComOrderInfoById(String comsalesorderId) {
        return comOrderInfoMapper.deleteComOrderInfoById(comsalesorderId);
    }
}
