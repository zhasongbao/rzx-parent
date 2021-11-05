package com.rzx.project.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author zhasbao
 * @description 发票信息提交接口入参
 * @date 2021/11/03 10:40
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillRecordCommitDTO implements Serializable {

    @NotBlank(message = "订单号不能为空")
    @ApiModelProperty("订单号")
    private String orderId;

    @NotBlank(message = "类型不能为空")
    @ApiModelProperty("类型 0-个人 1-企业")
    private String type;

    @NotBlank(message = "抬头名称不能为空")
    @ApiModelProperty("抬头名称")
    private String upName;

    @NotBlank(message = "邮箱地址不能为空")
    @ApiModelProperty("邮箱地址")
    private String email;

    @ApiModelProperty("税号")
    private String taxNumber;

    @ApiModelProperty("单位地址")
    private String companyAddress;

    @ApiModelProperty("单位电话")
    private String companyPhone;

    @ApiModelProperty("开户银行")
    private String openBank;

    @ApiModelProperty("银行账户")
    private String accountBank;

}
