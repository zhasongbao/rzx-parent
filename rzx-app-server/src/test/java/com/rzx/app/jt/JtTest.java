package com.rzx.app.jt;

import com.rzx.app.BaseTest;
import com.rzx.common.core.redis.RedisCache;
import com.rzx.common.utils.jt.JTApiUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 广西-捷通货车发行接口test
 *
 * @author hk
 * @date: 2021-09-14
 */
public class JtTest extends BaseTest {
    @Autowired
    RedisCache redisCache;

    //车牌校验接口
    @Test
    public void carUniqueResult() throws Exception {
        JTApiUtil.carUniqueResult("苏DJR675", "2", "1", "");
    }
}
