package com.rzx.project.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rzx.project.model.domain.ComOrderInfo;

/**
 * 任智行 主订单Mapper接口
 *
 * @author zy
 * @date 2021-09-28
 */
public interface ComOrderInfoMapper extends BaseMapper<ComOrderInfo> {

    /**
     * 查询任智行 主订单
     *
     * @param comsalesorderId 任智行 主订单ID
     * @return 任智行 主订单
     */
    ComOrderInfo selectComOrderInfoById(String comsalesorderId);

    /**
     * 查询任智行 主订单列表
     *
     * @param comOrderInfo 任智行 主订单
     * @return 任智行 主订单集合
     */
    List<ComOrderInfo> selectComOrderInfoList(ComOrderInfo comOrderInfo);


    /**
     * 修改任智行 主订单
     *
     * @param comOrderInfo 任智行 主订单
     * @return 结果
     */
    int updateComOrderInfo(ComOrderInfo comOrderInfo);

    /**
     * 删除任智行 主订单
     *
     * @param comsalesorderId 任智行 主订单ID
     * @return 结果
     */
    int deleteComOrderInfoById(String comsalesorderId);

    /**
     * 批量删除任智行 主订单
     *
     * @param comsalesorderIds 需要删除的数据ID
     * @return 结果
     */
    int deleteComOrderInfoByIds(String[] comsalesorderIds);
}
