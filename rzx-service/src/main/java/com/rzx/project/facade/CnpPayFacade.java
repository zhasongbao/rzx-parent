package com.rzx.project.facade;

import com.rzx.project.facade.dto.CnpPayPreParam;
import com.rzx.project.facade.vo.CnpPayResult;

/**
 * 微信小程序无卡支付外观类，支持扫码、小程序、h5场景
 *
 * @author zy
 * @date 2021/9/30 14:38
 */
public interface CnpPayFacade {

    /**
     * 无卡支付，支持微信扫码、h5、小程序支付
     *
     * @param param
     * @return
     */
    CnpPayResult cnpPay(CnpPayPreParam param);
}
