//package com.rzx.project.service.impl;
//
//import cn.hutool.core.util.RandomUtil;
//import com.rzx.common.constant.CacheConstants;
//import com.rzx.common.core.redis.RedisCache;
//import com.rzx.common.utils.SmsUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * 短信发送service
// *
// * @author zy
// * @date 2021/6/7 14:27
// */
//@Service
//@Slf4j
//public class SmsCodeServiceImpl implements ISmsCodeService{
//
//    @Autowired
//    private RedisCache redisCache;
//
//    /**
//     * 获取短信验证码
//     *
//     * @param phoneNumber 手机号
//     * @return
//     */
//    @Override
//    public String getVeriCode(String phoneNumber) {
//
//        String code = RandomUtil.randomNumbers(4);
//        log.info("验证码为：{}", code);
//        //保存redis中5分钟
//        redisCache.setCacheObject(CacheConstants.CACHED_MOBILE + phoneNumber, code, 5L, TimeUnit.MINUTES);
//
//        // 发送短信
//        String content = "验证码为:{0},有效时间为:5分钟，请妥善保管";
//        SmsUtil.sendSms(phoneNumber, content, "", String.format(content, code));
//        return "短信发送成功";
//    }
//}
