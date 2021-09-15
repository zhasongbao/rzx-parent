package com.rzx.project.service.impl;

import java.util.List;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rzx.project.mapper.BillrecordMapper;
import com.rzx.project.domain.Billrecord;
import com.rzx.project.service.IBillrecordService;

/**
 * 任智行 开票信息Service业务层处理
 *
 * @author zy
 * @date 2021-09-15
 */
@Service
public class BillrecordServiceImpl extends ServiceImpl<BillrecordMapper,Billrecord> implements IBillrecordService {
    @Autowired
    private BillrecordMapper billrecordMapper;

    /**
     * 查询任智行 开票信息
     *
     * @param billrecordId 任智行 开票信息ID
     * @return 任智行 开票信息
     */
    @Override
    public Billrecord selectBillrecordById(String billrecordId) {
        return billrecordMapper.selectBillrecordById(billrecordId);
    }

    /**
     * 查询任智行 开票信息列表
     *
     * @param billrecord 任智行 开票信息
     * @return 任智行 开票信息
     */
    @Override
    public List<Billrecord> selectBillrecordList(Billrecord billrecord) {
        return billrecordMapper.selectBillrecordList(billrecord);
    }

    /**
     * 新增任智行 开票信息
     *
     * @param billrecord 任智行 开票信息
     * @return 结果
     */
    @Override
    public int insertBillrecord(Billrecord billrecord) {
        LocalDateTime now = LocalDateTime.now();
        billrecord.setCreateTime(now);
        billrecord.setUpdateTime(now);
        return billrecordMapper.insert(billrecord);
    }

    /**
     * 修改任智行 开票信息
     *
     * @param billrecord 任智行 开票信息
     * @return 结果
     */
    @Override
    public int updateBillrecord(Billrecord billrecord) {
        billrecord.setUpdateTime(LocalDateTime.now());
        return billrecordMapper.updateBillrecord(billrecord);
    }

    /**
     * 批量删除任智行 开票信息
     *
     * @param billrecordIds 需要删除的任智行 开票信息ID
     * @return 结果
     */
    @Override
    public int deleteBillrecordByIds(String[] billrecordIds) {
        return billrecordMapper.deleteBillrecordByIds(billrecordIds);
    }

    /**
     * 删除任智行 开票信息信息
     *
     * @param billrecordId 任智行 开票信息ID
     * @return 结果
     */
    @Override
    public int deleteBillrecordById(String billrecordId) {
        return billrecordMapper.deleteBillrecordById(billrecordId);
    }
}
