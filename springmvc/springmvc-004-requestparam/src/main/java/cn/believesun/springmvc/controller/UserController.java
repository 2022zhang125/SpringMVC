package cn.believesun.springmvc.controller;

import cn.believesun.springmvc.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    // 首页
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    // 注册页面
    @RequestMapping("/user/register")
    public String register(){
        return "register";
    }
    // POST请求(第一种方式)
    @RequestMapping(value = "/user/register01",method = RequestMethod.POST)
    public String toRegister01(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("用户名:" + username + ",密码:" + password);
        /*返回逻辑视图名称*/
        return "ok";
    }
    // POST请求（第二种方式）
   /* @RequestMapping(value = "/user/register02",method = RequestMethod.POST)
    public String toRegister02(@RequestParam("username") String username, @RequestParam("password") String password){
        // 使用@RequestParam将这个的value值映射上我们的变量username。
        System.out.println("用户名:" + username + ",密码:" + password);
        return "ok";
    }*/

    // 第三种方式：省略注解
    /*@RequestMapping(value = "/user/register02",method = RequestMethod.POST)
    public String toRegister02(String username,String password){
        System.out.println("用户名:" + username + ",密码:" + password);
        return "ok";
    }*/
    // 第四种方式：使用POJO类作为接受
    @PostMapping("/user/register02")
    public String toRegister02(User user, @CookieValue("id")String id){
        System.out.println(user);
        System.out.println(id);
        return "ok";
    }
}
