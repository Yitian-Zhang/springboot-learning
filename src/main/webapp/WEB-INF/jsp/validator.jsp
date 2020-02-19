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
    <script src="https://code.jquery.com/jquery-3.2.0.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");

            $(document).ajaxSend(function (e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });

            $("#valid").click(function () {
                var pojo = {
                    id: null,
                    date: '2020-01-30',
                    doubleValue: 100.09,
                    integer: 100,
                    range: 1000,
                    email: 'email',
                    size: "adv2323",
                    regexp: 'a,b,c,d'
                }
                $.post({
                    url: './validate',
                    contentType: 'application/json',
                    data: JSON.stringify(pojo),
                    success: function (result) {

                    }
                });
            });
        });
    </script>
</head>
<body>
    <p>This page for validating params.</p><br>
    <input id="valid" name="valid" type="button" value="Click for Vaild"><br>
    <div><a href="http://localhost:8080/web/index">返回首页</a></div>
</body>
</html>
