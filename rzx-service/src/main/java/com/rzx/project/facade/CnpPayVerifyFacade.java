package com.rzx.project.facade;

import com.rzx.project.facade.dto.NotifyVerifyParam;
import com.rzx.project.facade.vo.NotifyVerifyResult;

/**
 * 异步回调外观类
 *
 * @author zy
 * @date 2021/9/30 16:23
 */
public interface CnpPayVerifyFacade {

    /**
     * 异步回调验签方法
     *
     * @param notifyVerifyParam
     * @return
     */
    NotifyVerifyResult cnpPayVerify(NotifyVerifyParam notifyVerifyParam);
}
