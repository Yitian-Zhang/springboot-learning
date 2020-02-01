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
    <script>
        $(function(){
            $('#btn').bind('click', function(){
                // alert('easyui');
                var opts = $("#dg").datagrid("options");
                opts.url = "./searchUser";
                // 获取查询参数
                var userId = $("#userId").val();
                var userName = $("#userName").val();
                // 组织参数
                var params = {};
                if (userId != null && userId.trim() != '') {
                    params.userId = userId;
                }
                if (userName != null && userName.trim() != '') {
                    params.userName = userName;
                }
                // 获取请求返回值并重新载入表格
                $("#dg").datagrid('load', params);
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
                        <td>用户名称: </td>
                        <td><input id="userName" name="userName" class="easyui-textbox" data-options="prompt:'输入用户名称'"
                                   style="width:100%; height: 32px"></td>
                        <td><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">easyui</a>
                        </td>
                    </tr>
                </table>
            </form>

        </div>

<%--        <div data-options="region:'south',title:'South Title',split:true" style="height:100px;"></div>--%>
<%--        <div data-options="region:'east',title:'East',split:true" style="width:100px;"></div>--%>
<%--        <div data-options="region:'west',title:'West',split:true" style="width:100px;"></div>--%>
        <div data-options="region:'center',title:'用户列表',iconCls:'icon-ok'" style="padding:5px;background:#eee;">
            <table id="dg" class="easyui-datagrid" data-options="border:true,singleSelect:true,fit:true,fitColumns:true">
                <thead>
                    <tr>
                        <th data-options="field:'id'">UserId</th>
                        <th data-options="field:'userName'">UserName</th>
                        <th data-options="field:'sex'">Sex</th>
                        <th data-options="field:'note'">Note</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${userList}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.userName}</td>
                        <td>${user.sex}</td>
                        <td>${user.note}</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div><a href="http://localhost:8080/web/index">返回首页</a></div>

</body>
</html>
