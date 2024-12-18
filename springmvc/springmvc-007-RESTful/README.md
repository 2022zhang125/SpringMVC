# RESTful风格的编程
    增加 POST
    删除 DELETE
    修改 PUT
    查看 GET
    通过发送不同类型的请求，使用同一URL，来区分出执行什么操作

# RESTful的语法格式
    URL: /webapp/user/1 GET
    这里向user路径发送一个RESTful的查询请求，其中携带参数为1
    后端接收
    
## 各种请求
    <!--查询--> GET请求
    <a th:href="@{/user/1}">发送查询（GET）请求</a> <br/>
    
    <!--新增--> POST请求
    <form th:action="@{/user}" method="post">
        用户名：<input type="text" name="username"> <br/>
        年龄：<input type="number" name="age"> <br/>
        密码：<input type="password" name="password"> <br/>
        <input type="submit" value="保存">
    </form>
    
    <!--修改--> PUT请求
    第一步：前提是在POST请求之下
    第二步：添加隐藏域
        <input type="hidden" name="_method" value="put" />
    第三步：在web.xml文件中，开启过滤器，用于将post请求转化为put、delete请求
        <!--添加HiddenHttpMethodFilter用于将POST请求转化为PUT、delete请求-->
        <filter>
            <filter-name>hiddenHttpMethodFilter</filter-name>
            <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
        </filter>
        <filter-mapping>
            <filter-name>hiddenHttpMethodFilter</filter-name>
            <url-pattern>/*</url-pattern>
        </filter-mapping>

    <!--删除操作-->
    <!--删除-->
    <a th:href="@{/user/120}" onclick="del(event)">删除id为120的用户</a>
    
    <form method="post" id="delForm">
        <input type="hidden" name="_method" value="delete">
    </form>
    <script>
        function del(event){
            // 获取表单对象
            const delForm = document.getElementById("delForm")
            // 将表单绑定action
            delForm.action = event.target.href;
            // 提交表单
            delForm.submit();
            // 阻止a标签跳转的默认行为
            event.preventDefault();
        }
    </script>
### 小问题
    如果，我们将HiddenHttpMethodFilter 配置在 CharacterEncodingFilter 之上，则会率先调用前者
    这样就会导致 后者无法执行原有操作
    因为，request.setCharacterEncoding("UTF-8") 的前面不能有 request.getParamter("")操作，这样会导致编码设置出错。
    因此，我们需要交换位置。
        所以，隐藏过滤器必须要在字符编码过滤器之后配置!!!.