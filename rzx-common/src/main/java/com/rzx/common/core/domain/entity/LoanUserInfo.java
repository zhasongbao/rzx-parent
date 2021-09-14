package com.rzx.common.core.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.rzx.common.annotation.Excel;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 司机贷用户对象 fm_load_user_info
 *
 * @author zy
 * @date 2021-06-04
 */
@Data
@ToString
@Builder
@TableName("fm_loan_user_info")
@AllArgsConstructor
@NoArgsConstructor
public class LoanUserInfo implements Serializable {

    private static final long serialVersionUID = 2233484662138266771L;

    /**
     * 主键id
     */
    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private Long userId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 修改人
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableField(value = "del_flag")
    private Boolean delFlag;


    /**
     * 支付密码
     */
    @Excel(name = "支付密码")
    @TableField(value = "pay_password")
    private String payPassword;

    /**
     * 贷款状态（ 处理状态：000 未申请， 001 审批中 002 审批失败 003 审批通过 ，004 逾期中）
     */
    @Excel(name = "贷款状态", readConverterExp = "处=理状态：000,未=申请，,0=01,审=批中,0=02,审=批失败,0=03,审=批通过,，=004,逾=期中")
    @TableField(value = "loan_status")
    private String loanStatus;

    /**
     * 用户状态；0、正常；1、停用；2、删除
     */
    @Excel(name = "用户状态；0、正常；1、停用")
    @TableField(value = "user_status")
    private String userStatus;

    /**
     * 用户名
     */
    @Excel(name = "用户名")
    @TableField(value = "username")
    private String username;

    /**
     * 身份证
     */
    @Excel(name = "身份证")
    @TableField(value = "id_card")
    private String idCard;

    /**
     * 手机号码
     */
    @Excel(name = "手机号码")
    @TableField(value = "phone_number")
    private String phoneNumber;

    /**
     * 用户性别（1男 2女 3未知）
     */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    @TableField(value = "sex")
    private Integer sex;

    /**
     * 逾期天数
     */
    @Excel(name = "逾期天数", readConverterExp = "逾期天数")
    @TableField(value = "overdue_day")
    private Integer overdueDay;

    /**
     * 身份证姓名
     */
    @Excel(name = "身份证姓名")
    @TableField(value = "id_card_name")
    private String idCardName;
}
