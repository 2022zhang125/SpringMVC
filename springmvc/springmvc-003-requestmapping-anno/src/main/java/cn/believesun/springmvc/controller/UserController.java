package cn.believesun.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
    @RequestMapping("/detail")
    public String userDetail(){
        return "user_detail";
    }
}
