package com.fangxie.payment.common.utils;

import com.alipay.api.AlipayResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

/**
 * 应答工具类
 */
@Slf4j
public class AlipayResponseUtils {

    /**
     * 简单打印应答
     *
     * @param response
     */
    public static void dumpResponse(AlipayResponse response) {
        if (response != null) {
            log.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
            if (StringUtils.isNotEmpty(response.getSubCode())) {
                log.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(),
                        response.getSubMsg()));
            }
            log.info("body:" + response.getBody());
        }
    }

}
