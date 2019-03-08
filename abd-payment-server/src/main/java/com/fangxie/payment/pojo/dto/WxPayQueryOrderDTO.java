package com.fangxie.payment.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一下单文档接口：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WxPayQueryOrderDTO {

    /**
     * <pre>
     * 字段名：接口版本号.
     * 变量名：version
     * 是否必填：单品优惠必填
     * 类型：String(32)
     * 示例值：1.0
     * 描述：单品优惠新增字段，区分原接口，固定填写1.0，
     * 查单接口上传version后查询结果才返回单品信息，不上传不返回单品信息。
     * 更多信息，详见文档：https://pay.weixin.qq.com/wiki/doc/api/danpin.php?chapter=9_102&index=2
     * </pre>
     */
    private String version;

    /**
     * <pre>
     * 微信订单号
     * transaction_id
     * 二选一
     * String(32)
     * 1009660380201506130728806387
     * 微信的订单号，优先使用
     * </pre>
     */
    private String transactionId;

    /**
     * <pre>
     * 商户订单号
     * out_trade_no
     * 二选一
     * String(32)
     * 20150806125346
     * 商户系统内部的订单号，当没提供transaction_id时需要传这个。
     * </pre>
     */
    private String outTradeNo;


}
