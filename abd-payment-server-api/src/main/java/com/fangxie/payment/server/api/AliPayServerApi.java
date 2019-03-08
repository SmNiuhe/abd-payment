package com.fangxie.payment.server.api;

import com.fangxie.payment.common.pojo.dto.AlipayTradePrecreateInput;
import com.fangxie.payment.common.pojo.result.ResultBody;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "none", url = "http://localhost:8009/ali-pay")
public interface AliPayServerApi {


    @RequestMapping("/authRedirect")
    String authRedirect();


    /**
     * 当面付2.0预下单(生成二维码)
     *
     * @param alipayTradePrecreateInput
     * @return
     */
    @PostMapping("/trade/pre-create")
    public ResultBody tradePrecreate(@RequestBody AlipayTradePrecreateInput alipayTradePrecreateInput);

    @GetMapping("/trade/pre-create")
    public ResultBody tradePrecreateDefault();

    /**
     * 当面付2.0消费查询
     *
     * @param outTradeNo
     * @return
     */
    @GetMapping("/trade/query/{outTradeNo}")
    public ResultBody queryTradeResult(@PathVariable(value = "outTradeNo") String outTradeNo);


    /**
     * 当面付2.0消费退款
     *
     * @param outTradeNo
     * @return
     */
    @GetMapping("/trade/refund/{outTradeNo}")
    public ResultBody tradeRefund(@PathVariable(value = "outTradeNo") String outTradeNo);

}
