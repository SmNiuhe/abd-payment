package com.fangxie.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@EnableTransactionManagement
@ServletComponentScan
@MapperScan(basePackages = "com.fangxie.payment.repository.mapper")
@ComponentScan(basePackages = "com.fangxie.*")
@SpringBootApplication
public class AbdPaymentServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AbdPaymentServerApplication.class, args);
    }

}
