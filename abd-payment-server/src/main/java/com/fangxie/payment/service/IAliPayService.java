package com.fangxie.payment.service;

import com.fangxie.payment.common.result.ResultBody;
import com.fangxie.payment.pojo.dto.AlipayTradePrecreateDTO;
import com.fangxie.payment.pojo.dto.AlipayTradeQueryDTO;
import com.fangxie.payment.pojo.dto.AlipayTradeRefundDTO;
import com.fangxie.payment.trade.model.builder.AlipayTradePayRequestBuilder;
import com.fangxie.payment.trade.model.result.AlipayF2FPayResult;

/**
 * 支付宝支付服务
 */
public interface IAliPayService {


    /**
     * 当面付2.0流程支付
     *
     * @param builder
     * @return
     */
    AlipayF2FPayResult tradePay(AlipayTradePayRequestBuilder builder);

    /**
     * 当面付2.0消费查询
     *
     * @param alipayTradeQueryDTO
     * @return
     */
    ResultBody queryTradeResult(AlipayTradeQueryDTO alipayTradeQueryDTO);

    /**
     * 当面付2.0消费退款
     *
     * @param alipayTradeRefundDTO
     * @return
     */
    ResultBody tradeRefund(AlipayTradeRefundDTO alipayTradeRefundDTO);

    /**
     * 当面付2.0预下单(生成二维码)
     *
     * @param alipayTradePrecreateDTO
     * @return
     */
    ResultBody tradePrecreate(AlipayTradePrecreateDTO alipayTradePrecreateDTO);
}
