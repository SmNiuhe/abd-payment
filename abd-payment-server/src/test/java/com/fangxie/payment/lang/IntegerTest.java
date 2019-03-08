package com.fangxie.payment.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class IntegerTest {


    @Test
    public void whenEqual_isTrue() {

        Integer a = 2;
        Integer b = 2;
        Assert.assertTrue(a == b);
    }

    @Test
    public void whenEqual_isFalse() {

        Integer a = 3;
        Integer b = 2;
        Assert.assertFalse(a.equals(b));
    }

    @Test
    public void whenEqual_isNPE() {


        try {
            Integer a = null;
            Integer b = 2;
            Assert.assertFalse(a.equals(b));
        } catch (NullPointerException npe) {

            log.error("【Exception】:" + npe);
        }


    }


}
