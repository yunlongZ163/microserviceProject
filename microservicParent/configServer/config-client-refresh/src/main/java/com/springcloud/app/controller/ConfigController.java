package com.springcloud.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RefreshScope
public class ConfigController {
    @Value("${profile}")
    private String profile;

    @GetMapping
    public String hello(){
        return this.profile;
    }
}
