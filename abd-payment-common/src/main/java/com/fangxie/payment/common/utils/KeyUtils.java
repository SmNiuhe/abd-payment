package com.fangxie.payment.common.utils;

import com.fangxie.payment.common.enums.BusinessCodeEnum;

public class KeyUtils {

    /**
     * 生成订单号
     * 时间戳13 + 随机数5 + 业务代码编号2
     *
     * @return
     */
    public static synchronized String getOutTradeNo() {

        long random = (long) (Math.random() * 10_000L + 90_000L);
        String outTradeNo = System.currentTimeMillis()
                + Long.toString(random)
                + BusinessCodeEnum.PAYMENT_TRADE_NO.getCode();
        return outTradeNo;
    }


    /**
     * 商户退款单号
     *
     * @return
     */
    public static synchronized String getOutRefundNo() {

        long random = (long) (Math.random() * 10_000L + 90_000L);
        String outTradeNo = System.currentTimeMillis()
                + Long.toString(random)
                + BusinessCodeEnum.PAYMENT_TRADE_OutRefundNO.getCode();
        return outTradeNo;
    }


    /**
     * 生成商品id
     *
     * @return
     */
    public static synchronized String getProductId() {

        long random = (long) (Math.random() * 10_000L + 90_000L);
        String outTradeNo = System.currentTimeMillis()
                + Long.toString(random)
                + BusinessCodeEnum.PAYMENT_PRODUCT_ID.getCode();
        return outTradeNo;
    }


}
