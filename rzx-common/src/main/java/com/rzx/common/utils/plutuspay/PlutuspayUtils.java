package com.rzx.common.utils.plutuspay;

import net.sf.json.JSONObject;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlutuspayUtils {
	private static final Logger logger = LoggerFactory.getLogger(PlutuspayUtils.class);

	public static void main(String[] arg) {
		queryOrder("20200924101456256987721","YZD181315");
	}

	public static JSONObject queryOrder(String outTradeId,String sn) {
		try {

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("outTradeId", outTradeId);
			jsonObject.put("sn", sn);
			String param = AesEncryptionUtil.encryption(jsonObject.toString());

			String str = Http.post("https://api.plutuspay.com/open/v2/query", param);
			System.out.println(str);

			JSONObject json = JSONObject.fromObject(str);
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
				return JSONObject.fromObject(new String(bb));
			}

			return null;
		} catch (Exception e) {
			logger.error("云卓交易查询异常" + e.getMessage(), e);
		}
		return null;
	}

}
