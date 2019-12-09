package com.springcloud.app.service.impl;

import com.springcloud.app.dao.User;
import com.springcloud.app.dao.UserReq;
import com.springcloud.app.service.UserFeignClient;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FeignClientFallback implements UserFeignClient {

    @Override
    public User findById(Long id) {
        User user = new User();
        user.setUserName("findById");
        user.setPassWord("findById");
        return user;
    }

    @Override
    public User getUser(Map<String, Object> map) {
        User user = new User();
        user.setUserName("getUser");
        user.setPassWord("getUser");
        return user;
    }

    @Override
    public User getUserOfPost(UserReq userReq) {
        User user = new User();
        user.setUserName("getUserOfPost");
        user.setPassWord("getUserOfPost");
        return user;
    }
}
