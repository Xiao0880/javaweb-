<%@ page import="Entity.Book" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: xlw
  Date: 2026/6/29
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String adminName = (String) session.getAttribute("admin_name");
    if (adminName == null || adminName.trim().isEmpty()) {
        response.sendRedirect(request.getContextPath() + "/admin/admin_login.html");
        return;
    }
    
    List<Book> books = (List<Book>) request.getAttribute("books");
    String searchText = (String) request.getAttribute("searchText");
    String error = (String) request.getAttribute("error");
%>
<html>
<head>
    <title>图书管理</title>
</head>
<body>
    <h1>欢迎，<%= adminName %> - 管理员</h1>
    
    <div class="navbar">
        <a href="admin_statistics.do">首页</a>
        <a href="reader_management.jsp">读者管理</a>
        <a href="book_management.jsp">图书管理</a>
    </div>
    
    <h2>图书管理</h2>
    
    <form action="admin_book_search.do" method="get">
        <input type="text" name="search_text" placeholder="按书名、作者、ISBN搜索...">
        <button type="submit">搜索</button>
    </form>
    
    <% if (error != null) { %>
    <p style="color: red;"><%= error %></p>
    <% } %>
    
    <table border="1">
        <tr>
            <th>图书ID</th>
            <th>书名</th>
            <th>作者</th>
            <th>出版社</th>
            <th>出版日期</th>
            <th>ISBN</th>
            <th>类型ID</th>
            <th>库存</th>
            <th>操作</th>
        </tr>
        <% if (books != null && !books.isEmpty()) {
             for (Book book : books) { %>
        <tr>
            <td><%= book.getBookId() %></td>
            <td><%= book.getBookName() %></td>
            <td><%= book.getAuthor() %></td>
            <td><%= book.getPublisher() %></td>
            <td><%= book.getPublishDate() %></td>
            <td><%= book.getIsbn() %></td>
            <td><%= book.getTypeId() %></td>
            <td><%= book.getStock() %></td>
            <td>
                <form action="admin_book_search.do" method="post" style="display: inline;">
                    <input type="hidden" name="action" value="edit">
                    <input type="hidden" name="book_id" value="<%= book.getBookId() %>">
                    <input type="hidden" name="search_text" value="<%= searchText %>">
                    <button type="submit">修改</button>
                </form>
                <form action="admin_book_search.do" method="post" style="display: inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="book_id" value="<%= book.getBookId() %>">
                    <input type="hidden" name="search_text" value="<%= searchText != null ? searchText : "" %>">
                    <button type="submit" onclick="return confirm('确定要删除该图书吗？');">删除</button>
                </form>
            </td>
        </tr>
        <% } %>
        <% } else { %>
        <tr>
            <td colspan="9">暂无搜索结果</td>
        </tr>
        <% } %>
    </table>
</body>
</html>
