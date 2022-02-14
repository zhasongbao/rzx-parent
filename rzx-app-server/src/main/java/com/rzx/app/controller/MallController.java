package com.rzx.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.rzx.common.annotation.Log;
import com.rzx.common.core.controller.BaseController;
import com.rzx.common.core.domain.AjaxResult;
import com.rzx.common.core.page.TableDataInfo;
import com.rzx.common.enums.BusinessType;
import com.rzx.project.model.domain.CommodityConfig;
import com.rzx.project.model.domain.UserInfo;
import com.rzx.project.model.dto.ChannelUserDTO;
import com.rzx.project.model.dto.CommodityDepotDTO;
import com.rzx.project.model.dto.OrderLogisticsDTO;
import com.rzx.project.model.vo.ChannelUserVO;
import com.rzx.project.model.vo.CommodityDepotVO;
import com.rzx.project.service.ICommodityConfigService;
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

import java.util.List;

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
public class MallController extends BaseController {

    @Autowired
    private IOrderInfoService orderInfoService;
    @Autowired
    private ICommodityConfigService commodityConfigService;
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

    /**
     * 渠道跳转商城时调用查询，没有则创建用户
     * 获取用户信息      * getChannelUser.action
     * @param dto
     * @return
     */
    @ApiOperation(value = "获取用户信息")
    @Log(title = "获取用户信息", businessType = BusinessType.UPDATE)
    @PostMapping("/getChannelUser")
    public AjaxResult<ChannelUserVO> getChannelUser(@RequestBody @Validated ChannelUserDTO dto){
        return AjaxResult.success(mallService.getChannelUser(dto));
    }

    /**
     * 商品库列表 commodityDepotList.action
     */
    @PostMapping("/commodityDepotList")
    @ApiOperation(value = "商品库列表")
    public TableDataInfo commodityDepotList(@RequestBody CommodityDepotDTO dto){
        startPage();
        List<CommodityDepotVO> list = mallService.commodityDepotList(dto);
        return getDataTable(list);
    }

    /**
     * 批量查询商品区域内库存状态 regionStore.action
     */
    @PostMapping("/regionStore")
    @ApiOperation(value = "批量查询商品区域内库存状态")
    public TableDataInfo regionStore(@RequestBody CommodityDepotDTO dto){
        return getDataTable(null);
    }

}
