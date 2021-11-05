package com.rzx.quartz.task;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rzx.common.constant.Constants;
import com.rzx.common.enums.ProvidEnum;
import com.rzx.common.enums.StatusEnum;
import com.rzx.common.utils.BigDecimalUtils;
import com.rzx.common.utils.Tools;
import com.rzx.common.utils.provid.yunzhonghe.YunZhongHeUtils;
import com.rzx.project.model.domain.CommodityClass;
import com.rzx.project.model.domain.CommodityConfig;
import com.rzx.project.model.domain.ProvidAddress;
import com.rzx.project.service.ICommodityClassService;
import com.rzx.project.service.ICommodityConfigService;
import com.rzx.project.service.IProvidAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 供应商 云中鹤定时任务
 * @author zhasbao
 */
@Component("providForYzhTask")
public class ProvidForYzhTask {
    private static final Logger logger = LoggerFactory.getLogger(ProvidForYzhTask.class);


    @Autowired
    private ICommodityConfigService commodityConfigService;
    @Autowired
    private ICommodityClassService commodityClassService;
    @Autowired
    private IProvidAddressService providAddressService;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 云中鹤商品数据全量获取
     */
    public void execute() {
        logger.error("providForYzhTask_execute_start...." + Tools.date2Str(new Date()));
        try {
            // 获取总页数
            JSONObject getResp = YunZhongHeUtils.getProductIdsByPage(1);
            
            if(!ObjectUtil.isEmpty(getResp)&& !getResp.getString("RESULT_DATA").isEmpty() &&
                    !StringUtils.isEmpty(getResp.getInteger("TOTAL_PAGE")) && getResp.getInteger("TOTAL_PAGE") >0){
                // 分页获取商品ID
                for (Integer i = 1; i <= getResp.getInteger("TOTAL_PAGE"); i++) {
                    Integer finalI = i;
                    threadPoolTaskExecutor.execute(()->{
                        logger.error("providForYzhTask_execute_page=" + finalI);
                        JSONObject resp = YunZhongHeUtils.getProductIdsByPage(finalI);
                        if (!ObjectUtil.isEmpty(resp) && !StringUtils.isEmpty(resp.getString("RESULT_DATA"))) {
                            String cResultData = resp.getString("RESULT_DATA");
                            String[] comIds = cResultData.replace("[", "").replace("]", "").split(",");
                            JSONObject infoResp = null;

                            for (String comId : comIds) {
                                try {
                                    CommodityConfig pd = CommodityConfig.builder()
                                            .provid(ProvidEnum.YZH.getCode()).commodityCode(comId).build();
                                    // 获取商品详情
                                    infoResp = YunZhongHeUtils.detial(comId);
                                    if (!ObjectUtil.isEmpty(infoResp) && !ObjectUtil.isEmpty(infoResp.getJSONObject("RESULT_DATA"))) {
                                        JSONObject iResultData = infoResp.getJSONObject("RESULT_DATA");
                                        List<CommodityConfig> list = commodityConfigService.selectCommodityConfigList(pd);
                                        if (!CollectionUtils.isEmpty(list)) {
                                            // 更新
                                            pd = list.get(0);
                                            dowith(pd, iResultData);
                                            commodityConfigService.updateCommodityConfig(pd);
                                        } else {
                                            // 新增
                                            pd.setCommodityconfigId(Tools.get32UUID());
                                            pd.setStatus( StatusEnum.VALID.getCode());
                                            pd.setChooseFlag(Constants.NO_FLAG);
                                            pd.setRate("10");
                                            dowith(pd, iResultData);
                                            commodityConfigService.save(pd);
                                        }
                                    }
                                } catch (Exception e) {
                                    logger.error("商品处理异常_comId=" + comId);
                                    logger.error(e.getMessage(), e);
                                }
                            }
                        }
                    });
                }
            }
        } catch (Exception e) {
            logger.error("providForYzhTask_execute_e=" + e);
            logger.error(e.getMessage(), e);
        }
        logger.error("providForYzhTask_execute_end...." + Tools.date2Str(new Date()));
    }

