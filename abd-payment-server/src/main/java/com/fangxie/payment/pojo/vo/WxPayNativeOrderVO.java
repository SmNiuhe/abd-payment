package com.fangxie.payment.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WxPayNativeOrderVO {

    /**
     * 商户订单
     */
    private String outTradeNo;

    /**
     * 二维码地址
     */
    private String codeUrl;
}
