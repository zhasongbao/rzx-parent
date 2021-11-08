package com.rzx.common.utils.plutuspay;


import com.alibaba.fastjson.JSONObject;
import org.bouncycastle.util.encoders.Base64;

public class Test {
    public static void main(String[] arg) {

//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("outTransId", "NPCLL5mUSL71nZ91605234437091");
//
//        String param = AesEncryptionUtil.Encryption(jsonObject.toString());
//        System.out.println(param);
//
//        String str = Http.post("https://api.plutuspay.com/open/v2/jsPay", param);
//        System.out.println(str);
//
//        JSONObject json = JSON.parseObject(str);
//
//        String signature = json.getString("signature");
//        String content = json.getString("content");
//
//        //验签
//        Boolean verify = AesEncryptionUtil.verify256(content, Base64.decode(signature));
//        //验签成功
//        if (verify) {
//            //解密
//            byte[] bb = AesEncryptionUtil.decrypt(Base64.decode(content), AesEncryptionUtil.SECRET_KEY);
//            //服务器返回内容
//            System.out.println("业务数据:" + new String(bb));
//        }
    	queryOrder("e7m7QkdsBVxrn171605868513547");
    }

    public static JSONObject queryOrder(String outTradeId) {
		try {

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("outTradeId", outTradeId);
			jsonObject.put("sn", "YZD181315");
			String param = AesEncryptionUtil.encryption(jsonObject.toString());

			String str = Http.post("https://api.plutuspay.com/open/v2/query", param);
			System.out.println(str);

			JSONObject json = JSONObject.parseObject(str);
			if(!json.containsKey("signature")){
				return json;
			}
			String signature = json.getString("signature");
			String content = json.getString("content");

			Boolean verify = AesEncryptionUtil.verify256(content, Base64.decode(signature));
			// 验签成功
			if (verify) {
				// 解密
				byte[] bb = AesEncryptionUtil.decrypt(Base64.decode(content), AesEncryptionUtil.SECRET_KEY);
				// 服务器返回内容
				System.out.println("业务数据:" + new String(bb));
				return JSONObject.parseObject(new String(bb));
			}

			return null;
		} catch (Exception e) {
//			logger.error("云卓交易查询异常" + e.getMessage(), e);
			e.printStackTrace();
		}
		return null;
	}
}
