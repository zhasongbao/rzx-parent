package com.rzx.project.service.impl;

import java.util.List;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rzx.project.mapper.OrderInfoMapper;
import com.rzx.project.domain.OrderInfo;
import com.rzx.project.service.IOrderInfoService;

/**
 * 任智行 销售订单Service业务层处理
 *
 * @author zy
 * @date 2021-09-15
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper,OrderInfo> implements IOrderInfoService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    /**
     * 查询任智行 销售订单
     *
     * @param salesorderId 任智行 销售订单ID
     * @return 任智行 销售订单
     */
    @Override
    public OrderInfo selectOrderInfoById(String salesorderId) {
        return orderInfoMapper.selectOrderInfoById(salesorderId);
    }

    /**
     * 查询任智行 销售订单列表
     *
     * @param orderInfo 任智行 销售订单
     * @return 任智行 销售订单
     */
    @Override
    public List<OrderInfo> selectOrderInfoList(OrderInfo orderInfo) {
        return orderInfoMapper.selectOrderInfoList(orderInfo);
    }

    /**
     * 新增任智行 销售订单
     *
     * @param orderInfo 任智行 销售订单
     * @return 结果
     */
    @Override
    public int insertOrderInfo(OrderInfo orderInfo) {
        LocalDateTime now = LocalDateTime.now();
        orderInfo.setCreateTime(now);
        orderInfo.setUpdateTime(now);
        return orderInfoMapper.insert(orderInfo);
    }

    /**
     * 修改任智行 销售订单
     *
     * @param orderInfo 任智行 销售订单
     * @return 结果
     */
    @Override
    public int updateOrderInfo(OrderInfo orderInfo) {
        orderInfo.setUpdateTime(LocalDateTime.now());
        return orderInfoMapper.updateOrderInfo(orderInfo);
    }

    /**
     * 批量删除任智行 销售订单
     *
     * @param salesorderIds 需要删除的任智行 销售订单ID
     * @return 结果
     */
    @Override
    public int deleteOrderInfoByIds(String[] salesorderIds) {
        return orderInfoMapper.deleteOrderInfoByIds(salesorderIds);
    }

    /**
     * 删除任智行 销售订单信息
     *
     * @param salesorderId 任智行 销售订单ID
     * @return 结果
     */
    @Override
    public int deleteOrderInfoById(String salesorderId) {
        return orderInfoMapper.deleteOrderInfoById(salesorderId);
    }
}
