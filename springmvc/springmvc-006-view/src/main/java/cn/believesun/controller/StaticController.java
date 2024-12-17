package cn.believesun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticController {
    @RequestMapping("/static")
    public String toStatic(){
        return "redirect:/static/1.jpg";
    }
}
