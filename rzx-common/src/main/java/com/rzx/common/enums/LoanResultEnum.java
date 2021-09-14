package com.rzx.common.enums;

/**
 * 还款结果枚举类
 *
 * @author zy
 * @date 2021/6/11 17:10
 */
public enum LoanResultEnum {

    /**
     * 放款成功
     */
    SUCCESS("Success", "放款成功"),

    /**
     * 放款处理中
     */
    PROCESSING("Processing", "放款处理中"),

    /**
     * 放款失败
     */
    FAIL("Fail", "审批失败");

    private final String code;

    private final String info;

    LoanResultEnum(String code, String info) {
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
