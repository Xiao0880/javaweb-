<%@ page import="Entity.BookType" %>
    <%@ page import="java.util.List" %>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
            <% String adminName=(String) session.getAttribute("admin_name"); if (adminName==null ||
                adminName.trim().isEmpty()) { response.sendRedirect(request.getContextPath() + "/admin/admin_login.html"
                ); return; } String error=(String) request.getAttribute("error"); List<BookType> bookTypes = (List
                <BookType>) request.getAttribute("bookTypes");
                    %>
                    <html lang="zh-CN">

                    <head>
                        <title>图书管理系统 - 添加图书</title>
                        <link rel="stylesheet" href="../css/style.css">
                    </head>

                    <body>
                        <div class="navigation">
                            <div class="container">
                                <h1>图书管理系统 - 管理员后台</h1>
                                <div class="nav-links">
                                    <a href="admin_statistics.do">首页</a>
                                    <a href="admin_reader_search.do">读者管理</a>
                                    <a href="admin_book_search.do">图书管理</a>
                                </div>
                            </div>
                        </div>

                        <div class="container">
                            <div class="form-container">
                                <h2>添加图书</h2>

                                <% if (error !=null) { %>
                                    <div class="error-message">
                                        <%= error %>
                                    </div>
                                    <% } %>

                                        <form action="admin_book_add.do" method="post">
                                            <label for="book_name">书名：</label>
                                            <input type="text" id="book_name" name="book_name" placeholder="请输入书名"
                                                required>

                                            <label for="author">作者：</label>
                                            <input type="text" id="author" name="author" placeholder="请输入作者" required>

                                            <label for="publisher">出版社：</label>
                                            <input type="text" id="publisher" name="publisher" placeholder="请输入出版社"
                                                required>

                                            <label for="publish_date">出版日期：</label>
                                            <input type="date" id="publish_date" name="publish_date" required>

                                            <label for="isbn">ISBN：</label>
                                            <input type="text" id="isbn" name="isbn" placeholder="请输入ISBN" required>

                                            <label for="type_id">类型：</label>
                                            <select id="type_id" name="type_id" required>
                                                <% for (BookType type : bookTypes) { %>
                                                    <option value="<%= type.getTypeId() %>">
                                                        <%= type.getTypeName() %>
                                                    </option>
                                                    <% } %>
                                            </select>

                                            <label for="stock">库存：</label>
                                            <input type="number" id="stock" name="stock" placeholder="请输入库存数量" required>

                                            <input type="submit" value="添加">

                                            <div class="form-links">
                                                <a href="admin_book_search.do">取消</a>
                                            </div>
                                        </form>
                            </div>
                        </div>
                    </body>

                    </html>