package com.rzx.admin.controller.system;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rzx.common.annotation.Log;
import com.rzx.common.core.controller.BaseController;
import com.rzx.common.core.domain.AjaxResult;
import com.rzx.common.enums.BusinessType;
import com.rzx.project.domain.OrderInfo;
import com.rzx.project.service.IOrderInfoService;
import com.rzx.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import com.rzx.common.core.page.TableDataInfo;

/**
 * 任智行 销售订单Controller
 *
 * @author zy
 * @date 2021-09-15
 */
@Api(value = "任智行 销售订单控制器")
@ApiModel(value = "任智行 销售订单Controller")
@RestController
@RequestMapping("/order")
public class OrderInfoController extends BaseController {
    @Autowired
    private IOrderInfoService orderInfoService;

    /**
     * 查询任智行 销售订单列表
     */
//    @PreAuthorize(hasPermi = "project:info:list")
    @GetMapping("/list")
    @ApiOperation(value = "查询任智行 销售订单列表")
    public TableDataInfo list(OrderInfo orderInfo){
        startPage();
        List<OrderInfo> list = orderInfoService.selectOrderInfoList(orderInfo);
        return getDataTable(list);
    }
    
    /**
     * 导出任智行 销售订单列表
     */
    @ApiOperation(value = "导出任智行 销售订单列表")
//    @PreAuthorize(hasPermi = "project:info:export")
    @Log(title = "任智行 销售订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(OrderInfo orderInfo) {
        List<OrderInfo> list = orderInfoService.selectOrderInfoList(orderInfo);
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
}
