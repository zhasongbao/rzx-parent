package com.rzx.project.service.impl;

import cn.hutool.core.convert.Convert;
import com.rzx.common.configuration.PayConfig;
import com.rzx.common.core.domain.entity.SysDictData;
import com.rzx.common.enums.PayWayEnum;
import com.rzx.common.exception.BankBizException;
import com.rzx.common.utils.StringUtils;
import com.rzx.common.utils.spring.SpringUtils;
import com.rzx.project.facade.*;
import com.rzx.project.facade.dto.*;
import com.rzx.project.facade.vo.*;
import com.rzx.project.factory.PayBizSourceFactory;
import com.rzx.project.model.domain.PayChannelCode;
import com.rzx.project.service.IPayBizService;
import com.rzx.project.service.IPayChannelCodeService;
import com.rzx.project.service.ISystemDictTypeService;
import com.rzx.project.strategy.PayBizSourceStrategy;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 支付业务类
 *
 * @author zy
 * @date 2021/9/30 14:56
 */
@Service
public class PayBizServiceImpl implements IPayBizService {
    @Autowired
    private IPayChannelCodeService payChannelCodeService;
    @Autowired
    private PayConfig payConfig;
    @Autowired
    private PayBizSourceFactory payBizSourceFactory;
    @Autowired
    private ISystemDictTypeService sysDictTypeService;
//    @Autowired
//    private IBankOrganizeService bankOrganizeService;

    /**
     * 无卡支付、支持小程序支付、扫码、h5支付
     *
     * @param param 参数
     * @return
     */
    @Override
    public CnpPayResult cnpPay(CnpPayPreParam param) {
        //获取支付渠道编码
        String payChannelCode = StringUtils.isEmpty(param.getPayChannelCode()) ? getPayWay() : param.getPayChannelCode();
        param.setPayChannelCode(payChannelCode);
        PayChannelCode channelCode = payChannelCodeService.getDataByChannelCode(payChannelCode);
        if (Objects.isNull(channelCode)) {
            throw BankBizException.BANK_CHANNEL_CODE_IS_NULL;
        }
        CnpPayFacade bean = SpringUtils.getBean(payChannelCode);
        return bean.cnpPay(param);
    }

    /**
     * 回调验签方法
     *
     * @param parameterMap   回调支付map
     * @param payChannelCode 支付渠道编码
     * @return
     */
    @Override
    public NotifyVerifyResult notifyVerify(Map<String, Object> parameterMap, String payChannelCode) {
        NotifyVerifyParam notifyVerifyParam = new NotifyVerifyParam();
        notifyVerifyParam.setParamMap(parameterMap);
        notifyVerifyParam.setPayChannelCode(payChannelCode);
        PayChannelCode channelCode = payChannelCodeService.getDataByChannelCode(payChannelCode);
        if (Objects.isNull(channelCode)) {
            throw BankBizException.BANK_CHANNEL_CODE_IS_NULL;
        }
        CnpPayVerifyFacade bean = SpringUtils.getBean(payChannelCode);
        return bean.cnpPayVerify(notifyVerifyParam);
    }

    /**
     * 订单查询
     *
     * @param orderQueryParam 支付结果对象
     * @return
     */
    @Override
    public OrderQueryResult orderQuery(OrderQueryParam orderQueryParam) {
        String payChannelCode = orderQueryParam.getPayChannelCode();
        PayChannelCode channel = payChannelCodeService.getDataByChannelCode(payChannelCode);
        if (Objects.isNull(channel)) {
            throw BankBizException.BANK_CHANNEL_CODE_IS_NULL;
        }
        OrderQueryFacade bean = SpringUtils.getBean(payChannelCode);
        return bean.orderQuery(orderQueryParam);
    }

