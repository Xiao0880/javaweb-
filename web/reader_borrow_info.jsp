<%@ page import="Entity.Borrow" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.ViewBorrowInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <title>图书管理系统 - 借阅记录</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="navigation">
        <div class="container">
            <h1>图书管理系统</h1>
            <div class="nav-links">
                <a href="reader_index.jsp">首页</a>
                <a href="reader_info.do">个人信息</a>
            </div>
        </div>
    </div>
    
    <div class="container">
        <div class="table-container">
            <h2>借阅记录</h2>
            <table>
                <tr>
                    <th>书名</th>
                    <th>读者姓名</th>
                    <th>借阅日期</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                <% 
                    List<ViewBorrowInfo> borrowInfoList = (List<ViewBorrowInfo>) request.getAttribute("borrowInfoList");
                    if (borrowInfoList != null && !borrowInfoList.isEmpty()) {
                        for (ViewBorrowInfo borrow : borrowInfoList) { 
                %>
                <tr>
                    <td><%= borrow.getBookName() %></td>
                    <td><%= borrow.getReaderName() %></td>
                    <td><%= borrow.getBorrowDate() %></td>
                    <td><%= borrow.getReturnDate() != null ? "已归还" : "未归还" %></td>
                    <td>
                        <% if (borrow.getReturnDate() == null) { %>
                            <a href="return.do?borrow_id=<%= borrow.getBorrowId() %>" class="edit-link">归还</a>
                        <% } %>
                    </td>
                </tr>
                <% } %>
                <% } else { %>
                <tr>
                    <td colspan="5">暂无借阅记录</td>
                </tr>
                <% } %>
            </table>
        </div>
    </div>
</body>
</html>
