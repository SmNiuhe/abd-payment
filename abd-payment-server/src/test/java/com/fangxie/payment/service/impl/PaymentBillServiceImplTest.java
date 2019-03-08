package com.fangxie.payment.service.impl;

import com.fangxie.payment.AbdPaymentServerApplicationTests;
import com.fangxie.payment.pojo.entity.TPaymentBill;
import com.fangxie.payment.service.IPaymentBillService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentBillServiceImplTest extends AbdPaymentServerApplicationTests {

    @Autowired
    private IPaymentBillService paymentBillService;

    @Test
    public void whenSave_isSuccess() {

        TPaymentBill build = TPaymentBill.builder()
                .merchantName("北京同方科技")
                .build();
        boolean isSuccess = paymentBillService.save(build);
        Assert.assertTrue(isSuccess);
    }
    @Test
    public void whenGet_isSuccess() {

        TPaymentBill bill = paymentBillService.getById("15517666905619725410");
        Assert.assertNotNull(bill);
    }
}