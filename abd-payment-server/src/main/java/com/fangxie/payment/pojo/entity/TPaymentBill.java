package com.fangxie.payment.pojo.entity;

import com.fangxie.payment.enums.PaymentModeEnum;
import com.fangxie.payment.enums.PaymentStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * 支付-账单
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "t_payment_bill")
public class TPaymentBill {
    /**
     * 商户单号
     */
    @Id
    @Column(name = "merchant_trade_no")
    private String merchantTradeNo;

    /**
     * 商户名称
     */
    @Column(name = "merchant_name")
    private String merchantName;

    /**
     * 支付金额（单位：分）
     */
    @Column(name = "total_amount")
    private Long totalAmount;

    /**
     * 折扣金额
     */
    @Column(name = "discountable_amount")
    private Long discountableAmount;

    /**
     * 支付状态（未支付，支付成功，支付失败，已关单，支付错误）
     */
    @Column(name = "payment_status")
    private PaymentStatusEnum paymentStatus;

    /**
     * 支付方式（1 微信，2 支付宝）
     */
    @Column(name = "payment_mode")
    private PaymentModeEnum paymentMode;

    /**
     * 支付时间
     */
    @Column(name = "payment_time")
    private Date paymentTime;

    /**
     * 订单待支付-初始时间
     */
    @Column(name = "time_start")
    private Date timeStart;

    /**
     * 订单待支付-过期时间
     */
    @Column(name = "time_expire")
    private Date timeExpire;

    /**
     * 交易单号
     */
    @Column(name = "trade_no")
    private String tradeNo;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 修改人
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 是否删除
     */
    @Column(name = "is_del")
    private String isDel;

}