package cn.believesun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForwardController {
    @RequestMapping("/a")
    public String toA(){
        // return "a";
        // 使用SpringMVC特有的转发方式
        // 这就不是逻辑视图名称了
        return "forward:/b";
        // return "redirect:/b" 采用重定向的方式
    }

    @RequestMapping("/b")
    public String toB(){
        return "b";
    }
}
