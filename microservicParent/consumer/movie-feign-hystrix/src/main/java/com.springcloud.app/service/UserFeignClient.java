package com.springcloud.app.service;

import com.springcloud.app.dao.User;
import com.springcloud.app.dao.UserReq;
import com.springcloud.app.service.impl.FeignClientFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "microservice-provider-user",fallback = FeignClientFallback.class)
public interface UserFeignClient {
    /**
     * 当接口的入参比较少的时候可以采用这种方式
     * @param id
     * @return
     */
    //value的值是别的项目接口路径
    @RequestMapping(value = "/provider/user/{id}",method = RequestMethod.GET)
    User findById(@PathVariable("id") Long id);

    /**
     * 下面方法为get多参数请求
     * @param map
     * @return
     */
    @RequestMapping(value = "/provider/user/get",method = RequestMethod.GET)
    User getUser(@RequestParam Map<String, Object> map);

    /**
     * 下面方法为Post多参数请求
     * @param userReq
     * @return
     */
    @RequestMapping(value="provider/user/postUser")
    User getUserOfPost(@RequestBody UserReq userReq);



}
