<%@ page import="Entity.Reader" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <title>图书管理系统 - 个人信息</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="navigation">
        <div class="container">
            <h1>图书管理系统</h1>
            <div class="nav-links">
                <a href="reader_index.jsp">首页</a>
                <a href="borrow_info.do">借阅记录</a>
            </div>
        </div>
    </div>
    
    <div class="container">
        <div class="table-container">
            <h2>个人信息</h2>
            <%
                Reader reader = (Reader) request.getAttribute("reader");
            %>
            <table>
                <tr>
                    <th>读者ID</th>
                    <td><%= reader.getReaderId() %></td>
                </tr>
                <tr>
                    <th>读者姓名</th>
                    <td><%= reader.getReaderName() %></td>
                </tr>
                <tr>
                    <th>密码</th>
                    <td><%= reader.getReaderPassword() %></td>
                </tr>
                <tr>
                    <th>性别</th>
                    <td><%= reader.getGender() %></td>
                </tr>
                <tr>
                    <th>联系电话</th>
                    <td><%= reader.getPhone() %></td>
                </tr>
                <tr>
                    <th>注册日期</th>
                    <td><%= reader.getRegisterDate() %></td>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>