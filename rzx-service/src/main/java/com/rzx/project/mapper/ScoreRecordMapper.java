package com.rzx.project.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rzx.project.domain.ScoreRecord;

/**
 * 任智行渠道用户积分流水Mapper接口
 *
 * @author zy
 * @date 2021-09-28
 */
public interface ScoreRecordMapper extends BaseMapper<ScoreRecord> {

    /**
     * 查询任智行渠道用户积分流水
     *
     * @param scoreRecordId 任智行渠道用户积分流水ID
     * @return 任智行渠道用户积分流水
     */
    ScoreRecord selectScoreRecordById(String scoreRecordId);

    /**
     * 查询任智行渠道用户积分流水列表
     *
     * @param scoreRecord 任智行渠道用户积分流水
     * @return 任智行渠道用户积分流水集合
     */
    List<ScoreRecord> selectScoreRecordList(ScoreRecord scoreRecord);


    /**
     * 修改任智行渠道用户积分流水
     *
     * @param scoreRecord 任智行渠道用户积分流水
     * @return 结果
     */
    int updateScoreRecord(ScoreRecord scoreRecord);

    /**
     * 删除任智行渠道用户积分流水
     *
     * @param scoreRecordId 任智行渠道用户积分流水ID
     * @return 结果
     */
    int deleteScoreRecordById(String scoreRecordId);

    /**
     * 批量删除任智行渠道用户积分流水
     *
     * @param scoreRecordIds 需要删除的数据ID
     * @return 结果
     */
    int deleteScoreRecordByIds(String[] scoreRecordIds);
}
