package com.rzx.project.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rzx.project.domain.CommodityConfig;

/**
 * 任智行 商品配置Service接口
 *
 * @author zy
 * @date 2021-09-28
 */
public interface ICommodityConfigService extends IService<CommodityConfig> {

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
     * 新增任智行 商品配置
     *
     * @param commodityConfig 任智行 商品配置
     * @return 结果
     */
    int insertCommodityConfig(CommodityConfig commodityConfig);

    /**
     * 修改任智行 商品配置
     *
     * @param commodityConfig 任智行 商品配置
     * @return 结果
     */
    int updateCommodityConfig(CommodityConfig commodityConfig);

    /**
     * 批量删除任智行 商品配置
     *
     * @param commodityconfigIds 需要删除的任智行 商品配置ID
     * @return 结果
     */
    int deleteCommodityConfigByIds(String[] commodityconfigIds);

    /**
     * 删除任智行 商品配置信息
     *
     * @param commodityconfigId 任智行 商品配置ID
     * @return 结果
     */
    int deleteCommodityConfigById(String commodityconfigId);
}
