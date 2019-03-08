package com.fangxie.payment.common.exception;

import com.fangxie.payment.common.result.ResultInfoInterface;
import lombok.Setter;

/**
 * 统一错误码异常
 *
 * @author <a href="smniuhe@gmail.com">smniuhe</a>
 * @Description: TODO
 */
public class GlobalErrorInfoException extends Exception {

    @Setter
    private ResultInfoInterface errorInfo;

    public GlobalErrorInfoException(ResultInfoInterface errorInfo) {
        this.errorInfo = errorInfo;
    }

    public ResultInfoInterface getErrorInfo() {
        return errorInfo;
    }

}
