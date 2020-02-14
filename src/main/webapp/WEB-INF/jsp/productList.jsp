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
    <title>Product List</title>
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

            $('#addProduct').click(function () {
                // alert('easyui');

                // 获取查询参数
                var productName = $("#productName").val();
                var stock = $("#stock").val();
                var price = $("#price").val();
                var note = $("#note").val();

                // 组织参数
                var params = {
                    productName: productName,
                    stock: stock,
                    price: price,
                    version: 0,
                    note: note
                };

                $.post("./insert", params, function (result) {
                   alert(result.msgInfo);
                   // 刷新页面
                    window.location.reload();
                });


                // 获取请求返回值并重新载入表格
                // var opts = $("#dg").datagrid("options");
                // opts.url = "./list";
                // $("#dg").datagrid('reload');
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
                        <td>商品名称: </td>
                        <td><input id="productName" name="productName" class="easyui-textbox" data-options="prompt:'输入商品名称'"
                                   style="width:100%; height: 32px"></td>
                        <td>库存: </td>
                        <td><input id="stock" name="stock" class="easyui-textbox" data-options="prompt:'输入商品库存'"
                                   style="width:100%; height: 32px"></td>
                        <td>单价：</td>
                        <td><input id="price" name="price" class="easyui-textbox" data-options="prompt:'输入商品单价'"
                                   style="width:100%; height: 32px"></td>
                        <td>备注：</td>
                        <td><input id="note" name="note" class="easyui-textbox" data-options="prompt:'输入商品备注'"
                                   style="width:100%; height: 32px"></td>
                        <td><a id="addProduct" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">添加商品记录</a>
                        </td>
                    </tr>
                </table>
            </form>

        </div>
        <div data-options="region:'center',title:'商品记录列表，记录数：${productList.size()}',iconCls:'icon-ok'" style="padding:5px;background:#eee;">
            <table id="dg" class="easyui-datagrid" data-options="border:true,singleSelect:true,fit:true,fitColumns:true">
                <thead>
                    <tr>
                        <th data-options="field:'id'">id</th>
                        <th data-options="field:'productName'">productName</th>
                        <th data-options="field:'stock'">stock</th>
                        <th data-options="field:'price'">price</th>
                        <th data-options="field:'version'">version</th>
                        <th data-options="field:'note'">note</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${productList}" var="product">
                    <tr>
                        <td>${product.id}</td>
                        <td>${product.productName}</td>
                        <td>${product.stock}</td>
                        <td>${product.price}</td>
                        <td>${product.version}</td>
                        <td>${product.note}</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div><a href="http://localhost:8080/web/index">返回首页</a></div>

</body>
</html>
