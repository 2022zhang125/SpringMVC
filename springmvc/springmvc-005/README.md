# 三个域对象

## request域对象
    SpringMVC中获取request域对象的方式，一共有五种

        1.原生ServletAPI （HttpServletRequest request）
        @RequestMapping("/requestServletAPI")
        public String requestServletApi(HttpServletRequest request){
            // 存放数据
            request.setAttribute("requestScope","使用SpringMVC中的原生Servlet API实现一次请求的请求域数据共享");
            return "ok"; // 这里默认是使用转发机制（forward）
        }

        2.Map接口
        @RequestMapping("/requestScopeMap")
        public String requestScopeMap(Map<String, Object> map){
            map.put("requestScope","使用SpringMVC中的Map接口实现一次请求的请求域数据共享");
            return "ok";
        }

        3.Model接口
        @RequestMapping("/requestScopeModel")
        public String requestScopeModel(Model model){
            model.addAttribute("requestScope","使用SpringMVC中的Model接口实现一次请求的请求域数据共享");
            return "ok";
        }

        4.ModelMap类
        @RequestMapping("/requestScopeModelMap")
        public String requestModelMap(ModelMap modelMap){
            modelMap.put("requestScope","使用SpringMVC中的ModelMap接口实现一次请求的请求域数据共享");
            return "ok";
        }

        5.ModelAndView类
        @RequestMapping("/requestModelAndView")
        public ModelAndView requestModelAndView(){
            ModelAndView mav = new ModelAndView();
            mav.addObject("requestScope","使用SpringMVC中的ModelAndView类实现一次请求的请求域数据共享");
            mav.setViewName("ok");
            return mav;
        }
### tips:
    只要是请求，在经过Dispatcher分发器时，都会被封装成一个ModelAndView对象。（适配器模型）

## session域对象
    获取Session对象的两种方式
    1、通过原生HttpSession来获取
    @RequestMapping("/sessionServletAPI")
    public String sessionServletApi(HttpSession session){
        session.setAttribute("sessionScope","在SpringMVC中使用原生Servlet API获取Session域对象并统一会话域数据");
        return "ok";
    }

    2、使用ModelMap，再通过在类上使用 @SessionAttributes 注解来指定某个key存储到Session域中。
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

## application域对象
    获取ServletContext对象的方式（用得少），所以直接用ServletAPI原生的即可
    @RequestMapping("/applicationServletAPI")
    public String applicationServletAPI(HttpServletRequest request){
        ServletContext context = request.getServletContext();// 这里不能直接将 ServletContext作为参数进行获取，需要通过request域、session域来间接获取。
        context.setAttribute("applicationScope","使用原生Servlet API实现应用域数据共享");
        return "ok";
    }
    