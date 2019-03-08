package com.fangxie.payment.common.config;

import com.fangxie.payment.AbdPaymentServerApplicationTests;
import com.fangxie.payment.common.properties.FileProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class FilePropertiesTest extends AbdPaymentServerApplicationTests {

    @Autowired
    private FileProperties fileProperties;

    @Test
    public void getPath() {

        String baseDir = fileProperties.getBaseDir();
        String temp = fileProperties.getTemp();
        log.info("【baseDir】:" + baseDir);
        log.info("【temp】:" + temp);
    }

}