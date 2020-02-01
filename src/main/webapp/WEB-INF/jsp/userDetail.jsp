<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yitian
  Date: 2020-01-29
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/icon.css">
    <script type="text/javascript" src="/static/jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="/static/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    <script type="text/javascript">

    </script>
</head>
<body>
    <div id="cc" class="easyui-layout" style="width:100%;height:80%;">
        <table>
            <tr>
                <td>用户ID：</td>
                <td>${user.id}</td>
            </tr>
            <tr>
                <td>用户名称：</td>
                <td>${user.userName}</td>
            </tr>
            <tr>
                <td>性别：</td>
                <td>${user.sex}</td>
            </tr>
            <tr>
                <td>备注：</td>
                <td>${user.note}</td>
            </tr>
        </table>
    </div>
    <div><a href="http://localhost:8080/web/index">返回首页</a></div>

</body>
</html>
