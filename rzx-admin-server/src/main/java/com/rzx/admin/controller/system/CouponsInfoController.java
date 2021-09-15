package com.rzx.admin.controller.system;

import java.util.List;

import com.rzx.common.constant.Constants;
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
import com.rzx.project.domain.CouponsInfo;
import com.rzx.project.service.ICouponsInfoService;
import com.rzx.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import com.rzx.common.core.page.TableDataInfo;

/**
 * 任智行 券信息Controller
 *
 * @author zy
 * @date 2021-09-15
 */
@Api(value = "任智行 券信息控制器")
@ApiModel(value = "任智行 券信息Controller")
@RestController
@RequestMapping("/coupons")
public class CouponsInfoController extends BaseController {
    @Autowired
    private ICouponsInfoService couponsInfoService;

    /**
     * 查询任智行 券信息列表
     */
//    @PreAuthorize(hasPermi = "project:info:list")
    @GetMapping("/list")
    @ApiOperation(value = "查询任智行 券信息列表")
    public TableDataInfo list(CouponsInfo couponsInfo){
        startPage();
        List<CouponsInfo> list = couponsInfoService.selectCouponsInfoList(couponsInfo);
        return getDataTable(list);
    }
    
    /**
     * 导出任智行 券信息列表
     */
    @ApiOperation(value = "导出任智行 券信息列表")
//    @PreAuthorize(hasPermi = "project:info:export")
    @Log(title = "任智行 券信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CouponsInfo couponsInfo) {
        List<CouponsInfo> list = couponsInfoService.selectCouponsInfoList(couponsInfo);
        ExcelUtil<CouponsInfo> util = new ExcelUtil<CouponsInfo>(CouponsInfo.class);
        util.exportExcel(list, "info");
    }

    /**
     * 获取任智行 券信息详细信息
     */
//    @PreAuthorize(hasPermi = "project:info:query")
    @ApiOperation(value = "获取任智行 券信息详细信息")
    @GetMapping(value = "/{couponsinfoId}")
    public AjaxResult<CouponsInfo> getInfo(@PathVariable("couponsinfoId") String couponsinfoId){
        return AjaxResult.success(couponsInfoService.selectCouponsInfoById(couponsinfoId));
    }

    /**
     * 新增任智行 券信息
     */
//    @PreAuthorize(hasPermi = "project:info:add")
    @Log(title = "任智行 券信息", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增任智行 券信息")
    @PostMapping(value = "/add")
    public AjaxResult<Integer> add(@RequestBody CouponsInfo couponsInfo){
        return toAjax(couponsInfoService.insertCouponsInfo(couponsInfo));
    }

    /**
     * 修改任智行 券信息
     */
//    @PreAuthorize(hasPermi = "project:info:edit")
    @ApiOperation(value = "修改任智行 券信息")
    @Log(title = "任智行 券信息", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/edit")
    public AjaxResult<Integer> edit(@RequestBody CouponsInfo couponsInfo){
        return toAjax(couponsInfoService.updateCouponsInfo(couponsInfo));
    }

    /**
     * 删除任智行 券信息
     */
    @ApiOperation(value = "删除任智行 券信息")
//    @PreAuthorize(hasPermi = "project:info:remove")
    @Log(title = "任智行 券信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{couponsinfoIds}")
    public AjaxResult<Integer> remove(@PathVariable String[] couponsinfoIds){
        return toAjax(couponsInfoService.deleteCouponsInfoByIds(couponsinfoIds));
    }
}
