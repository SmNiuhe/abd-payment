package com.fangxie.payment.common.exception;

import com.fangxie.payment.common.result.ResultInfoInterface;
import lombok.Getter;

/**
 * @Description: 参数校验异常
 * @author: <a href="smniuhe@gmail.com"/>smniuhe</a>
 */
@Getter
public class ParamValidateException extends RuntimeException {


    private ResultInfoInterface errorInfo;

    public ParamValidateException(ResultInfoInterface errorInfo) {

        this.errorInfo = errorInfo;
    }
}


