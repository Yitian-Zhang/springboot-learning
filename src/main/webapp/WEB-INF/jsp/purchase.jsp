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
    <title>Product Purchase</title>
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
                var userId = $("#userId").val();
                var productId = $("#productId").val();
                var quantity = $("#quantity").val();

                var params = {
                    userId: userId,
                    productId: productId,
                    quantity: quantity
                };

                $.post("./start", params, function(result) {
                    alert(result.msgInfo);
                });
            });

            $("#submit1").click(function () {
                var userId = $("#userId1").val();
                var productId = $("#productId1").val();

                for(var i = 1; i <= 2000; i++) {
                    var params = {
                        userId: userId,
                        productId: productId,
                        quantity: 1
                    };
                    $.post("./startCASWithCount", params, function(result) {
                        // alert(result.msgInfo);
                    });
                }
            });
        })
    </script>
</head>
<body>
    <table>
        <tr>
            <td>用户ID：</td>
            <td>
                <input id="userId" name="userId" type="text"/>
            </td>
        </tr>
        <tr>
            <td>商品ID：</td>
            <td>
                <input id="productId" name="productId" type="text"/>
            </td>
        </tr>
        <tr>
            <td>购买数量：</td>
            <td>
                <input id="quantity" name="quantity" type="text"/>
            </td>
        </tr>
        <tr>
            <td>
                <button id="submit" name="submit">普通购买商品</button>
            </td>
            <td>
                注：可以输入商品熟练进行购买。
            </td>
        </tr>
    </table>
    <table>
        <tr>
            <td>用户ID：</td>
            <td>
                <input id="userId1" name="userId1" type="text"/>
            </td>
        </tr>
        <tr>
            <td>商品ID：</td>
            <td>
                <input id="productId1" name="productId1" type="text"/>
            </td>
        </tr>
        <tr>
            <td>
                <button id="submit1" name="submit1">开始抢购商品</button>
            </td>
            <td>
                注：默认2000人抢购1000个商品，每人限买1个商品。(根据数据库所在服务器性能进行调整)
            </td>
        </tr>
    </table>
    <div><a href="http://localhost:8080/web/index">返回首页</a></div>
</body>
</html>
