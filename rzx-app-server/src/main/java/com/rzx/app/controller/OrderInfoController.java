package com.rzx.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONObjectCodec;
import com.rzx.common.annotation.Log;
import com.rzx.common.core.controller.BaseController;
import com.rzx.common.core.domain.AjaxResult;
import com.rzx.common.core.domain.model.AppLoginUser;
import com.rzx.common.core.page.TableDataInfo;
import com.rzx.common.enums.BusinessType;
import com.rzx.common.utils.poi.ExcelUtil;
import com.rzx.project.domain.OrderInfo;
import com.rzx.project.domain.dto.BuyPackageDTO;
import com.rzx.project.domain.dto.OrderInfoDTO;
import com.rzx.project.domain.dto.PackageLinkCouponsDTO;
import com.rzx.project.domain.vo.BuyPackageVO;
import com.rzx.project.service.IOrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
     * 查询已购订单列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "查询已购订单列表")
    public TableDataInfo list(@RequestBody @Validated OrderInfo dto){
        startPage();
        List<OrderInfo> list = orderInfoService.selectOrderInfoList(dto);
        return getDataTable(list);
    }
    
    /**
     * 导出任智行 销售订单列表
     */
    @ApiOperation(value = "导出任智行 销售订单列表")
//    @PreAuthorize(hasPermi = "project:info:export")
    @Log(title = "任智行 销售订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(OrderInfo dto) {
        List<OrderInfo> list = orderInfoService.selectOrderInfoList(dto);
        ExcelUtil<OrderInfo> util = new ExcelUtil<OrderInfo>(OrderInfo.class);
        util.exportExcel(list, "info");
    }

    /**
     * 获取任智行 销售订单详细信息
     */
//    @PreAuthorize(hasPermi = "project:info:query")
    @ApiOperation(value = "获取任智行 销售订单详细信息")
    @GetMapping(value = "/{salesorderId}")
    public AjaxResult<OrderInfo> getInfo(@PathVariable("salesorderId") String salesorderId){
        return AjaxResult.success(orderInfoService.selectOrderInfoById(salesorderId));
    }

    /**
     * 新增任智行 销售订单
     */
//    @PreAuthorize(hasPermi = "project:info:add")
    @Log(title = "任智行 销售订单", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增任智行 销售订单")
    @PostMapping(value = "/add")
    public AjaxResult<Integer> add(@RequestBody OrderInfo orderInfo){
        return toAjax(orderInfoService.insertOrderInfo(orderInfo));
    }

    /**
     * 修改任智行 销售订单
     */
//    @PreAuthorize(hasPermi = "project:info:edit")
    @ApiOperation(value = "修改任智行 销售订单")
    @Log(title = "任智行 销售订单", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/edit")
    public AjaxResult<Integer> edit(@RequestBody OrderInfo orderInfo){
        return toAjax(orderInfoService.updateOrderInfo(orderInfo));
    }

    /**
     * 删除任智行 销售订单
     */
    @ApiOperation(value = "删除任智行 销售订单")
//    @PreAuthorize(hasPermi = "project:info:remove")
    @Log(title = "任智行 销售订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{salesorderIds}")
    public AjaxResult<Integer> remove(@PathVariable String[] salesorderIds){
        return toAjax(orderInfoService.deleteOrderInfoByIds(salesorderIds));
    }

    /**
     * 购买礼包 buyPackage.action
     * @param dto
     * @return
     */
    @ApiOperation(value = "购买礼包")
    @Log(title = "任智行 购买礼包 入口为商城销售礼包icon跳转", businessType = BusinessType.INSERT)
    @PostMapping("/buyPackage")
    public AjaxResult<BuyPackageVO> buyPackage(@Validated BuyPackageDTO dto){
        return AjaxResult.success(orderInfoService.buyPackage(dto));
    }

    /**
     * 礼包绑定券号生成订单 giftPackageLinkCoupons.action
     * @param dto
     * @return
     */
    @ApiOperation(value = "礼包绑定券号生成订单")
    @Log(title = "任智行 礼包绑定券号生成订单", businessType = BusinessType.UPDATE)
    @PostMapping("/giftPackageLinkCoupons")
    public AjaxResult<JSONObject> giftPackageLinkCoupons(@Validated PackageLinkCouponsDTO dto){
        return AjaxResult.success(orderInfoService.giftPackageLinkCoupons(dto));
    }

}
