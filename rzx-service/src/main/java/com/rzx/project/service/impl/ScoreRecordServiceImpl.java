package com.rzx.project.service.impl;

import java.util.List;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rzx.project.mapper.ScoreRecordMapper;
import com.rzx.project.domain.ScoreRecord;
import com.rzx.project.service.IScoreRecordService;

/**
 * 任智行渠道用户积分流水Service业务层处理
 *
 * @author zy
 * @date 2021-09-28
 */
@Service
public class ScoreRecordServiceImpl extends ServiceImpl<ScoreRecordMapper,ScoreRecord> implements IScoreRecordService {
    @Autowired
    private ScoreRecordMapper scoreRecordMapper;

    /**
     * 查询任智行渠道用户积分流水
     *
     * @param scoreRecordId 任智行渠道用户积分流水ID
     * @return 任智行渠道用户积分流水
     */
    @Override
    public ScoreRecord selectScoreRecordById(String scoreRecordId) {
        return scoreRecordMapper.selectScoreRecordById(scoreRecordId);
    }

    /**
     * 查询任智行渠道用户积分流水列表
     *
     * @param scoreRecord 任智行渠道用户积分流水
     * @return 任智行渠道用户积分流水
     */
    @Override
    public List<ScoreRecord> selectScoreRecordList(ScoreRecord scoreRecord) {
        return scoreRecordMapper.selectScoreRecordList(scoreRecord);
    }

    /**
     * 新增任智行渠道用户积分流水
     *
     * @param scoreRecord 任智行渠道用户积分流水
     * @return 结果
     */
    @Override
    public int insertScoreRecord(ScoreRecord scoreRecord) {
        LocalDateTime now = LocalDateTime.now();
        scoreRecord.setCreateTime(now);
        scoreRecord.setUpdateTime(now);
        return scoreRecordMapper.insert(scoreRecord);
    }

    /**
     * 修改任智行渠道用户积分流水
     *
     * @param scoreRecord 任智行渠道用户积分流水
     * @return 结果
     */
    @Override
    public int updateScoreRecord(ScoreRecord scoreRecord) {
        scoreRecord.setUpdateTime(LocalDateTime.now());
        return scoreRecordMapper.updateScoreRecord(scoreRecord);
    }

    /**
     * 批量删除任智行渠道用户积分流水
     *
     * @param scoreRecordIds 需要删除的任智行渠道用户积分流水ID
     * @return 结果
     */
    @Override
    public int deleteScoreRecordByIds(String[] scoreRecordIds) {
        return scoreRecordMapper.deleteScoreRecordByIds(scoreRecordIds);
    }

    /**
     * 删除任智行渠道用户积分流水信息
     *
     * @param scoreRecordId 任智行渠道用户积分流水ID
     * @return 结果
     */
    @Override
    public int deleteScoreRecordById(String scoreRecordId) {
        return scoreRecordMapper.deleteScoreRecordById(scoreRecordId);
    }
}
