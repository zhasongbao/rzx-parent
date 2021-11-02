package com.rzx.common.utils.provid.yunzhonghe;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.rzx.common.constant.Constants;
import com.rzx.common.core.mongodb.MongodbService;
import com.rzx.common.utils.MD5;
import com.rzx.common.utils.http.HttpClientUtil;
import com.rzx.common.utils.spring.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 云中鹤
 * token参数生成规则:  MD5(wid + accessToken + timestamp)
 */
public class YunZhongHeUtils {

    private static final Logger logger = LoggerFactory.getLogger(YunZhongHeUtils.class);
    private static MongodbService mongodbService = SpringUtils.getBean(MongodbService.class);

    //当前环境
    private static final String ACTIVE = SpringUtils.getActiveProfiles()[0];
    //生产
    private static final String PROD = "prod";

    /**
     * 云中鹤请求URL
     */
    private static String URL_DEV = "http://beta.open.limofang.cn/api";
//    	private static String URL_DEV = "http://open.fygift.com/api";
    private static String URL_PRO = "http://open.fygift.com/api";

    /** 测试环境 wid: 3597 accessToken：3DyQs3P8gaNeXmhhGfutrqmwxFwagg*/
    /** 正式环境 wid: 4066 accessToken：tytdn4MkvDhGGRV9JqUb6ynbZDtFk5*/
    /**
     * 云中鹤 wid
     */
    public static String wid_dev = "3597";
//    	public static String wid_dev = "4066";
    public static String wid_pro = "4066";
    /**
     * 云中鹤 accessToken
     */
    private static String accessToken_dev = "3DyQs3P8gaNeXmhhGfutrqmwxFwagg";
//        private static String accessToken_dev = "tytdn4MkvDhGGRV9JqUb6ynbZDtFk5";
    private static String accessToken_pro = "tytdn4MkvDhGGRV9JqUb6ynbZDtFk5";

    public static String url() {
        if (PROD.equals(ACTIVE)) {
            return URL_PRO;
        } else {
            return URL_DEV;
        }
    }

    public static String wid() {
        if (PROD.equals(ACTIVE)) {
            return wid_pro;
        } else {
            return wid_dev;
        }
    }

    public static String accessToken() {
        if (PROD.equals(ACTIVE)) {
            return accessToken_pro;
        } else {
            return accessToken_dev;
        }
    }

//	/**
//	 * 通用日志保存
//	 * @param requestDTO
//	 * @param method
//	 * @param responseDTO
//	 */
//	public static void saveLog(String requestDTO, String method, JSONObject responseDTO) throws Exception{
//		CommonService commonService = (CommonService) ServiceHelper.getService("commonService");
//		if (commonService != null) {
//			JSONObject pd = new JSONObject();
//			pd.put("requestDTO", requestDTO);
//			pd.put("method", method);
//			pd.put("responseDTO", responseDTO);
//			commonService.YZHRequestLogSave(pd);
//		}
//	}

    public static void saveLog(String paraJson, JSONObject result, String orderId, String urlPath) {
        // 保存日志
        final Map<String, Object> paraPd = new HashMap<>();
        paraPd.put("result", result == null ? "" : result);
        paraPd.put("paraJson", paraJson == null ? "" : paraJson);
        paraPd.put("orderId", orderId);
        paraPd.put("urlPath", urlPath);
        new Thread(() -> {
            paraPd.put("a_id", IdUtil.simpleUUID());
            paraPd.put("a_createTime", DateUtil.now());
            paraPd.put("paraJson", paraJson);
            mongodbService.save(paraPd, Constants.YUNZHONGHE_REQUESTAPILOG);
        }).start();
    }

