package com.qingshuge.qingshuge;

//import org.testng.annotations.Test;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

@SpringBootTest
class QingshugeApplicationTests {

    @Test
    public void contextLoads() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("application.yml");

    }

}
