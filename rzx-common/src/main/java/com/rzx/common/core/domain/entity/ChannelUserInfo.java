package com.rzx.common.core.domain.entity;

import com.rzx.common.annotation.Excel;
import com.rzx.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户信息对象 rzx_user_info
 *
 * @author zy
 * @date 2021-09-16
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class ChannelUserInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String userInfoId;
    
    /** 来源用户id */
    @Excel(name = "渠道用户id")
    private String userId;

    /** 来源用户id */
    @Excel(name = "渠道用户类型(0-代理分销用户 1-普通用户 )")
    private String userType;

    /** 来源(1-任货行 2-任意行 3-任通行) */
    @Excel(name = "来源(1-任货行 2-任意行 3-任通行)")
    private String source;
    
    /** 状态(1-有效 0-无效) */
    @Excel(name = "状态(1-有效 0-无效)")
    private String status;
    
    /** 积分 */
    @Excel(name = "积分")
    private String score;
    


}