    public static void main(String[] args) throws Exception {
        remain(); //3.1.查询预存款余额 1932.38
//		getAllProductIds();  //所有商品ID
//		getProductIdsByPage(114);
//		getProvince();		 //获取一级地址(省份)
//		getCity("21");		 //获取二地址(城市)
//		getCounty("1845");		 //获取三级地址(县/区)
//		getTown("1848");		 //获取四级地址(镇/街道)
//		getAreaCodeByAddress("广东省深圳市南山区阳光科创中心"); //根据地址查询地址编码
//		getAllProductIds();
        //347811,528358,528478,563505,563507,563510,563514,563554,563555,563559,563561,563563,563564,563565,563566,563567,563568,563569,563570,563571,563572,563573,563574,563575,563576,563577,563578,563579,563580,563581,563582,563583,563584,563585,563586,563587,563588,563589,563590,563591,563592,563593,563594,563595,563596,563597,563598,563599,563600,563601,563602,563603,563604,563605,563606,563607,563608,563609,563610,563611,563612,563613,563614,563615,563616,563617,563618,563619,563620,563621,563622,563623,563624,563625,563626,563627,563628,563629,563630,563631,563632,563633,563634,563635,563636,563637,563638,563639,563640,563641,563642,563643,563644,563645,563646,563647,563648,563649,563650,563651,563652,563653,563654,563655,563656,563657,563658,563659,563660,563661,563662,563663,563664,563665,563666,563667,563668,563669,563670,563671,563672,563673,563674,563675,563676,563677,563678,563679,563680,563681,563682,563683,563684,563685,563686,563687,563688,563689,563690,563691,563692,563693,563695,563698,563699,563700,563701,563702,563703,563704,563705,563706,563707,563708,563709,563710,563711,563712,563713,563714,563715,563716,563717,563718,563719,563720,563721,563722,563723,563724,563725,563726,563727,563728,563729,563730,563731,563732,563733,563734,563735,563736,563737,563738,563739,563740,563741,563742,563743,563744,563745,563746,563747,563748,563749,563750,563751,563752,563753,563754,563755,563756,563757,563758,563759,563760,563761,563762,563763,563764,563765,563766,563767,563768,563769,563770,563771,563772,563773,563774,563775,563776,563777,563778,563779,563780,563781,563782,563783,563784,563785,563786,563787,563788,563789,563790,563791,563792,563793,563794,563795,563796,563797,563798,563799,563800,563801,563802,563803,563804,563805,563806,563807,563808,563809,563810,563811,563812,563813,563814,563815,563816,563817,563818,563819,563820,563821,563822,563823,563824,563825,563826,563827,563828,563829,563830,563831,563832,563833,563834,563835,563836,563837,563838,563839,563840,563841,563842,563843,563844,563845,563846,563847,563848,563849,563850,563851,563852,563853,563854,563855,563856,563857,563858,563859,563860,563861,563862,563863,563864,563865,563866,563867,563868,563869,563870,563871,563872,563873,563874,563875,563876,563877,563878,563879,563880,563881,563882,563883,563884,563885,563886,563887,563888,563889,563890,563891,563892,563893,563894,563895,563896,563897,563898,563899,563900,563901,563902,563903,563904,563905,563906,563907,563908,563909,563910,563911,563912,563913,563914,563915,563916,563917,563918,563919,563920,563921,563922,563923,563924,563925,563926,563927,563928,563929,563930,563931,563932,563933,563934,563935,563936,563937,563938,563939,563940,563941,563942,563943,563944,563945,563946,563947,563948,563949,563950,563951,563952,563953,563954,563955,563956,563957,563958,563959,563960,563961,563962,563963,563964,563965,563966,563967,563968,563969,563970,563971,563972,563973,563974,563975,563976,563977,563978,563979,563980,563981,563982,563983,563984,563985,563986,563987,563988,563989,563990,563991,563992,563993,563994,563995,563996,563997,563998,563999,564000,564001,564002,564003,564004,564005,564006,564007,564008,564009,564010,564011,564012,564013,564014,564015,564016,564017,564018,564019,564020,564021,564022,564023,564024,564025,564026,564027,564028,564029,564030,564031,564032,564033,564034,564035,564036,564037,564038,564039,564040,564041,564042,564043,564044,564045,564046,564047,564048,564049,564050,564051,564052,564053,564054,564055,564056,564057,564058,564059,564060,564061,564062,564063,564064,564065,564066,564067,564068,564069,564070,564071,564072,564073,564074,564075,564076,564077,564078,564079,564080,564081,564082,564083,564084,564085,564086,564087,564088,564089,564090,564091,564092,564093,564094,564095,564096,564097,564098,564099,564100,564101,564102,564103,564104,564105,564106,564107,564108,564109,564110,564111,564112,564113,564114,564115,564116,564117,564118,564119,564120,564121,564122,564123,564124,564125,564126,564127,564128,564129,564130,564131,564132,564133,564134,564135,564136,564137,564138,564139,564140,564141,564142,564143,564144,564145,564146,564147,564148,564149,564150,564151,564152,564153,564154,564155,564156,564157,564158,564159,564160,564161,564162,564163,564164,564165,564171,564173,564176,564179,564181,564185,564186,564187,564194,564195,564196,564197,564198,564199,564200,564201,564202,564203,564208,564210,564211,564213,564214,564215,564216,564217,564219,564222,564223,564225,564228,564229,564230,564232,564236,564238,564239,564240,564241,564242,564244,564246,564247,564248,564254,564255,564256,564257,564258,564260,564261,564264,564265,564270,564274,564284,564291,564296,564297,564314,564330,564335,564338,564340,564363,564365,564366,564369,564370,564371,564372,564374,564375,564376,564378,564379,564380,564381,564382,564383,564384,564385,564386,564387,564388,564389,564393,564394,564395,564396,564398,564399,564400,564401,564402,564403,564404,564405,564406,564407,564408,564409,564410,564411,564413,564414,564415,564416,564417,564418,564419,564420,564421,564422,564424,564425,564426,564428,564430,564431,564432,564433,564435,564437,564439,564440,564441,564442,564443,564446,564447,564450,564451,564452,564454,564458,564461,564474,564479,564483,564494,564495,564497,564500,564501,564503,564511,564515,564518,564521,564525,564526,564527,564528,564529,564530,564531,564532,564534,564535,564537,564538,564539,564540,564541,564542,564544,564545,564546,564547,564548,564549,564550,564551,564552,564553,564554,564555,564556,564557,564559,564560,564561,564562,564563,564565,564567,564568,564569,564570,564571,564572,564573,564574,564575,564576,564577,564578,564579,564580,564581,564582,564583,564584,564585,564586,564587,564588,564589,564590,564591,564592,564593,564594,564595,564596,564597,564598,564599,564600,564601,564602,564603,564604,564605,564606,564607,564608,564609,564610,564611,564612,564613,564614,564615,564616,564617,564618,564619,564620,564621,564622,564623,564624,564625,564626,564627,564628,564629,564630,564631,564632,564633,564634,564635,564636,564637,564638,564639,564640,564641,564642,564643,564644,564645,564646,564647,564648,564649,564650,564651,564652,564653,564654,564656,564657,564658,564659,564660,564661,564662,564664,564666,564667,564668,564669,564670,564675,564676,564680,564681,564682,564683,564684,564685,564686,564687,564688,564689,564690,564691,564692,564693,564694,564695,564696,564697,564698,564699,564700,564701,564702,564703,564704,564705,564706,564707,564708,564709,564710,564711,564712,564713,564714,564715,564716,564717,564718,564719,564720,564721,564722,564723,564724,564725,564726,564727,564728,564729,564730,564731,564732,564733,564734,564735,564736,564737,564738,564739,564740,564741,564742,564743,564744,564745,564746,564747,564748,564749,564750,564751,564752,564753,564754,564755,564756,564757,564758,564759,564760,564761,564762,564763,564764,564765,564766,564767,564768,564769,564770,564771,564772,564773,564774,564775,564776,564777,564778,564779],"2":[564780,564781,564782,564783,564784,564785,564786,564787,564788,564789,564790,564791,564792,564793,564794,564795,564796,564797,564798,564799,564800,564801,564802,564803,564804,564805,564806,564807,564808,564809,564810,564811,564812,564813,564814,564815,564816,564817,564818,564819,564820,564821,564822,564823,564824,564825,564826,564827,564828,564829,564830,564831,564832,564833,564834,564835,564836,564837,564838,564839,564840,564841,564842,564843,564844,564845,564846,564847,564848,564849,564850,564851,564852,564853,564854,564855,564856,564857,564858,564859,564860,564861,564862,564863,564864,564865,564866,564867,564868,564869,564870,564871,564872,564873,564874,564875,564876,564877,564878,564879,564880,564881,564882,564883,564884,564885,564886,564887,564888,564889,564890,564891,564892,564893,564894,564895,564896,564897,564898,564899,564900,564901,564902,564903,564904,564905,564906,564908,564909,564910,564911,568862,568863,568864,572135,572141,572142,572143,572145,572148,572149,572150,572153,572154,572155,572156,572157,572158,572160,572163,572164,572165,572166,572167,572173,572176,574203,574204,574205,574206,574207,574208,574210,574211,574212,574213,574214,574215,574216,574217,574219,574220,574221,574222,574223,574225,574226,574227,574228,574229,574230,574231,574232,574233,574234,574235,574236,574237,574238,574239,574240,574241,574242,574244,574245,574246,574247,574248,574249,574250,574251,574252,574253,574254,574255,574256,574258,574259,574260,574261,574262,574263,574264,574265,574266,574267,574270,574272,574273,574274,574275,574279,574280,574282,574283,574288,574291,574538,574547
//		detial("528358"); 				//商品详情
//		getSaleStatus("2726697"); 	//商品可售状态
//		orderTrack("08021424508820035");

//		cateRootCate(); 			// 5.1.获取一级产品分类
//		cateChilds("1567"); 	// 5.2.获取下级产品分类115
//		cateDetials("1536"); 		// 5.3.获取单个分类详情
//		cancel("08301632476240234");

    }

