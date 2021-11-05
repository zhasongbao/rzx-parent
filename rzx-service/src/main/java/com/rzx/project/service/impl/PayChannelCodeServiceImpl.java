package com.rzx.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rzx.common.constant.CacheConstants;
import com.rzx.common.constant.Constants;
import com.rzx.common.core.redis.RedisCache;
import com.rzx.project.model.domain.PayChannelCode;
import com.rzx.project.mapper.PayChannelCodeMapper;
import com.rzx.project.service.IPayChannelCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 支付渠道编码Service业务层处理
 *
 * @author zy
 * @date 2021-09-30
 */
@Service
public class PayChannelCodeServiceImpl extends ServiceImpl<PayChannelCodeMapper, PayChannelCode> implements IPayChannelCodeService {
    @Autowired
    private PayChannelCodeMapper payChannelCodeMapper;
    @Autowired
    private RedisCache redisCache;

    /**
     * 查询支付渠道编码
     *
     * @param id 支付渠道编码ID
     * @return 支付渠道编码
     */
    @Override
    public PayChannelCode selectPayChannelCodeById(Long id) {
        return payChannelCodeMapper.selectPayChannelCodeById(id);
    }

    /**
     * 查询支付渠道编码列表
     *
     * @param payChannelCode 支付渠道编码
     * @return 支付渠道编码
     */
    @Override
    public List<PayChannelCode> selectPayChannelCodeList(PayChannelCode payChannelCode) {
        return payChannelCodeMapper.selectPayChannelCodeList(payChannelCode);
    }

    /**
     * 新增支付渠道编码
     *
     * @param payChannelCode 支付渠道编码
     * @return 结果
     */
    @Override
    public int insertPayChannelCode(PayChannelCode payChannelCode) {
        LocalDateTime now = LocalDateTime.now();
        payChannelCode.setCreateTime(now);
        payChannelCode.setUpdateTime(now);
        return payChannelCodeMapper.insert(payChannelCode);
    }

    /**
     * 修改支付渠道编码
     *
     * @param payChannelCode 支付渠道编码
     * @return 结果
     */
    @Override
    public int updatePayChannelCode(PayChannelCode payChannelCode) {
        payChannelCode.setUpdateTime(LocalDateTime.now());
        return payChannelCodeMapper.updatePayChannelCode(payChannelCode);
    }

    /**
     * 批量删除支付渠道编码
     *
     * @param ids 需要删除的支付渠道编码ID
     * @return 结果
     */
    @Override
    public int deletePayChannelCodeByIds(Long[] ids) {
        return payChannelCodeMapper.deletePayChannelCodeByIds(ids);
    }

    /**
     * 删除支付渠道编码信息
     *
     * @param id 支付渠道编码ID
     * @return 结果
     */
    @Override
    public int deletePayChannelCodeById(Long id) {
        return payChannelCodeMapper.deletePayChannelCodeById(id);
    }

    /**
     * 通过渠道编码查询
     *
     * @param payChannelCode 渠道编码
     * @return
     */
    @Override
    public PayChannelCode getDataByChannelCode(String payChannelCode) {
        String key = CacheConstants.CACHED_CHANNEL_CODE + payChannelCode;
        PayChannelCode channel = redisCache.getCacheObject(key);
        if (Objects.isNull(channel)) {
            LambdaQueryWrapper<PayChannelCode> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(PayChannelCode::getPayChannelCode, payChannelCode);
            queryWrapper.last(Constants.SQL_LIMIT_1);
            channel = payChannelCodeMapper.selectOne(queryWrapper);
            if (Objects.nonNull(channel)) {
                redisCache.setCacheObject(key, channel, 1L, TimeUnit.DAYS);
            }
        }
        return channel;
    }
}
