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
    <title>Title</title>
</head>
<body>
    <H1>本项目中包含的Spring MVC测试页面：</H1><br>
    <hr/>
    <a href="http://localhost:8080/user/userList">UserList页面：/user/userList</a><br>
    <a href="http://localhost:8080/user/register">InsertUser页面：/user/register</a><br>
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
    <hr/>
    <a href="http://localhost:8080/param/converter?user=username1-2-thisisanote">
        自定义Converter参数传递: /param/converter?user=username1-2-thisisanote</a><br>
    <a href="http://localhost:8080/param/list?userList=user1-1-note1,user2-2-note2,user3-1-note3">
        自定义Converter参数传递(数组传递): /param/list?userList=user1-1-note1,user2-2-note2,user3-1-note3</a><br>
    <hr/>
    <a href="http://localhost:8080/param/valid/page">
        参数校验页面: /param/valid/page</a><br>
    <a href="http://localhost:8080/param/valid/user-validator?user=-1-note1&date=2020-02-01">
        自定义参数校验页面: /param/valid/user-validator?user=-1-note1&date=2020-02-01</a><br>
    <hr/>
    <a href="http://localhost:8080/data/model?id=1">
        DataModel请求1: /data/model?id=1</a><br>
    <a href="http://localhost:8080/data/modelMap?id=1">
        DataModel请求2: /data/modelMap?id=1</a><br>
    <a href="http://localhost:8080/data/modelandview?id=1">
        DataModel请求3: /data/modelandview?id=1</a><br>
    <hr/>
    <a href="http://localhost:8080/file/upload/page">
        文件上传页面: /file/upload/page</a><br>
    <hr/>
    <a href="http://localhost:8080/interceptor/start">
        拦截器欢迎页面：/interceptor/start></a><br>
    <hr/>
    <a href="http://localhost:8080/international/page">
        国际化页面：/international/page</a><br>
    <hr/>
    <a href="http://localhost:8080/redirect/redirect1ById?userName=reUserName1&sex=1&note=none">
        使用ID重定向1：/redirect/redirect1ById?userName=reUserName1&sex=1&note=none</a><br>
    <a href="http://localhost:8080/redirect/redirect2ById?userName=reUserName1&sex=1&note=none">
        使用ID重定向2：/redirect/redirect2ById?userName=reUserName1&sex=1&note=none</a><br>
    <a href="http://localhost:8080/redirect/redirectUser1?userName=reUserName1&sex=1&note=none">
        带参数重定向1：/redirect/redirectUser1?userName=reUserName1&sex=1&note=none</a><br>
    <a href="http://localhost:8080/redirect/redirectUser2?userName=reUserName1&sex=1&note=none">
        带参数重定向2：/redirect/redirectUser2?userName=reUserName2&sex=1&note=none</a><br>
    <hr/>
    <a href="http://localhost:8080/session/page">
        Session页面：/session/page</a><br>
    <a href="http://localhost:8080/restful/page">
        RESTful页面：/restful/page</a><br>
    <hr/>
    <a href="http://localhost:8080/mapper/annotation/getUserById?id=1">
        MyBatis注解测试（getUserById）：/mapper/annotation/getUserById?id=1</a><br>
    <a href="http://localhost:8080/mapper/annotation/searchUser?id=1&userName=yitian">
        MyBatis注解测试（searchUser）：/mapper/annotation/searchUser?id=1&userName=yitian</a><br>
    <a href="http://localhost:8080/mapper/provider/getUser?id=1">
        MyBatis Provider测试（getUser）：/mapper/provider/getUser?id=1</a><br>
    <a href="http://localhost:8080/mapper/provider/getUserList">
        MyBatis Provider测试（getUserList）：/mapper/provider/getUserList</a><br>
    <hr/>
    <a href="http://localhost:8080/mapper/annotation/insertUser?userName=yitian&sex=1&note=none">
        MyBatis注解测试（insertUser）：/mapper/annotation/insertUser?userName=yitian&sex=1&note=none</a><br>
    <a href="http://localhost:8080/mapper/provider/insertUser?userName=yitian&sex=1&note=none">
        MyBatis Provider测试（insertUser）：/mapper/provider/insertUser?userName=yitian&sex=1&note=none</a><br>
    <hr/>
    <a href="http://localhost:8080/mapper/annotation/updateUser?id=3&userName=yitian&sex=1&note=none">
        MyBatis注解测试（updateUser）：/mapper/annotation/updateUser?id=3&userName=yitian&sex=1&note=none</a><br>
    <a href="http://localhost:8080/mapper/provider/updateUser?id=4&userName=yitian&sex=1&note=none">
        MyBatis Provider测试（updateUser）：/mapper/provider/updateUser?id=4&userName=yitian&sex=1&note=none</a><br>
    <hr/>
    <a href="http://localhost:8080/mapper/annotation/deleteUser?id=3">
        MyBatis注解测试（deleteUser）：/mapper/annotation/deleteUser?id=3</a><br>
    <a href="http://localhost:8080/mapper/provider/deleteUser?id=4">
        MyBatis Provider测试（deleteUser）：/mapper/provider/deleteUser?id=4</a><br>
    <hr/>
</body>
</html>