    /**
     * 云中鹤 2.1.创建订单接口
     *
     * @param order   订单信息
     * @param address 物流信息
     * @return
     */
    public static JSONObject createOrder(JSONObject order, JSONObject address) {
        String timestamp = String.valueOf((new Date()).getTime());
        String param = "wid=" + wid() + "&timestamp=" + timestamp + "&token=" + MD5.md5(wid() + accessToken() + timestamp).toUpperCase();
        param = param + "&thirdOrder=" + order.getString("ORDER_ID")
                + "&pid_nums=" + order.getString("COMMODITY_CODE") + "_1"
                + "&receiverName=" + address.getString("RECEIVE_NAME")
                + "&province=" + address.getString("PROVINCE")
                + "&city=" + address.getString("CITY")
                + "&county=" + address.getString("COUNTY")
                + "&town=" + address.getString("TOWN")
                + "&address=" + address.getString("RECEIVE_ADDRESS")
                + "&mobile=" + address.getString("RECEIVE_PHONE");
        JSONObject respJson = HttpClientUtil.postFormUrlEncoded(url() + "/order/submit.php", param);
        saveLog(param, respJson, order.getString("ORDER_ID"), "/order/submit.php");
        return respJson;
    }

    /**
     * 云中鹤 2.6.取消订单接口
     *
     * @param orderId 订单号
     * @return
     */
    public static JSONObject cancel(String orderId) {
        String timestamp = String.valueOf((new Date()).getTime());
        String param = "wid=" + wid() + "&timestamp=" + timestamp + "&token=" + MD5.md5(wid() + accessToken() + timestamp).toUpperCase();
        param = param + "&thirdOrder=" + orderId;
        JSONObject respJson = HttpClientUtil.postFormUrlEncoded(url() + "/order/cancel.php", param);
        saveLog(param, respJson, orderId, "/order/cancel.php");
        return respJson;
    }

