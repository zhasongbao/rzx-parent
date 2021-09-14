package com.rzx.project.system.service;


import com.rzx.project.system.domain.SysLoginInFor;

import java.util.List;

/**
 * 系统访问日志情况信息 服务层
 *
 * @author ruoyi
 */
public interface ISysLoginInForService {

    /**
     * 新增系统登录日志
     *
     * @param loginInFor 访问日志对象
     */
    void insertLoginInFor(SysLoginInFor loginInFor);

    /**
     * 查询系统登录日志集合
     *
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    List<SysLoginInFor> selectLoginInForList(SysLoginInFor logininfor);

    /**
     * 批量删除系统登录日志
     *
     * @param infoIds 需要删除的登录日志ID
     * @return
     */
    int deleteLoginInForByIds(Long[] infoIds);

    /**
     * 清空系统登录日志
     */
    void cleanLoginInFor();
}
