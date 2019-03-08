package com.fangxie.payment.service.impl;

import com.fangxie.payment.common.utils.DateTimeUtils;
import com.fangxie.payment.common.utils.KeyUtils;
import com.fangxie.payment.pojo.entity.TPaymentBill;
import com.fangxie.payment.service.IPaymentBillService;
import org.springframework.stereotype.Service;

/**
 * 支付账单(table server)
 */
@Service
public class PaymentBillServiceImpl extends ServiceImpl<TPaymentBill> implements IPaymentBillService {

    @Override
    public boolean save(TPaymentBill entity) {

        // 商户单号
        entity.setMerchantTradeNo(KeyUtils.getOutTradeNo());
        entity.setCreateTime(DateTimeUtils.getNowDateTime());
        // TODO 当前登录用户
        entity.setCreateBy("smniuhe");
        return super.save(entity);
    }
}
