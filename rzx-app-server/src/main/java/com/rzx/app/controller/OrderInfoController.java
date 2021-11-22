package com.rzx.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.rzx.common.annotation.Log;
import com.rzx.common.core.controller.BaseController;
import com.rzx.common.core.domain.AjaxResult;
import com.rzx.common.core.page.TableDataInfo;
import com.rzx.common.enums.BusinessType;
import com.rzx.common.enums.OrderTypeEnum;
import com.rzx.common.enums.SalesOrderStatusEnum;
import com.rzx.common.enums.StatusEnum;
import com.rzx.common.exception.CustomException;
import com.rzx.common.utils.StringUtils;
import com.rzx.common.utils.poi.ExcelUtil;
import com.rzx.project.model.domain.OrderInfo;
import com.rzx.project.model.dto.*;
import com.rzx.project.model.vo.BuyPackageVO;
import com.rzx.project.model.vo.NowExchangeVO;
import com.rzx.project.service.IOrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单Controller
 *
 * @author zy
 * @date 2021-09-15
 */
@Api(value = "订单控制器")
@ApiModel(value = "订单Controller")
@RestController
@RequestMapping("/order")
public class OrderInfoController extends BaseController {
    @Autowired
    private IOrderInfoService orderInfoService;

    /**
     * 礼包订单列表 salesOrderList.action
     */
    @GetMapping("/list")
    @ApiOperation(value = "礼包订单列表")
    public TableDataInfo list(OrderInfo dto){
        dto.setStatus(StatusEnum.VALID.getCode());
        dto.setOrderType(OrderTypeEnum.GIFT_PACK.getCode());
        dto.setOrderStatus(SalesOrderStatusEnum.YES_PAY.getCode());
        startPage();
        List<OrderInfo> list = orderInfoService.selectOrderInfoList(dto);
        return getDataTable(list);
    }
    
    /**
     * 导出任智行 销售订单列表
     */
    @ApiOperation(value = "导出销售订单列表")
    @Log(title = "销售订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(OrderInfo dto) {
        List<OrderInfo> list = orderInfoService.selectOrderInfoList(dto);
        ExcelUtil<OrderInfo> util = new ExcelUtil<OrderInfo>(OrderInfo.class);
        util.exportExcel(list, "info");
    }

    /**
     * 获取销售订单详细信息 giftOrderInfo.action
     */
    @ApiOperation(value = "获取销售订单详细信息")
    @GetMapping(value = "/{salesorderId}")
    public AjaxResult<OrderInfo> getInfo(@PathVariable("salesorderId") String salesorderId){
        return AjaxResult.success(orderInfoService.selectOrderInfoById(salesorderId));
    }

    /**
     * 新增任智行 销售订单
     */
    @Log(title = "销售订单", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增 销售订单")
    @PostMapping(value = "/add")
    public AjaxResult<Integer> add(@RequestBody OrderInfo orderInfo){
        return toAjax(orderInfoService.insertOrderInfo(orderInfo));
    }

    /**
     * 修改任智行 销售订单
     */
    @ApiOperation(value = "修改销售订单")
    @Log(title = "销售订单", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/edit")
    public AjaxResult<Integer> edit(@RequestBody OrderInfo orderInfo){
        return toAjax(orderInfoService.updateOrderInfo(orderInfo));
    }

    /**
     * 删除任智行 销售订单
     */
    @ApiOperation(value = "删除任智行 销售订单")
    @Log(title = "销售订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{salesorderIds}")
    public AjaxResult<Integer> remove(@PathVariable String[] salesorderIds){
        return toAjax(orderInfoService.deleteOrderInfoByIds(salesorderIds));
    }

    /**
     * 购买礼包 buyPackage.action
     * @param dto
     * @return
     */
    @ApiOperation(value = "购买礼包 入口为商城销售礼包icon跳转")
    @Log(title = "购买礼包", businessType = BusinessType.INSERT)
    @PostMapping("/buyPackage")
    public AjaxResult<BuyPackageVO> buyPackage(@RequestBody @Validated BuyPackageDTO dto){
        return AjaxResult.success(orderInfoService.buyPackage(dto));
    }

    /**
     * 礼包绑定券号生成订单 giftPackageLinkCoupons.action
     * @param dto
     * @return
     */
    @ApiOperation(value = "礼包绑定券号生成订单")
    @Log(title = "礼包绑定券号生成订单", businessType = BusinessType.UPDATE)
    @PostMapping("/giftPackageLinkCoupons")
    public AjaxResult<JSONObject> giftPackageLinkCoupons(@RequestBody @Validated PackageLinkCouponsDTO dto){
        return AjaxResult.success(orderInfoService.giftPackageLinkCoupons(dto));
    }

    /**
     * 发票信息提交接口      * billRecordCommit.action
     * @param dto
     * @return
     */
    @ApiOperation(value = "发票信息提交接口")
    @Log(title = "发票信息提交接口", businessType = BusinessType.INSERT)
    @PostMapping("/billRecordCommit")
    public AjaxResult<Integer> billRecordCommit(@RequestBody @Validated BillRecordCommitDTO dto){
        return toAjax(orderInfoService.billRecordCommit(dto));
    }

    /**
     * 马上兑换      * nowExchange.action
     * @param dto
     * @return
     */
    @ApiOperation(value = "马上兑换接口")
    @PostMapping("/nowExchange")
    public AjaxResult<NowExchangeVO> nowExchange(@RequestBody @Validated NowExchangeDTO dto){
        return AjaxResult.success(orderInfoService.nowExchange(dto));
    }

    /**
     * 确认兑换      * confirmExchange.action
     * @param dto
     * @return
     */
    @ApiOperation(value = "确认兑换接口")
    @Log(title = "马上兑换接口", businessType = BusinessType.UPDATE)
    @PostMapping("/confirmExchange")
    public AjaxResult<Integer> confirmExchange(@RequestBody @Validated ConfirmExchangeDTO dto){
        return toAjax(orderInfoService.confirmExchange(dto));
    }

    /**
     * 兑换记录查询 exchangeRecordList.action
     */
    @GetMapping("/exchangeRecordList")
    @ApiOperation(value = "兑换记录列表")
    public TableDataInfo exchangeRecordList(ExchangeRecordListDTO dto){
        startPage();
        List<OrderInfo> list = orderInfoService.exchangeRecordList(dto);
        return getDataTable(list);
    }

    /**
     * 物流信息查询      * orderLogistics.action
     * @param dto
     * @return
     */
    @ApiOperation(value = "物流信息查询")
    @PostMapping("/orderLogistics")
    public AjaxResult<JSONObject> orderLogistics(@RequestBody @Validated OrderLogisticsDTO dto){
        return AjaxResult.success(orderInfoService.orderLogistics(dto));
    }

    /**
     * C扫B支付 获取支付二维码      * preCreate.action
     * @param salesOrderId 订单唯一标识
     * @return
     */
    @ApiOperation(value = "C扫B支付 获取支付二维码")
    @PostMapping("/preCreate")
    public AjaxResult<String> preCreate(@PathVariable String salesOrderId){
        if(StringUtils.isEmpty(salesOrderId)){
            throw new CustomException("订单唯一标识不能为空!");
        }
        return AjaxResult.success(orderInfoService.preCreate(salesOrderId));
    }
}
