package com.fangxie.payment.enums;

import com.fangxie.payment.common.result.ResultInfoInterface;
import lombok.Getter;

@Getter
public enum WxpayOrderStatusEnum implements ResultInfoInterface {

    /**
     * 订单预支付
     */
    PRECREATE_SUCCESS(400_101, "微信预下单成功"),
    PRECREATE_FAILED(400_102, "系统异常，预下单状态未知!!!!!!"),


    /**
     * 订单查询
     */
    QUERY_SUCCESS(400_111, "微信订单查询成功"),


    /**
     * 订单退款
     */
    REFUND_SUCCESS(400_121, "微信订单退款中"),


    /**
     * 订单退款查询
     */
    REFUND_QUERY_SUCCESS(400_131, "微信订单退款查询"),
    REFUND_QUERY_FAILED(400_132, "微信订单退款查询失败"),
    ;

    private int code;
    private String message;

    WxpayOrderStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
