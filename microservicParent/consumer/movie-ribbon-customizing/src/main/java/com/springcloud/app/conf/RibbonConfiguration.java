package com.springcloud.app.conf;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonConfiguration {

        public IRule ribbonRule(){
            //将负载均衡规则改为随机
            return new RandomRule();
        }
}
