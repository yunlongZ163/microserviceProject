package com.springcloud.app.conf;


import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.lang.reflect.Type;

@Component
public class BeanConf {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public Decoder decoder(){
        return new Decoder() {
            @Override
            public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
                return null;
            }
        };
    }
}
