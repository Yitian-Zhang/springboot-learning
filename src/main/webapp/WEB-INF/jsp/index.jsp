<%--
  Created by IntelliJ IDEA.
  User: yitian
  Date: 2020-01-29
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index Page</title>
    <script type="text/javascript">

    </script>
    <style type="text/css">
        p.top-title {
            color: black;
            font-size: 30px;
            padding-top: 20px;
            padding-bottom: 0px;
            margin-bottom: 0px;
        }

        p.top-author {
            margin-top: 5px;
        }
    </style>
</head>
<body>
    <div>
        <p class="top-title">本项目中包含的测试页面</p>
        <p class="top-author">
            Author: yitian,
            GitHub:
            <a href="https://github.com/Yitian-Zhang/springboot-learning">
                https://github.com/Yitian-Zhang/springboot-learning
            </a>
        </p>

    </div>
    <table style="border: #1e282c; background: white">
        <tr>
            <td>
                Spring AOP测试：
            </td>
            <td>
                <hr/>
                <a href="http://localhost:8080/user/print?id=1&useName=yitian&sex=1&note=none">
                    测试MyAspect：/user/print?id=1&useName=yitian&sex=1&note=none</a><br>
                <a href="http://localhost:8080/user/checkandprint?id=1&useName=yitian&sex=1&note=none">
                    使用@DeclareParents引入UserValidator接口：/user/checkandprint?id=1&useName=yitian&sex=1&note=none</a><br>
                <a href="http://localhost:8080/user/manyAspects">
                    多个切面运行测试：/user/manyAspects</a><br>
            </td>
        </tr>

        <tr>
            <td>
                数据创建与显示页面：
            </td>
            <td>
                <hr/>
                <a href="http://localhost:8080/user/userList">UserList页面：/user/userList</a><br>
                <a href="http://localhost:8080/user/register">InsertUser页面：/user/register</a><br>
            </td>
        </tr>
        <tr>
            <td>
                参数传递URL：
            </td>
            <td>
                <hr/>
                <a href="http://localhost:8080/param/no/annotation?intVal=10&longVal=200">
                    参数传递: /param/no/annotation?intVal=10&longVal=200</a><br>
                <a href="http://localhost:8080/param/annotation?int_val=10&long_val=20&str_val=yitian">
                    参数传递: /param/annotation?int_val=10&long_val=20&str_val=yitian</a><br>
                <a href="http://localhost:8080/param/requestArray?intArr=10,11,12&longArr=20,30,40&strArr=yitian1,yitian2">
                    参数传递: /param/requestArray?intArr=10,11,12&longArr=20,30,40&strArr=yitian1,yitian2</a><br>
                <a href="http://localhost:8080/param/1">
                    参数传递: /param/1</a><br>
                <a href="http://localhost:8080/param/formatter">
                    格式化参数传递页面: /param/formatter</a><br>
                <a href="http://localhost:8080/param/converter?user=username1-2-thisisanote">
                    自定义Converter参数传递: /param/converter?user=username1-2-thisisanote</a><br>
                <a href="http://localhost:8080/param/list?userList=user1-1-note1,user2-2-note2,user3-1-note3">
                    自定义Converter参数传递(数组传递): /param/list?userList=user1-1-note1,user2-2-note2,user3-1-note3</a><br>
                <a href="http://localhost:8080/param/valid/page">
                    参数校验页面: /param/valid/page</a><br>
                <a href="http://localhost:8080/param/valid/user-validator?user=-1-note1&date=2020-02-01">
                    自定义参数校验页面: /param/valid/user-validator?user=-1-note1&date=2020-02-01</a><br>
                <a href="http://localhost:8080/param/valid/user?user=username-1-">
                    自定义参数校验页面: /param/valid/user?user=username-1-</a><br>
            </td>
        </tr>
        <tr>
            <td>
                DataModel URL：
            </td>
            <td>
                <hr/>
                <a href="http://localhost:8080/data/model?id=1">
                    DataModel请求1: /data/model?id=1</a><br>
                <a href="http://localhost:8080/data/modelMap?id=1">
                    DataModel请求2: /data/modelMap?id=1</a><br>
                <a href="http://localhost:8080/data/modelandview?id=1">
                    DataModel请求3: /data/modelandview?id=1</a><br>
            </td>
        </tr>
        <tr>
            <td>
                文件上传/拦截器/国际化页面：
            </td>
            <td>
                <hr/>
                <a href="http://localhost:8080/file/upload/page">
                    文件上传页面: /file/upload/page</a><br>
                <a href="http://localhost:8080/interceptor/start">
                    拦截器欢迎页面：/interceptor/start></a><br>
                <a href="http://localhost:8080/international/page">
                    国际化页面：/international/page</a><br>
            </td>
        </tr>
        <tr>
            <td>
                参数重定向URL：
            </td>
            <td>
                <hr/>
                <a href="http://localhost:8080/redirect/redirect1ById?userName=reUserName1&sex=1&note=none">
                    使用ID重定向1：/redirect/redirect1ById?userName=reUserName1&sex=1&note=none</a><br>
                <a href="http://localhost:8080/redirect/redirect2ById?userName=reUserName1&sex=1&note=none">
                    使用ID重定向2：/redirect/redirect2ById?userName=reUserName1&sex=1&note=none</a><br>
                <a href="http://localhost:8080/redirect/redirectUser1?userName=reUserName1&sex=1&note=none">
                    带参数重定向1：/redirect/redirectUser1?userName=reUserName1&sex=1&note=none</a><br>
                <a href="http://localhost:8080/redirect/redirectUser2?userName=reUserName1&sex=1&note=none">
                    带参数重定向2：/redirect/redirectUser2?userName=reUserName2&sex=1&note=none</a><br>
            </td>
        </tr>
        <tr>
            <td>
                操作Session和RESTful：
            </td>
            <td>
                <hr/>
                <a href="http://localhost:8080/session/page">
                    Session页面：/session/page</a><br>
                <a href="http://localhost:8080/restful/page">
                    RESTful页面：/restful/page</a><br>
            </td>
        </tr>
        <tr>
            <td>
                MyBatis注解和Provider学习：
            </td>
            <td>
                <hr/>
                <a href="http://localhost:8080/mapper/annotation/getUserById?id=1">
                    MyBatis注解测试（getUserById）：/mapper/annotation/getUserById?id=1</a><br>
                <a href="http://localhost:8080/mapper/annotation/searchUser?id=1&userName=yitian">
                    MyBatis注解测试（searchUser）：/mapper/annotation/searchUser?id=1&userName=yitian</a><br>
                <a href="http://localhost:8080/mapper/provider/getUser?id=1">
                    MyBatis Provider测试（getUser）：/mapper/provider/getUser?id=1</a><br>
                <a href="http://localhost:8080/mapper/provider/getUserList">
                    MyBatis Provider测试（getUserList）：/mapper/provider/getUserList</a><br>
                <a href="http://localhost:8080/mapper/annotation/insertUser?userName=yitian&sex=1&note=none">
                    MyBatis注解测试（insertUser）：/mapper/annotation/insertUser?userName=yitian&sex=1&note=none</a><br>
                <a href="http://localhost:8080/mapper/provider/insertUser?userName=yitian&sex=1&note=none">
                    MyBatis Provider测试（insertUser）：/mapper/provider/insertUser?userName=yitian&sex=1&note=none</a><br>
                <a href="http://localhost:8080/mapper/annotation/updateUser?id=3&userName=yitian&sex=1&note=none">
                    MyBatis注解测试（updateUser）：/mapper/annotation/updateUser?id=3&userName=yitian&sex=1&note=none</a><br>
                <a href="http://localhost:8080/mapper/provider/updateUser?id=4&userName=yitian&sex=1&note=none">
                    MyBatis Provider测试（updateUser）：/mapper/provider/updateUser?id=4&userName=yitian&sex=1&note=none</a><br>
                <a href="http://localhost:8080/mapper/annotation/deleteUser?id=3">
                    MyBatis注解测试（deleteUser）：/mapper/annotation/deleteUser?id=3</a><br>
                <a href="http://localhost:8080/mapper/provider/deleteUser?id=4">
                    MyBatis Provider测试（deleteUser）：/mapper/provider/deleteUser?id=4</a><br>
            </td>
        </tr>
        <tr>
            <td>
                MyBatis ResultMap深入学习：
            </td>
            <td>
                <hr/>
                <a href="http://localhost:8080/resultmap/getAuthor?id=2">
                    使用ResultMap查找Author：/resultmap/getAuthor?id=2</a><br>
                <a href="http://localhost:8080/resultmap/getBlog?id=1">
                    使用ResultMap查找Blog：/resultmap/getBlog?id=1</a><br>
                <a href="http://localhost:8080/resultmap/getVehicle?id=1">
                    使用ResultMap查找Vehicle1：/resultmap/getVehicle?id=1</a><br>
                <a href="http://localhost:8080/resultmap/getVehicle?id=2">
                    使用ResultMap查找Vehicle2：/resultmap/getVehicle?id=2</a><br>
                <a href="http://localhost:8080/resultmap/getVehicle?id=3">
                    使用ResultMap查找Vehicle3：/resultmap/getVehicle?id=3</a><br>
                <a href="http://localhost:8080/resultmap/getVehicle?id=4">
                    使用ResultMap查找Vehicle4：/resultmap/getVehicle?id=4</a><br>
                <a href="http://localhost:8080/resultmap/getVehicleList">
                    使用ResultMap查找VehicleList：/resultmap/getVehicleList</a><br>
            </td>
        </tr>
        <tr>
            <td>
                Spring异步线程池：
            </td>
            <td>
                <hr/>
                <a href="http://localhost:8080/thread/async">
                    Spring异步方法生成报表（观察后台输出）：/thread/async</a><br>
                <a href="http://localhost:8080/thread/sync">
                    Spring同步方法生成报表（观察后台输出）：/thread/sync</a><br>
            </td>
        </tr>

        <tr>
            <td>
                Spring数据库事务：
            </td>
            <td>
                <hr/>
                <a href="http://localhost:8080/user/getUser?id=1">
                    获取用户（观察DEBUG级别日志）：/user/getUser?id=1</a><br>
                <a href="http://localhost:8080/user/insertUser?userName=tname&sex=1&note=none">
                    插入用户（观察DEBUG级别日志）：/user/insertUser?userName=tname&sex=1&note=none</a><br>
                <a href="http://localhost:8080/user/insertUsers?userName1=tname1&sex1=1&note1=none&userName2=tname2&sex2=1&note2=none">
                    批量插入用户（观察DEBUG级别日志）：/user/insertUsers?userName1=tname1&sex1=1&note1=none&userName2=tname2&sex2=1&note2=none</a><br>
            </td>
        </tr>

        <tr>
            <td>
                Spring项目监测：
            </td>
            <td>
                <hr/>
                <a href="http://localhost:8080/actuator">
                    HTTP监测页面：/actuator</a><br>
                <a href="http://localhost:8080/app/shutdown">
                    HTTP关闭服务：/app/shutdown</a><br>
                <a href="http://localhost:8080/app/logout">
                    Spring Security退出登录：/app/logout</a><br>
            </td>
        </tr>

        <tr>
            <td>
                Spring Boot整合Redis：
            </td>
            <td>
                <hr/>
                <a href="http://localhost:8080/redis/string">
                    Redis的String操作：/redis/string</a><br>
                <a href="http://localhost:8080/redis/hash">
                    Redis的Hash操作：/redis/hash</a><br>
                <a href="http://localhost:8080/redis/list">
                    Redis的List操作：/redis/list</a><br>
                <a href="http://localhost:8080/redis/set">
                    Redis的Set操作：/redis/set</a><br>
                <a href="http://localhost:8080/redis/zset">
                    Redis的zSet操作：/redis/zset</a><br>
            </td>
        </tr>
        <tr>
            <td>
                Spring Boot开启Redis Cache：
            </td>
            <td>
                <hr/>
                <a href="http://localhost:8080/user/cache/getUser?id=1">
                    GetUser：/user/cache/getUser?id=1</a><br>
                <a href="http://localhost:8080/user/cache/insertUser?userName=yitian_cache&sex=1&note=none">
                    InsertUser：/user/cache/insertUser?userName=yitian_cache&sex=1&note=none</a><br>
                <a href="http://localhost:8080/user/cache/findUsers?userName=yitian">
                    FindUsers：/user/cache/findUsers?userName=yitian</a><br>
                <a href="http://localhost:8080/user/cache/deleteUser?id=26">
                    DeleteUser：/user/cache/deleteUser?id=26</a><br>
                <a href="http://localhost:8080/user/cache/updateUserName?id=24&userName=yitian_new">
                    UpdateUserName：/user/cache/updateUserName?id=24&userName=yitian_new</a><br>
            </td>
        </tr>
        <tr>
            <td>
                商品秒杀实例测试URL：
            </td>
            <td>
                <hr/>
                <a href="http://localhost:8080/purchase/page">
                    商品抢购页面：/purchase/page</a><br/>
                <a href="http://localhost:8080/record/list">
                    购买记录页面：/record/list</a><br/>
                <a href="http://localhost:8080/product/list">
                    商品信息页面：/product/list</a><br/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <hr/>
            </td>
        </tr>
    </table>

</body>
</html>
