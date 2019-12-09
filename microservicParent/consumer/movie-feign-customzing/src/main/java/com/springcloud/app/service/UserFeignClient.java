package com.springcloud.app.service;

import com.springcloud.app.conf.FeignConfiguration;
import com.springcloud.app.dao.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "microservice-provider-user",configuration = FeignConfiguration.class)
public interface UserFeignClient {

    //这里需要注意的时候是使用@RequestLine注解,第二注意括号中@RequestLine("GET /provider/user/{id}")的数据格式，第三接口的方法入参中使用@Param注解
    //第四：@EnableFeignClients为我们提供了defaultConfiguration属性用来指定默认配置
    @RequestLine("GET /provider/user/{id}")
    User findById(@Param("id") Long id);
}
