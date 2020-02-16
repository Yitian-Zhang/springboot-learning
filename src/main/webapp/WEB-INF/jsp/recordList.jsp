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
    <title>Record List</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.7.0/themes/icon.css">
    <script type="text/javascript" src="/static/jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="/static/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {

            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");

            $(document).ajaxSend(function (e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });

            $('#btn').click(function () {
                // 获取查询参数
                var userId = $("#userId").val();
                var productId = $("#productId").val();
                // 组织参数
                var params = {};
                if (userId != null && userId.trim() != '') {
                    params.userId = userId;
                }
                if (productId != null && productId.trim() != '') {
                    params.productId = productId;
                }

                // 获取请求返回值并重新载入表格
                var opts = $("#dg").datagrid("options");
                opts.url = "./search";
                $("#dg").datagrid('load', params);
                // window.location.reload();
            });
        });



    </script>
</head>
<body>
    <div id="cc" class="easyui-layout" style="width:100%;height:80%;">
        <div data-options="region:'north',title:'用户搜索',split:true" style="height:80px;">
            <form id="ff" method="post">
                <table>
                    <tr>
                        <td>用户Id: </td>
                        <td><input id="userId" name="userId" class="easyui-textbox" data-options="prompt:'输入用户ID'"
                                   style="width:100%; height: 32px"></td>
                        <td>商品Id: </td>
                        <td><input id="productId" name="productId" class="easyui-textbox" data-options="prompt:'输入商品ID'"
                                   style="width:100%; height: 32px"></td>
                        <td><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询购买记录</a>
                        </td>
                    </tr>
                </table>
            </form>

        </div>
        <div data-options="region:'center',title:'购买记录列表，记录数：${recordList.size()}',iconCls:'icon-ok'" style="padding:5px;background:#eee;">
            <table id="dg" class="easyui-datagrid" data-options="border:true,singleSelect:true,fit:true,fitColumns:true">
                <thead>
                    <tr>
                        <th data-options="field:'id'">id</th>
                        <th data-options="field:'userId'">userId</th>
                        <th data-options="field:'productId'">productId</th>
                        <th data-options="field:'price'">price</th>
                        <th data-options="field:'quantity'">quantity</th>
                        <th data-options="field:'totalPrice'">totalPrice</th>
                        <th data-options="field:'purchaseTime'">purchaseTime</th>
                        <th data-options="field:'note'">note</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${recordList}" var="record">
                    <tr>
                        <td>${record.id}</td>
                        <td>${record.userId}</td>
                        <td>${record.productId}</td>
                        <td>${record.price}</td>
                        <td>${record.quantity}</td>
                        <td>${record.totalPrice}</td>
                        <td>${record.purchaseTime}</td>
                        <td>${record.note}</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div><a href="http://localhost:8080/web/index">返回首页</a></div>

</body>
</html>
