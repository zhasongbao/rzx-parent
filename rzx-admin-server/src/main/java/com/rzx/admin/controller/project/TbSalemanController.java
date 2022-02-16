package com.rzx.admin.controller.project;

import java.util.List;

import com.rzx.project.service.ryx.ITbSalemanService;
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
import com.rzx.project.model.domain.TbSaleman;
import com.rzx.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import com.rzx.common.core.page.TableDataInfo;

/**
 * 任意行用户Controller
 *
 * @author zhasbao
 * @date 2022-02-16
 */
@Api(value = "任意行用户控制器")
@ApiModel(value = "任意行用户Controller")
@RestController
@RequestMapping("/project/saleman")
public class TbSalemanController extends BaseController {
    @Autowired
    private ITbSalemanService tbSalemanService;

    /**
     * 查询任意行用户列表
     */
    @PreAuthorize("@ss.hasPermi('project:saleman:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询任意行用户列表")
    public TableDataInfo list(TbSaleman tbSaleman){
        startPage();
        List<TbSaleman> list = tbSalemanService.selectTbSalemanList(tbSaleman);
        return getDataTable(list);
    }
    
    /**
     * 导出任意行用户列表
     */
    @ApiOperation(value = "导出任意行用户列表")
    @PreAuthorize("@ss.hasPermi('project:saleman:export')")
    @Log(title = "任意行用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TbSaleman tbSaleman) {
        List<TbSaleman> list = tbSalemanService.selectTbSalemanList(tbSaleman);
        ExcelUtil<TbSaleman> util = new ExcelUtil<TbSaleman>(TbSaleman.class);
        util.exportExcel(list, "saleman");
    }

    /**
     * 获取任意行用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:saleman:query')")
    @ApiOperation(value = "获取任意行用户详细信息")
    @GetMapping(value = "/{salemanId}")
    public AjaxResult<TbSaleman> getInfo(@PathVariable("salemanId") String salemanId){
        return AjaxResult.success(tbSalemanService.selectTbSalemanById(salemanId));
    }

    /**
     * 新增任意行用户
     */
    @PreAuthorize("@ss.hasPermi('project:saleman:add')")
    @Log(title = "任意行用户", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增任意行用户")
    @PostMapping(value = "/add")
    public AjaxResult<Integer> add(@RequestBody TbSaleman tbSaleman){
        return toAjax(tbSalemanService.insertTbSaleman(tbSaleman));
    }

    /**
     * 修改任意行用户
     */
    @PreAuthorize("@ss.hasPermi('project:saleman:edit')")
    @ApiOperation(value = "修改任意行用户")
    @Log(title = "任意行用户", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/edit")
    public AjaxResult<Integer> edit(@RequestBody TbSaleman tbSaleman){
        return toAjax(tbSalemanService.updateTbSaleman(tbSaleman));
    }

    /**
     * 删除任意行用户
     */
    @ApiOperation(value = "删除任意行用户")
    @PreAuthorize("@ss.hasPermi('project:saleman:remove')")
    @Log(title = "任意行用户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{salemanIds}")
    public AjaxResult<Integer> remove(@PathVariable String[] salemanIds){
        return toAjax(tbSalemanService.deleteTbSalemanByIds(salemanIds));
    }
}
