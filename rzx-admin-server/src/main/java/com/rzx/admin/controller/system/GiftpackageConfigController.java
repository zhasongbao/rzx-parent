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
import com.rzx.project.domain.GiftpackageConfig;
import com.rzx.project.service.IGiftpackageConfigService;
import com.rzx.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import com.rzx.common.core.page.TableDataInfo;

/**
 * 任智行 礼包配置Controller
 *
 * @author zy
 * @date 2021-09-15
 */
@Api(value = "任智行 礼包配置控制器")
@ApiModel(value = "任智行 礼包配置Controller")
@RestController
@RequestMapping("/gift")
public class GiftpackageConfigController extends BaseController {
    @Autowired
    private IGiftpackageConfigService giftpackageConfigService;

    /**
     * 查询任智行 礼包配置列表
     */
//    @PreAuthorize(hasPermi = "project:config:list")
    @GetMapping("/list")
    @ApiOperation(value = "查询任智行 礼包配置列表")
    public TableDataInfo list(GiftpackageConfig giftpackageConfig){
        startPage();
        List<GiftpackageConfig> list = giftpackageConfigService.selectGiftpackageConfigList(giftpackageConfig);
        return getDataTable(list);
    }
    
    /**
     * 导出任智行 礼包配置列表
     */
    @ApiOperation(value = "导出任智行 礼包配置列表")
//    @PreAuthorize(hasPermi = "project:config:export")
    @Log(title = "任智行 礼包配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(GiftpackageConfig giftpackageConfig) {
        List<GiftpackageConfig> list = giftpackageConfigService.selectGiftpackageConfigList(giftpackageConfig);
        ExcelUtil<GiftpackageConfig> util = new ExcelUtil<GiftpackageConfig>(GiftpackageConfig.class);
        util.exportExcel(list, "config");
    }

    /**
     * 获取任智行 礼包配置详细信息
     */
//    @PreAuthorize(hasPermi = "project:config:query")
    @ApiOperation(value = "获取任智行 礼包配置详细信息")
    @GetMapping(value = "/{giftpackageId}")
    public AjaxResult<GiftpackageConfig> getInfo(@PathVariable("giftpackageId") String giftpackageId){
        return AjaxResult.success(giftpackageConfigService.selectGiftpackageConfigById(giftpackageId));
    }

    /**
     * 新增任智行 礼包配置
     */
//    @PreAuthorize(hasPermi = "project:config:add")
    @Log(title = "任智行 礼包配置", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增任智行 礼包配置")
    @PostMapping(value = "/add")
    public AjaxResult<Integer> add(@RequestBody GiftpackageConfig giftpackageConfig){
        return toAjax(giftpackageConfigService.insertGiftpackageConfig(giftpackageConfig));
    }

    /**
     * 修改任智行 礼包配置
     */
//    @PreAuthorize(hasPermi = "project:config:edit")
    @ApiOperation(value = "修改任智行 礼包配置")
    @Log(title = "任智行 礼包配置", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/edit")
    public AjaxResult<Integer> edit(@RequestBody GiftpackageConfig giftpackageConfig){
        return toAjax(giftpackageConfigService.updateGiftpackageConfig(giftpackageConfig));
    }

    /**
     * 删除任智行 礼包配置
     */
    @ApiOperation(value = "删除任智行 礼包配置")
//    @PreAuthorize(hasPermi = "project:config:remove")
    @Log(title = "任智行 礼包配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{giftpackageIds}")
    public AjaxResult<Integer> remove(@PathVariable String[] giftpackageIds){
        return toAjax(giftpackageConfigService.deleteGiftpackageConfigByIds(giftpackageIds));
    }
}
