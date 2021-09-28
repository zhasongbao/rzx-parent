package com.rzx.project.service.impl;

import java.util.List;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rzx.project.mapper.BillRecordMapper;
import com.rzx.project.domain.BillRecord;
import com.rzx.project.service.IBillRecordService;

/**
 * 任智行 开票信息Service业务层处理
 *
 * @author zy
 * @date 2021-09-28
 */
@Service
public class BillRecordServiceImpl extends ServiceImpl<BillRecordMapper,BillRecord> implements IBillRecordService {
    @Autowired
    private BillRecordMapper billRecordMapper;

    /**
     * 查询任智行 开票信息
     *
     * @param billRecordId 任智行 开票信息ID
     * @return 任智行 开票信息
     */
    @Override
    public BillRecord selectBillRecordById(String billRecordId) {
        return billRecordMapper.selectBillRecordById(billRecordId);
    }

    /**
     * 查询任智行 开票信息列表
     *
     * @param billRecord 任智行 开票信息
     * @return 任智行 开票信息
     */
    @Override
    public List<BillRecord> selectBillRecordList(BillRecord billRecord) {
        return billRecordMapper.selectBillRecordList(billRecord);
    }

    /**
     * 新增任智行 开票信息
     *
     * @param billRecord 任智行 开票信息
     * @return 结果
     */
    @Override
    public int insertBillRecord(BillRecord billRecord) {
        LocalDateTime now = LocalDateTime.now();
        billRecord.setCreateTime(now);
        billRecord.setUpdateTime(now);
        return billRecordMapper.insert(billRecord);
    }

    /**
     * 修改任智行 开票信息
     *
     * @param billRecord 任智行 开票信息
     * @return 结果
     */
    @Override
    public int updateBillRecord(BillRecord billRecord) {
        billRecord.setUpdateTime(LocalDateTime.now());
        return billRecordMapper.updateBillRecord(billRecord);
    }

    /**
     * 批量删除任智行 开票信息
     *
     * @param billRecordIds 需要删除的任智行 开票信息ID
     * @return 结果
     */
    @Override
    public int deleteBillRecordByIds(String[] billRecordIds) {
        return billRecordMapper.deleteBillRecordByIds(billRecordIds);
    }

    /**
     * 删除任智行 开票信息信息
     *
     * @param billRecordId 任智行 开票信息ID
     * @return 结果
     */
    @Override
    public int deleteBillRecordById(String billRecordId) {
        return billRecordMapper.deleteBillRecordById(billRecordId);
    }
}
