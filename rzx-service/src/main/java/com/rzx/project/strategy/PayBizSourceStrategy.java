package com.rzx.project.strategy;

import com.rzx.project.facade.vo.NotifyVerifyResult;

import java.util.Map;

/**
 * 业务支付策略类
 *
 * @author zy
 * @date 2021/10/8 14:06
 */
public interface PayBizSourceStrategy {

    /**
     * 支付来源类型： BizSourceCodeEnum
     *
     * @return
     */
    String getBizSourceCode();

    /**
     * 业务支付成功回调处理
     *
     * @param verifyResult  支付回调参数
     * @param bankReturnMsg 回调map
     * @return
     */
    boolean completePay(NotifyVerifyResult verifyResult, Map<String, Object> bankReturnMsg);
}