    /**
     * 参数处理
     * @param pd
     * @param iResultData
     */
    public void dowith(CommodityConfig pd, JSONObject iResultData) {
        if(!ObjectUtil.isEmpty(iResultData.getJSONObject("PRODUCT_DATA"))){
            pd.setCommodityName(iResultData.getJSONObject("PRODUCT_DATA").getString("name"));
            pd.setBrand(iResultData.getJSONObject("PRODUCT_DATA").getString("brand"));
            pd.setProductCate( iResultData.getJSONObject("PRODUCT_DATA").getString("productCate"));
            pd.setModel(iResultData.getJSONObject("PRODUCT_DATA").getString("productCode"));
            pd.setThumbnailImage(iResultData.getJSONObject("PRODUCT_DATA").getString("thumbnailImage"));
            if("selling".equals(iResultData.getJSONObject("PRODUCT_DATA").getString("status"))){
                pd.setSaleStatus("1");
            }else{
                pd.setSaleStatus("0");
            }
            String PROVID_SOURCE = iResultData.getJSONObject("PRODUCT_DATA").getString("type");
            if ("system".equals(PROVID_SOURCE)) {
                PROVID_SOURCE = "1";
            } else if ("provider".equals(PROVID_SOURCE)) {
                PROVID_SOURCE = "21";
            } else if ("jindong".equals(PROVID_SOURCE) || "xinfutong".equals(PROVID_SOURCE)) {
                PROVID_SOURCE = "4";
            } else if ("wangyi".equals(PROVID_SOURCE)) {
                PROVID_SOURCE = "5";
            }
            pd.setProvidSource(PROVID_SOURCE);
            if("undercarriage".equals(iResultData.getJSONObject("PRODUCT_DATA").getString("status"))){
                // 下架商品挪出礼包/选品库
                pd.setGiftpackageId("");
                pd.setChooseFlag(Constants.NO_FLAG);
            }
            pd.setMarketPrice(iResultData.getJSONObject("PRODUCT_DATA").getString("marketPrice"));
            pd.setRetailAmount(iResultData.getJSONObject("PRODUCT_DATA").getString("retailPrice"));
            String amount = BigDecimalUtils.multiply(iResultData.getJSONObject("PRODUCT_DATA").getString("retailPrice"),"1.1",2);
            pd.setAmount(amount);
            pd.setProductPlace(iResultData.getJSONObject("PRODUCT_DATA").getString("productPlace"));
            pd.setIsToReturn(iResultData.getJSONObject("PRODUCT_DATA").getString("is7ToReturn"));
//            pd.put("EXPLAIN", iResultData.getJSONObject("PRODUCT_DATA").getString("features"));
        }
        if(!StringUtils.isEmpty(iResultData.getString("PRODUCT_DESCRIPTION"))){
            pd.setImage(iResultData.getString("PRODUCT_DESCRIPTION").replace("\"",""));
        }
        if(!ObjectUtil.isEmpty(iResultData.getJSONArray("PRODUCT_IMAGE"))){
            JSONArray PRODUCT_IMAGE = iResultData.getJSONArray("PRODUCT_IMAGE");
            String IMAGE_URLS = "";
            JSONObject pi = null;
            for (int j = 0; j < PRODUCT_IMAGE.size(); j++) {
                pi = (JSONObject) PRODUCT_IMAGE.get(j);
                if(StringUtils.isEmpty(IMAGE_URLS)){
                    IMAGE_URLS = pi.getString("imageUrl");
                }else{
                    IMAGE_URLS = IMAGE_URLS + "," + pi.getString("imageUrl");
                }
            }
            pd.setImageUrls(IMAGE_URLS);
        }
    }

    /**
     * 定时获取 云中鹤商品分类数据全量获取
     */
    public void getAllClass() {
        logger.error("providForYzhTask_getAllClass_start...." + Tools.date2Str(new Date()));
        try {
            // 获取一级产品分类
            JSONObject resp = YunZhongHeUtils.cateRootCate();
            if(!resp.isEmpty() && !resp.getJSONArray("RESULT_DATA").isEmpty()){
                JSONArray oneArray = resp.getJSONArray("RESULT_DATA");
                List<CommodityClass> oneList = commoityClassParam(oneArray);
                batchInserOrUpdate(oneList);
                // 获取下级分类
                getChilds(oneList);
            }
        } catch (Exception e) {
            logger.error("providForYzhTask_getAllClass_e=" + e);
        }
        logger.error("providForYzhTask_getAllClass_end...." + Tools.date2Str(new Date()));
    }

    /**
     * 递归获取下级分类
     * @param oneList
     * @throws Exception
     */
    private void getChilds(List<CommodityClass> oneList) throws Exception {
        for (CommodityClass one : oneList) {
            // 获取下级产品分类
            JSONObject resp2 = YunZhongHeUtils.cateChilds(one.getCode());
            if(!ObjectUtil.isEmpty(resp2) && !ObjectUtil.isEmpty(resp2.getJSONArray("RESULT_DATA"))){
                JSONArray twoArray = resp2.getJSONArray("RESULT_DATA");
                List<CommodityClass> twoList = commoityClassParam(twoArray);
                batchInserOrUpdate(twoList);
                getChilds(twoList);
            }
        }
    }