    /**
     * 云中鹤 2.7.取消订单接口（子订单取消）
     *
     * @param order 订单
     * @return
     */
    public static JSONObject cancelByOrderKey(JSONObject order) {
        String timestamp = String.valueOf((new Date()).getTime());
        String param = "wid=" + wid() + "&timestamp=" + timestamp + "&token=" + MD5.md5(wid() + accessToken() + timestamp).toUpperCase();
        param = param + "&thirdOrder=" + order.getString("ORDER_ID") + "&orderKey=" + order.getString("ORDER_KEY");
        JSONObject respJson = HttpClientUtil.postFormUrlEncoded(url() + "/order/cancelByOrderKey.php", param);
        return respJson;
    }

    /**
     * 云中鹤 1.10.批量查询商品库存（system产品库存少于10的时候返回库存数）
     *
     * @param pid_nums 商品ID_数量， 多个使用逗号(,)拼接
     * @param pid_nums 配送地址ID, 格式：1_0_0 (分别代表1、2、3级地址)
     * @return
     */
    public static JSONObject stockBatch(String pid_nums, String address) {
        String timestamp = String.valueOf((new Date()).getTime());
        String param = "wid=" + wid() + "&timestamp=" + timestamp + "&token=" + MD5.md5(wid() + accessToken() + timestamp).toUpperCase();
        param = param + "&pid_nums=" + pid_nums + "&address=" + address;
        JSONObject respJson = HttpClientUtil.postFormUrlEncoded(url() + "/product/v2/stockBatch.php", param);
        return respJson;
    }

