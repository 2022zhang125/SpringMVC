# 视图View的逻辑视图名称和物理视图名称的拼接与渲染

# SpringMVC自带的转发与重定向方式
    这里return的就不是逻辑视图名称了。在SpringMVC中，默认return时就是转发，因为可以访问内部资源 /WEB-INF下的
    转发：return "forward:/b" 
    重定向：return "redirect:/b" （使用的较多）