package com.fangxie.payment.common.result;

/**
 * @Description: 分页响应结构
 * @author: <a href="smniuhe@gmail.com"/>smniuhe</a>
 */
public class PageResultBody extends ResultBody {

    /**
     * 状态值
     */
    private int code;

    /**
     * 描述信息
     */
    private String message;

    /**
     * 响应结果
     */
    private PageInfoBody pageInfoBody;

    public PageResultBody(ResultInfoInterface resultInfo) {
        super(resultInfo);
    }

    public PageResultBody(PageInfoBody pageInfoBody) {
        super(pageInfoBody);
    }

    public PageResultBody(ResultInfoInterface resultInfo, PageInfoBody pageInfoBody) {
        super(resultInfo, pageInfoBody);
    }

    /**
     * 响应成功
     *
     * @param pageInfoBody
     * @return
     */
    public static PageResultBody success(PageInfoBody pageInfoBody) {

        PageResultBody pageResultBody = new PageResultBody(pageInfoBody);
        return pageResultBody;
    }

    /**
     * 响应数据
     *
     * @param resultInfo
     * @param pageInfoBody
     * @return
     */
    public static PageResultBody body(ResultInfoInterface resultInfo, PageInfoBody pageInfoBody) {

        PageResultBody pageResultBody = new PageResultBody(resultInfo, pageInfoBody);
        return pageResultBody;
    }
}
