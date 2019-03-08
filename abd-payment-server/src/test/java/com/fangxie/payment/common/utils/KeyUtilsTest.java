package com.fangxie.payment.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class KeyUtilsTest {

    @Test
    public void getOutTradeNo() {

        for (int i = 0; i < 100; i++) {
            log.info("【订单号】：" + KeyUtils.getOutTradeNo());
        }

    }
}