package com.rzx.project.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rzx.project.domain.GiftPackageConfig;

/**
 * 任智行 礼包配置Mapper接口
 *
 * @author zy
 * @date 2021-09-28
 */
public interface GiftPackageConfigMapper extends BaseMapper<GiftPackageConfig> {

    /**
     * 查询任智行 礼包配置
     *
     * @param giftpackageId 任智行 礼包配置ID
     * @return 任智行 礼包配置
     */
    GiftPackageConfig selectGiftPackageConfigById(String giftpackageId);

    /**
     * 查询任智行 礼包配置列表
     *
     * @param giftPackageConfig 任智行 礼包配置
     * @return 任智行 礼包配置集合
     */
    List<GiftPackageConfig> selectGiftPackageConfigList(GiftPackageConfig giftPackageConfig);


    /**
     * 修改任智行 礼包配置
     *
     * @param giftPackageConfig 任智行 礼包配置
     * @return 结果
     */
    int updateGiftPackageConfig(GiftPackageConfig giftPackageConfig);

    /**
     * 删除任智行 礼包配置
     *
     * @param giftpackageId 任智行 礼包配置ID
     * @return 结果
     */
    int deleteGiftPackageConfigById(String giftpackageId);

    /**
     * 批量删除任智行 礼包配置
     *
     * @param giftpackageIds 需要删除的数据ID
     * @return 结果
     */
    int deleteGiftPackageConfigByIds(String[] giftpackageIds);
}
