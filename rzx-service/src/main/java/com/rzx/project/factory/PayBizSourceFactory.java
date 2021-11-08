package com.rzx.project.factory;

import com.rzx.project.strategy.PayBizSourceStrategy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务支付来源工厂类
 *
 * @author zy
 * @date 2021/10/8 14:39
 */
@Component
public class PayBizSourceFactory implements InitializingBean, ApplicationContextAware {
    private static final Map<String, PayBizSourceStrategy> PAY_BIZ_MAP = new HashMap<>(8);

    private ApplicationContext appContext;

    /**
     * 支付来源类型编码获取对应的处理器
     *
     * @param bizSourceCode 支付来源类型： BizSourceCodeEnum
     * @return
     */
    public PayBizSourceStrategy getHandler(String bizSourceCode) {
        return PAY_BIZ_MAP.get(bizSourceCode);
    }

    @Override
    public void afterPropertiesSet() {
        this.appContext.getBeansOfType(PayBizSourceStrategy.class).values().forEach(handler -> PAY_BIZ_MAP.put(handler.getBizSourceCode(), handler));
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.appContext = applicationContext;
    }
}
