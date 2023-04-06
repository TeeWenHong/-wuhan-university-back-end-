package com.qingshuge.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


//    全局配置类
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
//                .allowedOrigins("Http://192.168.5.242:8080","null")
                .allowedOrigins("Http://10.131.239.242:8080","null")
                .allowedMethods("GET","POST","PUT","OPTIONS","DELETE")
                .allowCredentials(true)
                .maxAge(3600);
    }

}
