package com.rzx.project.service.impl;

import cn.hutool.core.convert.Convert;
import com.rzx.common.constant.Constants;
import com.rzx.common.core.domain.entity.SysDictData;
import com.rzx.common.core.redis.RedisCache;
import com.rzx.common.utils.provid.baihui.BaiHuiUtils;
import com.rzx.project.service.ICommonService;
import com.rzx.project.service.ISystemDictDataService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhasbao
 * @createTime 2021-09-28
 */
@Service
public class CommonServiceImpl implements ICommonService {
    private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

    @Autowired
    private ISystemDictDataService systemDictDataService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public String getBaihuiToken() {
        // 1、获取token
        String tokenKey = Constants.REDIS_BAIHUI_TOKEN;
        String token = redisCache.getCacheObject(tokenKey);
        if(StringUtils.isEmpty(token)) {
            token = BaiHuiUtils.getToken();
            redisCache.setCacheObject(tokenKey, token, 12L, TimeUnit.HOURS);
        }
        if(StringUtils.isEmpty(token)) {
            logger.error("getBaihuiToken_token获取失败");
        }
        return token;
    }

    @Override
    public String getYZPaySN() {
        int day = LocalDateTime.now().getDayOfWeek().getValue();
        List<SysDictData> dicList = systemDictDataService.selectDictDataByType("yzpay_sn_config");
        if (CollectionUtils.isEmpty(dicList)) {
            return null;
        }
        for (SysDictData dic : dicList) {
            String dayStr = dic.getDictValue();
            for (String str : dayStr.split(",")) {
                if (str.equals(Convert.toStr(day))) {
                    return dic.getRemark();
                }
            }
        }

        return null;
    }
}
