package com.rzx.admin.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rzx.project.system.domain.vo.RouterVo;
import com.rzx.project.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.rzx.common.constant.Constants;
import com.rzx.common.core.domain.AjaxResult;
import com.rzx.common.core.domain.entity.SysMenu;
import com.rzx.common.core.domain.entity.SysUser;
import com.rzx.common.core.domain.model.LoginBody;
import com.rzx.common.core.domain.model.LoginUser;
import com.rzx.common.utils.ServletUtils;
import com.rzx.framework.web.service.SysLoginService;
import com.rzx.framework.web.service.SysPermissionService;
import com.rzx.framework.web.service.TokenService;

/**
 * 登录验证
 *
 * @author fm
 */
@RestController
public class SysLoginController {
    @Autowired
    private SysLoginService loginService;
    @Autowired
    private ISysMenuService menuService;
    @Autowired
    private SysPermissionService permissionService;
    @Autowired
    private TokenService tokenService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult<Map<String, String>> login(@RequestBody LoginBody loginBody) {
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());

        Map<String, String> map = new HashMap<>(2);
        map.put(Constants.TOKEN, token);

        return AjaxResult.success(map);
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult<Map<String, Object>> getInfo() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);

        Map<String, Object> map = new HashMap<>(6);
        map.put("user", user);
        map.put("roles", roles);
        map.put("permissions", permissions);
        return AjaxResult.success(map);
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult<List<RouterVo>> getRouters() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}
