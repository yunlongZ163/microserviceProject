package com.springcloud.app.conf;

import feign.Contract;
import org.springframework.context.annotation.Bean;

public class FeignConfiguration {

    @Bean
    public Contract feignContract(){
        return  new feign.Contract.Default();
    }

}
