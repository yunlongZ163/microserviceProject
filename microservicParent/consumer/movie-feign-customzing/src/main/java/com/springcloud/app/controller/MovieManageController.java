package com.springcloud.app.controller;

import com.springcloud.app.dao.User;
import com.springcloud.app.service.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movie")
public class MovieManageController {


    @Autowired
    private UserFeignClient userFeignClient;


    @GetMapping(value = "/userFeign/{id}")
    public User findUserOfFeign(@PathVariable Long id){
        System.out.println("输出值");
        return this.userFeignClient.findById(id);
    }
}
