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
                    $.post("./start", params, function(result) {
                        // alert(result.msgInfo);
                    });
                }
            });

            $("#submitWithCAS").click(function () {
                var userId = $("#userId1").val();
                var productId = $("#productId1").val();

                for(var i = 1; i <= 2000; i++) {
                    var params = {
                        userId: userId,
                        productId: productId,
                        quantity: 1
                    };
                    $.post("./startCAS", params, function(result) {
                        // alert(result.msgInfo);
                    });
                }
            });

            $("#submitWithCASTime").click(function () {
                var userId = $("#userId1").val();
                var productId = $("#productId1").val();

                for(var i = 1; i <= 2000; i++) {
                    var params = {
                        userId: userId,
                        productId: productId,
                        quantity: 1
                    };
                    $.post("./startCASWithTime", params, function(result) {
                        // alert(result.msgInfo);
                    });
                }
            });

            $("#submitWithCASCount").click(function () {
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

            $("#submitWitRedis").click(function () {
                var userId = $("#userId1").val();
                var productId = $("#productId1").val();

                for(var i = 1; i <= 2000; i++) {
                    var params = {
                        userId: userId,
                        productId: productId,
                        quantity: 1
                    };
                    $.post("./startWithRedis", params, function(result) {
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
                注：可以输入商品数量进行购买。
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
                <button id="submit1" name="submit1">开始抢购商品(MYSQL行锁方式)</button>
            </td>
            <td>
                注：默认2000人抢购1000个商品，每人限买1个商品。(根据数据库所在服务器性能进行调整)
            </td>
        </tr>
        <tr>
            <td>
                <button id="submitWithCAS" name="submit1">开始抢购商品(CAS)</button>
            </td>
            <td>
                注：CAS方式进行商品抢购，使用version避免ABA问题
            </td>
        </tr>
        <tr>
            <td>
                <button id="submitWithCASTime" name="submit1">开始抢购商品(CAS时间戳重入限制)</button>
            </td>
            <td>
                注：CAS方式进行商品抢购，并使用时间戳限制重入时间间隔
            </td>
        </tr>
        <tr>
            <td>
                <button id="submitWithCASCount" name="submit1">开始抢购商品(CAS次数重入限制)</button>
            </td>
            <td>
                注：CAS方式进行商品抢购，并使用计数方式限制重入次数
            </td>
        </tr>
        <tr>
            <td>
                <button id="submitWitRedis" name="submit1">开始抢购商品(Redis)</button>
            </td>
            <td>
                注：使用Redis方式实现高并发下的商品抢购，抢购记录先暂时保存在Redis，后通过定时任务同步至MySQL
            </td>
        </tr>
    </table>
    <div><a href="http://localhost:8080/web/index">返回首页</a></div>
</body>
</html>
