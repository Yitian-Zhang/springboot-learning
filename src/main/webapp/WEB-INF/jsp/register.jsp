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
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/icon.css">
    <script type="text/javascript" src="/static/jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="/static/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#submit").click(function () {
                var token = $("meta[name='_csrf']").attr("content");
                var header = $("meta[name='_csrf_header']").attr("content");

                $(document).ajaxSend(function (e, xhr, options) {
                    xhr.setRequestHeader(header, token);
                });

                var userName = $("#userName").val();
                var note = $("#note").val();
                var sex = $("#sex").val();
                if (userName == '' || sex == '' || note == '') {
                    alert("表单参数需填满!");
                    return;
                }
                var params = {
                    userName: userName,
                    sex: sex,
                    note: note
                };
                $.post({
                    url: "./createUser",
                    contentType: "application/json",
                    data: JSON.stringify(params),
                    success: function (result) {
                        if (result == null || result.userName == null) {
                            alert("注册失败！");
                            return;
                        }
                        alert("注册成功！");
                    }
                });
            });
        });
    </script>
</head>
<body>
    <form id="insertForm" method="post">
        <table>
            <tr>
                <td>用户名：</td>
                <td><input id="userName" name="userName"></td>
            </tr>
            <tr>
                <td>性别：</td>
                <td><input id="sex" name="sex"></td>
            </tr>
            <tr>
                <td>备注：</td>
                <td><input id="note" name="note"></td>
            </tr>
            <tr>
                <td></td>
                <td align="right" style="height: 32px;"><input id="submit" type="button" value="SUBMIT"></td>
            </tr>
        </table>
    </form>
    <div><a href="http://localhost:8080/web/index">返回首页</a></div>
</body>
</html>
