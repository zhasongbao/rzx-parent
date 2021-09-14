package com.rzx.common.enums;

/**
 * 司机贷款申请状态枚举类
 *
 * @author zy
 * @date 2021/6/8 10:11
 */
public enum LoanCreditApplyEnum {

    /**
     * 未申请
     */
    NOT_APPLY("000", "未申请"),

    /**
     * 审批中
     */
    WAIT("001", "审批中"),

    /**
     * 审批失败
     */
    FAIL("002", "审批失败"),

    /**
     * 审批通过
     */
    SUCCESS("003", "审批通过"),

    /**
     * 逾期中
     */
    OVERDUE("004", "逾期中");

    /**
     * 状态码
     */
    private final String code;

    /**
     * 状态信息
     */
    private final String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    LoanCreditApplyEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static LoanCreditApplyEnum enums(String code) {
        for (LoanCreditApplyEnum creditApplyEnum : LoanCreditApplyEnum.values()) {
            if (code.equals(creditApplyEnum.getCode())) {
                return creditApplyEnum;
            }
        }
        return null;
    }
}
