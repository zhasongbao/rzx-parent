package com.rzx.project.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rzx.common.annotation.Excel;
import com.rzx.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * 任智行渠道用户积分流水对象 rzx_score_record
 *
 * @author zy
 * @date 2021-09-28
 */
@Data
@Builder
@TableName("rzx_score_record")
@ApiModel(value = "任智行渠道用户积分流水对象", description = "任智行渠道用户积分流水rzx_score_record表")
public class ScoreRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Tolerate
    public ScoreRecord() {
    }

    /** $column.columnComment */
    @ApiModelProperty(value = "${column.columnComment}")
    @TableId(value ="score_record_id", type = IdType.ASSIGN_ID)
    private String scoreRecordId;
    
    /** 用户id */
    @Excel(name = "用户id")
    @ApiModelProperty(value = "用户id")
    @TableField(value = "user_info_id")
    private String userInfoId;
    
    /** 渠道来源用户id */
    @Excel(name = "渠道来源用户id")
    @ApiModelProperty(value = "渠道来源用户id")
    @TableField(value = "user_id")
    private String userId;
    
    /** 渠道来源(1-任货行 2-任意行 3-任通行) */
    @Excel(name = "渠道来源(1-任货行 2-任意行 3-任通行)")
    @ApiModelProperty(value = "渠道来源(1-任货行 2-任意行 3-任通行)")
    @TableField(value = "in_source")
    private String inSource;
    
    /** 来源id */
    @Excel(name = "来源id")
    @ApiModelProperty(value = "来源id")
    @TableField(value = "source_id")
    private String sourceId;
    
    /** 来源类型 1-积分消费 2-渠道积分转换 */
    @Excel(name = "来源类型 1-积分消费 2-渠道积分转换")
    @ApiModelProperty(value = "来源类型 1-积分消费 2-渠道积分转换")
    @TableField(value = "source_type")
    private String sourceType;
    
    /** 积分 */
    @Excel(name = "积分")
    @ApiModelProperty(value = "积分")
    @TableField(value = "score")
    private Integer score;
    
    /** 当前剩余积分 */
    @Excel(name = "当前剩余积分")
    @ApiModelProperty(value = "当前剩余积分")
    @TableField(value = "surplus_score")
    private Integer surplusScore;
    
    /** 状态(1-有效 0-无效) */
    @Excel(name = "状态(1-有效 0-无效)")
    @ApiModelProperty(value = "状态(1-有效 0-无效)")
    @TableField(value = "status")
    private String status;
    


}
