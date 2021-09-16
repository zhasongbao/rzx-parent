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
 * 用户信息对象 rzx_user_info
 *
 * @author zy
 * @date 2021-09-16
 */
@Data
@ToString
@TableName("rzx_user_info")
@ApiModel(value = "用户信息对象", description = "用户信息rzx_user_info表")
@EqualsAndHashCode(callSuper = true)
public class UserInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @ApiModelProperty(value = "${column.columnComment}")
    @TableId(value ="user_info_id", type = IdType.ASSIGN_ID)
    private String userInfoId;
    
    /** 来源用户id */
    @Excel(name = "来源用户id")
    @ApiModelProperty(value = "来源用户id")
    @TableField(value = "user_id")
    private String userId;
    
    /** 来源(1-任货行 2-任意行 3-任通行) */
    @Excel(name = "来源(1-任货行 2-任意行 3-任通行)")
    @ApiModelProperty(value = "来源(1-任货行 2-任意行 3-任通行)")
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
    private String score;
    


}
