package com.fangxie.payment.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 支付宝订单-预支付
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlipayTradePrecreateVO {

    /**
     * 二维码
     */
    private String qrCode;

    /**
     * 商户单号
     */
    private String merchantTradeNo;

}
