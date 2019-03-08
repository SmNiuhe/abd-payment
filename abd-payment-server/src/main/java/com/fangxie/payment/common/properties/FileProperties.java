package com.fangxie.payment.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @Description: 文件配置属性
 * @author: <a href="smniuhe@gmail.com"/>smniuhe</a>
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "file.directory")
@Order(-1)
public class FileProperties {


    /**
     * 文件上传目录
     */
    private String baseDir;

    /**
     * 文件上传临时目录
     */
    private String temp;
}
