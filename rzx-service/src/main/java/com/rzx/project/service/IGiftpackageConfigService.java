package com.rzx.project.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rzx.project.domain.GiftpackageConfig;

/**
 * 任智行 礼包配置Service接口
 *
 * @author zy
 * @date 2021-09-15
 */
public interface IGiftpackageConfigService extends IService<GiftpackageConfig> {

    /**
     * 查询任智行 礼包配置
     *
     * @param giftpackageId 任智行 礼包配置ID
     * @return 任智行 礼包配置
     */
    GiftpackageConfig selectGiftpackageConfigById(String giftpackageId);

    /**
     * 查询任智行 礼包配置列表
     *
     * @param giftpackageConfig 任智行 礼包配置
     * @return 任智行 礼包配置集合
     */
    List<GiftpackageConfig> selectGiftpackageConfigList(GiftpackageConfig giftpackageConfig);

    /**
     * 新增任智行 礼包配置
     *
     * @param giftpackageConfig 任智行 礼包配置
     * @return 结果
     */
    int insertGiftpackageConfig(GiftpackageConfig giftpackageConfig);

    /**
     * 修改任智行 礼包配置
     *
     * @param giftpackageConfig 任智行 礼包配置
     * @return 结果
     */
    int updateGiftpackageConfig(GiftpackageConfig giftpackageConfig);

    /**
     * 批量删除任智行 礼包配置
     *
     * @param giftpackageIds 需要删除的任智行 礼包配置ID
     * @return 结果
     */
    int deleteGiftpackageConfigByIds(String[] giftpackageIds);

    /**
     * 删除任智行 礼包配置信息
     *
     * @param giftpackageId 任智行 礼包配置ID
     * @return 结果
     */
    int deleteGiftpackageConfigById(String giftpackageId);
}
