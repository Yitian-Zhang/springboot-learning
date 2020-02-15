# springboot-learning
### 项目整体介绍
该项目是为了学习Spring Boot相关技术而创建的示例学习项目。该项目中主要使用的技术包括：
1. Spring Boot
2. MyBatis
3. JSP
4. EasyUI
5. Spring MVC
6. Maven
7. Spring Security
8. Spring Boot+Redis
9. Spring Redis Cache

### 项目使用
1. 项目启动类：cn.zyt.springbootlearning.SpringbootLearningApplication
2. 项目主页地址：http://localhost:8080/web/index（该页面中包含下述各种功能的请求连接以供测试）
3. 项目配置文件：application.properties
4. MyBatis示例配置文件：properties/mybatis-config.xml，目前由于mybatis的配置较为简单，
因此使用在application.properties配置文件进行设置的方式，如果MyBatis的配置复杂，
则可以在application.properties文中指定mybatis-config.xml配置文件的目录并在该文件中进行MyBatis的详细配置。

### 该项目的整体结构（更新中）
```$xslt
.
├── HELP.md
├── README.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── springboot-learning.iml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── cn
│   │   │       └── zyt
│   │   │           └── springbootlearning
│   │   │               ├── ServletInitializer.java
│   │   │               ├── SpringbootLearningApplication.java
│   │   │               ├── aop
│   │   │               │   ├── Interceptor.java
│   │   │               │   ├── Invocation.java
│   │   │               │   ├── MyInterceptor.java
│   │   │               │   ├── ProxyBean.java
│   │   │               │   ├── TestProxy.java
│   │   │               │   └── aspect
│   │   │               │       └── MyAspect.java
│   │   │               ├── common
│   │   │               ├── component
│   │   │               │   ├── MyInterceptor1.java
│   │   │               │   ├── MyMultiInterceptor1.java
│   │   │               │   ├── MyMultiInterceptor2.java
│   │   │               │   ├── MyMultiInterceptor3.java
│   │   │               │   ├── StringToUserConverter.java
│   │   │               │   ├── UserValidator.java
│   │   │               │   ├── endpoints
│   │   │               │   │   └── DataBaseConnectionEndpoint.java
│   │   │               │   └── indicator
│   │   │               │       └── WwwHealthIndicator.java
│   │   │               ├── config
│   │   │               │   ├── AsyncConfiguration.java
│   │   │               │   ├── MyBatisConfiguration.java
│   │   │               │   ├── RedisConfiguration.java
│   │   │               │   ├── SpringRedisConfiguration.java
│   │   │               │   ├── WebMvcConfiguration.java
│   │   │               │   └── WebSecurityConfiguration.java
│   │   │               ├── controller
│   │   │               │   ├── ApplicationController.java
│   │   │               │   ├── AsyncController.java
│   │   │               │   ├── DataModelController.java
│   │   │               │   ├── FileUploadController.java
│   │   │               │   ├── IndexController.java
│   │   │               │   ├── InterceptorController.java
│   │   │               │   ├── InternationalController.java
│   │   │               │   ├── MapperTestController.java
│   │   │               │   ├── NodeController.java
│   │   │               │   ├── ParamController.java
│   │   │               │   ├── ProductController.java
│   │   │               │   ├── PurchaseController.java
│   │   │               │   ├── PurchaseRecordController.java
│   │   │               │   ├── RedirectController.java
│   │   │               │   ├── RedisController.java
│   │   │               │   ├── RedisMultiController.java
│   │   │               │   ├── RestController.java
│   │   │               │   ├── ResultMapTestController.java
│   │   │               │   ├── SessionController.java
│   │   │               │   ├── UserCacheController.java
│   │   │               │   └── UserController.java
│   │   │               ├── dao
│   │   │               │   ├── AuthorMapper.java
│   │   │               │   ├── BlogMapper.java
│   │   │               │   ├── NodeMapper.java
│   │   │               │   ├── ProductMapper.java
│   │   │               │   ├── PurchaseRecordMapper.java
│   │   │               │   ├── UserMapper.java
│   │   │               │   └── VehicleMapper.java
│   │   │               ├── domain
│   │   │               │   ├── Node.java
│   │   │               │   ├── SexEnum.java
│   │   │               │   ├── User.java
│   │   │               │   ├── ValidatorPojo.java
│   │   │               │   ├── business
│   │   │               │   │   ├── ProductPO.java
│   │   │               │   │   └── PurchaseRecordPO.java
│   │   │               │   └── mybatis
│   │   │               │       ├── Author.java
│   │   │               │       ├── Blog.java
│   │   │               │       ├── Comment.java
│   │   │               │       ├── DraftPost.java
│   │   │               │       ├── Post.java
│   │   │               │       ├── Tag.java
│   │   │               │       └── discriminator
│   │   │               │           ├── Car.java
│   │   │               │           ├── Suv.java
│   │   │               │           ├── Truck.java
│   │   │               │           └── Vehicle.java
│   │   │               ├── provider
│   │   │               │   ├── AuthorProvider.java
│   │   │               │   ├── PurchaseRecordProvider.java
│   │   │               │   └── UserProvider.java
│   │   │               ├── service
│   │   │               │   ├── AsyncService.java
│   │   │               │   ├── HelloService.java
│   │   │               │   ├── PurchaseService.java
│   │   │               │   ├── UserBatchService.java
│   │   │               │   ├── UserCacheService.java
│   │   │               │   ├── UserService.java
│   │   │               │   ├── UserValidator.java
│   │   │               │   ├── impl
│   │   │               │   │   ├── AsyncServiceImpl.java
│   │   │               │   │   ├── HelloServiceImpl.java
│   │   │               │   │   ├── PurchaseServiceImpl.java
│   │   │               │   │   ├── UserBatchSeviceImpl.java
│   │   │               │   │   ├── UserCacheServiceImpl.java
│   │   │               │   │   ├── UserServiceImpl.java
│   │   │               │   │   └── UserValidatorImpl.java
│   │   │               │   └── scheduling
│   │   │               │       └── SchedulingService.java
│   │   │               ├── tools
│   │   │               │   └── DataSourceShow.java
│   │   │               ├── typehandler
│   │   │               │   └── SexTypeHandler.java
│   │   │               └── vo
│   │   │                   ├── CommonResult.java
│   │   │                   ├── ResultVO.java
│   │   │                   └── UserVO.java
│   │   ├── resources
│   │   │   ├── application.properties
│   │   │   ├── mapper
│   │   │   │   ├── authorMapper.xml
│   │   │   │   ├── blogMapper.xml
│   │   │   │   ├── nodeMapper.xml
│   │   │   │   ├── productMapper.xml
│   │   │   │   ├── purchaseRecordMapper.xml
│   │   │   │   ├── userMapper.xml
│   │   │   │   └── vehicleMapper.xml
│   │   │   ├── properties
│   │   │   │   ├── international.properties
│   │   │   │   ├── international_en_US.properties
│   │   │   │   ├── international_zh_CN.properties
│   │   │   │   └── mybatis-config-template.xml
│   │   │   └── templates
│   │   │       └── index.html
│   │   └── webapp
│   │       └── WEB-INF
│   │           └── jsp
│   │               ├── formatter.jsp
│   │               ├── index.jsp
│   │               ├── international.jsp
│   │               ├── logout.jsp
│   │               ├── logoutResult.jsp
│   │               ├── productList.jsp
│   │               ├── purchase.jsp
│   │               ├── recordList.jsp
│   │               ├── register.jsp
│   │               ├── restful.jsp
│   │               ├── session.jsp
│   │               ├── sessionDetail.jsp
│   │               ├── shutdown.jsp
│   │               ├── upload.jsp
│   │               ├── userDetail.jsp
│   │               ├── userList.jsp
│   │               ├── validator.jsp
│   │               └── welcome.jsp
│   └── test
│       └── java
│           └── cn
│               └── zyt
│                   └── springbootlearning
│                       ├── SpringbootLearningApplicationTests.java
│                       ├── config
│                       │   └── RedisConfigurationTest.java
│                       ├── dao
│                       │   ├── AuthorMapperTest.java
│                       │   └── UserMapperTest.java
│                       └── service
│                           └── UserServiceTest.java
└── upload
    └── Xnip2020-02-01_15-18-04.jpg
```
如上目录结构生成的命令如下：
```bash
tree -L 10 -I "target|static"
```

