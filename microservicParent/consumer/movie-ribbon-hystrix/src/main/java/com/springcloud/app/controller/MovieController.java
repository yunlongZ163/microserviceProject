package com.springcloud.app.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.app.dao.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class MovieController {
    private static  final Logger logger = LoggerFactory.getLogger(MovieController.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;


    /**
     * RibbonS实现客户端负载均衡
     */
    @GetMapping(value = {"/log-user-instance"})
    public void logUserInstance(){
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("http://microservice-provider-userprovider/user/"+1);
        logger.info("{}:{}:{}",serviceInstance.getServiceId(),serviceInstance.getHost(),serviceInstance.getPort());
    }

    @HystrixCommand(fallbackMethod = "findByIdFallback",commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000"),@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds",value = "10000")
    },threadPoolProperties = {
            @HystrixProperty(name="coreSize",value = "1"),@HystrixProperty(name = "maxQueueSize",value = "10")
    })
    @GetMapping("user/{id}")
    public User findById(@PathVariable long id){
        return this.restTemplate.getForObject("http://microservice-provider-user/provider/user/"+id, User.class);
    }

    public User findByIdFallback(long id){
        User user = new User();
        user.setUserName("默认用户");
        user.setPassWord("初始密码");
        return user;
    }


    /**
     * 如需获得导致fallback的原因，只需在回调方法入参中加入Throwable就行
     * @param id
     * @param throwable
     * @return
     */
    public User findByIdFallbackEnableThrow(long id,Throwable throwable){
        logger.error("调用远程服务出现异常",throwable);
        User user = new User();
        user.setUserName("默认用户");
        user.setPassWord("初始密码");
        return user;
    }
}
