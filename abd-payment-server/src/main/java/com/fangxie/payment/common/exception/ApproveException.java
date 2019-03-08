package com.fangxie.payment.common.exception;

import com.fangxie.payment.common.result.ResultInfoInterface;
import lombok.Getter;

/**
 * @Description: 银行联机接口相关请求异常
 * @author: <a href="smniuhe@gmail.com"/>smniuhe</a>
 */
@Getter
public class ApproveException extends RuntimeException {

    private ResultInfoInterface errorInfo;

    public ApproveException(ResultInfoInterface errorInfo) {

        this.errorInfo = errorInfo;
    }
}