### 项目实现功能
对项目中主要实现的示例功能的相关包和类进行说明：

1.简单探索Spring AOP的面向切面编程，使用简单的约定流程的方式理解AOP的相关概念，主要包含的包和类如下：
```
# 该package中定义了AOP中相关的拦截器，启动器，动态代理等
cn.zyt.springbootlearning.aop.*
# 自定义实现的切面，包括切点、各类通知和连接点概念
MyAspect
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
14.详细使用MyBatis:

(1) 主要包括MyBatis注解的使用和Provider的使用
```
# 对Mapper进行测试的Controller
cn.zyt.springbootlearning.controller.MapperTestController
# 下面三个文件为更新后的文件
cn.zyt.springbootlearning.dao.UserMapper
cn.zyt.springbootlearning.provider.UserProvider
WEB-INF/jsp/index.jsp
```
(2) MyBatis的ResultMap详细使用：
```
# 相应的domain package，包括该包中的所有类
cn.zyt.springbootlearning.domain.mybatis.*
# Author和Blog类对应的mapper接口文件
cn.zyt.springbootlearning.dao.AuthorMapper
cn.zyt.springbootlearning.dao.BlogMapper

# Author和Blog对应的Provider类
cn.zyt.springbootlearning.provider.AuthorProvider

# Author和Blog对应的mybatis映射文件，其中包括了resultMap的使用
mapper/authorMapper.xml
mapper/blogMapper.xml

