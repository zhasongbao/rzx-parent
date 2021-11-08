package com.rzx.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信相关配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "weixin")
public class WeiXinConfig {
    private String appid;

    private String secret;
    /**
     * 默认支付方式  0:微信 1云卓
     */
    private String defPayWay;
    private String mchId;
    private String notifyUrl;
}
