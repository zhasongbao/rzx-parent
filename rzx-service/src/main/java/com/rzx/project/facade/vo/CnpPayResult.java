package com.rzx.project.facade.vo;

import com.rzx.common.enums.PayLinkNetWorkStatusEnum;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 微信小程序返回结果
 *
 * @author zy
 * @date 2021/09/12 14:16
 */
@Data
@ToString
public class CnpPayResult implements Serializable {

    private static final long serialVersionUID = -1452786350585159303L;

    /**
     * 支付信息，扫码、h5、 小程序(Map结构)支付
     */
    private String payMessage;

    /**
     * 请求渠道报文
     */
    private String requestBankMsg;

    /**
     * 银行渠道返回信息
     */
    private String bankReturnMsg;

    /**
     * 渠道方流水号
     */
    private String bankTrxNo;

    /**
     * 支付渠道编码
     */
    private String payChannelCode;

    /**
     * 支付通信状态
     */
    private PayLinkNetWorkStatusEnum payLinkNetWorkStatusEnum;

    /**
     * 渠道方分配的商户号
     **/
    private String subMerchantNo;
}