# 用于对Author和Blog进行测试的Controller类
cn.zyt.springbootlearning.controller.ResultMapTestController
WEB-INF/jsp/index.jsp

```
(3) ResultMap中<discriminator>的使用
```$xslt
# 基础DOMAIN类，其中Vehcile为基类，Car，Suv和Truck继承自Vehicle
cn.zyt.springbootlearning.domain.mybatis.discriminator.Vehicle
cn.zyt.springbootlearning.domain.mybatis.discriminator.Car extend Vehicle
cn.zyt.springbootlearning.domain.mybatis.discriminator.Suv extend Vehicle
cn.zyt.springbootlearning.domain.mybatis.discriminator.Truck extend Vehicle
# MyBatis配置文件，其中定义了resultmap并使用discriminator。根据vehicle_type查询字段判断，
vehicle_type=1则映射为Car对象，=2则映射为Suv对象，=3则映射为Truck对象，否则将查询结果映射为Vehicle基类对象
mapper/vehicleMapper.xml
cn.zyt.springbootlearning.dao.VehicleMapper
# 进行测试的控制类
cn.zyt.springbootlearning.controller.ResultMapTestController
WEB-INF/jsp/index.jsp
```
15.开启并使用Spring异步线程池
```$xslt
# Spring配置类使用@EnableAsync开启异步线程池
cn.zyt.springbootlearning.config.AsyncConfiguration

# 定义包含异步方法的服务
cn.zyt.springbootlearning.service.AsyncService
# 在Service中使用@Async注解指明需要使用异步的方法
cn.zyt.springbootlearning.service.impl.AsyncServiceImpl

