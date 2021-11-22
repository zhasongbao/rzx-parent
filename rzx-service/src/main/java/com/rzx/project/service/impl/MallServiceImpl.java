package com.rzx.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rzx.common.constant.Constants;
import com.rzx.common.enums.StatusEnum;
import com.rzx.common.exception.CustomException;
import com.rzx.common.utils.uuid.UUID;
import com.rzx.project.model.domain.CommodityConfig;
import com.rzx.project.model.domain.UserInfo;
import com.rzx.project.model.dto.ChannelUserDTO;
import com.rzx.project.model.dto.CommodityDepotDTO;
import com.rzx.project.model.vo.ChannelUserVO;
import com.rzx.project.model.vo.CommodityDepotVO;
import com.rzx.project.service.ICommodityConfigService;
import com.rzx.project.service.IUserInfoService;
import com.rzx.project.service.MallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhasbao
 * @description 商城相关接口实现
 * @date 2021/11/04 18:30
 */
@Service("mallServiceImpl")
public class MallServiceImpl implements MallService {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private ICommodityConfigService commodityConfigService;

    @Override
    public ChannelUserVO getChannelUser(ChannelUserDTO dto) {
        // 查询对应券是否为已销售未核销
        LambdaQueryWrapper<UserInfo> queryWrapperCoupons = new LambdaQueryWrapper<>();
        queryWrapperCoupons.eq(UserInfo::getUserId, dto.getUserId());
        queryWrapperCoupons.eq(UserInfo::getUserType, dto.getUserType());
        queryWrapperCoupons.eq(UserInfo::getSource, dto.getSource());
        UserInfo userInfo = userInfoService.getOne(queryWrapperCoupons);
        if(ObjectUtils.isEmpty(userInfo)){
            userInfo = UserInfo.builder()
                    .userInfoId(UUID.get32UUID())
                    .userId(dto.getUserId())
                    .userType(dto.getUserType())
                    .source(dto.getSource())
                    .score(0)
                    .build();
            // 创建渠道用户
            int i = userInfoService.insertUserInfo(userInfo);
            if(i < 1){
                throw new CustomException("新建用户失败，请稍后再试!");
            }
        }
        return ChannelUserVO.builder()
                .userInfoId(userInfo.getUserInfoId())
                .userId(userInfo.getUserId())
                .userType(userInfo.getUserType())
                .source(userInfo.getSource())
                .score(userInfo.getScore())
                .createTime(userInfo.getCreateTime())
                .build();
    }

    @Override
    public List<CommodityDepotVO> commodityDepotList(CommodityDepotDTO dto) {
        List<CommodityDepotVO> listVo = new ArrayList<>();

        List<CommodityConfig> list = new ArrayList<>();
        if(Constants.NO_FLAG.equals(dto.getType())){
            list = commodityConfigService.selectCommodityConfigList(
                    CommodityConfig.builder().gxChooseFlag(Constants.YES_FLAG).status(StatusEnum.VALID.getCode()).build());
        }else{
            list = commodityConfigService.selectCommodityConfigList(
                    CommodityConfig.builder().chooseFlag(Constants.YES_FLAG).status(StatusEnum.VALID.getCode()).build());
        }

        for (CommodityConfig commodity : list) {
            String amount = "";
            if(Constants.NO_FLAG.equals(dto.getType())){
                amount = commodity.getGxAmount();
            }else{
                amount = commodity.getAmount();
            }
            listVo.add(CommodityDepotVO.builder()
                    .commodityConfigId(commodity.getCommodityconfigId())
                    .commodityCode(commodity.getCommodityCode())
                    .commodityName(commodity.getCommodityName())
                    .image(commodity.getImage())
                    .imageUrls(commodity.getImageUrls())
                    .thumbnailImage(commodity.getThumbnailImage())
                    .marketPrice(commodity.getMarketPrice())
                    .amount(amount)
                    .provid(commodity.getProvid())
                    .typeId(commodity.getTypeId())
                    .providSource(commodity.getProvidSource())
                    .saleStatus(commodity.getSaleStatus())
                    .createTime(commodity.getCreateTime())
                    .updateTime(commodity.getUpdateTime())
                    .providSource(commodity.getCommodityCode())
                    .build());
        }
        return listVo;
    }
}
