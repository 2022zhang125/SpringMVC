# 视图View的逻辑视图名称和物理视图名称的拼接与渲染

# SpringMVC自带的转发与重定向方式
    这里return的就不是逻辑视图名称了。在SpringMVC中，默认return时就是转发，因为可以访问内部资源 /WEB-INF下的
    转发：return "forward:/b" 
    重定向：return "redirect:/b" （使用的较多）

# 如果对于一个只是跳转，而没有任何业务的Controller时，我们可以采用 mvc:view-controller去指定 路径与资源的映射关系，从而简化代码
    但是，在springmvc.xml文件中配置时，会导致所有的注解失效，于是，我们需要绑定上 mvc-annotation-driven/> 一起使用。
    <mvc:view-controller path="/" view-name="index" />
    <mvc:annotation-driven/>

# 访问服务器静态资源
    1.打开web容器自带的 defaultServlet 这个Servlet是当我们的DispatcherServlet为404时，会使用这个Servlet去静态资源里面找。
    2.配置静态资源处理
        <mvc:resources mapping="/static/**" location="/static/" />
    3.注意：静态资源一定要放在webapp下，不能放在WEB-INF下