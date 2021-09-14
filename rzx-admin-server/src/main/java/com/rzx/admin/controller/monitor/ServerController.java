package com.rzx.admin.controller.monitor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rzx.common.core.domain.AjaxResult;
import com.rzx.framework.web.domain.Server;

/**
 * 服务器监控
 *
 * @author zy
 */
@RestController
@RequestMapping("/monitor/server")
public class ServerController {


    @PreAuthorize("@ss.hasPermi('monitor:server:list')")
    @GetMapping()
    public AjaxResult<Server> getInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        return AjaxResult.success(server);
    }
}
