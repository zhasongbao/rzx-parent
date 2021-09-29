package com.rzx.project.service.impl;

import java.util.List;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rzx.project.mapper.CommodityClassMapper;
import com.rzx.project.domain.CommodityClass;
import com.rzx.project.service.ICommodityClassService;

/**
 * 任智行 商品分类Service业务层处理
 *
 * @author zy
 * @date 2021-09-28
 */
@Service
public class CommodityClassServiceImpl extends ServiceImpl<CommodityClassMapper,CommodityClass> implements ICommodityClassService {
    @Autowired
    private CommodityClassMapper commodityClassMapper;

    /**
     * 查询任智行 商品分类
     *
     * @param commodityclassId 任智行 商品分类ID
     * @return 任智行 商品分类
     */
    @Override
    public CommodityClass selectCommodityClassById(String commodityclassId) {
        return commodityClassMapper.selectCommodityClassById(commodityclassId);
    }

    /**
     * 查询任智行 商品分类列表
     *
     * @param commodityClass 任智行 商品分类
     * @return 任智行 商品分类
     */
    @Override
    public List<CommodityClass> selectCommodityClassList(CommodityClass commodityClass) {
        return commodityClassMapper.selectCommodityClassList(commodityClass);
    }

    /**
     * 新增任智行 商品分类
     *
     * @param commodityClass 任智行 商品分类
     * @return 结果
     */
    @Override
    public int insertCommodityClass(CommodityClass commodityClass) {
        LocalDateTime now = LocalDateTime.now();
        commodityClass.setCreateTime(now);
        commodityClass.setUpdateTime(now);
        return commodityClassMapper.insert(commodityClass);
    }

    /**
     * 修改任智行 商品分类
     *
     * @param commodityClass 任智行 商品分类
     * @return 结果
     */
    @Override
    public int updateCommodityClass(CommodityClass commodityClass) {
        commodityClass.setUpdateTime(LocalDateTime.now());
        return commodityClassMapper.updateCommodityClass(commodityClass);
    }

    /**
     * 批量删除任智行 商品分类
     *
     * @param commodityclassIds 需要删除的任智行 商品分类ID
     * @return 结果
     */
    @Override
    public int deleteCommodityClassByIds(String[] commodityclassIds) {
        return commodityClassMapper.deleteCommodityClassByIds(commodityclassIds);
    }

    /**
     * 删除任智行 商品分类信息
     *
     * @param commodityclassId 任智行 商品分类ID
     * @return 结果
     */
    @Override
    public int deleteCommodityClassById(String commodityclassId) {
        return commodityClassMapper.deleteCommodityClassById(commodityclassId);
    }

    /**
     * 批量插入
     * @param list
     * @throws Exception
     */
    @Override
    public int batchInsertOrUpdate(List<CommodityClass> list){
        return commodityClassMapper.batchInsertOrUpdate(list);
    }

}
