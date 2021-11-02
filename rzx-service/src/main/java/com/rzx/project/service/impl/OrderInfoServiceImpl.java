package com.rzx.project.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rzx.common.constant.Constants;
import com.rzx.common.enums.*;
import com.rzx.common.exception.CustomException;
import com.rzx.common.utils.uuid.IdUtils;
import com.rzx.common.utils.uuid.UUID;
import com.rzx.project.domain.CouponsInfo;
import com.rzx.project.domain.OrderInfo;
import com.rzx.project.domain.TbSaleman;
import com.rzx.project.domain.UserInfo;
import com.rzx.project.domain.dto.BuyPackageDTO;
import com.rzx.project.domain.dto.PackageLinkCouponsDTO;
import com.rzx.project.domain.vo.BuyPackageVO;
import com.rzx.project.mapper.OrderInfoMapper;
import com.rzx.project.service.ICouponsInfoService;
import com.rzx.project.service.IOrderInfoService;
import com.rzx.project.service.IUserInfoService;
import com.rzx.project.service.ryx.ITbSalemanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 任智行 销售订单Service业务层处理
 *
 * @author zy
 * @date 2021-09-28
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper,OrderInfo> implements IOrderInfoService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private ITbSalemanService salemanService;
    @Autowired
    private ICouponsInfoService couponsInfoService;

    /**
     * 查询任智行 销售订单
     *
     * @param salesorderId 任智行 销售订单ID
     * @return 任智行 销售订单
     */
    @Override
    public OrderInfo selectOrderInfoById(String salesorderId) {
        return orderInfoMapper.selectOrderInfoById(salesorderId);
    }

    /**
     * 查询任智行 销售订单列表
     *
     * @param orderInfo 任智行 销售订单
     * @return 任智行 销售订单
     */
    @Override
    public List<OrderInfo> selectOrderInfoList(OrderInfo orderInfo) {
        return orderInfoMapper.selectOrderInfoList(orderInfo);
    }

    /**
     * 新增任智行 销售订单
     *
     * @param orderInfo 任智行 销售订单
     * @return 结果
     */
    @Override
    public int insertOrderInfo(OrderInfo orderInfo) {
        LocalDateTime now = LocalDateTime.now();
        orderInfo.setCreateTime(now);
        orderInfo.setUpdateTime(now);
        return orderInfoMapper.insert(orderInfo);
    }

    /**
     * 修改任智行 销售订单
     *
     * @param orderInfo 任智行 销售订单
     * @return 结果
     */
    @Override
    public int updateOrderInfo(OrderInfo orderInfo) {
        orderInfo.setUpdateTime(LocalDateTime.now());
        return orderInfoMapper.updateOrderInfo(orderInfo);
    }

    /**
     * 批量删除任智行 销售订单
     *
     * @param salesorderIds 需要删除的任智行 销售订单ID
     * @return 结果
     */
    @Override
    public int deleteOrderInfoByIds(String[] salesorderIds) {
        return orderInfoMapper.deleteOrderInfoByIds(salesorderIds);
    }

    /**
     * 删除任智行 销售订单信息
     *
     * @param salesorderId 任智行 销售订单ID
     * @return 结果
     */
    @Override
    public int deleteOrderInfoById(String salesorderId) {
        return orderInfoMapper.deleteOrderInfoById(salesorderId);
    }

    /**
     * 购买礼包
     * @param dto
     * @return
     */
    @Override
    public BuyPackageVO buyPackage(BuyPackageDTO dto) {
        UserInfo userInfo = userInfoService.selectUserInfoById(dto.getUserInfoId());
        if(ObjectUtils.isEmpty(userInfo)){
            throw new CustomException("未找到商城用户信息!");
        }
        OrderInfo order = OrderInfo.builder()
                .salesorderId(UUID.get32UUID())
                .orderId(IdUtils.getId())
                .userInfoId(dto.getUserInfoId())
                .giftpackageId(dto.getGiftPackageId())
                .initTotalAmount(dto.getInitAmount())
                .saleTotalAmount(dto.getSaleAmount())
                .payWay(Constants.NO_FLAG)
                .orderStatus(SalesOrderStatusEnum.NO_PAY.getCode())
                .status(StatusEnum.VALID.getCode())
                .orderType(OrderTypeEnum.GIFT_PACK.getCode())
                .remark("礼包购买")
                .checkStatus(CheckStatusEnum.NO_CHECK.getCode())
                .build();
        int i = orderInfoMapper.insert(order);
        return BuyPackageVO.builder()
                .orderId(order.getOrderId())
                .saleAmount(dto.getSaleAmount())
                .payWay(order.getPayWay())
                .notifyUrl("") // TODO 云卓支付接口回调地址
                .build();
    }

    @Override
    public JSONObject giftPackageLinkCoupons(PackageLinkCouponsDTO dto) {
        JSONObject json = new JSONObject();
        if (GiftPackTypeEnum.SINGLE.getCode().equals(dto.getType()) && StringUtils.isEmpty(dto.getCommodityCode())) {
            throw new CustomException("单品类型礼包时商品编码不能为空");
        }
        String salemanId = dto.getSalemanId();
        if(!StringUtils.isEmpty(dto.getUserInfoId())){
            // 商城跳转过来的(只有任意行渠道用户有权限跳转过来)
            UserInfo userInfo = userInfoService.selectUserInfoById(dto.getUserInfoId());
            if(ObjectUtils.isEmpty(userInfo)){
                throw new CustomException("未找到商城用户信息!");
            }
            if(ObjectUtils.isEmpty(userInfo.getUserId())){
                throw new CustomException("商城对应渠道用户ID为空!");
            }
            salemanId = userInfo.getUserId();
        }

        TbSaleman saleman = salemanService.selectTbSalemanById(salemanId);
        if(ObjectUtils.isEmpty(saleman)){
            throw new CustomException("商城对应任意行渠道用户不存在!");
        }

        CouponsInfo coupons = couponsInfoService.selectCouponsInfoById(dto.getCouponsInfoId());
        if(ObjectUtils.isEmpty(coupons) || SaleStatusEnum.YES_SALE.getCode().equals(coupons.getSaleStatus())){
            throw new CustomException("券号不存在或销售!");
        }

        OrderInfo order = OrderInfo.builder()
                .salesorderId(UUID.get32UUID())
                .orderId(IdUtils.getId())
                .salemanId(salemanId)
                .userInfoId(dto.getUserInfoId())
                .commodityCode(dto.getCommodityCode())
                .giftpackageId(dto.getGiftPackageId())
                .couponsinfoId(dto.getCouponsInfoId())
                .initTotalAmount(dto.getInitAmount())
                .saleTotalAmount(dto.getSaleAmount())
                .payWay(PayWayEnum.YZ.getCode())
                .orderStatus(SalesOrderStatusEnum.NO_PAY.getCode())
                .status(StatusEnum.VALID.getCode())
                .orderType(OrderTypeEnum.GIFT_PACK.getCode())
                .checkStatus(CheckStatusEnum.NO_CHECK.getCode())
                .build();
        int i = orderInfoMapper.insert(order);
        if(i>0){
            json.put("salesOrderId", order.getSalesorderId());
            return json;
        }else{
            throw new CustomException("处理失败请稍后重试!");
        }
    }
}
