package com.rzx.project.domain.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author zhasbao
 * @version 1.0
 * @title
 * @description
 * @company 深圳分米互联科技有限公司
 * @date 2021/9/16 16:30
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoDTO implements Serializable {

    @NotBlank(message = "用户id不能为空")
    private String userId;
}
