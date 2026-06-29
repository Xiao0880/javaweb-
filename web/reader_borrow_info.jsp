<%@ page import="Entity.Borrow" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.ViewBorrowInfo" %><%--
  Created by IntelliJ IDEA.
  User: xlw
  Date: 2026/6/27
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>借阅记录</title>
</head>
<body>
<%
    List<ViewBorrowInfo> borrowInfoList = (List<ViewBorrowInfo>) request.getAttribute("borrowInfoList");
    int borrowCount = (borrowInfoList != null) ? borrowInfoList.size() : 0;
%>

<table border="1">
    <!-- 表头行 -->
    <tr>
        <th>书名</th>
        <th>读者姓名</th>
        <th>借阅日期</th>
        <th>状态</th><!-- 借阅状态(是否超时) -->
        <th></th>
    </tr>
    <!-- 数据行 -->
    <% if (borrowInfoList != null && !borrowInfoList.isEmpty()) {
        for (ViewBorrowInfo borrow : borrowInfoList) { %>
    <tr>
        <td><%= borrow.getBookName() %></td>
        <td><%= borrow.getReaderName() %></td>
        <td><%= borrow.getBorrowDate() %></td>
        <% if (borrow.getReturnDate() != null) { %>
        <td>已归还</td>
        <% } else { %>
        <td>未归还</td>
        <% } %>
        <td><a href="return.do?borrow_id=<%= borrow.getBorrowId() %>">归还</a></td>
    </tr>
    <% } %>
    <% } else { %>
    <tr>
        <td colspan="4">暂无搜索结果</td>
    </tr>
    <% } %>

</table>
</body>
</html>
