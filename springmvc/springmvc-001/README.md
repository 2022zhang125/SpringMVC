# SpringMVC第一个项目

## 第一步：引入依赖
```xml
<dependencies>
        <!--Spring MVC-->
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>6.1.11</version>
        </dependency>
        <!--Servlet依赖-->
        <dependency>
            <groupId>jakarta.servlet</groupId>
            <artifactId>jakarta.servlet-api</artifactId>
            <version>6.0.0</version>
            <!--不参与war包的打包，因为Tomcat中有。provided表示该依赖由第三方容器提供。-->
            <scope>provided</scope>
        </dependency>
        <!--LogBack-->
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.4.14</version>
        </dependency>
        <!--Spring6和thymeleaf整合的依赖-->
        <dependency>
            <groupId>org.thymeleaf</groupId>
            <artifactId>thymeleaf-spring6</artifactId>
            <version>3.1.2.RELEASE</version>
        </dependency>
    </dependencies>
```

## 第二步：编写Web.xml文件，配置DispatchServlet(前端控制器)
```xml
<servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    </servlet>
<servlet-mapping>
    <servlet-name>springmvc</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```
    这里的 / 路径代表的是 除了.jsp以外的所有路径，因为JSP本身就是一个Servlet，所以不需要特地通过DispatchServlet。
    而 /* 代表的就是全部路径了，所以不要配错。
## 第三步：编写Controller层
```java
package cn.believesun.springmvc.controller;

import org.springframework.stereotype.Controller;

// 纳入IoC容器中进行管理操作
@Controller
public class FirstController {

}
```
## 第四步：编写springmvc的配置文件：格式（<servlet-name>-servlet.xml）在WEB-INF下的
    注：这里的servlet-name要与web.xml中的servlet-name一致，这里为 springmvc
    配置包扫描器和视图解析器
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
    <!--配置包扫描器-->
    <context:component-scan base-package="cn.believesun.springmvc.controller"/>
    <!--配置视图解析器-->
    <import resource="view.xml"/>
</beans>
```
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--视图解析器-->
    <bean id="thymeleafViewResolver" class="org.thymeleaf.spring6.view.ThymeleafViewResolver">
        <!--作用于视图渲染的过程中，可以设置视图渲染后输出时采用的编码字符集-->
        <property name="characterEncoding" value="UTF-8"/>
        <!--如果配置多个视图解析器，它来决定优先使用哪个视图解析器，它的值越小优先级越高-->
        <property name="order" value="1"/>
        <!--当 ThymeleafViewResolver 渲染模板时，会使用该模板引擎来解析、编译和渲染模板-->
        <property name="templateEngine">
            <bean class="org.thymeleaf.spring6.SpringTemplateEngine">
                <!--用于指定 Thymeleaf 模板引擎使用的模板解析器。模板解析器负责根据模板位置、模板资源名称、文件编码等信息，加载模板并对其进行解析-->
                <property name="templateResolver">
                    <bean class="org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver">
                        <!--设置模板文件的位置（前缀）-->
                        <property name="prefix" value="/WEB-INF/templates/"/>
                        <!--设置模板文件后缀（后缀），Thymeleaf文件扩展名不一定是html，也可以是其他，例如txt，大部分都是html-->
                        <property name="suffix" value=".html"/>
                        <!--设置模板类型，例如：HTML,TEXT,JAVASCRIPT,CSS等-->
                        <property name="templateMode" value="HTML"/>
                        <!--用于模板文件在读取和解析过程中采用的编码字符集-->
                        <property name="characterEncoding" value="UTF-8"/>
                    </bean>
                </property>
            </bean>
        </property>
    </bean>
</beans>
```
    注意：这里的这几个属性尤其的重要！！！
         <!--设置模板文件的位置（前缀）-->
        <property name="prefix" value="/WEB-INF/templates/"/>
        <!--设置模板文件后缀（后缀），Thymeleaf文件扩展名不一定是html，也可以是其他，例如txt，大部分都是html-->
        <property name="suffix" value=".html"/>
        <!--设置模板类型，例如：HTML,TEXT,JAVASCRIPT,CSS等-->
        <property name="templateMode" value="HTML"/>
        <!--用于模板文件在读取和解析过程中采用的编码字符集-->
        <property name="characterEncoding" value="UTF-8"/>
    其中的前缀，后缀用于将 Controller层中返回的 “逻辑视图名称” 通过加入前后缀变成 “物理视图名称” 从而定位到我们写的代码
    所以说，这里的前端页面应该是 在/WEB-INF/templates/路径下的以 .html结尾的文件。

## 第五步：编写Controller层代码，将逻辑视图名称进行返回。
```java
package cn.believesun.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 纳入IoC容器中进行管理操作
@Controller
public class FirstController {
    @RequestMapping("/hello")
    public String helloWorld(){
        return "HelloWorld";
    }
}
```
## 第六步：配置Tomcat服务器
    略过

