package com.fangxie.payment.service;

import com.fangxie.payment.common.pojo.dto.WxPayRefundQueryInput;
import com.fangxie.payment.common.result.ResultBody;
import com.fangxie.payment.pojo.dto.WxPayQueryOrderDTO;
import com.fangxie.payment.pojo.dto.WxPayRefundDTO;
import com.fangxie.payment.pojo.dto.WxPayRefundQueryDTO;
import com.fangxie.payment.pojo.dto.WxPayUnifiedOrderDTO;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * 微信支付服务
 */
public interface IWxPayService {

    /**
     * 调用统一下单接口，并组装生成支付所需参数对象.
     *
     * @param wxPayUnifiedOrderDTO 统一下单请求参数
     * @return 返回 {@link com.github.binarywang.wxpay.bean.order}包下的类对象
     */
    ResultBody createOrder(WxPayUnifiedOrderDTO wxPayUnifiedOrderDTO) throws WxPayException;


    /**
     * <pre>
     * 查询订单(详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_2)
     * 该接口提供所有微信支付订单的查询，商户可以通过查询订单接口主动查询订单状态，完成下一步的业务逻辑。
     * 需要调用查询接口的情况：
     * ◆ 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知；
     * ◆ 调用支付接口后，返回系统错误或未知交易状态情况；
     * ◆ 调用被扫支付API，返回USERPAYING的状态；
     * ◆ 调用关单或撤销接口API之前，需确认支付状态；
     * 接口地址：https://api.mch.weixin.qq.com/pay/orderquery
     * </pre>
     *
     * @param wxPayQueryOrderDTO
     */
    ResultBody queryOrder(WxPayQueryOrderDTO wxPayQueryOrderDTO) throws WxPayException;


    /**
     * <pre>
     * 微信支付-申请退款
     * 详见 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
     * 接口链接：https://api.mch.weixin.qq.com/secapi/pay/refund
     * </pre>
     *
     * @param wxPayRefundDTO 请求对象
     * @return 退款操作结果
     */
    ResultBody refund(WxPayRefundDTO wxPayRefundDTO) throws WxPayException;


    /**
     * <pre>
     * 微信支付-查询退款
     * 应用场景：
     *  提交退款申请后，通过调用该接口查询退款状态。退款有一定延时，用零钱支付的退款20分钟内到账，
     *  银行卡支付的退款3个工作日后重新查询退款状态。
     * 详见 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_5
     * 接口链接：https://api.mch.weixin.qq.com/pay/refundquery
     * </pre>
     * 以下四个参数四选一
     *
     * @param wxPayRefundQueryDTO
     * @return 退款信息
     */
    ResultBody refundQuery(WxPayRefundQueryDTO wxPayRefundQueryDTO);
}
