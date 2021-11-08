package com.rzx.project.facade.dto;

import com.rzx.common.core.BasePayPreParamEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 退款
 *
 * @author zy
 * @date 2021/10/25 11:23
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CnpRefundParam extends BasePayPreParamEntity implements Serializable {

    /**
     * 平台退款流水号
     */
    @NotBlank(message = "平台退款流水号不能为空")
    private String refundOutTrxNo;

    /**
     * 渠道方分配的商户号
     **/
    private String subMerchantNo;

}