    /**
     * 云中鹤 订单物流信息接口-根据己方订单号（任意行订单号）获取
     *
     * @param orderId 订单号
     * @return
     */
    public static JSONObject orderTrack(String orderId) {
        String timestamp = String.valueOf((new Date()).getTime());
        String param = "wid=" + wid() + "&timestamp=" + timestamp + "&token=" + MD5.md5(wid() + accessToken() + timestamp).toUpperCase();
        param = param + "&thirdOrder=" + orderId;
        JSONObject respJson = HttpClientUtil.postFormUrlEncoded(url() + "/order/orderTrack.php", param);
        saveLog(param, respJson, orderId, "/order/orderTrack.php");
        return respJson;
    }

    /**
     * 云中鹤 1.1.获取所有商品ID 不分页
     *
     * @return
     */
    public static JSONObject getAllProductIds() {
        String timestamp = String.valueOf((new Date()).getTime());
        String param = "wid=" + wid() + "&timestamp=" + timestamp + "&token=" + MD5.md5(wid() + accessToken() + timestamp).toUpperCase();
        JSONObject respJson = HttpClientUtil.postFormUrlEncoded(url() + "/product/getAllProductIds.php", param);
        saveLog(param, respJson, null, "/product/getAllProductIds.php");
        return respJson;
    }

    /**
     * 云中鹤 1.2.分页获取当前页商品ID, 每页数据100条
     *
     * @param page 页码
     * @return
     */
    public static JSONObject getProductIdsByPage(int page) {
        String timestamp = String.valueOf((new Date()).getTime());
        String param = "wid=" + wid() + "&timestamp=" + timestamp + "&token=" + MD5.md5(wid() + accessToken() + timestamp).toUpperCase();
        param = param + "&page=" + page;
        JSONObject respJson = HttpClientUtil.postFormUrlEncoded(url() + "/product/v2/getProductIdsByPage.php", param);
		saveLog(param,respJson,null,"/product/v2/getProductIdsByPage.php");
        return respJson;
    }

