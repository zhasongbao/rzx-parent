package com.rzx.common.exception;

/**
 * 银行申请流程异常
 *
 * @author zy
 * @date 2021/9/14 19:13
 */
public class BankBizException extends RuntimeException {

    private static final long serialVersionUID = -3190977414247846311L;

    /**
     * 银行渠道编码为空
     */
    public static final BankBizException BANK_CHANNEL_CODE_IS_NULL = new BankBizException(
            "银行渠道编码为空", 1000000);

    /**
     * 银行短信签约异常
     */
    public static final BankBizException BANK_SIGN_ERROR = new BankBizException(
            "银行短信签约异常", 1000001);

    /**
     * 非法的银行渠道编码
     */
    public static final BankBizException BANK_CHANNEL_CODE_ILLEGAL = new BankBizException(
            "非法的银行渠道编码", 1000002);

    /**
     * 异常信息
     */
    private final String msg;

    /**
     * 具体异常码
     */
    private final int code;

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public BankBizException(String message) {
        super(message);
        this.code = 400;
        this.msg = message;
    }

    public BankBizException(String message, int code) {
        super(message);
        this.code = code;
        this.msg = message;
    }

    public BankBizException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
        this.msg = msgFormat;
    }
}
