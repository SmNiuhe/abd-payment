package com.fangxie.payment.enums;

import lombok.Getter;

import java.util.Objects;

/**
 * 支付状态
 */
@Getter
public enum PaymentStatusEnum {


    NOTPAY(1, "未支付"),

    SUCCESS(2, "支付成功"),

    REFUND(3, "已退款"),

    CLOSED(4, "已关单"),

    PAYERROR(5, "支付错误"),

    UNKNOWN(99, "未知"),

    ;


    private Integer code;

    private String message;

    PaymentStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public static PaymentStatusEnum getByCode(Integer code) {

        PaymentStatusEnum[] values = PaymentStatusEnum.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].code == code) {
                return values[i];
            }
        }
        return UNKNOWN;
    }

    public static String getMessageByCode(Integer code) {
        if (Objects.isNull(code)) {
            return UNKNOWN.message;
        }
        return getByCode(code).getMessage();
    }

}