    /**
     * 云中鹤 1.3.获取单个商品详情
     *
     * @param pid 商品ID
     * @return
     */
    public static JSONObject detial(String pid) {
        String timestamp = String.valueOf((new Date()).getTime());
        String param = "wid=" + wid() + "&timestamp=" + timestamp + "&token=" + MD5.md5(wid() + accessToken() + timestamp).toUpperCase();
        param = param + "&pid=" + pid;
        JSONObject respJson = HttpClientUtil.postFormUrlEncoded(url() + "/product/detial.php", param);
        saveLog(param, respJson, null, "/product/detial.php");
        return respJson;
    }

    /**
     * 云中鹤 1.6.查询商品可售状态
     *
     * @param pid 商品ID
     * @return
     */
    public static JSONObject getSaleStatus(String pid) {
        String timestamp = String.valueOf((new Date()).getTime());
        String param = "wid=" + wid() + "&timestamp=" + timestamp + "&token=" + MD5.md5(wid() + accessToken() + timestamp).toUpperCase();
        param = param + "&pid=" + pid;
        JSONObject respJson = HttpClientUtil.postFormUrlEncoded(url() + "/product/saleStatus.php", param);
        saveLog(param, respJson, null, "/product/saleStatus.php");
        return respJson;
    }

    /**
     * 云中鹤 3.1.查询预存款余额
     *
     * @return
     */
    public static JSONObject remain() {
        String timestamp = String.valueOf((new Date()).getTime());
        String param = "wid=" + wid() + "&timestamp=" + timestamp + "&token=" + MD5.md5(wid() + accessToken() + timestamp).toUpperCase();
        JSONObject respJson = HttpClientUtil.postFormUrlEncoded(url() + "/finance/remain.php", param);
        saveLog(param, respJson, null, "/finance/remain.php");
        return respJson;
    }

    /**
     * 云中鹤 4.1.获取一级地址(省份)
     *
     * @return
     */
    public static JSONObject getProvince() {
        String timestamp = String.valueOf((new Date()).getTime());
        String param = "wid=" + wid() + "&timestamp=" + timestamp + "&token=" + MD5.md5(wid() + accessToken() + timestamp).toUpperCase();
        JSONObject respJson = HttpClientUtil.postFormUrlEncoded(url() + "/area/province.php", param);
        saveLog(param, respJson, null, "/area/province.php");
        return respJson;
    }

    /**
     * 云中鹤 4.2.获取二地址(城市)
     *
     * @param province 省份Code
     * @return
     */
    public static JSONObject getCity(String province) {
        String timestamp = String.valueOf((new Date()).getTime());
        String param = "wid=" + wid() + "&timestamp=" + timestamp + "&token=" + MD5.md5(wid() + accessToken() + timestamp).toUpperCase();
        param = param + "&province=" + province;
        JSONObject respJson = HttpClientUtil.postFormUrlEncoded(url() + "/area/city.php", param);
        saveLog(param, respJson, null, "/area/city.php");
        return respJson;
    }

    /**
     * 云中鹤 4.3.获取三级地址(县/区)
     *
     * @param city 城市Code
     * @return
     */
    public static JSONObject getCounty(String city) {
        String timestamp = String.valueOf((new Date()).getTime());
        String param = "wid=" + wid() + "&timestamp=" + timestamp + "&token=" + MD5.md5(wid() + accessToken() + timestamp).toUpperCase();
        param = param + "&city=" + city;
        JSONObject respJson = HttpClientUtil.postFormUrlEncoded(url() + "/area/county.php", param);
        saveLog(param, respJson, null, "/area/county.php");
        return respJson;
    }

