package cn.believesun.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationScopeTestController {
    @RequestMapping("/applicationServletAPI")
    public String applicationServletAPI(HttpServletRequest request){
        ServletContext context = request.getServletContext();// 这里不能直接将 ServletContext作为参数进行获取，需要通过request域、session域来间接获取。
        context.setAttribute("applicationScope","使用原生Servlet API实现应用域数据共享");
        return "ok";
    }
}
