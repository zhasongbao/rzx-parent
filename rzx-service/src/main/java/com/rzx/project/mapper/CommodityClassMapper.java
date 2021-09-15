package com.rzx.project.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rzx.project.domain.CommodityClass;

/**
 * 任智行 商品分类Mapper接口
 *
 * @author zy
 * @date 2021-09-15
 */
public interface CommodityClassMapper extends BaseMapper<CommodityClass> {

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
     * 修改任智行 商品分类
     *
     * @param commodityClass 任智行 商品分类
     * @return 结果
     */
    int updateCommodityClass(CommodityClass commodityClass);

    /**
     * 删除任智行 商品分类
     *
     * @param commodityclassId 任智行 商品分类ID
     * @return 结果
     */
    int deleteCommodityClassById(String commodityclassId);

    /**
     * 批量删除任智行 商品分类
     *
     * @param commodityclassIds 需要删除的数据ID
     * @return 结果
     */
    int deleteCommodityClassByIds(String[] commodityclassIds);
}
