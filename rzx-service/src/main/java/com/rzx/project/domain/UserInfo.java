package com.rzx.project.domain;

import com.rzx.common.core.domain.BaseEntity;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.rzx.common.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.ToString;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * 任智行渠道用户信息对象 rzx_user_info
 *
 * @author zhasbao
 * @date 2021-10-20
 */
@Data
@ToString
@TableName("rzx_user_info")
@ApiModel(value = "任智行渠道用户信息对象", description = "任智行渠道用户信息rzx_user_info表")
@EqualsAndHashCode(callSuper = true)
public class UserInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @ApiModelProperty(value = "${column.columnComment}")
    @TableId(value ="user_info_id", type = IdType.ASSIGN_ID)
    private String userInfoId;
    
    /** 渠道来源用户id 各渠道用户openId */
    @Excel(name = "渠道来源用户id 各渠道用户openId")
    @ApiModelProperty(value = "渠道来源用户id 各渠道用户openId")
    @TableField(value = "user_id")
    private String userId;

    /** 渠道来源用户id 各渠道用户openId */
    @Excel(name = "渠道用户类型")
    @ApiModelProperty(value = "渠道用户类型(0-代理分销用户 1-普通用户 )")
    @TableField(value = "user_type")
    private String userType;
    
    /** 渠道来源(1-任货行 2-任意行 3-任通行) */
    @Excel(name = "渠道来源(1-任货行 2-任意行 3-任通行)")
    @ApiModelProperty(value = "渠道来源(1-任货行 2-任意行 3-任通行)")
    @TableField(value = "source")
    private String source;
    
    /** 状态(1-有效 0-无效) */
    @Excel(name = "状态(1-有效 0-无效)")
    @ApiModelProperty(value = "状态(1-有效 0-无效)")
    @TableField(value = "status")
    private String status;
    
    /** 积分 */
    @Excel(name = "积分")
    @ApiModelProperty(value = "积分")
    @TableField(value = "score")
    private Integer score;
    


}
