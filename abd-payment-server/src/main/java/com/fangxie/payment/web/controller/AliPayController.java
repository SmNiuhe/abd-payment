package com.fangxie.payment.web.controller;

import com.fangxie.payment.common.pojo.dto.AlipayTradePrecreateInput;
import com.fangxie.payment.common.result.ResultBody;
import com.fangxie.payment.pojo.dto.AlipayTradePrecreateDTO;
import com.fangxie.payment.pojo.dto.AlipayTradeQueryDTO;
import com.fangxie.payment.pojo.dto.AlipayTradeRefundDTO;
import com.fangxie.payment.service.IAliPayService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ali-pay")
public class AliPayController {

    @Autowired
    private IAliPayService aliPayService;

    /**
     * 支付宝支付开通-认证回调
     */
    @RequestMapping("/authRedirect")
    public String authRedirect() {
        return "HT authRedirect";
    }

    @PostMapping("/trade/pre-create")
    public ResultBody tradePrecreate(@RequestBody AlipayTradePrecreateInput alipayTradeQueryInput) {

        AlipayTradePrecreateDTO alipayTradePrecreateDTO = new AlipayTradePrecreateDTO();
        BeanUtils.copyProperties(alipayTradeQueryInput, alipayTradePrecreateDTO);
        return aliPayService.tradePrecreate(alipayTradePrecreateDTO);
    }

    @GetMapping("/trade/pre-create")
    public ResultBody tradePrecreateDefault() {

        return aliPayService.tradePrecreate(null);
    }


    @GetMapping("/trade/query/{outTradeNo}")
    public ResultBody queryTradeResult(@PathVariable String outTradeNo) {

        // TODO: 考虑到后续可能需要扩展问题，不过可以通过商户单号查询，也可以通过订单号查询(支付宝支付后台订单号)
        AlipayTradeQueryDTO alipayTradeQueryDTO = new AlipayTradeQueryDTO(outTradeNo);
        return aliPayService.queryTradeResult(alipayTradeQueryDTO);
    }


    @GetMapping("/trade/refund/{outTradeNo}")
    public ResultBody tradeRefund(@PathVariable String outTradeNo) {

        // TODO:支付宝交易号，当面付支付成功后支付宝会返回给商户系统。通过此支付宝交易号进行交易退款
        AlipayTradeRefundDTO alipayTradeRefundDTO = AlipayTradeRefundDTO.builder().outTradeNo(outTradeNo).build();
        return aliPayService.tradeRefund(alipayTradeRefundDTO);
    }


}
