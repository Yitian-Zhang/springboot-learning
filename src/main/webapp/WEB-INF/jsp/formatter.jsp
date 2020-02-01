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
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/icon.css">
    <script type="text/javascript" src="/static/jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="/static/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.0.js"></script>
    <script type="text/javascript">

    </script>
</head>
<body>
    <form id="insertForm" method="post" action="./formatter/commit">
        <table>
            <tr>
                <td>日期：</td>
                <td><input id="date" name="date" type="text" value="2020-01-30"></td>
            </tr>
            <tr>
                <td>金额：</td>
                <td><input id="number" name="number" type="text" value="1,234,567.89"></td>
            </tr>
            <tr>
                <td align="right" style="height: 32px;"><input type="submit" value="SUBMIT"></td>
            </tr>
        </table>
    </form>
    <div><a href="http://localhost:8080/web/index">返回首页</a></div>
</body>
</html>
