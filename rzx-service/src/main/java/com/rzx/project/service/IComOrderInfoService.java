package com.rzx.project.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rzx.project.domain.ComOrderInfo;

/**
 * 任智行 主订单Service接口
 *
 * @author zy
 * @date 2021-09-28
 */
public interface IComOrderInfoService extends IService<ComOrderInfo> {

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
     * 新增任智行 主订单
     *
     * @param comOrderInfo 任智行 主订单
     * @return 结果
     */
    int insertComOrderInfo(ComOrderInfo comOrderInfo);

    /**
     * 修改任智行 主订单
     *
     * @param comOrderInfo 任智行 主订单
     * @return 结果
     */
    int updateComOrderInfo(ComOrderInfo comOrderInfo);

    /**
     * 批量删除任智行 主订单
     *
     * @param comsalesorderIds 需要删除的任智行 主订单ID
     * @return 结果
     */
    int deleteComOrderInfoByIds(String[] comsalesorderIds);

    /**
     * 删除任智行 主订单信息
     *
     * @param comsalesorderId 任智行 主订单ID
     * @return 结果
     */
    int deleteComOrderInfoById(String comsalesorderId);
}
