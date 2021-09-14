package com.rzx.admin.controller.monitor;

import java.util.List;

import com.rzx.project.system.domain.SysLoginInFor;
import com.rzx.project.system.service.ISysLoginInForService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rzx.common.annotation.Log;
import com.rzx.common.core.controller.BaseController;
import com.rzx.common.core.domain.AjaxResult;
import com.rzx.common.core.page.TableDataInfo;
import com.rzx.common.enums.BusinessType;
import com.rzx.common.utils.poi.ExcelUtil;

/**
 * 系统访问记录
 *
 * @author zy
 */
@RestController
@RequestMapping("/monitor/logininfor")
public class SysLoginInForController extends BaseController {
    @Autowired
    private ISysLoginInForService loginInForService;

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysLoginInFor logininfor) {
        startPage();
        List<SysLoginInFor> list = loginInForService.selectLoginInForList(logininfor);
        return getDataTable(list);
    }

    @Log(title = "登录日志", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('monitor:logininfor:export')")
    @GetMapping("/export")
    public AjaxResult export(SysLoginInFor logininfor) {
        List<SysLoginInFor> list = loginInForService.selectLoginInForList(logininfor);
        ExcelUtil<SysLoginInFor> util = new ExcelUtil<SysLoginInFor>(SysLoginInFor.class);
        return util.exportExcel(list, "登录日志");
    }

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
    @Log(title = "登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    public AjaxResult remove(@PathVariable Long[] infoIds) {
        return toAjax(loginInForService.deleteLoginInForByIds(infoIds));
    }

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
    @Log(title = "登录日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public AjaxResult clean() {
        loginInForService.cleanLoginInFor();
        return AjaxResult.success();
    }
}
