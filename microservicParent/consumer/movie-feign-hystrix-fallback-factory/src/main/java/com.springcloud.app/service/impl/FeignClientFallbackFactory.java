package com.springcloud.app.service.impl;

import com.springcloud.app.dao.User;
import com.springcloud.app.dao.UserReq;
import com.springcloud.app.service.UserFeignClient;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Component
public class FeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {
    private static final Logger logger = LoggerFactory.getLogger(FeignClientFallbackFactory.class);
    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {
            @Override
            public User findById(@PathVariable("id") Long id) {
                logger.info("fallback;reason was:",throwable);
                User user = new User();
                user.setUserName("默认用户");
                user.setPassWord("默认用户");
                return user;
            }

            @Override
            public User getUser(Map<String, Object> map) {
                logger.info("fallback;reason was:",throwable);
                User user = new User();
                user.setUserName("findById");
                user.setPassWord("findById");
                return user;
            }

            @Override
            public User getUserOfPost(UserReq userReq) {
                logger.info("fallback;reason was:",throwable);
                User user = new User();
                user.setUserName("findById");
                user.setPassWord("findById");
                return user;
            }
        };
    }
}
