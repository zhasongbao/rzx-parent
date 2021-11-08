package com.rzx.project.facade;

import com.rzx.project.facade.dto.OrderQueryParam;
import com.rzx.project.facade.vo.OrderQueryResult;

/**
 * 订单查询接口
 *
 * @author zy
 * @date 2021/9/30 16:37
 */
public interface OrderQueryFacade {

    /**
     * 订单查询
     *
     * @param orderQueryParam
     * @return
     */
    OrderQueryResult orderQuery(OrderQueryParam orderQueryParam);
}
