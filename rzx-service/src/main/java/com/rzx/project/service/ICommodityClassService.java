package com.rzx.project.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rzx.project.model.domain.CommodityClass;

/**
 * 任智行 商品分类Service接口
 *
 * @author zy
 * @date 2021-09-28
 */
public interface ICommodityClassService extends IService<CommodityClass> {

    /**
     * 查询任智行 商品分类
     *
     * @param commodityclassId 任智行 商品分类ID
     * @return 任智行 商品分类
     */
    CommodityClass selectCommodityClassById(String commodityclassId);

    /**
     * 查询任智行 商品分类列表
     *
     * @param commodityClass 任智行 商品分类
     * @return 任智行 商品分类集合
     */
    List<CommodityClass> selectCommodityClassList(CommodityClass commodityClass);

    /**
     * 新增任智行 商品分类
     *
     * @param commodityClass 任智行 商品分类
     * @return 结果
     */
    int insertCommodityClass(CommodityClass commodityClass);

    /**
     * 修改任智行 商品分类
     *
     * @param commodityClass 任智行 商品分类
     * @return 结果
     */
    int updateCommodityClass(CommodityClass commodityClass);

    /**
     * 批量删除任智行 商品分类
     *
     * @param commodityclassIds 需要删除的任智行 商品分类ID
     * @return 结果
     */
    int deleteCommodityClassByIds(String[] commodityclassIds);

    /**
     * 删除任智行 商品分类信息
     *
     * @param commodityclassId 任智行 商品分类ID
     * @return 结果
     */
    int deleteCommodityClassById(String commodityclassId);

    /**
     * 批量插入
     * @param list
     * @throws Exception
     */
    int batchInsertOrUpdate(List<CommodityClass> list);
}
