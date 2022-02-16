package com.rzx.app.controller;

import com.rzx.common.annotation.Log;
import com.rzx.common.core.controller.BaseController;
import com.rzx.common.core.domain.AjaxResult;
import com.rzx.common.core.page.TableDataInfo;
import com.rzx.common.enums.BusinessType;
import com.rzx.common.utils.poi.ExcelUtil;
import com.rzx.project.model.domain.GiftPackageConfig;
import com.rzx.project.service.IGiftPackageConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 任智行 礼包配置Controller
 *
 * @author zhasbao
 * @date 2021-09-15
 */
@Api(value = "任智行 礼包配置控制器")
@ApiModel(value = "任智行 礼包配置Controller")
@RestController
@RequestMapping("/gift")
public class GiftPackageConfigController extends BaseController {
    @Autowired
    private IGiftPackageConfigService GiftPackageConfigService;

    /**
     * 查询任智行 获取礼包列表 getGitfList.action
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询任智行 获取礼包列表")
    public TableDataInfo list(GiftPackageConfig GiftPackageConfig){
        startPage();
        List<GiftPackageConfig> list = GiftPackageConfigService.selectGiftPackageConfigList(GiftPackageConfig);
        return getDataTable(list);
    }
    
    /**
     * 导出任智行 礼包配置列表
     */
    @ApiOperation(value = "导出任智行 礼包配置列表")
    @Log(title = "任智行 礼包配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(GiftPackageConfig GiftPackageConfig) {
        List<GiftPackageConfig> list = GiftPackageConfigService.selectGiftPackageConfigList(GiftPackageConfig);
        ExcelUtil<GiftPackageConfig> util = new ExcelUtil<GiftPackageConfig>(GiftPackageConfig.class);
        util.exportExcel(list, "config");
    }

    /**
     * 获取任智行 礼包配置详细信息
     */
//    @PreAuthorize(hasPermi = "project:config:query")
    @ApiOperation(value = "获取任智行 礼包配置详细信息")
    @GetMapping(value = "/{giftpackageId}")
    public AjaxResult<GiftPackageConfig> getInfo(@PathVariable("giftpackageId") String giftpackageId){
        return AjaxResult.success(GiftPackageConfigService.selectGiftPackageConfigById(giftpackageId));
    }

    /**
     * 新增任智行 礼包配置
     */
//    @PreAuthorize(hasPermi = "project:config:add")
    @Log(title = "任智行 礼包配置", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增任智行 礼包配置")
    @PostMapping(value = "/add")
    public AjaxResult<Integer> add(@RequestBody GiftPackageConfig GiftPackageConfig){
        return toAjax(GiftPackageConfigService.insertGiftPackageConfig(GiftPackageConfig));
    }

    /**
     * 修改任智行 礼包配置
     */
//    @PreAuthorize(hasPermi = "project:config:edit")
    @ApiOperation(value = "修改任智行 礼包配置")
    @Log(title = "任智行 礼包配置", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/edit")
    public AjaxResult<Integer> edit(@RequestBody GiftPackageConfig GiftPackageConfig){
        return toAjax(GiftPackageConfigService.updateGiftPackageConfig(GiftPackageConfig));
    }

    /**
     * 删除任智行 礼包配置
     */
    @ApiOperation(value = "删除任智行 礼包配置")
//    @PreAuthorize(hasPermi = "project:config:remove")
    @Log(title = "任智行 礼包配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{giftpackageIds}")
    public AjaxResult<Integer> remove(@PathVariable String[] giftpackageIds){
        return toAjax(GiftPackageConfigService.deleteGiftPackageConfigByIds(giftpackageIds));
    }
}
