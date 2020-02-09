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
    <title>Application Shutdown</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/icon.css">
    <script type="text/javascript" src="/static/jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="/static/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.0.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {

            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");

            $(document).ajaxSend(function (e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });

            $("#submit").click(function () {
                <%--alert("${_csrf.token}");--%>
                $.post({
                    url: "/actuator/shutdown",
                    success: function (result) {
                        if (result != null || result.message != null) {
                            alert(result.message);
                            return;
                        }
                        alert("关闭Spring Boot应用失败！");
                    }
                })
            })
        })
    </script>
</head>
<body>
    <input id="submit" name="submit" type="button" value="关闭应用" />
    <div><a href="http://localhost:8080/web/index">返回首页</a></div>
</body>
</html>
