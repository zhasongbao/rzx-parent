package com.rzx.admin.controller.system;

import java.util.List;
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
import com.rzx.project.domain.CommodityConfig;
import com.rzx.project.service.ICommodityConfigService;
import com.rzx.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import com.rzx.common.core.page.TableDataInfo;

/**
 * 任智行 商品配置Controller
 *
 * @author zy
 * @date 2021-09-15
 */
@Api(value = "任智行 商品配置控制器")
@ApiModel(value = "任智行 商品配置Controller")
@RestController
@RequestMapping("/commodity")
public class CommodityConfigController extends BaseController {
    @Autowired
    private ICommodityConfigService commodityConfigService;

    /**
     * 查询任智行 商品配置列表
     */
//    @PreAuthorize(hasPermi = "project:config:list")
    @PostMapping("/list")
    @ApiOperation(value = "查询任智行 商品配置列表")
    public TableDataInfo list(CommodityConfig commodityConfig){
        startPage();
        List<CommodityConfig> list = commodityConfigService.selectCommodityConfigList(commodityConfig);
        return getDataTable(list);
    }
    
    /**
     * 导出任智行 商品配置列表
     */
    @ApiOperation(value = "导出任智行 商品配置列表")
//    @PreAuthorize(hasPermi = "project:config:export")
    @Log(title = "任智行 商品配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CommodityConfig commodityConfig) {
        List<CommodityConfig> list = commodityConfigService.selectCommodityConfigList(commodityConfig);
        ExcelUtil<CommodityConfig> util = new ExcelUtil<CommodityConfig>(CommodityConfig.class);
        util.exportExcel(list, "config");
    }

    /**
     * 获取任智行 商品配置详细信息
     */
//    @PreAuthorize(hasPermi = "project:config:query")
    @ApiOperation(value = "获取任智行 商品配置详细信息")
    @GetMapping(value = "/{commodityconfigId}")
    public AjaxResult<CommodityConfig> getInfo(@PathVariable("commodityconfigId") String commodityconfigId){
        return AjaxResult.success(commodityConfigService.selectCommodityConfigById(commodityconfigId));
    }

    /**
     * 新增任智行 商品配置
     */
//    @PreAuthorize(hasPermi = "project:config:add")
    @Log(title = "任智行 商品配置", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增任智行 商品配置")
    @PostMapping(value = "/add")
    public AjaxResult<Integer> add(@RequestBody CommodityConfig commodityConfig){
        return toAjax(commodityConfigService.insertCommodityConfig(commodityConfig));
    }

    /**
     * 修改任智行 商品配置
     */
//    @PreAuthorize(hasPermi = "project:config:edit")
    @ApiOperation(value = "修改任智行 商品配置")
    @Log(title = "任智行 商品配置", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/edit")
    public AjaxResult<Integer> edit(@RequestBody CommodityConfig commodityConfig){
        return toAjax(commodityConfigService.updateCommodityConfig(commodityConfig));
    }

    /**
     * 删除任智行 商品配置
     */
    @ApiOperation(value = "删除任智行 商品配置")
//    @PreAuthorize(hasPermi = "project:config:remove")
    @Log(title = "任智行 商品配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{commodityconfigIds}")
    public AjaxResult<Integer> remove(@PathVariable String[] commodityconfigIds){
        return toAjax(commodityConfigService.deleteCommodityConfigByIds(commodityconfigIds));
    }
}
