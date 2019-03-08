package com.fangxie.payment.common.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: <a href="smniuhe@gmail.com"/>smniuhe</a>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageInfoBody<T> {

    /**
     * 总记录数
     */
    private long total;

    /**
     * 响应结果
     */
    private List<T> list;

}
