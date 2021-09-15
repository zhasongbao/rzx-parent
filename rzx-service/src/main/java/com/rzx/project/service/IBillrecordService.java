package com.rzx.project.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rzx.project.domain.Billrecord;

/**
 * 任智行 开票信息Service接口
 *
 * @author zy
 * @date 2021-09-15
 */
public interface IBillrecordService extends IService<Billrecord> {

    /**
     * 查询任智行 开票信息
     *
     * @param billrecordId 任智行 开票信息ID
     * @return 任智行 开票信息
     */
    Billrecord selectBillrecordById(String billrecordId);

    /**
     * 查询任智行 开票信息列表
     *
     * @param billrecord 任智行 开票信息
     * @return 任智行 开票信息集合
     */
    List<Billrecord> selectBillrecordList(Billrecord billrecord);

    /**
     * 新增任智行 开票信息
     *
     * @param billrecord 任智行 开票信息
     * @return 结果
     */
    int insertBillrecord(Billrecord billrecord);

    /**
     * 修改任智行 开票信息
     *
     * @param billrecord 任智行 开票信息
     * @return 结果
     */
    int updateBillrecord(Billrecord billrecord);

    /**
     * 批量删除任智行 开票信息
     *
     * @param billrecordIds 需要删除的任智行 开票信息ID
     * @return 结果
     */
    int deleteBillrecordByIds(String[] billrecordIds);

    /**
     * 删除任智行 开票信息信息
     *
     * @param billrecordId 任智行 开票信息ID
     * @return 结果
     */
    int deleteBillrecordById(String billrecordId);
}
