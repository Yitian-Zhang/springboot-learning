# springboot-learning
### 项目整体介绍
该项目是为了学习Spring Boot相关技术而创建的示例学习项目。该项目中主要使用的技术包括：
1. Spring Boot
2. MyBatis
3. JSP
4. EasyUI
5. Spring MVC
6. Maven

### 项目使用
1. 项目启动类：cn.zyt.springbootlearning.SpringbootLearningApplication
2. 项目主页地址：http://localhost:8080/web/index（该页面中包含下述各种功能的请求连接以供测试）
3. 项目配置文件：application.properties
4. MyBatis示例配置文件：properties/mybatis-config.xml，目前由于mybatis的配置较为简单，
因此使用在application.properties配置文件进行设置的方式，如果MyBatis的配置复杂，
则可以在application.properties文中指定mybatis-config.xml配置文件的目录并在该文件中进行MyBatis的详细配置。

### 该项目的整体结构
```$xslt
.
├── main
│   ├── java
│   │   └── cn
│   │       └── zyt
│   │           └── springbootlearning
│   │               ├── aop
│   │               ├── aspect
│   │               ├── component
│   │               ├── config
│   │               ├── controller
│   │               ├── dao
│   │               ├── domain
│   │               ├── service
│   │               │   └── impl
│   │               ├── tools
│   │               └── typehandler
│   ├── resources
│   │   ├── mapper
│   │   ├── properties
│   │   ├── static
│   │   │   ├── jquery-easyui-1.7.0
│   │   │   └── other
│   │   └── templates
│   └── webapp
│       └── WEB-INF
│           └── jsp
└── test
    └── java
        └── cn
            └── zyt
                └── springbootlearning
```

### 项目实现功能
对项目中主要实现的示例功能的相关包和类进行说明：

1.简单探索Spring AOP的面向切面编程，使用简单的约定流程的方式理解AOP的相关概念，主要包含的包和类如下：
```
# 该package中定义了AOP中相关的拦截器，启动器，动态代理等
cn.zyt.springbootlearning.aop.*
# 自定义实现的切面，包括切点、各类通知和连接点概念
cn.zyt.springbootlearning.aspect.MyAspect
# 将自定义切面注入IoC容器的配置类
cn.zyt.springbootlearning.config.MyBatisConfig
# 对切点中的方法进行增强的用户验证类
cn.zyt.springbootlearning.component.UserValidator
```
2.使用User POJO实现Spring Boot中涉及到的各类技术点，其中定义的Domain对象位于：
```
# 项目中主要使用的domain对象，其中User对象为主要使用，Node对象暂未使用，SexEnum为User中sex属性的枚举类型
cn.zyt.springbootlearning.domain.*
# sex属性和SexNum相互适配类
cn.zyt.springbootlearning.typehandler.SexTypeHandler
```
3.Spring Boot整合MyBatis
```
# 项目中定义的Mapper接口，与下面的mapper文件中的xml文件配合使用
cn.zyt.springbootlearning.dao
# 定义mybatis的xml配置文件
resources/mapper
```
4.项目中的controller和service层:
```
cn.zyt.springbootlearning.service
cn.zyt.springbootlearning.controller
```
5.Spring Boot整合EasyUI和JSP作为前端视图：
```
# easyui静态资源路径
resources/static/jquery-easyui-1.7.0
# JSP页面所在路径
/webapp/WEB-INF/jsp
# EasyUI整合页面：
WEB-INF/jsp/userList.jsp
```
6.Spring Boot实现国际化
```
# 国际化控制器
cn.zyt.springbootlearning.controller.InternationalController
# 国际化配置文件目录
resources/properties/*
# 国际化页面
WEB-INF/jsp/international.jsp
```
7.Spring Boot数据模型的使用
```
cn.zyt.springbootlearning.controller.DataModelController
```
8.Spring Boot参数传递
```
# 参数传递controller
cn.zyt.springbootlearning.controller.ParamController
# 格式化参数传递
WEB-INF/jsp/formatter.jsp
# 自定义参数转换规则
cn.zyt.springbootlearning.component.StringToUserConverter
```
9.Spring Boot文件上传实现
```$xslt
cn.zyt.springbootlearning.controller.FileUploadController
WEB-INF/jsp/upload.jsp
```
10.Spring Boot配置拦截器
```$xslt
# 拦截器控制器
cn.zyt.springbootlearning.controller.InterceptorController
# 自定义拦截器
cn.zyt.springbootlearning.component.MyInterceptor1
cn.zyt.springbootlearning.component.MyMultiInterceptor1
cn.zyt.springbootlearning.component.MyMultiInterceptor2
cn.zyt.springbootlearning.component.MyMultiInterceptor3
# 拦截器注册到IoC容器configuration类
cn.zyt.springbootlearning.config.MyWebMvcConfiguration
# 拦截器请求页面
WEB-INF/jsp/welcome.jsp
```
11.Spring Boot重定向实现
```$xslt
cn.zyt.springbootlearning.controller.RedirectController
```
12.Spring Boot操作Session
```$xslt
cn.zyt.springbootlearning.controller.SessionController
WEB-INF/jsp/session.jsp
WEB-INF/jsp/sessionDetail.jsp
```
13.Spring Boot中实现RESTful风格接口
```$xslt
# REST控制器，配合userMapper和userService的更新使用
cn.zyt.springbootlearning.controller.RestController
# View Objects
cn.zyt.springbootlearning.vo.UserVO
cn.zyt.springbootlearning.vo.ResultVO
# RESTful风格页面，其中包含POST创建用户资源，GET请求用户资源，PUT更新用户资源，DELETE删除用户资源的操作
WEB-INF/jsp/restful.jsp
``` 
14.详细使用MyBatis（1），主要包括MyBatis注解的使用和Provider的使用
```
# 对Mapper进行测试的Controller
cn.zyt.springbootlearning.controller.MapperTestController
# 下面三个文件为更新后的文件
cn.zyt.springbootlearning.dao.UserMapper
cn.zyt.springbootlearning.provider.UserProvider
WEB-INF/jsp/index.jsp
```

