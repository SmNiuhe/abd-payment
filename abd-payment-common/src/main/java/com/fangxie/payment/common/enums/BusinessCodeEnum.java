package com.fangxie.payment.common.enums;

import lombok.Getter;

/**
 * 业务代码-生成主键组成部分，时间戳 + 随机数 + 业务代码
 */
@Getter
public enum BusinessCodeEnum {

    /**
     * 支付-订单号
     */
    PAYMENT_TRADE_NO("10", "支付-订单号"),

    PAYMENT_PRODUCT_ID("11", "支付-商户号"),

    PAYMENT_TRADE_OutRefundNO("12", "支付-商户退款单号"),

    ;


    private String code;
    private String message;


    BusinessCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
