package com.fangxie.payment.service.impl;

import com.alipay.api.domain.TradeFundBill;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.fangxie.payment.common.properties.AliPayTradeProperties;
import com.fangxie.payment.common.result.ResultBody;
import com.fangxie.payment.common.utils.AlipayResponseUtils;
import com.fangxie.payment.pojo.dto.AlipayTradePrecreateDTO;
import com.fangxie.payment.pojo.dto.AlipayTradeQueryDTO;
import com.fangxie.payment.pojo.dto.AlipayTradeRefundDTO;
import com.fangxie.payment.pojo.vo.AlipayTradePrecreateVO;
import com.fangxie.payment.service.IAliPayService;
import com.fangxie.payment.trade.config.Configs;
import com.fangxie.payment.trade.model.builder.AlipayTradePayRequestBuilder;
import com.fangxie.payment.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.fangxie.payment.trade.model.builder.AlipayTradeQueryRequestBuilder;
import com.fangxie.payment.trade.model.builder.AlipayTradeRefundRequestBuilder;
import com.fangxie.payment.trade.model.result.AlipayF2FPayResult;
import com.fangxie.payment.trade.model.result.AlipayF2FPrecreateResult;
import com.fangxie.payment.trade.model.result.AlipayF2FQueryResult;
import com.fangxie.payment.trade.model.result.AlipayF2FRefundResult;
import com.fangxie.payment.trade.service.AlipayMonitorService;
import com.fangxie.payment.trade.service.AlipayTradeService;
import com.fangxie.payment.trade.service.impl.AlipayMonitorServiceImpl;
import com.fangxie.payment.trade.service.impl.AlipayTradeServiceImpl;
import com.fangxie.payment.trade.service.impl.AlipayTradeWithHBServiceImpl;
import com.fangxie.payment.trade.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.fangxie.payment.enums.AlipayTradeStausEnum.*;

@Slf4j
@Service
public class AliPayServiceImpl implements IAliPayService {

    /**
     * properties
     */
    @Autowired
    private AliPayTradeProperties aliPayTradeProperties;

    // 支付宝当面付2.0服务
    private static AlipayTradeService tradeService;

    // 支付宝当面付2.0服务（集成了交易保障接口逻辑）
    private static AlipayTradeService tradeWithHBService;

    // 支付宝交易保障接口服务，供测试接口api使用，请先阅读readme.txt
    private static AlipayMonitorService monitorService;

    static {
        /** 一定要在创建AlipayTradeService之前调用Configs.init()设置默认参数
         *  Configs会读取classpath下的zfbinfo.properties文件配置信息，如果找不到该文件则确认该文件是否在classpath目录
         */
        Configs.init("properties/zfbinfo.properties");

        /** 使用Configs提供的默认参数
         *  AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
         */
        tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();

        // 支付宝当面付2.0服务（集成了交易保障接口逻辑）
        tradeWithHBService = new AlipayTradeWithHBServiceImpl.ClientBuilder().build();

        /** 如果需要在程序中覆盖Configs提供的默认参数, 可以使用ClientBuilder类的setXXX方法修改默认参数 否则使用代码中的默认设置 */
        monitorService = new AlipayMonitorServiceImpl.ClientBuilder()
                .setGatewayUrl("http://mcloudmonitor.com/gateway.do").setCharset("GBK")
                .setFormat("json").build();
    }


    @Override
    public AlipayF2FPayResult tradePay(AlipayTradePayRequestBuilder builder) {
        return null;
    }

    @Override
    public ResultBody queryTradeResult(AlipayTradeQueryDTO alipayTradeQueryDTO) {

        // 创建查询请求builder，设置请求参数
        AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder();
        BeanUtils.copyProperties(alipayTradeQueryDTO, builder);
        AlipayF2FQueryResult result = tradeService.queryTradeResult(builder);
        ResultBody resultBody;
        switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("【查询返回该订单支付成功】)");

                AlipayTradeQueryResponse response = result.getResponse();
                AlipayResponseUtils.dumpResponse(response);

                if (Utils.isListNotEmpty(response.getFundBillList())) {
                    for (TradeFundBill bill : response.getFundBillList()) {
                        log.info("【支付渠道】{}:{}", bill.getFundChannel(), bill.getAmount());
                    }
                }
                resultBody = ResultBody.body(QUERY_SUCCESS, response);
                break;

            case FAILED:
                log.error("【查询返回该订单支付失败或被关闭!!!】");
                resultBody = ResultBody.header(QUERY_FAILED);
                break;

            case UNKNOWN:
                log.error("【系统异常，订单支付状态未知!!!】");
                resultBody = ResultBody.header(QUERY_UNKNOWN);
                break;

            default:
                log.error("【不支持的交易状态，交易返回异常!!!】");
                resultBody = ResultBody.header(ERROR);
                break;
        }

        return resultBody;
    }

    @Override
    public ResultBody tradeRefund(AlipayTradeRefundDTO alipayTradeRefundDTO) {

        // TODO: 测试阶段，后续的话都是可以通过【前端传递的信息】和【数据库持久化的订单信息】做业务处理
        String outTradeNo = alipayTradeRefundDTO.getOutTradeNo();
        BeanUtils.copyProperties(aliPayTradeProperties, alipayTradeRefundDTO);
        alipayTradeRefundDTO.setOutTradeNo(outTradeNo);
        AlipayTradeRefundRequestBuilder builder = new AlipayTradeRefundRequestBuilder();
        BeanUtils.copyProperties(alipayTradeRefundDTO, builder);
        AlipayF2FRefundResult result = tradeService.tradeRefund(builder);
        ResultBody resultBody;
        switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("【支付宝退款成功】");
                resultBody = ResultBody.header(REFUND_SUCCESS);
                break;

            case FAILED:
                log.error("【支付宝退款失败!!!】");
                resultBody = ResultBody.body(REFUND_FAILED, result);
                break;

            case UNKNOWN:
                log.error("【系统异常，订单退款状态未知!!!】");
                resultBody = ResultBody.header(REFUND_UNKNOWN);
                break;

            default:
                log.error("【不支持的交易状态，交易返回异常!!!】");
                resultBody = ResultBody.header(ERROR);
                break;
        }
        return resultBody;
    }

    @Override
    public ResultBody tradePrecreate(AlipayTradePrecreateDTO alipayTradePrecreateDTO) {

        // 入口 tradePrecreaDefault： 需要使用默认配置
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder();
        BeanUtils.copyProperties(aliPayTradeProperties, builder);
        AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
        ResultBody resultBody;
        switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("【支付宝预下单成功】");
                AlipayTradePrecreateResponse response = result.getResponse();
                AlipayResponseUtils.dumpResponse(response);
                AlipayTradePrecreateVO alipayTradePrecreateVO = new AlipayTradePrecreateVO(response.getQrCode(), response.getOutTradeNo());
                resultBody = ResultBody.body(PRECREATE_SUCCESS, alipayTradePrecreateVO);
                break;

            case FAILED:
                log.error("【支付宝预下单失败!!!】");
                resultBody = ResultBody.header(PRECREATE_FAILED);
                break;

            case UNKNOWN:
                log.error("【系统异常，预下单状态未知!!!】");
                resultBody = ResultBody.header(PRECREATE_UNKNOWN);
                break;

            default:
                log.error("【不支持的交易状态，交易返回异常!!!】");
                resultBody = ResultBody.header(ERROR);
                break;
        }

        return resultBody;
    }
}
