package com.rzx.common.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 支付配置
 *
 * @author zy
 * @date 2021/9/30 15:54
 */
@Data
@Component
@ConfigurationProperties(prefix = "pay")
public class PayConfig {

    /**
     * ip地址
     */
    private String ip;

    /**
     * 支付方式
     */
    private String payway;

    /**
     * 回调地址
     */
    private String notifyurl;
}
