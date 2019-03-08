package com.fangxie.payment.apache;

import com.fangxie.utils.UUIDUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

public class lang3Test {


    @Test
    public void RandomStringUtilsTest() {

        String random = RandomStringUtils.random(32);
        System.out.println("nonce_str:" + random);
    }


    @Test
    public void UUID_randomUUID_Test() {
/*
        String value = UUID.randomUUID().toString();
        System.out.println(value);*/
        System.out.println(UUIDUtils.getUUID());

    }



}

