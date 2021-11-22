package com.rzx.project.service;


import com.rzx.project.model.dto.ChannelUserDTO;
import com.rzx.project.model.dto.CommodityDepotDTO;
import com.rzx.project.model.vo.ChannelUserVO;
import com.rzx.project.model.vo.CommodityDepotVO;

import java.util.List;

/**
 * 商城相关接口
 *
 * @author zhasbao
 * @date: 2021-10-14
 */
public interface MallService {

    /**
     * 获取用户信息
     * @param dto
     * @return
     */
    ChannelUserVO getChannelUser(ChannelUserDTO dto);

    /**
     * 商品库列表
     * @param dto
     * @return
     */
    List<CommodityDepotVO> commodityDepotList(CommodityDepotDTO dto);


}
