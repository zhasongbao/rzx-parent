package com.rzx.project.service.impl;

import java.util.List;
import java.time.LocalDateTime;

import com.rzx.common.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rzx.project.mapper.GiftPackageConfigMapper;
import com.rzx.project.domain.GiftPackageConfig;
import com.rzx.project.service.IGiftPackageConfigService;

/**
 * 任智行 礼包配置Service业务层处理
 *
 * @author zy
 * @date 2021-09-28
 */
@Service
public class GiftPackageConfigServiceImpl extends ServiceImpl<GiftPackageConfigMapper,GiftPackageConfig> implements IGiftPackageConfigService {
    @Autowired
    private GiftPackageConfigMapper giftPackageConfigMapper;

    /**
     * 查询任智行 礼包配置
     *
     * @param giftpackageId 任智行 礼包配置ID
     * @return 任智行 礼包配置
     */
    @Override
    public GiftPackageConfig selectGiftPackageConfigById(String giftpackageId) {
        return giftPackageConfigMapper.selectGiftPackageConfigById(giftpackageId);
    }

    /**
     * 查询任智行 礼包配置列表
     *
     * @param giftPackageConfig 任智行 礼包配置
     * @return 任智行 礼包配置
     */
    @Override
    public List<GiftPackageConfig> selectGiftPackageConfigList(GiftPackageConfig giftPackageConfig) {
        giftPackageConfig.setStatus(StatusEnum.VALID.getCode());
        return giftPackageConfigMapper.selectGiftPackageConfigList(giftPackageConfig);
    }

    /**
     * 新增任智行 礼包配置
     *
     * @param giftPackageConfig 任智行 礼包配置
     * @return 结果
     */
    @Override
    public int insertGiftPackageConfig(GiftPackageConfig giftPackageConfig) {
        LocalDateTime now = LocalDateTime.now();
        giftPackageConfig.setCreateTime(now);
        giftPackageConfig.setUpdateTime(now);
        return giftPackageConfigMapper.insert(giftPackageConfig);
    }

    /**
     * 修改任智行 礼包配置
     *
     * @param giftPackageConfig 任智行 礼包配置
     * @return 结果
     */
    @Override
    public int updateGiftPackageConfig(GiftPackageConfig giftPackageConfig) {
        giftPackageConfig.setUpdateTime(LocalDateTime.now());
        return giftPackageConfigMapper.updateGiftPackageConfig(giftPackageConfig);
    }

    /**
     * 批量删除任智行 礼包配置
     *
     * @param giftpackageIds 需要删除的任智行 礼包配置ID
     * @return 结果
     */
    @Override
    public int deleteGiftPackageConfigByIds(String[] giftpackageIds) {
        return giftPackageConfigMapper.deleteGiftPackageConfigByIds(giftpackageIds);
    }

    /**
     * 删除任智行 礼包配置信息
     *
     * @param giftpackageId 任智行 礼包配置ID
     * @return 结果
     */
    @Override
    public int deleteGiftPackageConfigById(String giftpackageId) {
        return giftPackageConfigMapper.deleteGiftPackageConfigById(giftpackageId);
    }
}
