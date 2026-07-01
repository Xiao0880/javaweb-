<%@ page import="Entity.Reader" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Reader reader = (Reader) session.getAttribute("reader");
    String reader_name = reader.getReaderName();
    if (reader_name == null || reader_name.trim().isEmpty()) {
        response.sendRedirect(request.getContextPath() + "/reader_login.html");
        return;
    }
%>
<html lang="zh-CN">
<head>
    <title>图书管理系统 - 首页</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="navigation">
        <div class="container">
            <h1>图书管理系统</h1>
            <div class="nav-links">
                <a href="reader_info.do">个人信息</a>
                <a href="borrow_info.do">借阅记录</a>
            </div>
        </div>
    </div>
    
    <div class="container">
        <div class="search-container">
            <h2><%= reader_name %>，欢迎来到图书馆系统</h2>
            <form action="search_book" method="get" class="search-box">
                <input type="text" id="search_text" name="search_text" placeholder="按书名、作者、ISBN等搜索...">
                <button type="submit">搜索</button>
            </form>
        </div>
    </div>
</body>
</html>
