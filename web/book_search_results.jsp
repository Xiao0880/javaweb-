<%@ page import="Entity.Book" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <title>图书管理系统 - 搜索结果</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="navigation">
        <div class="container">
            <h1>图书管理系统</h1>
            <div class="nav-links">
                <a href="reader_index.jsp">首页</a>
                <a href="reader_info.do">个人信息</a>
                <a href="borrow_info.do">借阅记录</a>
            </div>
        </div>
    </div>
    
    <div class="container">
        <div class="table-container">
            <h2>图书搜索结果</h2>
            <table>
                <tr>
                    <th>图书编号</th>
                    <th>图书名称</th>
                    <th>作者</th>
                    <th>类型</th>
                    <th>出版社</th>
                    <th>出版日期</th>
                    <th>库存</th>
                    <th>操作</th>
                </tr>
                <% 
                    List<Book> books = (List<Book>) request.getSession().getAttribute("books");
                    if (books != null && !books.isEmpty()) {
                        for (Book book : books) { 
                %>
                <tr>
                    <td><%= book.getBookId() %></td>
                    <td><%= book.getBookName() %></td>
                    <td><%= book.getAuthor() %></td>
                    <td><%= book.getTypeName() %></td>
                    <td><%= book.getPublisher() %></td>
                    <td><%= book.getPublishDate() %></td>
                    <td><%= book.getStock() %></td>
                    <td>
                        <a href="book_detail.do?book_id=<%= book.getBookId() %>" style="padding: 6px 14px; background-color: #1890ff; color: white; border-radius: 4px; font-size: 13px; text-decoration: none; margin-right: 8px;">详情</a>
                        <a href="borrow.do?book_id=<%= book.getBookId() %>" class="edit-link">借阅</a>
                    </td>
                </tr>
                <% } %>
                <% } else { %>
                <tr>
                    <td colspan="8">暂无搜索结果</td>
                </tr>
                <% } %>
            </table>
        </div>
    </div>
</body>
</html>
