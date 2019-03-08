package com.fangxie.payment.service.impl;

import com.fangxie.payment.AbdPaymentServerApplicationTests;
import com.fangxie.payment.pojo.dto.WxPayUnifiedOrderDTO;
import com.fangxie.payment.service.IWxPayService;
import com.github.binarywang.wxpay.exception.WxPayException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class WxPayServiceImplTest extends AbdPaymentServerApplicationTests {

    @Autowired
    private IWxPayService wxPayService;

    @Test
    public void createOrder() {

        WxPayUnifiedOrderDTO wxPayUnifiedOrderDTO = new WxPayUnifiedOrderDTO();
        try {
            Object order = wxPayService.createOrder(wxPayUnifiedOrderDTO);
            log.error("【createOrderResult】:{}", order);
        } catch (WxPayException e) {
            e.printStackTrace();
            log.error("【WxPayException】：{}", e.getMessage());
        }

    }
}