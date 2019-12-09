package com.springcloud.app.conf;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * 使用@RibboncClient的configuration属性指定Ribbon的配置类
 */
@Configuration
@RibbonClient(name="microservice-provider-user",configuration = RibbonConfiguration.class)
public class TestConfiguration {
}
