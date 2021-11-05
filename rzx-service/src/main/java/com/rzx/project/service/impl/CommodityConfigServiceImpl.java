package com.rzx.project.service.impl;

import java.util.List;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rzx.project.mapper.CommodityConfigMapper;
import com.rzx.project.model.domain.CommodityConfig;
import com.rzx.project.service.ICommodityConfigService;

/**
 * 任智行 商品配置Service业务层处理
 *
 * @author zy
 * @date 2021-09-28
 */
@Service
public class CommodityConfigServiceImpl extends ServiceImpl<CommodityConfigMapper,CommodityConfig> implements ICommodityConfigService {
    @Autowired
    private CommodityConfigMapper commodityConfigMapper;

    /**
     * 查询任智行 商品配置
     *
     * @param commodityconfigId 任智行 商品配置ID
     * @return 任智行 商品配置
     */
    @Override
    public CommodityConfig selectCommodityConfigById(String commodityconfigId) {
        return commodityConfigMapper.selectCommodityConfigById(commodityconfigId);
    }

    /**
     * 查询任智行 商品配置列表
     *
     * @param commodityConfig 任智行 商品配置
     * @return 任智行 商品配置
     */
    @Override
    public List<CommodityConfig> selectCommodityConfigList(CommodityConfig commodityConfig) {
        return commodityConfigMapper.selectCommodityConfigList(commodityConfig);
    }

    /**
     * 新增任智行 商品配置
     *
     * @param commodityConfig 任智行 商品配置
     * @return 结果
     */
    @Override
    public int insertCommodityConfig(CommodityConfig commodityConfig) {
        LocalDateTime now = LocalDateTime.now();
        commodityConfig.setCreateTime(now);
        commodityConfig.setUpdateTime(now);
        return commodityConfigMapper.insert(commodityConfig);
    }

    /**
     * 修改任智行 商品配置
     *
     * @param commodityConfig 任智行 商品配置
     * @return 结果
     */
    @Override
    public int updateCommodityConfig(CommodityConfig commodityConfig) {
        commodityConfig.setUpdateTime(LocalDateTime.now());
        return commodityConfigMapper.updateCommodityConfig(commodityConfig);
    }

    /**
     * 批量删除任智行 商品配置
     *
     * @param commodityconfigIds 需要删除的任智行 商品配置ID
     * @return 结果
     */
    @Override
    public int deleteCommodityConfigByIds(String[] commodityconfigIds) {
        return commodityConfigMapper.deleteCommodityConfigByIds(commodityconfigIds);
    }

    /**
     * 删除任智行 商品配置信息
     *
     * @param commodityconfigId 任智行 商品配置ID
     * @return 结果
     */
    @Override
    public int deleteCommodityConfigById(String commodityconfigId) {
        return commodityConfigMapper.deleteCommodityConfigById(commodityconfigId);
    }
}
