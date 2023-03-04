package com.qingshuge;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.qingshuge.dao")
@SpringBootApplication
public class QingshugeApplication {

    public static void main(String[] args) {
        SpringApplication.run(QingshugeApplication.class, args);
    }

}
