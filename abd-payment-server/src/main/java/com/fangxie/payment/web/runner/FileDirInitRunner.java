package com.fangxie.payment.web.runner;

import com.fangxie.payment.common.properties.FileProperties;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

@Log4j2
@Component
public class FileDirInitRunner implements CommandLineRunner {

    @Autowired
    private FileProperties fileProperties;

    @Override
    public void run(String... args) throws Exception {

        String temp = fileProperties.getTemp();
        File fileTempDir = new File(temp);
        FileUtils.forceMkdir(fileTempDir);
        log.info("【初始化】文件临时目录：{}", fileTempDir);
    }

}
