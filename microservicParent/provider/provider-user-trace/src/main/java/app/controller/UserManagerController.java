package app.controller;

import app.dao.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserManagerController {

    @GetMapping(value="/user/{id}")
    public User getUser(@PathVariable Long id){
        User user = new User();
        user.setUserName("zhangyunlong");
        user.setPassWord("zhangyunlong");
        return user;
    }
}
