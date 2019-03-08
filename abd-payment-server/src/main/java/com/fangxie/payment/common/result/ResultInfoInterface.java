package com.fangxie.payment.common.result;

/**
 *  错误码接口
 * @Description: TODO
 * @author <a href="smniuhe@gmail.com">smniuhe</a>
 */
public interface ResultInfoInterface {

    /**
     * 错误码
     * @return
     */
    int getCode();

    /**
     * 错误信息
     * @return
     */
    String getMessage();

}
