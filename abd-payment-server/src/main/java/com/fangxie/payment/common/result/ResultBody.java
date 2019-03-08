package com.fangxie.payment.common.result;


import com.fangxie.payment.enums.ResultCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 返回体
 *
 * @author <a href="smniuhe@gmail.com">smniuhe</a>
 * @Description: 设置固定模式的返回结构
 */
@Data
public class ResultBody<T> implements Serializable {

    private int code;

    private String message;

    private T result;

    public ResultBody() {
    }

    public ResultBody(ResultInfoInterface errorInfo) {
        this.code = errorInfo.getCode();
        this.message = errorInfo.getMessage();
    }

    public ResultBody(T result) {
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.message = ResultCodeEnum.SUCCESS.getMessage();
        this.result = result;
    }

    public ResultBody(ResultInfoInterface errorInfo, T result) {
        this.code = errorInfo.getCode();
        this.message = errorInfo.getMessage();
        this.result = result;
    }

    /**
     * 响应成功
     *
     * @param result
     * @return
     */
    public static <T> ResultBody success(T result) {

        return new ResultBody(result);
    }

    /**
     * 响应header
     *
     * @return
     */
    public static ResultBody header(ResultInfoInterface errorInfo) {

        return new ResultBody(errorInfo);
    }

    /**
     * 响应数据
     *
     * @param result
     * @return
     */
    public static <T> ResultBody body(ResultInfoInterface errorInfo, T result) {

        return new ResultBody(errorInfo, result);
    }


}