# 测试异步方法的请求和调用是否是同一个线程
cn.zyt.springbootlearning.controller.AsyncController
WEB-INF/jsp/index.jsp
```
16.开启并使用Spring定时任务
```$xslt
# 在configuration中使用EnableScheduling开启定时任务，定时任务一般需要异步执行，所以设置在异步管理类中
cn.zyt.springbootlearning.config.AsyncConfiguration
# 具体实现的定时任务
cn.zyt.springbootlearning.service.scheduling.SchedulingService
```
17.开启Spring Security进行用户登录登出验证
```$xslt
# 加入Spring security依赖
pom.xml
# 配置用户名和角色已经验证的请求
cn.zyt.springbootlearning.config.WebSecurityConfiguration
# 配置登出的controller，映射登出页面
cn.zyt.springbootlearning.config.WebMvcConfiguration
cn.zyt.springbootlearning.controller.ApplicationController
# logout为登出页面，logoutResult为登出成功页面
WEB-INF/jsp/logout.jsp
WEB-INF/jsp/logoutResult.jsp
```
18.开启HTTP Actuator端点监测
```$xslt
# 引入Actuator依赖
pom.xml
# 加入端点配置
application.properties
# 配置访问权限和路径
cn.zyt.springbootlearning.config.WebSecurityConfiguration
```
(1) 开启shutdown端点
```$xslt
# 关闭服务页面controller
cn.zyt.springbootlearning.controller.ApplicationController
# 关闭页面
WEB-INF/jsp/shutdown.jsp
```
(2) 自定义监测端点
```$xslt
cn.zyt.springbootlearning.component.endpoints.DataBaseConnectionEndpoint
```
(3) 自定义health监测指标
```$xslt
# 开启health监测指标配置
application.properties
# 自定义www health指标
cn.zyt.springbootlearning.component.indicator.WwwHealthIndicator

```
19.理解Spring-data-redis项目中spring整合redis的过程
```$xslt
# 引入redis和jedis客户端依赖
pom.xml
# redisconfiguration类
cn.zyt.springbootlearning.config.RedisConfiguration
# 相关的Test类
cn.zyt.springbootlearning.config.RedisConfigurationTest
```
20.Spring Boot整合Redis已经RedisTemplate操作Redis基本数据类型示例
```
# Redis连接池配置文件
application.properties
# SpringRedis配置类，设置Redis序列化器
cn.zyt.springbootlearning.config.SpringRedisConfiguration
# 使用RedisTemplate操作Redis基本数据结构示例，包括String,hash,list,set,zset
cn.zyt.springbootlearning.controller.RedisController
# 相关的请求路径
WEB-INF/jsp/index.jsp
```
21.Redis中中其他功能的使用：事务处理、Redis pipeline、Redis运行lua脚本
```
# 该controller暂时无测试URL
cn.zyt.springbootlearning.controller.RedisMultiController
```
22.Spring Boot开启Redis Cache
```
# 配置Redis缓存管理器
application.properties
# 使用cacheEnabled注解使用Spring缓存注解
cn.zyt.springbootlearning.config.SpringRedisConfiguration
# 测试逻辑service类，缓存注解在impl类中
cn.zyt.springbootlearning.service.UserCacheService
cn.zyt.springbootlearning.service.impl.UserCacheServiceImpl
# 测试controller类
cn.zyt.springbootlearning.controller.UserCacheController
# 测试URL
WEB-INF/jsp/index.jsp
# 自定义缓存管理器
cn.zyt.springbootlearning.config.SpringRedisConfiguration
```

### 项目中包含的商品抢购实例

该项目做包含的基础结构（从上述整体结构中进行提取）：
```
.
├── HELP.md
├── README.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── springboot-learning.iml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── cn
│   │   │       └── zyt
│   │   │           └── springbootlearning
│   │   │               ├── ServletInitializer.java
│   │   │               ├── SpringbootLearningApplication.java
│   │   │               ├── ...
│   │   │               ├── controller
│   │   │               │   ├── ProductController.java
│   │   │               │   ├── PurchaseController.java
│   │   │               │   ├── PurchaseRecordController.java
│   │   │               ├── dao
│   │   │               │   ├── ProductMapper.java
│   │   │               │   └── PurchaseRecordMapper.java
│   │   │               ├── domain
│   │   │               │   └── business
│   │   │               │       ├── ProductPO.java
│   │   │               │       └── PurchaseRecordPO.java
│   │   │               ├── provider
│   │   │               │   ├── AuthorProvider.java
│   │   │               │   ├── PurchaseRecordProvider.java
│   │   │               ├── service
│   │   │               │   ├── PurchaseService.java
│   │   │               │   └── impl
│   │   │               │       └── PurchaseServiceImpl.java
│   │   │               └── vo
│   │   │                   └── CommonResult.java
│   │   ├── resources
│   │   │   ├── application.properties
│   │   │   ├── mapper
│   │   │   │   ├── productMapper.xml
│   │   │   │   └── purchaseRecordMapper.xml
│   │   │   ├── properties
│   │   └── webapp
│   │       └── WEB-INF
│   │           └── jsp
│   │               ├── index.jsp
│   │               ├── productList.jsp
│   │               ├── purchase.jsp
│   │               └── recordList.jsp
│   └── test
└── upload
```
其中cn.zyt.springbootlearning.service.impl.PurchaseServiceImpl类中包含了主要的抢购逻辑。
上述的结构为基础是结构，实现了正常商品购买的业务逻辑和整体框架，在大量并发的情况中会出现超发的问题。

例如在对product_id=2的商品进行2000次并发请求，购买1000件商品时，最后在数据库中得到的购买记录为1002个，
并且商品的stock变为-2，这就是出现了超发的问题。（该记录仍保存在数据库中），此时的sql语句为：
```
<select id="getProduct" parameterType="long" resultMap="productMap">
        select id, product_name, stock, price, version, note
        from tb_product
        where id=#{id}
