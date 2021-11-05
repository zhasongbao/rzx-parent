package com.rzx.project.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rzx.project.model.domain.CouponsInfo;

/**
 * 任智行 券信息Mapper接口
 *
 * @author zy
 * @date 2021-09-28
 */
public interface CouponsInfoMapper extends BaseMapper<CouponsInfo> {

    /**
     * 查询任智行 券信息
     *
     * @param couponsinfoId 任智行 券信息ID
     * @return 任智行 券信息
     */
    CouponsInfo selectCouponsInfoById(String couponsinfoId);

    /**
     * 查询任智行 券信息列表
     *
     * @param couponsInfo 任智行 券信息
     * @return 任智行 券信息集合
     */
    List<CouponsInfo> selectCouponsInfoList(CouponsInfo couponsInfo);


    /**
     * 修改任智行 券信息
     *
     * @param couponsInfo 任智行 券信息
     * @return 结果
     */
    int updateCouponsInfo(CouponsInfo couponsInfo);

    /**
     * 删除任智行 券信息
     *
     * @param couponsinfoId 任智行 券信息ID
     * @return 结果
     */
    int deleteCouponsInfoById(String couponsinfoId);

    /**
     * 批量删除任智行 券信息
     *
     * @param couponsinfoIds 需要删除的数据ID
     * @return 结果
     */
    int deleteCouponsInfoByIds(String[] couponsinfoIds);
}
