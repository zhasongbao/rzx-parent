package com.rzx.project.service.impl;

import java.util.List;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rzx.project.mapper.CouponsInfoMapper;
import com.rzx.project.domain.CouponsInfo;
import com.rzx.project.service.ICouponsInfoService;

/**
 * 任智行 券信息Service业务层处理
 *
 * @author zy
 * @date 2021-09-28
 */
@Service
public class CouponsInfoServiceImpl extends ServiceImpl<CouponsInfoMapper,CouponsInfo> implements ICouponsInfoService {
    @Autowired
    private CouponsInfoMapper couponsInfoMapper;

    /**
     * 查询任智行 券信息
     *
     * @param couponsinfoId 任智行 券信息ID
     * @return 任智行 券信息
     */
    @Override
    public CouponsInfo selectCouponsInfoById(String couponsinfoId) {
        return couponsInfoMapper.selectCouponsInfoById(couponsinfoId);
    }

    /**
     * 查询任智行 券信息列表
     *
     * @param couponsInfo 任智行 券信息
     * @return 任智行 券信息
     */
    @Override
    public List<CouponsInfo> selectCouponsInfoList(CouponsInfo couponsInfo) {
        return couponsInfoMapper.selectCouponsInfoList(couponsInfo);
    }

    /**
     * 新增任智行 券信息
     *
     * @param couponsInfo 任智行 券信息
     * @return 结果
     */
    @Override
    public int insertCouponsInfo(CouponsInfo couponsInfo) {
        LocalDateTime now = LocalDateTime.now();
        couponsInfo.setCreateTime(now);
        couponsInfo.setUpdateTime(now);
        return couponsInfoMapper.insert(couponsInfo);
    }

    /**
     * 修改任智行 券信息
     *
     * @param couponsInfo 任智行 券信息
     * @return 结果
     */
    @Override
    public int updateCouponsInfo(CouponsInfo couponsInfo) {
        couponsInfo.setUpdateTime(LocalDateTime.now());
        return couponsInfoMapper.updateCouponsInfo(couponsInfo);
    }

    /**
     * 批量删除任智行 券信息
     *
     * @param couponsinfoIds 需要删除的任智行 券信息ID
     * @return 结果
     */
    @Override
    public int deleteCouponsInfoByIds(String[] couponsinfoIds) {
        return couponsInfoMapper.deleteCouponsInfoByIds(couponsinfoIds);
    }

    /**
     * 删除任智行 券信息信息
     *
     * @param couponsinfoId 任智行 券信息ID
     * @return 结果
     */
    @Override
    public int deleteCouponsInfoById(String couponsinfoId) {
        return couponsInfoMapper.deleteCouponsInfoById(couponsinfoId);
    }
}
