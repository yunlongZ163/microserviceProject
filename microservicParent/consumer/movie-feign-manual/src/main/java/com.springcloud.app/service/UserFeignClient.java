package com.springcloud.app.service;


import com.springcloud.app.dao.User;

import com.springcloud.app.dao.UserReq;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public interface UserFeignClient {
    //value的值是别的项目接口路径
    @RequestMapping(value = "/provider/user/{id}",method = RequestMethod.GET)
    User findById(@PathVariable("id") Long id);

    /**
     * 下面方法为get多参数请求
     * @param map
     * @return
     */
    @RequestMapping(value = "/provider/user/get",method = RequestMethod.GET)
    User getUser(@RequestParam Map<String,Object> map);

    /**
     * 下面方法为Post多参数请求
     * @param userReq
     * @return
     */
    @RequestMapping(value="provider/user/postUser",method = RequestMethod.POST)
    User getUserOfPost(@RequestBody UserReq userReq);
}
