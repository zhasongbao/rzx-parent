package com.rzx.framework.remote.hcb.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;

/**
 * 货车司机钱包
 *
 * @author zy
 * @date 2021/6/4 15:10
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TruckUserWallet {

    /**
     * 主键id
     */
    private String truckUserWalletId;

    /**
     * 身份证号码
     */
    private String idCode;

    /**
     * 姓名
     */
    private String name;

    /**
     * 余额
     */
    private String balance;

    /**
     * 储蓄卡号
     */
    private String cardNo;

    /**
     * 银行编码
     */
    private String bankNo;

    /**
     * 一类户银行名称
     */
    private String bankName;

    /**
     * 荣邦会员ID
     */
    private String rongBangId;

    /**
     * 二类户或结算户（电子账户）
     */
    private String mediumId;

    /**
     * 押金金额
     */
    private String depositAmt;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME")
    private String createTime;

    /**
     * 手机号码
     */
    @TableField(value = "PHONE")
    private String phone;

    /**
     * $column.columnComment
     */
    @TableField(value = "INTEGRAL")
    private String integral;

    /**
     * 冻结金额
     */
    private String freezeAmount;

    /**
     * 荣邦银行账号（二类户）
     */
    private String rongBanagBankNo;

    /**
     * 开户状态0未开通1处理中2开通成功3开通失败4审核中
     */
    private String accountStatus;

    /**
     * 开户失败原因
     */
    private String failReason;

    /**
     * 银行卡图片
     */
    private String bankUrl;

    /**
     * 0个人用户，1企业用户钱包
     */
    private String walletType;
}
