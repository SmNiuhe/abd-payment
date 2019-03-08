package com.fangxie.payment.common.utils;

import org.junit.Test;

import java.util.Date;

public class DateTimeUtilsTest {

    @Test
    public void getNowDateTime() {

        Date nowDateTime = DateTimeUtils.getNowDateTime();
        System.out.println(nowDateTime);
    }

    @Test
    public void wx_time_start() {
    }

    @Test
    public void wx_time_expire() {
    }
}