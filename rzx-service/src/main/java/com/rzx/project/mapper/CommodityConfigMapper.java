package com.rzx.project.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rzx.project.model.domain.CommodityConfig;

/**
 * 任智行 商品配置Mapper接口
 *
 * @author zy
 * @date 2021-09-28
 */
public interface CommodityConfigMapper extends BaseMapper<CommodityConfig> {

    /**
     * 查询任智行 商品配置
     *
     * @param commodityconfigId 任智行 商品配置ID
     * @return 任智行 商品配置
     */
    CommodityConfig selectCommodityConfigById(String commodityconfigId);

    /**
     * 查询任智行 商品配置列表
     *
     * @param commodityConfig 任智行 商品配置
     * @return 任智行 商品配置集合
     */
    List<CommodityConfig> selectCommodityConfigList(CommodityConfig commodityConfig);


    /**
     * 修改任智行 商品配置
     *
     * @param commodityConfig 任智行 商品配置
     * @return 结果
     */
    int updateCommodityConfig(CommodityConfig commodityConfig);

    /**
     * 删除任智行 商品配置
     *
     * @param commodityconfigId 任智行 商品配置ID
     * @return 结果
     */
    int deleteCommodityConfigById(String commodityconfigId);

    /**
     * 批量删除任智行 商品配置
     *
     * @param commodityconfigIds 需要删除的数据ID
     * @return 结果
     */
    int deleteCommodityConfigByIds(String[] commodityconfigIds);
}
