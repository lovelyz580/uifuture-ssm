<!--
@author chenhx
@version index.jsp, v 0.1 2018-08-19 下午 6:10
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<h1>文件上传演示</h1>
<form action="uploadUserInfo" enctype="multipart/form-data" method="post">
    <table>
        <tr>
            <td><input type="text" placeholder="用户名" name="username"></td>
        </tr>
        <tr>
            <td>请上传头像:</td>
            <td><input type="file" name="image"></td>
        </tr>
        <tr>
            <td><input type="submit" value="提交"></td>
        </tr>
    </table>
</form>
</body>
</html>