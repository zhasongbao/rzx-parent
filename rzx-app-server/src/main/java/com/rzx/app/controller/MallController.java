package com.rzx.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.rzx.common.annotation.Log;
import com.rzx.common.core.domain.AjaxResult;
import com.rzx.common.enums.BusinessType;
import com.rzx.project.model.dto.OrderLogisticsDTO;
import com.rzx.project.service.IOrderInfoService;
import com.rzx.project.service.MallService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhasbao
 * @description 商城相关接口实现
 * @date 2021年11月04日18:30
 */
@Slf4j
@RestController
@RequestMapping(value = "/mallAbout")
@Api(value = "商城相关接口")
@ApiModel(value = "商城相关接口Controller")
public class MallController {

    @Autowired
    private IOrderInfoService orderInfoService;
    @Autowired
    private MallService mallService;

    /**
     * 物流信息查询      * orderLogistics.action
     * @param dto
     * @return
     */
    @ApiOperation(value = "物流信息查询")
    @Log(title = "物流信息查询", businessType = BusinessType.UPDATE)
    @PostMapping("/orderLogistics")
    public AjaxResult<JSONObject> orderLogistics(@RequestBody @Validated OrderLogisticsDTO dto){
        return AjaxResult.success(orderInfoService.orderLogistics(dto));
    }
}
