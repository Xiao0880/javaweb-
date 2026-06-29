<%@ page import="Entity.Reader" %><%--
  Created by IntelliJ IDEA.
  User: xlw
  Date: 2026/6/27
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息</title>
</head>
<body>
<h2>个人信息</h2>

<%
    Reader reader = (Reader) request.getAttribute("reader");
%>
<table border="1">
    <tr>
        <td>读者ID</td>
        <td><%= reader.getReaderId()%></td>
    </tr>
    <tr>
        <td>读者姓名</td>
        <td><%= reader.getReaderName()%></td>
    </tr>
    <tr>
        <td>密码</td>
        <td><%= reader.getReaderPassword()%></td>
    </tr>
    <tr>
        <td>性别</td>
        <td><%= reader.getGender()%></td>
    </tr>
    <tr>
        <td>联系电话</td>
        <td><%= reader.getPhone()%></td>
    </tr>
    <tr>
        <td>注册日期</td>
        <td><%= reader.getRegisterDate()%></td>
    </tr>
</table>
</body>
</html>