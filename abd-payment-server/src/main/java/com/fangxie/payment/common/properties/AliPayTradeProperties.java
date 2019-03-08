package com.fangxie.payment.common.properties;

import com.fangxie.payment.common.utils.KeyUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties
@PropertySource("classpath:properties/zfb-trade.properties")
@Data
public class AliPayTradeProperties {

    /**
     * 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
     * 需保证商户系统端不能重复，建议通过数据库sequence生成，
     */
    private String outTradeNo = KeyUtils.getOutTradeNo();

    /**
     * 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
     * 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
     */
    private String sellerId;

    /**
     * 订单总金额，整形，此处单位为元，精确到小数点后2位，不能超过1亿元
     * 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
     */
    private String totalAmount;

    /**
     * 订单可打折金额，此处单位为元，精确到小数点后2位
     * 可以配合商家平台配置折扣活动，如果订单部分商品参与打折，可以将部分商品总价填写至此字段，默认全部商品可打折
     * 如果该值未传入,但传入了【订单总金额】,【不可打折金额】 则该值默认为【订单总金额】- 【不可打折金额】
     */
    private String discountableAmount;

    /**
     * 订单不可打折金额，此处单位为元，精确到小数点后2位，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
     * 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
     */
    //
    private String undiscountableAmount;

    /**
     * 订单标题，粗略描述用户的支付目的。如“喜士多（浦东店）消费”
     */
    private String subject;

    /**
     * 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
     */
    @Value("${body}")
    private String body;

    /**
     * 商户操作员编号，添加此参数可以为商户操作员做销售统计
     */
    private String operatorId;

    /**
     * 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
     */
    private String storeId;

    /**
     * 支付宝商家平台中配置的商户门店号，详询支付宝技术支持
     */
    private String alipayStoreId;

    /**
     * 商户机具终端编号，当以机具方式接入支付宝时必传，详询支付宝技术支持
     */
    private String terminalId;


    /**
     * (推荐使用，相对时间) 支付超时时间，5m 5分钟
     */
    private String timeoutExpress;


    /**
     * 支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
     */
    private String notifyUrl;


    /****************************退款相关（precreate,refund 有部分属性重合）**********************************/

    /**
     * (必填) 退款金额，该金额必须小于等于订单的支付金额，单位为元
     */
    private String refundAmount;


    /**
     * (可选，需要支持重复退货时必填) 商户退款请求号，相同支付宝交易号下的不同退款请求号对应同一笔交易的不同退款申请，
     * 对于相同支付宝交易号下多笔相同商户退款请求号的退款交易，支付宝只会进行一次退款
     */
    private String outRequestNo;


    /**
     * (必填) 退款原因，可以说明用户退款原因，方便为商家后台提供统计
     */
    private String refundReason;

}
