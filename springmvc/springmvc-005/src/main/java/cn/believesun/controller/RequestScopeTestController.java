package cn.believesun.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class RequestScopeTestController {
    @RequestMapping("/requestServletAPI")
    public String requestServletApi(HttpServletRequest request){
        // 存放数据
        request.setAttribute("requestScope","使用SpringMVC中的原生Servlet API实现一次请求的请求域数据共享");
        return "ok"; // 这里默认是使用转发机制（forward）
    }

    @RequestMapping("/requestScopeMap")
    public String requestScopeMap(Map<String, Object> map){
        map.put("requestScope","使用SpringMVC中的Map接口实现一次请求的请求域数据共享");
        return "ok";
    }

    @RequestMapping("/requestScopeModel")
    public String requestScopeModel(Model model){
        model.addAttribute("requestScope","使用SpringMVC中的Model接口实现一次请求的请求域数据共享");
        return "ok";
    }

    @RequestMapping("/requestScopeModelMap")
    public String requestModelMap(ModelMap modelMap){
        modelMap.put("requestScope","使用SpringMVC中的ModelMap接口实现一次请求的请求域数据共享");
        return "ok";
    }

    @RequestMapping("/requestModelAndView")
    public ModelAndView requestModelAndView(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("requestScope","使用SpringMVC中的ModelAndView类实现一次请求的请求域数据共享");
        mav.setViewName("ok");
        return mav;
    }
}
