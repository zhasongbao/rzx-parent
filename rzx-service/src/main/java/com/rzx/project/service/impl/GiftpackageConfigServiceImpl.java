package com.rzx.project.service.impl;

import java.util.List;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rzx.project.mapper.GiftpackageConfigMapper;
import com.rzx.project.domain.GiftpackageConfig;
import com.rzx.project.service.IGiftpackageConfigService;

/**
 * 任智行 礼包配置Service业务层处理
 *
 * @author zy
 * @date 2021-09-15
 */
@Service
public class GiftpackageConfigServiceImpl extends ServiceImpl<GiftpackageConfigMapper,GiftpackageConfig> implements IGiftpackageConfigService {
    @Autowired
    private GiftpackageConfigMapper giftpackageConfigMapper;

    /**
     * 查询任智行 礼包配置
     *
     * @param giftpackageId 任智行 礼包配置ID
     * @return 任智行 礼包配置
     */
    @Override
    public GiftpackageConfig selectGiftpackageConfigById(String giftpackageId) {
        return giftpackageConfigMapper.selectGiftpackageConfigById(giftpackageId);
    }

    /**
     * 查询任智行 礼包配置列表
     *
     * @param giftpackageConfig 任智行 礼包配置
     * @return 任智行 礼包配置
     */
    @Override
    public List<GiftpackageConfig> selectGiftpackageConfigList(GiftpackageConfig giftpackageConfig) {
        return giftpackageConfigMapper.selectGiftpackageConfigList(giftpackageConfig);
    }

    /**
     * 新增任智行 礼包配置
     *
     * @param giftpackageConfig 任智行 礼包配置
     * @return 结果
     */
    @Override
    public int insertGiftpackageConfig(GiftpackageConfig giftpackageConfig) {
        LocalDateTime now = LocalDateTime.now();
        giftpackageConfig.setCreateTime(now);
        giftpackageConfig.setUpdateTime(now);
        return giftpackageConfigMapper.insert(giftpackageConfig);
    }

    /**
     * 修改任智行 礼包配置
     *
     * @param giftpackageConfig 任智行 礼包配置
     * @return 结果
     */
    @Override
    public int updateGiftpackageConfig(GiftpackageConfig giftpackageConfig) {
        giftpackageConfig.setUpdateTime(LocalDateTime.now());
        return giftpackageConfigMapper.updateGiftpackageConfig(giftpackageConfig);
    }

    /**
     * 批量删除任智行 礼包配置
     *
     * @param giftpackageIds 需要删除的任智行 礼包配置ID
     * @return 结果
     */
    @Override
    public int deleteGiftpackageConfigByIds(String[] giftpackageIds) {
        return giftpackageConfigMapper.deleteGiftpackageConfigByIds(giftpackageIds);
    }

    /**
     * 删除任智行 礼包配置信息
     *
     * @param giftpackageId 任智行 礼包配置ID
     * @return 结果
     */
    @Override
    public int deleteGiftpackageConfigById(String giftpackageId) {
        return giftpackageConfigMapper.deleteGiftpackageConfigById(giftpackageId);
    }
}
