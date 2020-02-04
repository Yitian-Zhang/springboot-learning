<%--
  Created by IntelliJ IDEA.
  User: yitian
  Date: 2020-02-04
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spring RESTfule Page</title>
    <script src="https://code.jquery.com/jquery-3.2.0.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#postCreateUser").click(function () {
                var params = {
                    'userName': 'user_name_new',
                    'sexCode': 1,
                    'note': 'note_new'
                };
                $.post({
                    url: "./user",
                    contentType: "application/json",
                    data: JSON.stringify(params),
                    success: function (result) {
                        if (result == null || result.success == false) {
                            alert("创建用户资源失败");
                            return;
                        } else {
                            alert(result.message);
                        }
                    }
                });
            });


            $("#getUser").click(function () {
               $.get("./user/1", function (result, status) {
                   if (result == null) {
                       alert("资源为空");
                   } else {
                       alert("用户信息为：" + JSON.stringify(result));
                   }
               })
            });

            $("#getUsersByName").click(function () {
                $.get("./user/reUserName1/0/5", function (result, status) {
                    if (result == null) {
                        alert("资源为空");
                    } else {
                        alert("用户集合信息为：" + JSON.stringify(result));
                    }
                })
            });

            $("#putUser").click(function () {
                var params = {
                    'userName': 'yitian',
                    'sexCode': 1,
                    'note': 'note_new'
                };
                $.ajax({
                    url: "./user/1",
                    type: 'PUT',
                    contentType: 'application/json',
                    data: JSON.stringify(params),
                    success: function (result) {
                        if (result == null) {
                            alert("结果为空")
                        } else {
                            alert("用户信息更新为：" + JSON.stringify(result));
                        }
                    }
                });
            });

            $("#deleteUser").click(function () {
                $.ajax({
                    url: "./user/2",
                    type: 'DELETE',
                    contentType: 'application/json',
                    success: function (result) {
                        if (result == null) {
                            alert("结果为空")
                        } else {
                            alert(result.message);
                        }
                    }
                });
            });
        });
    </script>
</head>
<body>

    <H1>测试RESTful下的请求</H1>
    <input type="button" id="postCreateUser" name="postCreateUser" value="使用POST动作创建资源（用户）"> <br>
    <input type="button" id="getUser" name="getUser" value="使用GET获取资源（用户）"><br>
    <input type="button" id="getUsersByName" name="getUsersByName" value="使用GET获取资源（用户）集合"><br>
    <input type="button" id="putUser" name="putUser" value="使用PUT更新资源（用户）"><br>
    <input type="button" id="deleteUser" name="deleteUser" value="使用DELETE删除资源（用户）"><br>

    <div><a href="http://localhost:8080/web/index">返回首页</a></div>
</body>
</html>
