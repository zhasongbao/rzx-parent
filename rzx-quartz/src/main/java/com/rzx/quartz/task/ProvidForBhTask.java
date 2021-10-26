package com.rzx.quartz.task;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rzx.common.constant.Constants;
import com.rzx.common.enums.BaiLiHuiNoticsTypeEnum;
import com.rzx.common.enums.ProvidEnum;
import com.rzx.common.enums.StatusEnum;
import com.rzx.common.utils.BigDecimalUtils;
import com.rzx.common.utils.Tools;
import com.rzx.common.utils.provid.baihui.BaiHuiUtils;
import com.rzx.project.domain.CommodityClass;
import com.rzx.project.domain.CommodityConfig;
import com.rzx.project.domain.OrderInfo;
import com.rzx.project.domain.ProvidAddress;
import com.rzx.project.service.ICommodityClassService;
import com.rzx.project.service.ICommodityConfigService;
import com.rzx.project.service.ICommonService;
import com.rzx.project.service.IOrderInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 供应商 百礼汇定时任务
 * @author zhasbao
 */
@Component("providForBhTask")
public class ProvidForBhTask {
    private static final Logger logger = LoggerFactory.getLogger(ProvidForBhTask.class);


    @Autowired
    private ProvidForYzhTask providForYzhTask;

