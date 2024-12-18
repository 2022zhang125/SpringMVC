package cn.believesun.springmvc.controller;

import cn.believesun.springmvc.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @GetMapping("/user/{id}")
    public String getUser(@PathVariable("id") String id){
        System.out.println("id = " + id);
        return "ok";
    }
    @PostMapping("/user")
    public String addUser(User user){
        System.out.println("PostRequest："+user);
        return "ok";
    }
    @PutMapping("/user")
    public String putUser(User user){
        System.out.println("PutRequest：" + user);
        return "ok";
    }
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") String id){
        System.out.println(id);
        return "ok";
    }
}
