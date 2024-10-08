package cn.believesun.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 纳入IoC容器中进行管理操作
@Controller
public class FirstController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/hello")
    public String helloWorld(){
        return "HelloWorld";
    }
    @RequestMapping("/world")
    public String worldHello(){
        return "WorldHello";
    }
}