</select>
```
使用如下命令来测试购买完成后的时间间隔以判断性能：
```
select min(purchase_time), max(purchase_time) from tb_purchase_record where product_id=2;
```
结果为1000条记录，22s完成。

下面通过几种不同的方法来解决该问题。主要包含如下的几种方法，并对相应的代码进行了改造。
1.使用MySQL悲观锁
```
<!-- 出现超发现象，主要是因为共享资源（这里是stock）被多少个线程修改从而出现并发问题，
         这里使用for update的MySQL悲观锁来解决超发问题 -->
<select id="getProduct" parameterType="long" resultMap="productMap">
        select id, product_name, stock, price, version, note
        from tb_product
        where id=#{id} for update
</select>
# 相应的service方法
cn.zyt.springbootlearning.service.impl.PurchaseServiceImpl#purchase
```
使用product_id=3来进行测试，stock=-1。MySQL行锁失效，暂时未找到原因。

2.使用乐观锁(CAS)模式
使用product_id=4来进行测试，存在200多个stock没有被消费，性能：41s
```
# 相应的方法
cn.zyt.springbootlearning.service.impl.PurchaseServiceImpl#purchaseCAS
```

3.使用改进的乐观锁模式
(1) 使用时间戳限制重入的乐观锁
product_id=5进行测试，stock并发没有问题，stock成功为0。性能：39s
```
# 相应的方法
cn.zyt.springbootlearning.service.impl.PurchaseServiceImpl#purchaseCASWithTime
```

(2) 使用限定次数重入的乐观锁
使用product_id=6进行测试，因为次数的限制，有93个stock没有被消费，性能26s。
将count次数增加到5进行测试，所有stock成功被消费，性能：27s。
```
# 相应的方法
cn.zyt.springbootlearning.service.impl.PurchaseServiceImpl#purchaseCASWithCount
```

4.使用Redis来处理大量并发情况
使用product_id=7进行测试
