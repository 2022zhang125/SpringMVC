# 这一模块主要是研究 @RequestMapping 注解的相关属性。
    
# @RequestMapping之Value属性
    @Target({ElementType.TYPE, ElementType.METHOD})
    不同点：
        当出现在Method方法上时，用户输入一个路径，经过DispatchServelt，然后映射到逻辑视图名称，拼接成物理视图名称，最后映射回去。
        但是这样会导致，一个 path 只能对应 一个 method。
        当出现在Type类上时，在Method上也使用路径时会自动加上Type上的路径。

## 2.怎么解决相同路径报错的问题？
    使用RequestMapping注解在类体上，这样其类中的方法在通过路径调用的时候就会被强制加入类路径。
    路径：类路径 + 方法路径

## 3.Ant风格的Value值（就是我们的路径可以使用通配符）。
    通配符如下
    ?：代替一个字符（路径不能有? / \）
        /test/a?b
        经过测试，可以包含 空格，大于小于号等特殊字符，但是不能不写。
    *：代替0或任何一个字符
        /test/a*b
        /test/a12ewqdasdasdb
        经过测试，与上面不同点就在于可以不写。就是 /test/ab 可以
    **(较为特殊)：可以代替0或任何一个字符其中可以包含 / 路径。
        当/test/**/abc时，直接报错！！！
        所以只能是，写在末尾
        /test/**
        /test/qweqweqeq/qweasda/sadada
        结论就是在Spring6中只能出现在末尾，且前后都为/，例如：http://localhost:8080/springmvc/abc/def/**
        不能放在除末尾之外的任何位置。但是Spring5中可以这样

## 4.使用RESTFul风格的URL进行数据传输
    传统的URL是：/springmvc/login?username=zhangsan&password=123
    RESTFul风格的是：/springmvc/login/zhangsan/123
    就是将请求的值作为URL进行发送。
```
    // RESTFul风格的URL路径
    @RequestMapping("/login/{username}/{b}")
    public String testRESTFul(@PathVariable("username") String username, @PathVariable("b") String password){
        System.out.println("账号：" + username + ",密码：" + password);
        return "ok";
    }
```
    注意：1.占位符使用的是 {} 
         2.接收参数使用 @PathVariable("username")......
         3.其中@PathVariable后要与占位符一致！！！
        
# @RequestMapping之method属性
    通过该属性可以限制前端发送的请求方式，如果前后端方式不同则出现405错误。
```
    @RequestMapping(value = "/test/login",method = RequestMethod.POST)
    public String testLogin(){
        return "ok";
    }
```

## 衍生Mappingw
    @PostMapping注解 代替的是 @RequestMapping("/test",RequestMethod.POST);
    @GetMapping
    @DeleteMapping
    @PatchMapping......

## Web的请求方式
    POST：增
    DELETE：删
    PUT：改
    GET：查
    HEAD：获取响应头
    注意：使用Form表单时，如果输入除post请求之外的请求，一律都是GET请求，要发的话就要用AJAX

# @RequestMapping的属性之params（限制请求的参数）
    第一种：必须要包含username和password否则会报 400 错误 Invalid request parameters.
        @RequestMapping(value = "/params",params = {"username","password"})
        public String testParams(){
            return "ok";
        }
    第二种：限定参数值
        @RequestMapping(value = "/params",params = {"username=zhangsan","password"})
        public String testParams(){
            return "ok";
        }
    第三种：不能包含某个参数
        @RequestMapping(value = "/params",params = {"!username","password"})
        public String testParams(){
            return "ok";
        }
    第四种：参数不能是某个参数值
        RequestMapping(value = "/params",params = {"username!=zhangsan","password"})
        public String testParams(){
            return "ok";
        }
##  Thymeleaf携带参数
    <!--使用Thymeleaf的携带参数-->
    <a th:href="@{/params(username='admin',password='1233')}">测试Params2属性</a><br>

# @RequestMapping之headers（限制请求时的请求头）用的少
    <a th:href="@{/params}">测试headers属性</a><br> 自动携带Headers
    // 请求头中必须包含 Referer和Host。
    RequestMapping(value = "/headers", headers={"Referer","Host"})
    public String testHeaders(){
        return "ok";
    }
    如果header属性没有映射成功,则报错为404