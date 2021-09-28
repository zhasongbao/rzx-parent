package com.rzx.project.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rzx.project.domain.BillRecord;

/**
 * 任智行 开票信息Mapper接口
 *
 * @author zy
 * @date 2021-09-28
 */
public interface BillRecordMapper extends BaseMapper<BillRecord> {

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
     * 修改任智行 开票信息
     *
     * @param billRecord 任智行 开票信息
     * @return 结果
     */
    int updateBillRecord(BillRecord billRecord);

    /**
     * 删除任智行 开票信息
     *
     * @param billRecordId 任智行 开票信息ID
     * @return 结果
     */
    int deleteBillRecordById(String billRecordId);

    /**
     * 批量删除任智行 开票信息
     *
     * @param billRecordIds 需要删除的数据ID
     * @return 结果
     */
    int deleteBillRecordByIds(String[] billRecordIds);
}
