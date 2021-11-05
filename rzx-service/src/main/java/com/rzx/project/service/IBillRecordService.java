package com.rzx.project.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rzx.project.model.domain.BillRecord;

/**
 * 任智行 开票信息Service接口
 *
 * @author zy
 * @date 2021-09-28
 */
public interface IBillRecordService extends IService<BillRecord> {

    /**
     * 查询任智行 开票信息
     *
     * @param billRecordId 任智行 开票信息ID
     * @return 任智行 开票信息
     */
    BillRecord selectBillRecordById(String billRecordId);

    /**
     * 查询任智行 开票信息列表
     *
     * @param billRecord 任智行 开票信息
     * @return 任智行 开票信息集合
     */
    List<BillRecord> selectBillRecordList(BillRecord billRecord);

    /**
     * 新增任智行 开票信息
     *
     * @param billRecord 任智行 开票信息
     * @return 结果
     */
    int insertBillRecord(BillRecord billRecord);

    /**
     * 修改任智行 开票信息
     *
     * @param billRecord 任智行 开票信息
     * @return 结果
     */
    int updateBillRecord(BillRecord billRecord);

    /**
     * 批量删除任智行 开票信息
     *
     * @param billRecordIds 需要删除的任智行 开票信息ID
     * @return 结果
     */
    int deleteBillRecordByIds(String[] billRecordIds);

    /**
     * 删除任智行 开票信息信息
     *
     * @param billRecordId 任智行 开票信息ID
     * @return 结果
     */
    int deleteBillRecordById(String billRecordId);
}
