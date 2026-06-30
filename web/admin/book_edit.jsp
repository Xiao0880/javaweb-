<%@ page import="Entity.Book" %>
    <%-- Created by IntelliJ IDEA. User: xlw Date: 2026/6/29 Time: 18:00 To change this template use File | Settings |
        File Templates. --%>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
            <% String adminName=(String) session.getAttribute("admin_name"); if (adminName==null ||
                adminName.trim().isEmpty()) { response.sendRedirect(request.getContextPath() + "/admin/admin_login.html"
                ); return; } Book book=(Book) request.getAttribute("book"); String error=(String)
                request.getAttribute("error"); %>
                <html>

                <head>
                    <title>修改图书信息</title>
                </head>

                <body>
                    <h1>欢迎，<%= adminName %> - 管理员</h1>

                    <div class="navbar">
                        <a href="admin_statistics.do">首页</a>
                        <a href="reader_management.jsp">读者管理</a>
                        <a href="book_management.jsp">图书管理</a>
                    </div>

                    <h2>修改图书信息</h2>

                    <% if (error !=null) { %>
                        <p style="color: red;">
                            <%= error %>
                        </p>
                        <% } %>

                            <% if (book !=null) { %>
                                <form action="admin_book_update.do" method="post">
                                    <input type="hidden" name="book_id" value="<%= book.getBookId() %>">
                                    <input type="hidden" name="search_text"
                                        value='<%= request.getAttribute("search_text") != null ? request.getAttribute("search_text") : "" %>'>

                                    <label for="book_name">书名：</label>
                                    <input type="text" id="book_name" name="book_name" value="<%= book.getBookName() %>"
                                        required><br>

                                    <label for="author">作者：</label>
                                    <input type="text" id="author" name="author" value="<%= book.getAuthor() %>"
                                        required><br>

                                    <label for="publisher">出版社：</label>
                                    <input type="text" id="publisher" name="publisher"
                                        value="<%= book.getPublisher() %>" required><br>

                                    <label for="publish_date">出版日期：</label>
                                    <input type="date" id="publish_date" name="publish_date"
                                        value="<%= book.getPublishDate() %>" required><br>

                                    <label for="isbn">ISBN：</label>
                                    <input type="text" id="isbn" name="isbn" value="<%= book.getIsbn() %>" required><br>

                                    <label for="type_id">类型ID：</label>
                                    <input type="number" id="type_id" name="type_id" value="<%= book.getTypeId() %>"
                                        required><br>

                                    <label for="stock">库存：</label>
                                    <input type="number" id="stock" name="stock" value="<%= book.getStock() %>"
                                        required><br>

                                    <button type="submit">保存</button>
                                    <a href="book_management.jsp">取消</a>
                                </form>
                                <% } else { %>
                                    <p>未找到图书信息</p>
                                    <a href="book_management.jsp">返回</a>
                                    <% } %>
                </body>

                </html>