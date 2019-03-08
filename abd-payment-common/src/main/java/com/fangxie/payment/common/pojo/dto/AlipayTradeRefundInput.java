package com.fangxie.payment.common.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 支付宝订单-退单
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlipayTradeRefundInput {

    /**
     * 支付宝交易号，当面付支付成功后支付宝会返回给商户系统。通过此支付宝交易号进行交易退款
     */
    private String tradeNo;

    /**
     * (推荐) 外部订单号，可通过外部订单号申请退款，推荐使用
     */
    private String outTradeNo;

    /**
     * 退款金额，该金额必须小于等于订单的支付金额，此处单位为元，精确到小数点后2位
     */
    private String refundAmount;

    /**
     * (可选，需要支持重复退货时必填) 商户退款请求号，相同支付宝交易号下的不同退款请求号对应同一笔交易的不同退款申请，
     * 对于相同支付宝交易号下多笔相同商户退款请求号的退款交易，支付宝只会进行一次退款
     */
    private String outRequestNo;

    /**
     * (必填) 退款原因，可以说明用户退款原因，方便为商家后台提供统计
     */
    private String refundReason;

    /**
     * (必填) 商户门店编号，退款情况下可以为商家后台提供退款权限判定和统计等作用，详询支付宝技术支持
     */
    private String storeId;

    /**
     * 支付宝商家平台中配置的商户门店号，详询支付宝技术支持
     */
    private String alipayStoreId;

    /**
     * 商户机具终端编号，当以机具方式接入支付宝时必传，详询支付宝技术支持
     */
    private String terminalId;

}
