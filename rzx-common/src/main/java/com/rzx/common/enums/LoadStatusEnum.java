package com.rzx.common.enums;

/**
 * 用户申请状态枚举类
 *
 * @author zy
 * @date 2021/6/4 18:18
 */
public enum LoadStatusEnum {

    /**
     * 未申请
     */
    NOT_APPLY("000" , "未申请"),

    /**
     * 审批中
     */
    WAIT("001" , "审批中"),

    /**
     * 审批失败
     */
    FAIL("002" , "审批失败"),

    /**
     * 审批通过
     */
    PASS("003" , "审批通过"),

    /**
     * 逾期中
     */
    OVERDUE("004" , "逾期中")
    ;

    private final String code;

    private final String info;

    LoadStatusEnum(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
