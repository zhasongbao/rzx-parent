package com.rzx.project.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rzx.project.domain.GiftpackageConfig;

/**
 * 任智行 礼包配置Mapper接口
 *
 * @author zy
 * @date 2021-09-15
 */
public interface GiftpackageConfigMapper extends BaseMapper<GiftpackageConfig> {

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
     * 修改任智行 礼包配置
     *
     * @param giftpackageConfig 任智行 礼包配置
     * @return 结果
     */
    int updateGiftpackageConfig(GiftpackageConfig giftpackageConfig);

    /**
     * 删除任智行 礼包配置
     *
     * @param giftpackageId 任智行 礼包配置ID
     * @return 结果
     */
    int deleteGiftpackageConfigById(String giftpackageId);

    /**
     * 批量删除任智行 礼包配置
     *
     * @param giftpackageIds 需要删除的数据ID
     * @return 结果
     */
    int deleteGiftpackageConfigByIds(String[] giftpackageIds);
}
