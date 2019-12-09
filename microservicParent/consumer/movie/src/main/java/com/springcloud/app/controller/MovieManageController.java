package com.springcloud.app.controller;

import com.springcloud.app.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movie")
public class MovieManageController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = {"/findUser/{id}"},method = {RequestMethod.GET},produces = MediaType.APPLICATION_JSON_VALUE)
    public User findUser(@PathVariable Long id) {
        System.out.println("test");
        return restTemplate.getForObject("http://localhost:8000/provider/user/" + id, User.class);
    }
}
