# 解决了SpringMVC配置文件不能再ClassPath下的问题
```xml
    <!--配置初始化参数，让SpringMVC在classpath中-->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring.xml</param-value>
        </init-param>
```

# 解决了用户第一次加载页面慢的情况
```xml
    <!--让DispatchServlet在服务器启动时自动创建-->
        <load-on-startup>1</load-on-startup>
```

