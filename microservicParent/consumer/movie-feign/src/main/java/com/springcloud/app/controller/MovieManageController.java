package com.springcloud.app.controller;

import com.springcloud.app.dao.User;
import com.springcloud.app.dao.UserReq;
import com.springcloud.app.service.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/movie")
public class MovieManageController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserFeignClient userFeignClient;

    //使用restTemplate进行接口调用
    @RequestMapping(value = {"/user/{id}"},method = {RequestMethod.GET},produces = MediaType.APPLICATION_JSON_VALUE)
    public User findUser(@PathVariable Long id) {
        System.out.println("test");
        return restTemplate.getForObject("http://localhost:8000/provider/user/" + id, User.class);
    }

    //使用feign客户端进行接口的调用，感觉调用的方式有点类似于RPC远程接口调用，客户端调用远程接口的方式跟调用自身的接口差不多
    @GetMapping(value = "/userFeign/{id}")
    public User findUserOfFeign(@PathVariable Long id){
        System.out.println("输出值");
        return this.userFeignClient.findById(id);
    }

    @GetMapping(value = "/getUser")
    public User findUserByParams(){
        Map<String,Object> map = new HashMap<>();
        map.put("1","1");
        map.put("2","2");
        return this.userFeignClient.getUser(map);
    }

    @GetMapping(value = "/postUser")
    public User postUser(){
        UserReq userReq = new UserReq();
        userReq.setId("1");
        userReq.setUserName("postUser");
        userReq.setPassword("postpassword");
        return this.userFeignClient.getUserOfPost(userReq);
    }

}
