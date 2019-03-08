package com.fangxie.payment.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class ZxingUtilsTest {

    @Test
    public void getQRCodeImge() {
    }

    @Test
    public void getQRCodeImge1() {
    }

    public static void main(String[] args) {

        String qrCode = "";
        String filePath = String.format("D:\\qr-%s.png",
                KeyUtils.getOutTradeNo());
        log.info("filePath:" + filePath);
        ZxingUtils.getQRCodeImge(qrCode, 256, filePath);
    }
}