    /**
     * 支付完成后业务处理
     *
     * @param notifyVerifyResult 支付回调返回结果
     * @param bankReturnMsg      第三方返回的map
     * @param bizSourceCode      支付来源编码 BizSourceCodeEnum枚举类
     * @return
     */
    @Override
    public boolean completePay(NotifyVerifyResult notifyVerifyResult, Map<String, Object> bankReturnMsg, String bizSourceCode) {
        PayBizSourceStrategy handler = payBizSourceFactory.getHandler(bizSourceCode);
        return handler.completePay(notifyVerifyResult, bankReturnMsg);
    }

//    /**
//     * 银行卡快捷支付
//     *
//     * @param quickPayParam
//     * @return
//     */
//    @Override
//    public QuickPayResult quickPay(QuickPayParam quickPayParam) {
//        String payChannelCode = StringUtils.EMPTY;
//        String bankCode = quickPayParam.getBankCode();
//        String bankAccountType = quickPayParam.getBankAccountType();
//        if ("02".equals(bankAccountType)) {
//            //如果信用卡走工商银行
//            payChannelCode = BankChannelCodeEnum.ICBC_QUICK_PAY.getCode();
//        } else if ("01".equals(bankAccountType)) {
//            payChannelCode = getBankChannelCode(bankCode);
//        }
//        quickPayParam.setPayChannelCode(payChannelCode);
//        QuickPayFacade bean = SpringUtils.getBean(payChannelCode);
//        return bean.quickPay(quickPayParam);
//    }

    /**
     * 无卡退款
     *
     * @param param
     * @return
     */
    @Override
    public CnpRefundResult refund(CnpRefundParam param) {
        String payChannelCode = param.getPayChannelCode();
        PayChannelCode channelCode = payChannelCodeService.getDataByChannelCode(payChannelCode);
        if (Objects.isNull(channelCode)) {
            throw BankBizException.BANK_CHANNEL_CODE_IS_NULL;
        }
        CnpRefundFacade facade = SpringUtils.getBean(payChannelCode);
        return facade.refund(param);
    }

    /**
     * 退款查询
     *
     * @param param
     * @return
     */
    @Override
    public RefundQueryResult refundQuery(RefundQueryParam param) {
        String payChannelCode = param.getPayChannelCode();
        PayChannelCode channel = payChannelCodeService.getDataByChannelCode(payChannelCode);
        if (Objects.isNull(channel)) {
            throw BankBizException.BANK_CHANNEL_CODE_IS_NULL;
        }
        RefundQueryFacade facade = SpringUtils.getBean(payChannelCode);
        return facade.refundQuery(param);
    }

    /**
     * 获取支付方式
     *
     * @return
     */
    private String getPayWay() {
        List<SysDictData> dicList = sysDictTypeService.selectDictDataByType("pay_way_config");
        if (CollectionUtils.isEmpty(dicList)) {
            return payConfig.getPayway();
        }
        String payWay = StringUtils.EMPTY;
        int day = LocalDateTime.now().getDayOfWeek().getValue();
        for (SysDictData dic : dicList) {
            String dayStr = dic.getRemark();
            for (String str : dayStr.split(",")) {
                if (str.equals(Convert.toStr(day))) {
                    payWay = dic.getDictValue();
                    break;
                }
            }
        }
        PayWayEnum payWayEnum = PayWayEnum.getPayWayEnumByCode(payWay);
        if (Objects.isNull(payWayEnum)) {
            return PayWayEnum.YUN_ZHUO_PAY.getPayChannelCode();
        }
        return payWayEnum.getPayChannelCode();
    }

//    /**
//     * 获取银行渠道编码
//     *
//     * @param bankCode 银行渠道编码
//     * @return
//     */
//    private String getBankChannelCode(String bankCode) {
//        LambdaQueryWrapper<BankOrganize> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(BankOrganize::getBankCode, bankCode);
//        queryWrapper.last(Constants.SQL_LIMIT_1);
//        List<BankOrganize> list = bankOrganizeService.list(queryWrapper);
//        if (org.apache.commons.collections4.CollectionUtils.isEmpty(list)) {
//            throw BankBizException.BANK_CHANNEL_CODE_IS_NULL;
//        }
//        return list.get(0).getBankChannelCode();
//    }
}