    /**
     * @param array 參數
     * @return
     */
    private List<CommodityClass> commoityClassParam(JSONArray array) {
        List<CommodityClass> oneList = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JSONObject one = (JSONObject) array.get(i);
            oneList.add(
                    CommodityClass.builder()
                            .commodityclassId(Tools.get32UUID())
                            .code(one.getString("code"))
                            .name(one.getString("name"))
                            .level(one.getString("level"))
                            .provid(ProvidEnum.YZH.getCode())
                            .parentid(one.getString("parentId"))
                            .status(one.getBoolean("status") ? StatusEnum.VALID.getCode() : StatusEnum.INVALID.getCode())
                            .build());
        }
        return oneList;
    }

    /**
     * 批量插入
     * @param list
     * @throws Exception
     */
    private void batchInserOrUpdate(List<CommodityClass> list) throws Exception {
        commodityClassService.batchInsertOrUpdate(list);
    }

    /**
     * 定时获取 云中鹤系统地址
     */
    public void getAddress() {
        logger.error("providForYzhTask_getAddress_start...." + Tools.date2Str(new Date()));
        try {
            // 获取一级地址 省
            JSONObject resp = YunZhongHeUtils.getProvince();
            if(!ObjectUtil.isEmpty(resp)&& !ObjectUtil.isEmpty(resp.getJSONArray("RESULT_DATA"))){
                JSONArray provinceArray = resp.getJSONArray("RESULT_DATA");
                List<ProvidAddress> provinceList = addressParam(provinceArray,"0");
                batchInserOrUpdateAddress(provinceList);
                // 获取下级地址
                getAddress2(provinceList);
            }
        } catch (Exception e) {
            logger.error("providForYzhTask_getAddress_e=" + e);
        }
        logger.error("providForYzhTask_getAddress_end...." + Tools.date2Str(new Date()));
    }

    /**
     * @param array 参数处理
     * @return
     */
    private List<ProvidAddress> addressParam(JSONArray array, String parentId) {
        List<ProvidAddress> dataList = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JSONObject data = (JSONObject) array.get(i);
            String type = data.getString("type");
            if( "province".equals(type)){
                type = "1";
            }else if( "city".equals(type)){
                type = "2";
            }else if( "county".equals(type)){
                type = "3";
            }else if( "town".equals(type)){
                type = "4";
            }
            dataList.add(
                    ProvidAddress.builder()
                            .providaddressId(Tools.get32UUID())
                            .id(data.getString("code"))
                            .name(data.getString("name"))
                            .type(type)
                            .provid(ProvidEnum.YZH.getCode())
                            .parentId(parentId)
                            .status(StatusEnum.VALID.getCode())
                            .build()
            );
        }
        return dataList;
    }

    /**
     * 获取二级分类
     * @param dataList
     * @throws Exception
     */
    private void getAddress2(List<ProvidAddress> dataList) throws Exception {
        for (ProvidAddress data : dataList) {
            //获取二级地址
            JSONObject subResp = YunZhongHeUtils.getCity(data.getId());
            if(!ObjectUtil.isEmpty(subResp) && !ObjectUtil.isEmpty(subResp.getJSONArray("RESULT_DATA"))){
                JSONArray subArray = subResp.getJSONArray("RESULT_DATA");
                List<ProvidAddress> subList = addressParam(subArray,data.getId());
                batchInserOrUpdateAddress(subList);
                getAddress3(subList);
            }
        }
    }

    /**
     * 获取三级分类
     * @param dataList
     * @throws Exception
     */
    private void getAddress3(List<ProvidAddress> dataList) throws Exception {
        for (ProvidAddress data : dataList) {
            //获取二级地址
            JSONObject subResp = YunZhongHeUtils.getCounty(data.getId());
            if(!ObjectUtil.isEmpty(subResp) && !ObjectUtil.isEmpty(subResp.getJSONArray("RESULT_DATA"))){
                JSONArray subArray = subResp.getJSONArray("RESULT_DATA");
                List<ProvidAddress> subList = addressParam(subArray,data.getId());
                batchInserOrUpdateAddress(subList);
                getAddress4(subList);
            }
        }
    }

    /**
     * 获取四级分类
     * @param dataList
     * @throws Exception
     */
    private void getAddress4(List<ProvidAddress> dataList) throws Exception {
        for (ProvidAddress data : dataList) {
            //获取二级地址
            JSONObject subResp = YunZhongHeUtils.getTown(data.getId());
            if(!ObjectUtil.isEmpty(subResp) && !ObjectUtil.isEmpty(subResp.getJSONArray("RESULT_DATA"))){
                JSONArray subArray = subResp.getJSONArray("RESULT_DATA");
                List<ProvidAddress> subList = addressParam(subArray,data.getId());
                batchInserOrUpdateAddress(subList);
            }
        }
    }

    /**
     * 批量插入供应商地址表
     * @param list
     * @throws Exception
     */
    public void batchInserOrUpdateAddress(List<ProvidAddress> list) throws Exception {
        providAddressService.batchInsertOrUpdate(list);
    }
}

