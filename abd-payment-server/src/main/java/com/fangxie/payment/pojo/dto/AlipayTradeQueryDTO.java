package com.fangxie.payment.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 支付宝订单-查询
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlipayTradeQueryDTO {

    /**
     * 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
     * 需保证商户系统端不能重复，建议通过数据库sequence生成，
     */
    private String outTradeNo;
}
