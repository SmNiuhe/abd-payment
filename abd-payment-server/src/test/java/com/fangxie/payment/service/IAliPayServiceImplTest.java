package com.fangxie.payment.service;

import com.fangxie.payment.AbdPaymentServerApplicationTests;
import com.fangxie.payment.common.properties.AliPayTradeProperties;
import com.fangxie.payment.common.result.ResultBody;
import com.fangxie.payment.common.utils.KeyUtils;
import com.fangxie.payment.common.utils.ZxingUtils;
import com.fangxie.payment.pojo.dto.AlipayTradePrecreateDTO;
import com.fangxie.payment.pojo.dto.AlipayTradeQueryDTO;
import com.fangxie.payment.trade.model.ExtendParams;
import com.fangxie.payment.trade.model.GoodsDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class IAliPayServiceImplTest extends AbdPaymentServerApplicationTests {

    @Autowired
    private IAliPayService aliPayService;
    @Autowired
    private AliPayTradeProperties aliPayTradeProperties;

    @Test
    public void queryTradeResult() {

        System.out.println(aliPayTradeProperties);
        AlipayTradeQueryDTO build = AlipayTradeQueryDTO.builder()
                .outTradeNo("15517848565039261210")
                .build();
        ResultBody resultBody = aliPayService.queryTradeResult(build);
        log.info("【ResultBody】：{}", resultBody);
    }

    @Test
    public void tradeRefund() {
    }

    @Test
    public void whenTradePrecreate_isSuccess() {

        AlipayTradePrecreateDTO.AlipayTradePrecreateDTOBuilder builder = AlipayTradePrecreateDTO.builder()
                .outTradeNo(KeyUtils.getOutTradeNo())
                .subject("房协入会支付")
                .totalAmount("0.01")
                .undiscountableAmount("0")
                .sellerId("")
                .body("普通会员支付费用共0.01元")
                .operatorId("test_operator_id")
                .storeId("test_store_id")
                .timeoutExpress("120m")
                .notifyUrl("http://www.test-notify-url.com");

        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088100200300400500");
        builder.extendParams(extendParams);

        List<GoodsDetail> goodsDetailList = new ArrayList<>();
        GoodsDetail goods1 = GoodsDetail.newInstance("goods_id001", "xxx小面包", 1000, 1);
        GoodsDetail goods2 = GoodsDetail.newInstance("goods_id002", "xxx牙刷", 500, 2);
        goodsDetailList.add(goods1);
        goodsDetailList.add(goods2);
        builder.goodsDetailList(goodsDetailList);
        AlipayTradePrecreateDTO dto = builder.build();


        ResultBody resultBody = aliPayService.tradePrecreate(dto);
        log.info("【ResultBody】：{}", resultBody);
    }


    @Test
    public void whenTradePrecreate_properties_isSuccess() {

        AlipayTradePrecreateDTO alipayTradePrecreateDTO = new AlipayTradePrecreateDTO();
        BeanUtils.copyProperties(aliPayTradeProperties, alipayTradePrecreateDTO);


        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088100200300400500");
        alipayTradePrecreateDTO.setExtendParams(extendParams);

        List<GoodsDetail> goodsDetailList = new ArrayList<>();
        GoodsDetail goods1 = GoodsDetail.newInstance("goods_id001", "xxx小面包", 1000, 1);
        GoodsDetail goods2 = GoodsDetail.newInstance("goods_id002", "xxx牙刷", 500, 2);
        goodsDetailList.add(goods1);
        goodsDetailList.add(goods2);
        alipayTradePrecreateDTO.setGoodsDetailList(goodsDetailList);

        ResultBody resultBody = aliPayService.tradePrecreate(alipayTradePrecreateDTO);
        log.info("【ResultBody】：{}", resultBody);
    }


    @Test
    public void whenCreateQrcode_issuccess() {


//        String qrCode = "https://qr.alipay.com/bax03644zv96bwmejhbq408b";
//        String qrCode = "weixin://wxpay/bizpayurl?pr=OjEw7QR";
//        String qrCode = "weixin://wxpay/bizpayurl?pr=8ugp8lu";
        String qrCode = "weixin://wxpay/bizpayurl?pr=lNlqPyx";

        String filePath = String.format("D:\\qr-%s.png",
                KeyUtils.getOutTradeNo());
        log.info("【二维码地址】:" + filePath);
        ZxingUtils.getQRCodeImge(qrCode, 256, filePath);

    }
}