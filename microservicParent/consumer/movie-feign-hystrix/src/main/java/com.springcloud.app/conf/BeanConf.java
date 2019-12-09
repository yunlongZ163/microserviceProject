package com.springcloud.app.conf;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BeanConf {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
