package com.rzx.common.enums;

import lombok.Getter;

/**
 * 退款状态枚举类
 *
 * @author zy
 * @date 2021/10/25 11:13
 */
@Getter
public enum RefundStatusEnum {

    /**
     * 退款成功
     */
    SUCCESS("SUCCESS"),

    /**
     * 退款关闭
     */
    REFUND_CLOSE("REFUND_CLOSE"),

    /**
     * 退款处理中
     */
    PROCESS_ING("PROCESS_ING"),

    /**
     * 退款失败
     */
    FILE("FILE"),

    ;

    /**
     * 编码
     */
    private final String code;

    RefundStatusEnum(String code) {
        this.code = code;
    }
}
