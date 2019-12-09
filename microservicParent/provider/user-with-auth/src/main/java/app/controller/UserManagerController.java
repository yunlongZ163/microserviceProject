package app.controller;

import app.dao.User;
import app.dao.UserReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
public class UserManagerController {
    private static final Logger logger = LoggerFactory.getLogger(UserManagerController.class);

    @GetMapping(value="/user/{id}")
    public User getUser(@PathVariable Long id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            UserDetails user = (UserDetails) principal;
            Collection<? extends GrantedAuthority> collection = user.getAuthorities();
            for(GrantedAuthority c : collection){
                logger.info("当前用户是{}，角色是{}",user.getUsername(),c.getAuthority());
            }
        }else {
            //do other things
        }
        User user = new User();
        user.setUserName("zhangyunlong");
        user.setPassWord("zhangyunlong");
        return user;
    }

    @RequestMapping(value = "/user/get",method = RequestMethod.GET)
    public User getUserOfParams(@RequestParam Map<String,Object> map){
        System.out.println(map.get("1")+":"+map.get("2"));
        User user = new User();
        user.setUserName("getUserParams");
        user.setPassWord("getUserParams");
        return user;
    }


    @RequestMapping(value = "/user/postUser",method = RequestMethod.POST)
    public User getUserOfPost(@RequestBody UserReq userReq){
        System.out.println(userReq.getId()+":"+userReq.getUserName()+""+userReq.getPassword());
        User user = new User();
        user.setUserName("postUserParams");
        user.setPassWord("postUserParams");
        return user;
    }
}
