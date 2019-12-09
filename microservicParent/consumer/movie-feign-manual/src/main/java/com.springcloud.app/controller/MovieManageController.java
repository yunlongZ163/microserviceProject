package com.springcloud.app.controller;

import com.springcloud.app.dao.User;
import com.springcloud.app.dao.UserReq;
import com.springcloud.app.service.UserFeignClient;
import feign.Client;

import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;

import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Import(FeignClientsConfiguration.class)
@RestController
@RequestMapping("/movie")
public class MovieManageController {
    @Autowired
    private RestTemplate restTemplate;

    private UserFeignClient userFeignClient;

    private UserFeignClient adminUserFeignClient;

    @RequestMapping(value = {"/user/{id}"},method = {RequestMethod.GET},produces = MediaType.APPLICATION_JSON_VALUE)
    public User findUser(@PathVariable Long id) {
        System.out.println("test");
        return restTemplate.getForObject("http://localhost:8000/provider/user/" + id, User.class);
    }

    @GetMapping(value = "/userFeign/{id}")
    public User findUserOfFeign(@PathVariable Long id){
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

    @Autowired
    public MovieManageController(Decoder decoder, Encoder encoder, Client client, Contract contract){
        this.userFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("user","password1")).target(UserFeignClient.class,"http://microservice-provider-user/");
        this.adminUserFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("admin","password2")).target(UserFeignClient.class,"http://microservice-provider-user/");
    }

}
