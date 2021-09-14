package com.rzx.project.system.service.impl;

import com.rzx.project.system.domain.SysLoginInFor;
import com.rzx.project.system.mapper.SysLoginInForMapper;
import com.rzx.project.system.service.ISysLoginInForService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统访问日志情况信息 服务层处理
 *
 * @author zy
 */
@Service
public class SysLoginInForServiceImpl implements ISysLoginInForService {

    @Autowired
    private SysLoginInForMapper loginInForMapper;

    /**
     * 新增系统登录日志
     *
     * @param loginInFor 访问日志对象
     */
    @Override
    public void insertLoginInFor(SysLoginInFor loginInFor) {
        loginInForMapper.insertLoginInFor(loginInFor);
    }

    /**
     * 查询系统登录日志集合
     *
     * @param loginInFor 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<SysLoginInFor> selectLoginInForList(SysLoginInFor loginInFor) {
        return loginInForMapper.selectLogininforList(loginInFor);
    }

    /**
     * 批量删除系统登录日志
     *
     * @param infoIds 需要删除的登录日志ID
     * @return
     */
    @Override
    public int deleteLoginInForByIds(Long[] infoIds) {
        return loginInForMapper.deleteLogininforByIds(infoIds);
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLoginInFor() {
        loginInForMapper.cleanLogininfor();
    }
}
