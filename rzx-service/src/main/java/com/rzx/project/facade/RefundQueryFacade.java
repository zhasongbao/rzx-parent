package com.rzx.project.facade;

import com.rzx.project.facade.dto.RefundQueryParam;
import com.rzx.project.facade.vo.RefundQueryResult;

/**
 * 退款查询外观类
 *
 * @author zy
 * @date 2021/10/25 14:20
 */
public interface RefundQueryFacade {

    /**
     * 退款查询
     *
     * @param param
     * @return
     */
    RefundQueryResult refundQuery(RefundQueryParam param);
}
