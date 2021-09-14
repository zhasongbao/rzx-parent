package com.rzx.common.utils.ocr.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 银行卡OCR
 *
 * @author zy
 * @date 2021/6/15 17:08
 */
@Data
@ToString
public class BankCardOcrVO implements Serializable {

    private static final long serialVersionUID = -3199575816457913456L;

    /**
     * 银行卡号
     */
    private String cardNum;

    /**
     * 银行卡名称
     */
    private String bankName;

    public BankCardOcrVO() {
    }

    public BankCardOcrVO(String cardNum, String bankName) {
        this.cardNum = cardNum;
        this.bankName = bankName;
    }
}
