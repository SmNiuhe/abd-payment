package com.fangxie.payment.java8;

import com.fangxie.payment.common.utils.DateTimeUtils;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeTest {


    /**
     * 日期格式化测试
     */
    @Test
    public void DateTimeFormatterTest() {

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endStart = startTime.plusHours(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String value1 = startTime.format(formatter);
        String value2 = endStart.format(formatter);
        System.out.println(String.format("formatter before %s, formatter after %s", value1, value2));
    }

    @Test
    public void wx_timeTest() {

        String timeStart = DateTimeUtils.wx_time_start();

        String timeExpire = DateTimeUtils.wx_time_expire();
        System.out.println(timeStart);
        System.out.println(timeExpire);
    }
}
