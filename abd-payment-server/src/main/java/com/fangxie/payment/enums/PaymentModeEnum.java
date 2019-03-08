package com.fangxie.payment.enums;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum PaymentModeEnum {

    WeChat(1, "微信"),

    Alipay(2, "支付宝"),

    Union(3, "银联"),

    UNKNOWN(99, "未知"),

    ;


    private Integer code;

    private String message;

    PaymentModeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public static PaymentModeEnum getByCode(Integer code) {

        if (Objects.isNull(code)) {
            code = 0;
        }

        PaymentModeEnum[] values = PaymentModeEnum.values();
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
