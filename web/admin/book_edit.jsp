<%@ page import="Entity.Book" %>
    <%@ page import="Entity.BookType" %>
        <%@ page import="java.util.List" %>
            <%@ page contentType="text/html;charset=UTF-8" language="java" %>
                <% String adminName=(String) session.getAttribute("admin_name"); if (adminName==null ||
                    adminName.trim().isEmpty()) { response.sendRedirect(request.getContextPath()
                    + "/admin/admin_login.html" ); return; } Book book=(Book) request.getAttribute("book"); String
                    error=(String) request.getAttribute("error"); List<BookType> bookTypes = (List<BookType>)
                        request.getAttribute("bookTypes");
                        %>
                        <html lang="zh-CN">

                        <head>
                            <title>图书管理系统 - 修改图书信息</title>
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
                                    <h2>修改图书信息</h2>

                                    <% if (error !=null) { %>
                                        <div class="error-message">
                                            <%= error %>
                                        </div>
                                        <% } %>

                                            <% if (book !=null) { %>
                                                <form action="admin_book_update.do" method="post">
                                                    <input type="hidden" name="book_id" value="<%= book.getBookId() %>">
                                                    <input type="hidden" name="search_text"
                                                        value='<%= request.getAttribute("search_text") != null ? request.getAttribute("search_text") : "" %>'>

                                                    <label for="book_name">书名：</label>
                                                    <input type="text" id="book_name" name="book_name"
                                                        value="<%= book.getBookName() %>" required>

                                                    <label for="author">作者：</label>
                                                    <input type="text" id="author" name="author"
                                                        value="<%= book.getAuthor() %>" required>

                                                    <label for="publisher">出版社：</label>
                                                    <input type="text" id="publisher" name="publisher"
                                                        value="<%= book.getPublisher() %>" required>

                                                    <label for="publish_date">出版日期：</label>
                                                    <input type="date" id="publish_date" name="publish_date"
                                                        value="<%= book.getPublishDate() %>" required>

                                                    <label for="isbn">ISBN：</label>
                                                    <input type="text" id="isbn" name="isbn"
                                                        value="<%= book.getIsbn() %>" required>

                                                    <label for="type_id">类型：</label>
                                                    <select id="type_id" name="type_id" required>
                                                        <% for (BookType type : bookTypes) { %>
                                                            <option value="<%= type.getTypeId() %>"
                                                                <%=type.getTypeId()==book.getTypeId() ? "selected" : ""
                                                                %>><%= type.getTypeName() %>
                                                            </option>
                                                            <% } %>
                                                    </select>

                                                    <label for="stock">库存：</label>
                                                    <input type="number" id="stock" name="stock"
                                                        value="<%= book.getStock() %>" required>

                                                    <input type="submit" value="保存">

                                                    <div class="form-links">
                                                        <a href="admin_book_search.do">取消</a>
                                                        <a href="admin_book_add.do">添加图书</a>
                                                    </div>
                                                </form>
                                                <% } else { %>
                                                    <p>未找到图书信息</p>
                                                    <a href="admin_book_search.do" class="btn">返回</a>
                                                    <% } %>
                                </div>
                            </div>
                        </body>

                        </html>