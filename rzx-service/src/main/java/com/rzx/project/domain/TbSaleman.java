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
 * 任意行用户对象 tb_saleman
 *
 * @author zhasbao
 * @date 2021-11-02
 */
@Data
@ToString
@TableName("tb_saleman")
@ApiModel(value = "任意行用户对象", description = "任意行用户tb_saleman表")
@EqualsAndHashCode(callSuper = true)
public class TbSaleman extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @ApiModelProperty(value = "${column.columnComment}")
    @TableId(value ="SALEMAN_ID", type = IdType.ASSIGN_ID)
    private String salemanId;
    
    /** 业务员名称 */
    @Excel(name = "业务员名称")
    @ApiModelProperty(value = "业务员名称")
    @TableField(value = "NAME")
    private String name;
    
    /** 手机号码 */
    @Excel(name = "手机号码")
    @ApiModelProperty(value = "手机号码")
    @TableField(value = "PHONE")
    private String phone;
    
    /** 身份证号码 */
    @Excel(name = "身份证号码")
    @ApiModelProperty(value = "身份证号码")
    @TableField(value = "IDENTITY_CARD")
    private String identityCard;
    
    /** 上级业务员ID */
    @Excel(name = "上级业务员ID")
    @ApiModelProperty(value = "上级业务员ID")
    @TableField(value = "PARENT_ID")
    private String parentId;
    
    /** 提现密码 */
    @Excel(name = "提现密码")
    @ApiModelProperty(value = "提现密码")
    @TableField(value = "WITHDRAW_PWD")
    private String withdrawPwd;
    
    /** 业务员登录密码 */
    @Excel(name = "业务员登录密码")
    @ApiModelProperty(value = "业务员登录密码")
    @TableField(value = "LOGIN_PWD")
    private String loginPwd;
    
    /** 销量 */
    @Excel(name = "销量")
    @ApiModelProperty(value = "销量")
    @TableField(value = "SALES_VOLUME")
    private Long salesVolume;
    
    /** 创建时间 */
    @Excel(name = "创建时间")
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATEDATE")
    private String createdate;
    
    /** 最后一次登入时间 */
    @Excel(name = "最后一次登入时间")
    @ApiModelProperty(value = "最后一次登入时间")
    @TableField(value = "LASTLOGINTIME")
    private String lastlogintime;
    
    /** 个人积分结余 */
    @Excel(name = "个人积分结余")
    @ApiModelProperty(value = "个人积分结余")
    @TableField(value = "PERSONAL_INTEGRAL_BALANCE")
    private String personalIntegralBalance;
    
    /** 团队积分结余 */
    @Excel(name = "团队积分结余")
    @ApiModelProperty(value = "团队积分结余")
    @TableField(value = "TEAM_INTEGRAL_BALANCE")
    private String teamIntegralBalance;
    
    /** 个人积分 */
    @Excel(name = "个人积分")
    @ApiModelProperty(value = "个人积分")
    @TableField(value = "PERSONAL_INTEGRAL")
    private String personalIntegral;
    
    /** 团队积分 */
    @Excel(name = "团队积分")
    @ApiModelProperty(value = "团队积分")
    @TableField(value = "TEAM_INTEGRAL")
    private String teamIntegral;
    
    /** 用户状态(0:冻结)  1:正常 */
    @Excel(name = "用户状态(0:冻结)  1:正常")
    @ApiModelProperty(value = "用户状态(0:冻结)  1:正常")
    @TableField(value = "STATUS")
    private String status;
    
    /** 推广二维码url */
    @Excel(name = "推广二维码url")
    @ApiModelProperty(value = "推广二维码url")
    @TableField(value = "EXTEND_QRURL")
    private String extendQrurl;
    
    /** 历史销量 */
    @Excel(name = "历史销量")
    @ApiModelProperty(value = "历史销量")
    @TableField(value = "historySales")
    private String historysales;
    
    /** 上月销量 */
    @Excel(name = "上月销量")
    @ApiModelProperty(value = "上月销量")
    @TableField(value = "lastMonthSales")
    private String lastmonthsales;
    
    /** 微信OPENID */
    @Excel(name = "微信OPENID")
    @ApiModelProperty(value = "微信OPENID")
    @TableField(value = "WEIXIN_OPENID")
    private String weixinOpenid;
    
    /** 微信昵称 */
    @Excel(name = "微信昵称")
    @ApiModelProperty(value = "微信昵称")
    @TableField(value = "WEIXIN_NAME")
    private String weixinName;
    
    /** 微信头像 */
    @Excel(name = "微信头像")
    @ApiModelProperty(value = "微信头像")
    @TableField(value = "WEIXIN_PORTRAIT")
    private String weixinPortrait;
    
    /** 用户昵称 */
    @Excel(name = "用户昵称")
    @ApiModelProperty(value = "用户昵称")
    @TableField(value = "NICKNAME")
    private String nickname;
    
    /** 支付宝OPENID */
    @Excel(name = "支付宝OPENID")
    @ApiModelProperty(value = "支付宝OPENID")
    @TableField(value = "ALIPAY_OPENID")
    private String alipayOpenid;
    
    /** $column.columnComment */
    @Excel(name = "支付宝OPENID")
    @ApiModelProperty(value = "${column.columnComment}")
    @TableField(value = "USER_FLAG")
    private String userFlag;
    
    /** 自己对应的openid */
    @Excel(name = "自己对应的openid")
    @ApiModelProperty(value = "自己对应的openid")
    @TableField(value = "OWNER_WEIXIN_OPENID")
    private String ownerWeixinOpenid;
    
    /** 是否VIP */
    @Excel(name = "是否VIP")
    @ApiModelProperty(value = "是否VIP")
    @TableField(value = "IS_VIP")
    private String isVip;
    
    /** 所属经代公司(运营商)ID  */
    @Excel(name = "所属经代公司(运营商)ID ")
    @ApiModelProperty(value = "所属经代公司(运营商)ID ")
    @TableField(value = "AGENTCOMPANY_ID")
    private String agentcompanyId;
    
    /** 省份ID */
    @Excel(name = "省份ID")
    @ApiModelProperty(value = "省份ID")
    @TableField(value = "PROVINCE_ID")
    private String provinceId;
    
    /** 城市ID */
    @Excel(name = "城市ID")
    @ApiModelProperty(value = "城市ID")
    @TableField(value = "CITY_ID")
    private String cityId;
    
    /** 是否种子用户 */
    @Excel(name = "是否种子用户")
    @ApiModelProperty(value = "是否种子用户")
    @TableField(value = "IS_SEEDUSER")
    private String isSeeduser;
    
    /** 询单量 */
    @Excel(name = "询单量")
    @ApiModelProperty(value = "询单量")
    @TableField(value = "INQUIRY_LIST")
    private String inquiryList;
    
    /** 出单量 */
    @Excel(name = "出单量")
    @ApiModelProperty(value = "出单量")
    @TableField(value = "SINGLE_VOLUME")
    private String singleVolume;
    
    /** 保贝数量 */
    @Excel(name = "保贝数量")
    @ApiModelProperty(value = "保贝数量")
    @TableField(value = "BAOBEI")
    private String baobei;
    
    /** 原父节点 */
    @Excel(name = "原父节点")
    @ApiModelProperty(value = "原父节点")
    @TableField(value = "OLD_PARENT_ID")
    private String oldParentId;
    
    /** 身份证url */
    @Excel(name = "身份证url")
    @ApiModelProperty(value = "身份证url")
    @TableField(value = "IDCARD_URL")
    private String idcardUrl;
    
    /** 是否已经实名认证(0否 1是) */
    @Excel(name = "是否已经实名认证(0否 1是)")
    @ApiModelProperty(value = "是否已经实名认证(0否 1是)")
    @TableField(value = "HAS_REALAUTHEN")
    private String hasRealauthen;
    
    /** 是否备案（0未备案，1已备案） */
    @Excel(name = "是否备案", readConverterExp = "0=未备案，1已备案")
    @ApiModelProperty(value = "是否备案（0未备案，1已备案）")
    @TableField(value = "IS_RECORDS")
    private String isRecords;
    
    /** 业务员类别（0-通行宝柜员 1-普通代理人员） */
    @Excel(name = "业务员类别", readConverterExp = "0=-通行宝柜员,1=-普通代理人员")
    @ApiModelProperty(value = "业务员类别（0-通行宝柜员 1-普通代理人员）")
    @TableField(value = "USER_TYPE")
    private String userType;
    
    /** 是否可以线上支付(0可以 1不可以) */
    @Excel(name = "是否可以线上支付(0可以 1不可以)")
    @ApiModelProperty(value = "是否可以线上支付(0可以 1不可以)")
    @TableField(value = "ONLINEPAY_FLAG")
    private String onlinepayFlag;
    
    /** 是否注册加多保VIP（0未注册，1未支付，2 VIP） */
    @Excel(name = "是否注册加多保VIP", readConverterExp = "0=未注册，1未支付，2,V=IP")
    @ApiModelProperty(value = "是否注册加多保VIP（0未注册，1未支付，2 VIP）")
    @TableField(value = "VIP")
    private String vip;
    
    /** 出单城市ID */
    @Excel(name = "出单城市ID")
    @ApiModelProperty(value = "出单城市ID")
    @TableField(value = "ISSUE_CITY_ID")
    private String issueCityId;
    
    /** 一级下线人数 */
    @Excel(name = "一级下线人数")
    @ApiModelProperty(value = "一级下线人数")
    @TableField(value = "ONE_LEVEL")
    private String oneLevel;
    
    /** 二级下线人数 */
    @Excel(name = "二级下线人数")
    @ApiModelProperty(value = "二级下线人数")
    @TableField(value = "TWO_LEVEL")
    private String twoLevel;
    
    /** 三级下线人数 */
    @Excel(name = "三级下线人数")
    @ApiModelProperty(value = "三级下线人数")
    @TableField(value = "THREE_LEVEL")
    private String threeLevel;
    
    /** 是否无条件打开登录（0否，1是） */
    @Excel(name = "是否无条件打开登录", readConverterExp = "0=否，1是")
    @ApiModelProperty(value = "是否无条件打开登录（0否，1是）")
    @TableField(value = "IS_OPEN_LOGIN")
    private String isOpenLogin;
    
    /** 考试分数 */
    @Excel(name = "考试分数")
    @ApiModelProperty(value = "考试分数")
    @TableField(value = "EXAM_SCORE")
    private String examScore;
    
    /** 学历 */
    @Excel(name = "学历")
    @ApiModelProperty(value = "学历")
    @TableField(value = "EDUCATION")
    private String education;
    
    /** 渠道名称 */
    @Excel(name = "渠道名称")
    @ApiModelProperty(value = "渠道名称")
    @TableField(value = "CHANNEL_NAME")
    private String channelName;
    
    /** 渠道分成比例 */
    @Excel(name = "渠道分成比例")
    @ApiModelProperty(value = "渠道分成比例")
    @TableField(value = "CHANNEL_RATE")
    private String channelRate;
    
    /** 渠道车保贝负责人 */
    @Excel(name = "渠道车保贝负责人")
    @ApiModelProperty(value = "渠道车保贝负责人")
    @TableField(value = "CHANNEL_SALEMAN")
    private String channelSaleman;
    
    /** ETC专属二维码 */
    @Excel(name = "ETC专属二维码")
    @ApiModelProperty(value = "ETC专属二维码")
    @TableField(value = "ETC_EXTEND_QRURL")
    private String etcExtendQrurl;
    
    /** 0一般渠道 1头部渠道 */
    @Excel(name = "0一般渠道 1头部渠道")
    @ApiModelProperty(value = "0一般渠道 1头部渠道")
    @TableField(value = "CHANNEL_ATTRIBUTE")
    private String channelAttribute;
    
    /** 登录openid */
    @Excel(name = "登录openid")
    @ApiModelProperty(value = "登录openid")
    @TableField(value = "LOGIN_WEIXIN_OPENID")
    private String loginWeixinOpenid;
    
    /** 发放下级比例 */
    @Excel(name = "发放下级比例")
    @ApiModelProperty(value = "发放下级比例")
    @TableField(value = "SUBORDINATE_RATE")
    private String subordinateRate;
    
    /** 渠道公司ID */
    @Excel(name = "渠道公司ID")
    @ApiModelProperty(value = "渠道公司ID")
    @TableField(value = "CHANNELCOMPANY_ID")
    private String channelcompanyId;
    
    /** 通行收益是否转为保贝(0否，1是,2财务公对公转账) */
    @Excel(name = "通行收益是否转为保贝(0否，1是,2财务公对公转账)")
    @ApiModelProperty(value = "通行收益是否转为保贝(0否，1是,2财务公对公转账)")
    @TableField(value = "CHANNEL_TOBAOBEI")
    private String channelTobaobei;
    
    /** 渠道LOGO */
    @Excel(name = "渠道LOGO")
    @ApiModelProperty(value = "渠道LOGO")
    @TableField(value = "CHANNEL_LOGO")
    private String channelLogo;
    
    /** 银行卡卡号 */
    @Excel(name = "银行卡卡号")
    @ApiModelProperty(value = "银行卡卡号")
    @TableField(value = "BIND_MEDIUM")
    private String bindMedium;
    
    /** 银行预留手机号 */
    @Excel(name = "银行预留手机号")
    @ApiModelProperty(value = "银行预留手机号")
    @TableField(value = "MOBILE_NO")
    private String mobileNo;
    
    /** 银行编码 */
    @Excel(name = "银行编码")
    @ApiModelProperty(value = "银行编码")
    @TableField(value = "BANK_NO")
    private String bankNo;
    
    /** 合作方交易单号 */
    @Excel(name = "合作方交易单号")
    @ApiModelProperty(value = "合作方交易单号")
    @TableField(value = "CORP_SERNO")
    private String corpSerno;
    
    /** 是否带保险 */
    @Excel(name = "是否带保险")
    @ApiModelProperty(value = "是否带保险")
    @TableField(value = "HAS_INSURE")
    private String hasInsure;
    
    /** 所属运营商  已废弃 */
    @Excel(name = "所属运营商  已废弃")
    @ApiModelProperty(value = "所属运营商  已废弃")
    @TableField(value = "BELONG_OPERATOR_CODE")
    private String belongOperatorCode;
    
    /** 所属片区店 */
    @Excel(name = "所属片区店")
    @ApiModelProperty(value = "所属片区店")
    @TableField(value = "BELONG_AREA")
    private String belongArea;
    
    /** 所属门店 */
    @Excel(name = "所属门店")
    @ApiModelProperty(value = "所属门店")
    @TableField(value = "BELONG_CITY")
    private String belongCity;
    
    /** 是否可提现 */
    @Excel(name = "是否可提现")
    @ApiModelProperty(value = "是否可提现")
    @TableField(value = "CAN_WITHDRAW")
    private String canWithdraw;
    
    /** 执业证编号 */
    @Excel(name = "执业证编号")
    @ApiModelProperty(value = "执业证编号")
    @TableField(value = "PRACTICE_CODE")
    private String practiceCode;
    


}
