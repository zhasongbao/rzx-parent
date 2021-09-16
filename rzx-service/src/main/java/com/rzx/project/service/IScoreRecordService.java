package com.rzx.project.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rzx.project.domain.ScoreRecord;

/**
 * 用户积分流水Service接口
 *
 * @author zy
 * @date 2021-09-16
 */
public interface IScoreRecordService extends IService<ScoreRecord> {

    /**
     * 查询用户积分流水
     *
     * @param scoreRecordId 用户积分流水ID
     * @return 用户积分流水
     */
    ScoreRecord selectScoreRecordById(String scoreRecordId);

    /**
     * 查询用户积分流水列表
     *
     * @param scoreRecord 用户积分流水
     * @return 用户积分流水集合
     */
    List<ScoreRecord> selectScoreRecordList(ScoreRecord scoreRecord);

    /**
     * 新增用户积分流水
     *
     * @param scoreRecord 用户积分流水
     * @return 结果
     */
    int insertScoreRecord(ScoreRecord scoreRecord);

    /**
     * 修改用户积分流水
     *
     * @param scoreRecord 用户积分流水
     * @return 结果
     */
    int updateScoreRecord(ScoreRecord scoreRecord);

    /**
     * 批量删除用户积分流水
     *
     * @param scoreRecordIds 需要删除的用户积分流水ID
     * @return 结果
     */
    int deleteScoreRecordByIds(String[] scoreRecordIds);

    /**
     * 删除用户积分流水信息
     *
     * @param scoreRecordId 用户积分流水ID
     * @return 结果
     */
    int deleteScoreRecordById(String scoreRecordId);
}
