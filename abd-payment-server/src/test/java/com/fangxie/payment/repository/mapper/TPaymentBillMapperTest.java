package com.fangxie.payment.repository.mapper;

import com.fangxie.payment.AbdPaymentServerApplicationTests;
import com.fangxie.payment.common.utils.DateTimeUtils;
import com.fangxie.payment.common.utils.KeyUtils;
import com.fangxie.payment.enums.PaymentModeEnum;
import com.fangxie.payment.enums.PaymentStatusEnum;
import com.fangxie.payment.pojo.entity.TPaymentBill;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TPaymentBillMapperTest extends AbdPaymentServerApplicationTests {

    @Autowired
    private TPaymentBillMapper paymentBillMapper;

    @Test
    public void whenInsert_isSuccess() {

        TPaymentBill build = TPaymentBill.builder()
                .merchantTradeNo(KeyUtils.getOutTradeNo())
                .merchantName("洪图软件有限公司")
                .totalAmount(12800L)
                .paymentMode(PaymentModeEnum.Alipay)
                .paymentStatus(PaymentStatusEnum.NOTPAY)
                .discountableAmount(Long.valueOf("0"))
                .createTime(DateTimeUtils.getNowDateTime())
                .createBy("smniuhe")
                .build();
        int insert = paymentBillMapper.insert(build);
        Assert.assertTrue(insert > 0);
    }


    @Test
    public void whenUpdate_isSuccess() {


        TPaymentBill build = TPaymentBill.builder()
                .merchantTradeNo("15517585843019353610")
                .paymentStatus(PaymentStatusEnum.SUCCESS)
                .paymentMode(PaymentModeEnum.Union).build();
        int i = paymentBillMapper.updateByPrimaryKey(build);
        Assert.assertTrue(i > 0);
    }

    @Test
    public void whenInsertList_isSQLException() {

        // TODO 没有自增主键的原因，通用 mapper 不支持批量插入，代替： mapper.xml 自定义批量插入
        List<TPaymentBill> list = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            TPaymentBill build = TPaymentBill.builder()
                    .merchantTradeNo(KeyUtils.getOutTradeNo())
                    .merchantName("洪图软件有限公司1")
                    .totalAmount(12800L)
                    .paymentMode(PaymentModeEnum.Alipay)
                    .paymentStatus(PaymentStatusEnum.NOTPAY)
                    .discountableAmount(Long.valueOf("0"))
                    .createTime(DateTimeUtils.getNowDateTime())
                    .createBy("smniuhe")
                    .build();
            list.add(build);
        }
        try {
            paymentBillMapper.insertList(list);
        } catch (Exception e) {
            log.info("【Exception】:" + e.getMessage());
        }
    }
}