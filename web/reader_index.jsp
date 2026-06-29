<%@ page import="Entity.Reader" %><%--
  Created by IntelliJ IDEA.
  User: xlw
  Date: 2026/6/27
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    // 检查用户是否已登录（从 session 中获取用户名）
    Reader reader = (Reader) session.getAttribute("reader");
    String reader_name = reader.getReaderName();
    if (reader_name == null || reader_name.trim().isEmpty()) {
        // 未登录则重定向到登录页
        response.sendRedirect(request.getContextPath() + "/reader_login.html");
        return;
    }
%>
<html>
<head>
    <title>首页</title>
</head>
<body>
    <h1><%= reader_name %>欢迎来到图书馆系统</h1>
    <div class="navbar-links">

        <a href="reader_info.do">个人信息</a> <!-- 个人信息页面-->
        <a href="borrow_info.do">借阅记录</a> <!-- 借阅记录页面（未实现）-->

    </div>
    <form action="search_book" method="get">
        <div class="search-part">
            <input type="text" id="search_text" name="search_text" placeholder="按书名、作者、ISBN等搜索...">
            <button type="submit">搜索</button>
        </div>
    </form>

</body>
</html>
