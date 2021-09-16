package com.rzx.project.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rzx.project.domain.OrderInfo;
import com.rzx.project.domain.dto.OrderInfoDTO;

/**
 * 任智行 销售订单Mapper接口
 *
 * @author zy
 * @date 2021-09-15
 */
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    /**
     * 查询任智行 销售订单
     *
     * @param salesorderId 任智行 销售订单ID
     * @return 任智行 销售订单
     */
    OrderInfo selectOrderInfoById(String salesorderId);

    /**
     * 查询任智行 销售订单列表
     *
     * @param dto 任智行 销售订单
     * @return 任智行 销售订单集合
     */
    List<OrderInfo> selectOrderInfoList(OrderInfoDTO dto);


    /**
     * 修改任智行 销售订单
     *
     * @param orderInfo 任智行 销售订单
     * @return 结果
     */
    int updateOrderInfo(OrderInfo orderInfo);

    /**
     * 删除任智行 销售订单
     *
     * @param salesorderId 任智行 销售订单ID
     * @return 结果
     */
    int deleteOrderInfoById(String salesorderId);

    /**
     * 批量删除任智行 销售订单
     *
     * @param salesorderIds 需要删除的数据ID
     * @return 结果
     */
    int deleteOrderInfoByIds(String[] salesorderIds);
}
