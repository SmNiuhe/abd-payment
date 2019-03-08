package com.fangxie.payment.common.pojo.result;

/**
 * 应用系统级别的错误码
 *
 * @author <a href="smniuhe@gmail.com">smniuhe</a>
 * @Description: TODO
 */
public enum GlobalErrorInfoEnum implements ResultInfoInterface {

    /**
     * @code 000001
     * @message 响应成功
     */
    SUCCESS(200, "响应成功"),


    /**
     * @code 000002
     * @message 参数不完整
     */
    PARAMS_NO_COMPLETE(202, "参数不完整"),

    /**
     * @message 状态信息
     */
    NOT_FOUND(203, "后台服务丢失"),

    /**
     * @message 状态信息
     */
    HANDLER_ERROR(204, "后台处理异常");


    private int code;

    private String message;

    GlobalErrorInfoEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
