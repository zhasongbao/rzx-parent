package com.rzx.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rzx.project.model.domain.PayChannelCode;

import java.util.List;

/**
 * 支付渠道编码Service接口
 *
 * @author zy
 * @date 2021-09-30
 */
public interface IPayChannelCodeService extends IService<PayChannelCode> {

    /**
     * 查询支付渠道编码
     *
     * @param id 支付渠道编码ID
     * @return 支付渠道编码
     */
    PayChannelCode selectPayChannelCodeById(Long id);

    /**
     * 查询支付渠道编码列表
     *
     * @param payChannelCode 支付渠道编码
     * @return 支付渠道编码集合
     */
    List<PayChannelCode> selectPayChannelCodeList(PayChannelCode payChannelCode);

    /**
     * 新增支付渠道编码
     *
     * @param payChannelCode 支付渠道编码
     * @return 结果
     */
    int insertPayChannelCode(PayChannelCode payChannelCode);

    /**
     * 修改支付渠道编码
     *
     * @param payChannelCode 支付渠道编码
     * @return 结果
     */
    int updatePayChannelCode(PayChannelCode payChannelCode);

    /**
     * 批量删除支付渠道编码
     *
     * @param ids 需要删除的支付渠道编码ID
     * @return 结果
     */
    int deletePayChannelCodeByIds(Long[] ids);

    /**
     * 删除支付渠道编码信息
     *
     * @param id 支付渠道编码ID
     * @return 结果
     */
    int deletePayChannelCodeById(Long id);

    /**
     * 通过渠道编码查询
     *
     * @param payChannelCode 渠道编码
     * @return
     */
    PayChannelCode getDataByChannelCode(String payChannelCode);
}
