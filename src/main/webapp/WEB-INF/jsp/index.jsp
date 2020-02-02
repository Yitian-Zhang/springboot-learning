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
    <H1>本项目中包含的测试页面：</H1><br>
    <a href="http://localhost:8080/user/userList">UserList页面：/user/userList</a><br>
    <a href="http://localhost:8080/user/register">InsertUser页面：/user/register</a><br>
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
    <a href="http://localhost:8080/data/model?id=1">
        DataModel请求1: /data/model?id=1</a><br>
    <a href="http://localhost:8080/data/modelMap?id=1">
        DataModel请求2: /data/modelMap?id=1</a><br>
    <a href="http://localhost:8080/data/modelandview?id=1">
        DataModel请求3: /data/modelandview?id=1</a><br>
    <a href="http://localhost:8080/file/upload/page">
        文件上传页面: /file/upload/page</a><br>
    <a href="http://localhost:8080/interceptor/start">
        拦截器欢迎页面：/interceptor/start></a><br>
</body>
</html>
