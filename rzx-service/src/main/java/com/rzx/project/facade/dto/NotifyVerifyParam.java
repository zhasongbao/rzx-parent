package com.rzx.project.facade.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Map;

/**
 * 通知结果验签参数
 *
 * @author zy
 * @date 2021/5/28 14:17
 */
@Data
@ToString
public class NotifyVerifyParam implements Serializable {

    private static final long serialVersionUID = -2645235673565012555L;

    /**
     * 支付接口编码（非空）
     */
    @NotBlank
    private String payChannelCode;

    /**
     * 第三方返回的原始串
     */
    private String url;

    /**
     * 回调参数
     */
    private Map<String, Object> paramMap;

    public NotifyVerifyParam() {
    }

    public NotifyVerifyParam(@NotBlank String payChannelCode, Map<String, Object> paramMap) {
        this.payChannelCode = payChannelCode;
        this.paramMap = paramMap;
    }
}
