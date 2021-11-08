package com.rzx.project.facade.dto;

import com.rzx.common.core.BasePayPreParamEntity;
import lombok.*;

import java.io.Serializable;

/**
 * 无卡支付请求参数
 *
 * @author zy
 * @date 2021/9/30 14:38
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CnpPayPreParam extends BasePayPreParamEntity implements Serializable {

    private static final long serialVersionUID = -8096491102782782869L;

    /**
     * 微信个人openid
     **/
    private String openId;

    /**
     * 渠道方分配的商户号
     **/
    private String subMerchantNo;

}
