package com.rzx.framework.remote.hcb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rzx.common.exception.CustomException;
import com.rzx.common.utils.StringUtils;
import com.rzx.common.utils.http.HttpUtils;
import com.rzx.framework.remote.hcb.entity.TruckUserWallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 货车宝用户钱包表
 *
 * @author zy
 * @date 2021/6/4 15:07
 */
@Component
public class RemoteTruckUserWallet {

    private static final Logger log = LoggerFactory.getLogger(RemoteTruckUserWallet.class);

    private final static String GET_TRUCK_USER_WALLET_URL = "http://localhost:8080/hcbApi/truckUserWallet/selectTruckUserWalletByPhoneNumber";

    /**
     * 查询钱包
     *
     * @param phoneNumber 手机号码
     */
    public TruckUserWallet getTruckUserWalletByPhoneNumber(String phoneNumber) {

        String sb = "phoneNumber=" + phoneNumber;
        String sendGet = HttpUtils.sendGet(GET_TRUCK_USER_WALLET_URL, sb);
        if (StringUtils.isEmpty(sendGet)) {
            log.error("查询货车宝钱包异常：{}", sendGet);
            throw new CustomException("请求网络异常!");
        }
        JSONObject object = JSON.parseObject(sendGet);
        if (Objects.nonNull(object) && Objects.nonNull(object.getJSONObject("data"))) {
            JSONObject data = object.getJSONObject("data");
            String truckUserWalletId = data.getString("truckUserWalletId");
            String newIdCode = data.getString("idCode");
            String name = data.getString("name");
            String phone = data.getString("phone");

            return TruckUserWallet.builder()
                    .truckUserWalletId(truckUserWalletId)
                    .idCode(newIdCode)
                    .name(name)
                    .phone(phone)
                    .build();
        }
        return null;
    }
}
