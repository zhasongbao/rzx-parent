package com.rzx.project.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rzx.project.domain.OrderInfo;
import com.rzx.project.domain.dto.*;
import com.rzx.project.domain.vo.BuyPackageVO;
import com.rzx.project.domain.vo.NowExchangeVO;

/**
 * 任智行 销售订单Service接口
 *
 * @author zy
 * @date 2021-09-28
 */
public interface IOrderInfoService extends IService<OrderInfo> {

    /**
     * 查询任智行 销售订单
     *
     * @param salesorderId 任智行 销售订单ID
     * @return 任智行 销售订单
     */
    OrderInfo selectOrderInfoById(String salesorderId);

    /**
     * 查询任智行 销售订单列表
     *
     * @param dto 任智行 销售订单
     * @return 任智行 销售订单集合
     */
    List<OrderInfo> selectOrderInfoList(OrderInfo dto);

    /**
     * 新增任智行 销售订单
     *
     * @param orderInfo 任智行 销售订单
     * @return 结果
     */
    int insertOrderInfo(OrderInfo orderInfo);

    /**
     * 修改任智行 销售订单
     *
     * @param orderInfo 任智行 销售订单
     * @return 结果
     */
    int updateOrderInfo(OrderInfo orderInfo);

    /**
     * 批量删除任智行 销售订单
     *
     * @param salesorderIds 需要删除的任智行 销售订单ID
     * @return 结果
     */
    int deleteOrderInfoByIds(String[] salesorderIds);

    /**
     * 删除任智行 销售订单信息
     *
     * @param salesorderId 任智行 销售订单ID
     * @return 结果
     */
    int deleteOrderInfoById(String salesorderId);

    /**
     * 购买礼包
     * @param dto
     * @return
     */
    BuyPackageVO buyPackage(BuyPackageDTO dto);

    /**
     * 礼包绑定券号生成订单
     * @param dto
     * @return
     */
    JSONObject giftPackageLinkCoupons(PackageLinkCouponsDTO dto);

    /**
     * 发票信息提交
     * @param dto
     * @return
     */
    Integer billRecordCommit(BillRecordCommitDTO dto);


    /**
     * 马上兑换
     * @param dto
     * @return
     */
    NowExchangeVO nowExchange(NowExchangeDTO dto);

    /**
     * 确认兑换
     * @param dto
     * @return
     */
    Integer confirmExchange(ConfirmExchangeDTO dto);

}
