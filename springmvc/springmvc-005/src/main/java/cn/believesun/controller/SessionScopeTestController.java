package cn.believesun.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes({"sessionScope"})
@Controller
public class SessionScopeTestController {
    @RequestMapping("/sessionServletAPI")
    public String sessionServletApi(HttpSession session){
        session.setAttribute("sessionScope","在SpringMVC中使用原生Servlet API获取Session域对象并统一会话域数据");
        return "ok";
    }

    @RequestMapping("/sessionModelMap")
    public String sessionModelMap(ModelMap modelMap){
        modelMap.put("sessionScope","在SpringMVC中使用ModelMap接口获取Session域对象并统一会话域数据");
        return "ok";
    }
}
