<%@ page import="Entity.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: xlw
  Date: 2026/6/27
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>搜索结果</title>
</head>
<body>
<%
    List<Book> books = (List<Book>) request.getSession().getAttribute("books");
    int bookCount = (books != null) ? books.size() : 0;
%>
<div class="navbar-links">
    <a href="reader_index.jsp">首页</a>
    <a href="reader_info.jsp">个人信息</a> <!-- 个人信息页面（未实现）-->
    <a href="reader_borrow_info.jsp">借阅记录</a> <!-- 借阅记录页面（未实现）-->

</div>
<table border="1">
    <!-- 表头行 -->
    <tr>
        <th>图书编号</th>
        <th>图书名称</th>
        <th>作者</th>
        <th>类型</th>
        <th>出版社</th>
        <th>出版日期</th>
        <th>库存</th>
        <th></th>
    </tr>
    <!-- 数据行 -->
    <% if (books != null && !books.isEmpty()) {
         for (Book book : books) { %>
    <tr>
        <td><%= book.getBookId() %></td>
        <td><%= book.getBookName() %></td>
        <td><%= book.getAuthor() %></td>
        <td><%= book.getTypeName()%></td>
        <td><%= book.getPublisher()%></td>
        <td><%= book.getPublishDate()%></td>
        <td><%= book.getStock()%></td>
        <td><a href="borrow.do?book_id=<%= book.getBookId() %>">借阅</a></td>
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
