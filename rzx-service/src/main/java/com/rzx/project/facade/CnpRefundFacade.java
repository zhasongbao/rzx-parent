package com.rzx.project.facade;

import com.rzx.project.facade.dto.CnpRefundParam;
import com.rzx.project.facade.vo.CnpRefundResult;

/**
 * 无卡支付退款外观类
 *
 * @author zy
 * @date 2021/10/25 11:05
 */
public interface CnpRefundFacade {

    /**
     * 无卡退款
     *
     * @param param
     * @return
     */
    CnpRefundResult refund(CnpRefundParam param);
}
