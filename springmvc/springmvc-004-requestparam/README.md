# 后端接受请求数据（两种方式）

## 第一种：传统方式（HttpServletRequest request,HttpServletResponse response）

    // 注册页面 POST请求(第一种方式)
    @RequestMapping(value = "/user/register01",method = RequestMethod.POST)
    public String toRegister01(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("用户名: " + username + " ,密码: " + password);
        /*返回逻辑视图名称*/
        return "register";
    }

### 运行流程
    开启服务时--->DisPatcherServlet检测到 / ----> 执行index方法返回逻辑视图名称index ----> 加上前后缀变成物理视图名称 ----> 展示index.html页面
    用户点击超链接后进行跳转----> 被DispatcherServlet检测 /user/register ----> 执行register方法 ----> 拼接 ---->展示。
    用户填写完表单后，点击提交----> 被DispatcherServlet检测 /user/register /user/register01 ----> 执行toRegister方法，通过HttpServletRequest获取表单数据。

## 第二种：@RequestParam注解
    // POST请求（第二种方式）
    @RequestMapping(value = "/user/register02",method = RequestMethod.POST)
    public String toRegister02(@RequestParam("username") String username, @RequestParam("password") String password){
        // 使用@RequestParam将这个的value值映射上我们的变量username。
        System.out.println("用户名:" + username + ",密码:" + password);
        return "ok";
    }

### 注解的属性之 boolean required() default true;
    去判断提交过来的值中是否有这个值，如果没有就 400，默认是每个字段都要必填，可以自己手动改为false，则提交过来为 null。
    通过 defaultValue 去设定默认值。但还是以提交为主。

## 第三种：如果提交的参数名与控制器的方法的形参名一致时，注解可以省去但前提是要加pom.xml文件(Spring5之前的不需要加)
    @RequestMapping(value = "/user/register02",method = RequestMethod.POST)
    public String toRegister02(String username,String password){
        System.out.println("用户名:" + username + ",密码:" + password);
        return "ok";
    }
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.12.1</version>
                <configuration>
                    <source>19</source>
                    <target>19</target>
                    <compilerArgs>
                        <arg>-parameters</arg>
                    </compilerArgs>
                </configuration>
            </plugin>
        </plugins>
    </build>
    注意：如果控制器方法上的形参名和请求参数名没有对应上，则前者为null，不报错！！！、因为前端没有提交这个数据。

## 第四种：使用POJO类作为接受参数（常用！！！）
    要求：提交的属性名 = POJO类的属性名
    // 第四种方式：使用POJO类作为接受
    @PostMapping("/user/register02")
    public String toRegister02(User user){
        System.out.println(user);
        return "ok";
    }

# @RequestHeader注解，获取请求头信息
    @PostMapping("/user/register02")
    public String toRegister02(User user,@RequestHeader("Referer") String referer){
        System.out.println(user);
        System.out.println(referer); // 可以获取到请求头的信息。
        return "ok";
    }

# @CookieValue注解，获取id也就是我们的cookie
    <script type="text/javascript">
        function sendCookie(){
            document.cookie = "id=123456789; expires=Thu, 18 Dec 2025 12:00:00 UTC; path=/";
            document.location = "/springmvc/user/register";
        }
    </script>
    <button onclick="sendCookie()">向服务器端发送Cookie</button>

    public String toRegister02(User user, @CookieValue("id")String id){
        System.out.println(user);
        System.out.println(id); // 123456789
        return "ok";
    }

# GET请求在Tomcat8及其之前使用的是URIEncoding = ISO-8859-1，之后的版本默认使用UTF-8
    解决方法：在CATALINA_HOMT/conf/server.xml文件中在Connector后加入URIEncoding = "UTF-8"即可

# POST请求乱码问题
    解决方法：在接收变量之前使用request.setCharacterEncoding("UTF-8");去定义其编码格式，注意：必须要在request.getRequestParamter("")之前
    但是，在Tomcat10之前版本需要注意这个，之后则不需要设置。

// # 使用Filter过滤器去设置字符集，让所有的请求在请求资源的时候 自动设置编码集和请求集

# 使用SpringMVC自带的过滤器：CharacterEncodingFilter
```xml
    <!--使用SpringMVC自带的编码过滤器来定义请求和响应的字符编码方式-->
    <filter>
        <filter-name>characterEncodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <!--设置编码方式-->
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <init-param>
            <!--设置请求必须要使用我们的UTF-8-->
            <param-name>forceRequestEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
        <init-param>
            <!--设置响应必须要使用我们的UTF-8-->
            <param-name>forceResponseEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>characterEncodingFilter</filter-name>
        <!--对所有的请求都过滤-->
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```