    /**
     * 云中鹤 4.4.获取四级地址(镇/街道)
     *
     * @param county 城市Code
     * @return
     */
    public static JSONObject getTown(String county) {
        String timestamp = String.valueOf((new Date()).getTime());
        String param = "wid=" + wid() + "&timestamp=" + timestamp + "&token=" + MD5.md5(wid() + accessToken() + timestamp).toUpperCase();
        param = param + "&county=" + county;
        JSONObject respJson = HttpClientUtil.postFormUrlEncoded(url() + "/area/town.php", param);
        saveLog(param, respJson, null, "/area/town.php");
        return respJson;
    }

    /**
     * 云中鹤 4.5.根据地址查询地址编码
     *
     * @param address 详细的地址信息, 例如:广东省深圳市南山区阳光科创中心
     * @return
     */
    public static JSONObject getAreaCodeByAddress(String address) {
        String timestamp = String.valueOf((new Date()).getTime());
        String param = "wid=" + wid() + "&timestamp=" + timestamp + "&token=" + MD5.md5(wid() + accessToken() + timestamp).toUpperCase();
        param = param + "&address=" + address;
        JSONObject respJson = HttpClientUtil.postFormUrlEncoded(url() + "/area/getAreaCodeByAddress.php", param);
        saveLog(param, respJson, null, "/area/town.php");
        return respJson;
    }

    /**
     * 云中鹤 4.6.验证地址编码是否正确(主要用于验证三、四级地址是否完整正确)
     *
     * @param provinceId 一级地址编码
     * @param cityId     二级地址编码
     * @param countyId   三级地址编码, 如果是空请传0
     * @param townId     四级地址编码, 如果是空请传0
     * @return true 地址正确
     */
    public static JSONObject checkArea(String provinceId, String cityId, String countyId, String townId) {
        String timestamp = String.valueOf((new Date()).getTime());
        String param = "wid=" + wid() + "&timestamp=" + timestamp + "&token=" + MD5.md5(wid() + accessToken() + timestamp).toUpperCase();
        param = param + "&provinceId=" + provinceId
                + "&cityId=" + cityId
                + "&countyId=" + countyId
                + "&townId=" + townId;
        JSONObject respJson = HttpClientUtil.postFormUrlEncoded(url() + "/area/checkArea.php", param);
        saveLog(param, respJson, null, "/area/checkArea.php");
        return respJson;
    }

    /**
     * 云中鹤 5.1.获取一级产品分类
     *
     * @return
     */
    public static JSONObject cateRootCate() {
        String timestamp = String.valueOf((new Date()).getTime());
        String param = "wid=" + wid() + "&timestamp=" + timestamp + "&token=" + MD5.md5(wid() + accessToken() + timestamp).toUpperCase();
        JSONObject respJson = HttpClientUtil.postFormUrlEncoded(url() + "/cate/rootCate.php", param);
        saveLog(param, respJson, null, "/cate/rootCate.php");
        return respJson;
    }

    /**
     * 云中鹤 5.2.获取下级产品分类
     *
     * @return
     */
    public static JSONObject cateChilds(String parentCate) {
        String timestamp = String.valueOf((new Date()).getTime());
        String param = "wid=" + wid() + "&timestamp=" + timestamp + "&token=" + MD5.md5(wid() + accessToken() + timestamp).toUpperCase();
        param = param + "&parentCate=" + parentCate;
        JSONObject respJson = HttpClientUtil.postFormUrlEncoded(url() + "/cate/childs.php", param);
        saveLog(param, respJson, null, "/cate/childs.php");
        return respJson;
    }

    /**
     * 云中鹤 5.3.获取单个分类详情
     *
     * @return
     */
    public static JSONObject cateDetials(String cid) {
        String timestamp = String.valueOf((new Date()).getTime());
        String param = "wid=" + wid() + "&timestamp=" + timestamp + "&token=" + MD5.md5(wid() + accessToken() + timestamp).toUpperCase();
        param = param + "&cid=" + cid;
        JSONObject respJson = HttpClientUtil.postFormUrlEncoded(url() + "/cate/detial.php", param);
        saveLog(param, respJson, null, "/cate/detial.php");
        return respJson;
    }


}
