package com.fangxie.payment.enums;

import com.fangxie.payment.common.result.ResultInfoInterface;
import lombok.Getter;

@Getter
public enum AlipayTradeStausEnum implements ResultInfoInterface {

    /**
     * 订单预支付
     */
    PRECREATE_SUCCESS(400_001, "支付宝预下单成功"),
    PRECREATE_FAILED(400_002, "支付宝预下单失败!!!"),
    PRECREATE_UNKNOWN(400_003, "系统异常，预下单状态未知!!!"),

    /**
     * 订单查询
     */
    QUERY_SUCCESS(400_011, "查询返回该订单支付成功"),
    QUERY_FAILED(400_012, "查询返回该订单支付失败或被关闭!!!"),
    QUERY_UNKNOWN(400_013, "系统异常，订单支付状态未知!!!"),


    /**
     * 订单退款
     */
    REFUND_SUCCESS(400_021, "支付宝退款成功"),
    REFUND_FAILED(400_022, "支付宝退款失败!!!"),
    REFUND_UNKNOWN(400_023, "系统异常，订单退款状态未知!!!"),


    /**
     * 通用异常
     */
    ERROR(400_099, "不支持的交易状态，交易返回异常!!!"),
    ;

    private int code;
    private String message;

    AlipayTradeStausEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
