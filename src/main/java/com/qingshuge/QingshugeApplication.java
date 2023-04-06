package com.qingshuge;

import com.qingshuge.util.WebConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@MapperScan("com.qingshuge.dao")
@SpringBootApplication
//@Import(WebConfig.class)
public class QingshugeApplication {

    public static void main(String[] args) {
        SpringApplication.run(QingshugeApplication.class, args);
    }

}
