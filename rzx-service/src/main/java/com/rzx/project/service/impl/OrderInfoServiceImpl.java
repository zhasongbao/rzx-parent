package com.rzx.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rzx.common.constant.Constants;
import com.rzx.common.enums.*;
import com.rzx.common.exception.CustomException;
import com.rzx.common.utils.Tools;
import com.rzx.common.utils.spring.SpringUtils;
import com.rzx.common.utils.uuid.IdUtils;
import com.rzx.common.utils.uuid.UUID;
import com.rzx.project.domain.*;
import com.rzx.project.domain.dto.*;
import com.rzx.project.domain.vo.BuyPackageVO;
import com.rzx.project.domain.vo.NowExchangeVO;
import com.rzx.project.handler.SuppliereInterface;
import com.rzx.project.mapper.OrderInfoMapper;
import com.rzx.project.service.*;
import com.rzx.project.service.ryx.ITbSalemanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Date;
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
    @Autowired
    private IBillRecordService billRecordService;
    @Autowired
    private IGiftPackageConfigService giftPackageConfigService;
    @Autowired
    private ICommodityConfigService commodityConfigService;
    @Autowired
    private IReceiveAddressInfoService receiveAddressInfoService;

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

    /**
     * 礼包绑定券号生成订单
     * @param dto
     * @return
     */
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
        if(ObjectUtils.isEmpty(coupons) || CouponsSaleStatusEnum.YES_SALE.getCode().equals(coupons.getSaleStatus())){
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

    /**
     * 发票信息提交
     *
     * @param dto
     * @return
     */
    @Override
    public Integer billRecordCommit(BillRecordCommitDTO dto) {
        LambdaQueryWrapper<OrderInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderInfo::getOrderId, dto.getOrderId());
        OrderInfo order = getOne(queryWrapper);
        if (ObjectUtils.isEmpty(order)) {
            throw new CustomException("未找到对应订单!");
        }
        if (!StringUtils.isEmpty(order.getBillrecordId())) {
            throw new CustomException("该订单开票信息已提交，请勿重复提交！");
        }
        String billrecordId = UUID.get32UUID();
        billRecordService.insertBillRecord(BillRecord.builder()
                .billRecordId(billrecordId)
                .billStatus(Constants.NO_FLAG)
                .type(dto.getType())
                .upName(dto.getUpName())
                .email(dto.getEmail())
                .taxNumber(dto.getTaxNumber())
                .companyAddress(dto.getCompanyAddress())
                .companyPhone(dto.getCompanyPhone())
                .openBank(dto.getOpenBank())
                .accountBank(dto.getOpenBank())
                .build()
        );
        order.setBillrecordId(billrecordId);
        return orderInfoMapper.updateOrderInfo(order);
    }

    /**
     * 马上兑换
     *
     * @param dto
     * @return
     */
    @Override
    public NowExchangeVO nowExchange(NowExchangeDTO dto) {
        // 查询对应券是否为已销售未核销
        LambdaQueryWrapper<CouponsInfo> queryWrapperCoupons = new LambdaQueryWrapper<>();
        queryWrapperCoupons.eq(CouponsInfo::getCouponsinfoId, dto.getCouponsInfoId());
        queryWrapperCoupons.eq(CouponsInfo::getCouponsPwd, dto.getCouponsPwd());
        queryWrapperCoupons.eq(CouponsInfo::getSaleStatus, CouponsSaleStatusEnum.YES_SALE.getCode());
        queryWrapperCoupons.eq(CouponsInfo::getCheckStatus, CheckStatusEnum.NO_CHECK.getCode());
        CouponsInfo coupons =  couponsInfoService.getOne(queryWrapperCoupons);
        if (ObjectUtils.isEmpty(coupons)) {
            throw new CustomException("未找到该礼品信息！");
        }

        LambdaQueryWrapper<OrderInfo> queryWrapperOrder = new LambdaQueryWrapper<>();
        queryWrapperOrder.eq(OrderInfo::getCouponsinfoId, dto.getCouponsInfoId());
        queryWrapperOrder.eq(OrderInfo::getOrderStatus, SalesOrderStatusEnum.YES_PAY.getCode());
        OrderInfo order = getOne(queryWrapperOrder);
        if (ObjectUtils.isEmpty(order)) {
            throw new CustomException("未找到对应订单或对应订单未支付！");
        }
        GiftPackageConfig gift = giftPackageConfigService.selectGiftPackageConfigById(order.getGiftpackageId());
        if (ObjectUtils.isEmpty(gift)) {
            throw new CustomException("未找对应礼包信息！");
        }
        return NowExchangeVO.builder()
                .salesOrderId(order.getSalesorderId())
                .initTotalAmount(gift.getInitAmount())
                .saleTotalAmount(gift.getSaleAmount())
                .type(gift.getType())
                .commodityCode(order.getCommodityCode())
                .build();
    }

    /**
     * 确认兑换
     *
     * @param dto
     * @return
     */
    @Override
    public Integer confirmExchange(ConfirmExchangeDTO dto) {
        LambdaQueryWrapper<CommodityConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CommodityConfig::getCommodityCode, dto.getCommodityCode());
        queryWrapper.eq(CommodityConfig::getProvid, dto.getProvid());
        CommodityConfig commodity = commodityConfigService.getOne(queryWrapper);
        if (ObjectUtils.isEmpty(commodity)) {
            throw new CustomException("商品不存在或已下架！");
        }

        OrderInfo order = orderInfoMapper.selectOrderInfoById(dto.getSalesOrderId());
        if(ObjectUtils.isEmpty(order)){
            throw new CustomException("未找到对应订单！");
        }
        if(!SalesOrderStatusEnum.YES_PAY.getCode().equals(order.getOrderStatus())){
            throw new CustomException("订单未支付！");
        }
        if(CheckStatusEnum.YES_CHECK.getCode().equals(order.getCheckStatus())){
            throw new CustomException("该礼包已经兑换过了！");
        }

        CouponsInfo coupons = couponsInfoService.selectCouponsInfoById(order.getCouponsinfoId());
        if(ObjectUtils.isEmpty(coupons)){
            throw new CustomException("未找到该券信息！");
        }

        SuppliereInterface suppliere = SpringUtils.getBean(CommodityProvidEnum.getImplClassByValue(dto.getProvid()));
        // 查询供应商商品可售状态
        if (!suppliere.saleStatus(dto.getCommodityCode())) {
            throw new CustomException("该商品已下架，请选择其他商品！");
        }
        // 供应商验证地址编码是否正确
        if (!suppliere.getValid(dto.getProvid(), dto.getCity(), dto.getCounty(), dto.getTown())) {
            throw new CustomException("收货所在地区有误！");
        }

        // 地址信息
        ReceiveAddressInfo address = buildAddress(dto);
        order.setCommodityCode(dto.getCommodityCode());
        order.setReceiveaddressId(address.getReceiveaddressId());
        order.setOrderProvid(dto.getProvid());

        JSONObject orderJson = (JSONObject) JSON.toJSON(order);
        orderJson.put("confirmModel",Constants.YES_FLAG);//自动
        orderJson.put("providSource", commodity.getProvidSource());
        // 调用供应商生成订单
        JSONObject resp = suppliere.createOrder(orderJson, (JSONObject) JSON.toJSON(address));
        if(Constants.SUCCESS_CODE.equals(resp.getString("RC"))){
            order.setOrderProvid(dto.getProvid());
            order.setOrderKey(resp.getString("ORDER_KEY"));
            order.setOrderShipmentFee(resp.getString("ORDER_SHIPMENT_FEE"));
            order.setCheckStatus(CheckStatusEnum.YES_CHECK.getCode());
            // 更新券信息为已核销
            coupons.setCheckStatus(Constants.YES_FLAG);
            coupons.setCheckTime(Tools.date2Str(new Date()));
            couponsInfoService.updateCouponsInfo(coupons);
            // 记录地址信息
            receiveAddressInfoService.insertReceiveAddressInfo(address);
        }else{
            order.setCommodityCode("");
            order.setErrorCode(resp.getString("RC"));
            order.setErrorMessage(resp.getString("RM"));
            orderInfoMapper.updateOrderInfo(order);
            return 0;
        }
        order.setInitTotalAmount(dto.getInitTotalAmount());
        order.setSaleTotalAmount(dto.getSaleTotalAmount());
        order.setCommodityCode(dto.getCommodityCode());
        if(CommodityProvidEnum.BAIHUI.getCode().equals(dto.getProvid())){
            order.setOrderConfirm(Constants.YES_FLAG);
        }
        return orderInfoMapper.updateOrderInfo(order);
    }

    private ReceiveAddressInfo buildAddress(ConfirmExchangeDTO dto) {
        return ReceiveAddressInfo.builder()
                .receiveaddressId(UUID.get32UUID())
                .province(dto.getProvince())
                .provinceName(dto.getProvinceName())
                .city(dto.getCity())
                .cityName(dto.getCityName())
                .county(dto.getCounty())
                .countyName(dto.getCountyName())
                .town(dto.getTown())
                .townName(dto.getTownName())
                .receiveName(dto.getReceiveName())
                .receivePhone(dto.getReceivePhone())
                .receiveAddress(dto.getReceiveAddress())
                .build();
    }
}