    @Autowired
    private ICommodityConfigService commodityConfigService;
    @Autowired
    private ICommodityClassService commodityClassService;
    @Autowired
    private IOrderInfoService orderInfoService;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Resource
    private MongoTemplate mongoTemplate;
    @Autowired
    private ICommonService commonService;



    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(URLDecoder.decode("%E7%BB%BF%E7%8F%A0"));
    }


    /**
     * 百汇商品数据全量获取job
     */
    public void execute() {
        logger.error("forCommodityBhTask_execute_start...." + Tools.date2Str(new Date()));
        try {
            // 1、获取token
            String token = commonService.getBaihuiToken();
            if(StringUtils.isEmpty(token)) {
                logger.error("forCommodityBhTask_execute_token获取失败");
                return;
            }
            // 获取商品ID
            JSONObject idsResp = BaiHuiUtils.getGoodsId(token);
            if(!ObjectUtil.isEmpty(idsResp) && idsResp.getBoolean("result")){
                JSONObject data = (JSONObject) idsResp.get("data");
                if(!data.isEmpty() && !StringUtils.isEmpty(data.getString("ids"))){
                    String[] ids = data.getString("ids").split(",");
                    for (int i = 0; i < ids.length; i++) {
                        int finalI = i;
                        threadPoolTaskExecutor.execute(()->{
                            try {
                                String comId = ids[finalI];
                                if(StringUtils.isEmpty(comId)){
                                    logger.error("forCommodityBhTask_execute_comId为空");
                                    return;
                                }
                                JSONObject infoResp = BaiHuiUtils.getGoodInfo(token,comId);
                                if(!ObjectUtil.isEmpty(infoResp) && infoResp.getBoolean("result") && !ObjectUtil.isEmpty(infoResp.getJSONObject("data"))
                                        && !ObjectUtil.isEmpty(infoResp.getJSONObject("data").get("product"))){
                                    JSONObject product = infoResp.getJSONObject("data").getJSONObject("product");
                                    CommodityConfig pd = CommodityConfig.builder()
                                            .provid(ProvidEnum.BLH.getCode()).commodityCode(comId).build();
                                    List<CommodityConfig> list = commodityConfigService.selectCommodityConfigList(pd);
                                    if (!CollectionUtils.isEmpty(list)) {
                                        // 更新
                                        pd = list.get(0);
                                        dowith(pd, product);
                                        commodityConfigService.updateCommodityConfig(pd);
                                    } else {
                                        // 新增
                                        pd.setCommodityconfigId(Tools.get32UUID());
                                        pd.setStatus(StatusEnum.VALID.getCode());
                                        pd.setChooseFlag(Constants.NO_FLAG);
                                        pd.setRate("10");
                                        dowith(pd, product);
                                        commodityConfigService.save(pd);
                                    }
                                }
                            } catch (Exception exception) {
                                logger.error("forCommodityBhTask_execute_exception=" + exception);
                            }
                        });
                    }
                }else{
                    logger.error("forCommodityBhTask_execute_未获取到商品id");
                }
            }else{
                logger.error("forCommodityBhTask_execute_商品id获取失败");
            }
        } catch (Exception e) {
            logger.error("forCommodityBhTask_execute_e=" + e);
            logger.error(e.getMessage(), e);
        }
        logger.error("forCommodityBhTask_execute_end...." + Tools.date2Str(new Date()));
    }

    /**
     * 参数处理
     * @param pd
     * @param product
     */
    public void dowith(CommodityConfig pd, JSONObject product) {
        try {
            pd.setTypeId(product.getString("typeid"));
            pd.setCommodityName(StringUtils.isEmpty(product.getString("product_name")) ? "" : URLDecoder.decode(product.getString("product_name")));
            pd.setProvidSource(product.getString("product_group"));
            pd.setExplain(StringUtils.isEmpty(product.getString("sell_point")) ? "" : URLDecoder.decode(product.getString("sell_point")));
            pd.setProductCate(product.getString("category_id"));
            pd.setBrand(StringUtils.isEmpty(product.getString("brand")) ? "" : URLDecoder.decode(product.getString("brand")));
            pd.setMarketPrice(product.getString("market_price"));
            pd.setRetailAmount(product.getString("settlement"));
            String amount = BigDecimalUtils.multiply(product.getString("settlement"),"1.1",2);
            pd.setAmount(amount);
            pd.setSaleStatus(product.getString("state"));
            pd.setThumbnailImage(product.getString("product_images"));
            pd.setImageUrls(product.getString("images"));
            if("0".equals(product.getString("state"))){
                // 下架商品挪出礼包/商品库
                pd.setGiftpackageId("");
                pd.setChooseFlag(Constants.NO_FLAG);
            }
            pd.setImage(StringUtils.isEmpty(product.getString("app_introduce")) ? "" : URLDecoder.decode(product.getString("app_introduce")));
        } catch (Exception e) {
            logger.error("forCommodityBhTask_dowith_e=" + e);
        }
    }

    /**
     * 百汇商品分类数据全量获取job
     */
    public void getAllClass() {
        logger.error("forCommodityBhTask_getAllClass_start...." + Tools.date2Str(new Date()));
        try {
            // 获取token
            String token = commonService.getBaihuiToken();
            if(StringUtils.isEmpty(token)) {
                logger.error("forCommodityBhTask_execute_token获取失败");
                return;
            }
            JSONObject resp = BaiHuiUtils.getCategorys(token);
            if(!ObjectUtil.isEmpty(resp) && resp.getBoolean("result") && !ObjectUtil.isEmpty(resp.getJSONArray("data"))){
                JSONArray data = resp.getJSONArray("data");
                List<CommodityClass> oneList = commoityClassParam(data);
                batchInserOrUpdate(oneList);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        logger.error("forCommodityBhTask_getAllClass_end...." + Tools.date2Str(new Date()));
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
                            .commodityclassId(RandomUtil.randomUUID().replace("-",""))
                            .code(one.getString("code"))
                            .name(one.getString("name"))
                            .level("0".equals(one.getString("parentCode")) ? "0" : "1")
                            .provid(ProvidEnum.BLH.getCode())
                            .parentid(one.getString("parentCode"))
                            .status(StatusEnum.VALID.getCode())
                            .build()
            );
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
     * 定时获取 百礼汇系统地址job
     */
    public void getAddress() {
        logger.error("forCommodityBhTask_getAddress_start...." + Tools.date2Str(new Date()));
        try {
            String token = commonService.getBaihuiToken();
            // 获取一级地址
            JSONObject resp = BaiHuiUtils.getProvince(token);
            if(!ObjectUtil.isEmpty(resp) && resp.getBoolean("result") &&  !ObjectUtil.isEmpty(resp.getJSONObject("data")) &&
                    !ObjectUtil.isEmpty(resp.getJSONObject("data").getJSONArray("list"))){
                JSONArray oneArray = resp.getJSONObject("data").getJSONArray("list");
                List<ProvidAddress> provinceList = addressParam(oneArray,"1");
                providForYzhTask.batchInserOrUpdateAddress(provinceList);
                // 获取下级地址
                getAddress2(provinceList,token);
            }
        } catch (Exception e) {
            logger.error("forCommodityBhTask_getAddress_e=" + e);
        }
        logger.error("forCommodityBhTask_getAddress_end...." + Tools.date2Str(new Date()));
    }

    /**
     * 定时获取 百礼汇查询通知消息job  暂未启用
     */
    public void getNotics() {
        logger.error("forCommodityBhTask_getNotics_start...." + Tools.date2Str(new Date()));
        try {
            getNoticsNextPage();
        } catch (Exception e) {
            logger.error("forCommodityBhTask_getAddress_e=" + e);
        }
        logger.error("forCommodityBhTask_getAddress_end...." + Tools.date2Str(new Date()));
    }


    /**
     * 一次按时间正序至多获取100条，通知消息处理后调用消息删除接口,以避免获取到重复消息（不处理的消息也需要删除，否则容易引起消息堵塞）。
     * type 1-商品新增  2-商品基础信息更新 3-商品上架状态更新 4-商品市场价更新  5-商品结算价更新 10-实物订单出库 11-直充订单出库 12-卡券订单出库  99-订单关闭
     * @throws Exception
     */
    private void getNoticsNextPage() throws Exception {
        String token = commonService.getBaihuiToken();
        // 查询通知消息
        JSONObject resp = BaiHuiUtils.getNotics(token);
        if(!ObjectUtil.isEmpty(resp) && resp.getBoolean("result") &&  !ObjectUtil.isEmpty(resp.getJSONObject("data")) &&
                !ObjectUtil.isEmpty(resp.getJSONObject("data").getJSONArray("notice_list"))){
            JSONArray noticeList = resp.getJSONObject("data").getJSONArray("notice_list");
            String notice_ids = "";
            // 处理消息
            for (int i = 0; i < noticeList.size(); i++) {
                JSONObject notice = (JSONObject) noticeList.get(i);
                saveNotifyLog(notice);
                JSONObject result = notice.getJSONObject("result");
                if(BaiLiHuiNoticsTypeEnum.ADD.getCode().equals(notice.getString("type")) ||
                        BaiLiHuiNoticsTypeEnum.UPDATE_BASE.getCode().equals(notice.getString("type")) ||
                        BaiLiHuiNoticsTypeEnum.UPDATE_STATUS.getCode().equals(notice.getString("type")) ||
                        BaiLiHuiNoticsTypeEnum.UPDATE_SCJ.getCode().equals(notice.getString("type")) ||
                        BaiLiHuiNoticsTypeEnum.UPDATE_JSJ.getCode().equals(notice.getString("type")) ){
                    // 商品类通知
                    if(ObjectUtil.isNotEmpty(result) && !StringUtils.isEmpty(result.getString("itemId"))){
                        // 查询商品数据并更新商品
                        JSONObject infoResp = BaiHuiUtils.getGoodInfo(token, result.getString("itemId"));
                        if(!ObjectUtil.isEmpty(infoResp) && infoResp.getBoolean("result") && !ObjectUtil.isEmpty(infoResp.getJSONObject("data"))
                                && !ObjectUtil.isEmpty(infoResp.getJSONObject("data").get("product"))){
                            JSONObject product = infoResp.getJSONObject("data").getJSONObject("product");
                            CommodityConfig pd = CommodityConfig.builder()
                                    .provid(ProvidEnum.BLH.getCode())
                                    .commodityCode(result.getString("itemId"))
                                    .build();
                            List<CommodityConfig> list = commodityConfigService.selectCommodityConfigList(pd);
                            if (!CollectionUtils.isEmpty(list)) {
                                // 更新
                                pd = list.get(0);
                                dowith(pd, product);
                                commodityConfigService.updateCommodityConfig(pd);
                            } else {
                                // 新增
                                pd.setCommodityconfigId(Tools.get32UUID());
                                pd.setStatus(StatusEnum.VALID.getCode());
                                pd.setChooseFlag(Constants.NO_FLAG);
                                dowith(pd, product);
                                commodityConfigService.save(pd);
                            }
                        }
                    }
                }else if(BaiLiHuiNoticsTypeEnum.ORDER_OUT_SW.getCode().equals(notice.getString("type")) ||
                        BaiLiHuiNoticsTypeEnum.ORDER_OUT_ZC.getCode().equals(notice.getString("type")) ||
                        BaiLiHuiNoticsTypeEnum.ORDER_OUT_KQ.getCode().equals(notice.getString("type")) ||
                        BaiLiHuiNoticsTypeEnum.ORDER_CLOSE.getCode().equals(notice.getString("type"))){
                    // 订单类通知
                    if(ObjectUtil.isNotEmpty(result) && !StringUtils.isEmpty(result.getString("order_sn"))){
                        OrderInfo order = OrderInfo.builder()
                                .orderKey(result.getString("order_sn"))
                                .orderProvid(ProvidEnum.BLH.getCode())
                                .build();
                        List<OrderInfo>  orderList =  orderInfoService.selectOrderInfoList(order);
                        if(CollectionUtils.isEmpty(orderList)){
                            logger.error("forCommodityBhTask_订单未找到：" + result.getString("order_sn"));
                            continue;
                        }
                        order = orderList.get(0);
                        if(BaiLiHuiNoticsTypeEnum.ORDER_OUT_SW.getCode().equals(notice.getString("type"))){
                            order.setExpressCode(result.getString("express_code"));
                        }
                        orderInfoService.updateOrderInfo(order);
                    }
                }
                if(StringUtils.isEmpty(notice_ids)){
                    notice_ids = notice.getString("notice_id");
                }else{
                    notice_ids = notice_ids + "," + notice.getString("notice_id");
                }
            }
            // 删除消息
            BaiHuiUtils.doNotics(token,notice_ids);
            if(100 == resp.getJSONObject("data").getInteger("notice_num")){
                getNoticsNextPage();
            }
        }
    }

    /**
     * 保存通知记录
     *
     * @param notice  通知信息
     */
    private void saveNotifyLog(JSONObject notice) {
        JSONObject pd = new JSONObject();
        pd.put("a_id", Tools.get32UUID());
        pd.put("a_createTime", Tools.date2Str(new Date()));
        pd.putAll(notice);
        this.mongoTemplate.insert(pd, Constants.BAILIHUI_NOTICY_LOG);
    }

    /**
     * @param array 参数处理
     * @return
     */
    private List<ProvidAddress> addressParam(JSONArray array, String type) {
        List<ProvidAddress> dataList = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JSONObject data = (JSONObject) array.get(i);
            dataList.add(
                    ProvidAddress.builder()
                            .providaddressId(Tools.get32UUID())
                            .id(data.getString("id"))
                            .name(data.getString("name"))
                            .type(type)
                            .provid(ProvidEnum.BLH.getCode())
                            .parentId(data.getString("parent_id"))
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
    private void getAddress2(List<ProvidAddress> dataList,String token) throws Exception {
        for (ProvidAddress data : dataList) {
            //获取二级地址
            JSONObject resp = BaiHuiUtils.getCity(token,data.getId());
            if(!ObjectUtil.isEmpty(resp) && resp.getBoolean("result") && !ObjectUtil.isEmpty(resp.getJSONObject("data")) &&
                    !ObjectUtil.isEmpty(resp.getJSONObject("data").getJSONArray("list"))){
                JSONArray oneArray = resp.getJSONObject("data").getJSONArray("list");
                List<ProvidAddress> subList = addressParam(oneArray,"2");
                providForYzhTask.batchInserOrUpdateAddress(subList);
                getAddress3(subList, token);
            }
        }
    }

    /**
     * 获取三级分类
     * @param dataList
     * @throws Exception
     */
    private void getAddress3(List<ProvidAddress> dataList,String token) throws Exception {
        for (ProvidAddress data : dataList) {
            //获取三级地址
            JSONObject resp = BaiHuiUtils.getCounty(token,data.getId());
            if(!ObjectUtil.isEmpty(resp) && resp.getBoolean("result") && !ObjectUtil.isEmpty(resp.getJSONObject("data")) &&
                    !ObjectUtil.isEmpty(resp.getJSONObject("data").getJSONArray("list"))){
                JSONArray oneArray = resp.getJSONObject("data").getJSONArray("list");
                List<ProvidAddress> subList = addressParam(oneArray,"3");
                providForYzhTask.batchInserOrUpdateAddress(subList);
                getAddress4(subList, token);
            }
        }
    }

    /**
     * 获取四级分类
     * @param dataList
     * @throws Exception
     */
    private void getAddress4(List<ProvidAddress> dataList,String token) throws Exception {
        for (ProvidAddress data : dataList) {
            //获取二级地址
            JSONObject resp = BaiHuiUtils.getTown(token,data.getId());
            if(!ObjectUtil.isEmpty(resp) && resp.getBoolean("result") && !ObjectUtil.isEmpty(resp.getJSONObject("data")) &&
                    !ObjectUtil.isEmpty(resp.getJSONObject("data").getJSONArray("list"))){
                JSONArray oneArray = resp.getJSONObject("data").getJSONArray("list");
                List<ProvidAddress> subList = addressParam(oneArray,"4");
                providForYzhTask.batchInserOrUpdateAddress(subList);
            }
        }
    }

}

