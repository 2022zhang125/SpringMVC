package cn.believesun.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {
    @RequestMapping("/test/**")
    public String testAntValue(){
        return "ok";
    }
    // RESTFul风格的URL路径
    @RequestMapping("/login/{username}/{password}")
    public String testRESTFul(@PathVariable("username") String username, @PathVariable("password") String password){
        System.out.println("账号:" + username + ",密码:" + password);
        return "ok";
    }
    @RequestMapping(value = "/test/login",method = RequestMethod.POST)
    public String testLogin(){
        return "ok";
    }
    // 要求传递参数必须要有 username和password
    @RequestMapping(value = "/params",params = {"username=zhangsan","password"})
    public String testParams(){
        return "ok";
    }
}
