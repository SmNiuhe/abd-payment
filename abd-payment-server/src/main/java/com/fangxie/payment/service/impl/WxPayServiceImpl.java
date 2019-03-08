package com.fangxie.payment.service.impl;

import com.fangxie.payment.common.properties.WxPayTradeProperties;
import com.fangxie.payment.common.result.ResultBody;
import com.fangxie.payment.common.utils.DateTimeUtils;
import com.fangxie.payment.common.utils.KeyUtils;
import com.fangxie.payment.enums.WxpayOrderStatusEnum;
import com.fangxie.payment.pojo.dto.WxPayQueryOrderDTO;
import com.fangxie.payment.pojo.dto.WxPayRefundDTO;
import com.fangxie.payment.pojo.dto.WxPayRefundQueryDTO;
import com.fangxie.payment.pojo.dto.WxPayUnifiedOrderDTO;
import com.fangxie.payment.pojo.vo.WxPayNativeOrderVO;
import com.fangxie.payment.service.IWxPayService;
import com.fangxie.utils.UUIDUtils;
import com.github.binarywang.wxpay.bean.order.WxPayNativeOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayRefundQueryRequest;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.fangxie.payment.enums.WxpayOrderStatusEnum.REFUND_QUERY_FAILED;

@Slf4j
@Service
public class WxPayServiceImpl implements IWxPayService {

    @Autowired
    private WxPayService wxService;

    @Autowired
    private WxPayTradeProperties wxPayTradeProperties;

    @Override
    public ResultBody createOrder(WxPayUnifiedOrderDTO wxPayUnifiedOrderDTO) throws WxPayException {

        // 构建数据
        WxPayUnifiedOrderDTO target = new WxPayUnifiedOrderDTO();
        BeanUtils.copyProperties(wxPayTradeProperties, target);
        target.setOutTradeNo(KeyUtils.getOutTradeNo());
        target.setNonceStr(UUIDUtils.getUUID());
        target.setTimeStart(DateTimeUtils.wx_time_start());
        target.setTimeExpire(DateTimeUtils.wx_time_expire());
        target.setProductId(KeyUtils.getProductId());
        // 构建 WxPayUnifiedOrderRequest 对象
        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
        BeanUtils.copyProperties(target, request);
        WxPayNativeOrderResult wxPayNativeOrderResult = this.wxService.createOrder(request);
        WxPayNativeOrderVO wxPayNativeOrderVO = WxPayNativeOrderVO.builder()
                .codeUrl(wxPayNativeOrderResult.getCodeUrl()).outTradeNo(request.getOutTradeNo()).build();
        return ResultBody.body(WxpayOrderStatusEnum.PRECREATE_SUCCESS, wxPayNativeOrderVO);
    }

    @Override
    public ResultBody queryOrder(WxPayQueryOrderDTO wxPayQueryOrderDTO) throws WxPayException {

        // 构建数据
        String transactionId = wxPayQueryOrderDTO.getTransactionId();
        String outTradeNo = wxPayQueryOrderDTO.getOutTradeNo();
        WxPayOrderQueryResult wxPayOrderQueryResult = this.wxService.queryOrder(transactionId, outTradeNo);
        return ResultBody.body(WxpayOrderStatusEnum.QUERY_SUCCESS, wxPayOrderQueryResult);
    }

    @Override
    public ResultBody refund(WxPayRefundDTO wxPayRefundDTO) throws WxPayException {

        // 构建数据
        wxPayRefundDTO.setNonceStr(UUIDUtils.getUUID());
        wxPayRefundDTO.setOutRefundNo(KeyUtils.getOutRefundNo());
        wxPayRefundDTO.setTotalFee(1);
        wxPayRefundDTO.setRefundFee(1);

        WxPayRefundRequest wxPayRefundRequest = new WxPayRefundRequest();
        BeanUtils.copyProperties(wxPayRefundDTO, wxPayRefundRequest);
        WxPayRefundResult wxPayRefundResult = this.wxService.refund(wxPayRefundRequest);
        return ResultBody.body(WxpayOrderStatusEnum.REFUND_SUCCESS, wxPayRefundResult);
    }

    @Override
    public ResultBody refundQuery(WxPayRefundQueryDTO wxPayRefundQueryDTO) {

        // 构建数据
        WxPayRefundQueryRequest wxPayRefundQueryRequest = new WxPayRefundQueryRequest();
        BeanUtils.copyProperties(wxPayRefundQueryDTO, wxPayRefundQueryRequest);

        try {
            WxPayRefundQueryResult wxPayRefundQueryResult = this.wxService.refundQuery(wxPayRefundQueryRequest);
            return ResultBody.body(WxpayOrderStatusEnum.REFUND_QUERY_SUCCESS, wxPayRefundQueryResult);
        } catch (WxPayException e) {
            log.error("【退款订单查询错误】：{}", e);
            return ResultBody.body(REFUND_QUERY_FAILED, e.getReturnMsg());
        }

    }
}
