<%@ page import="Entity.Book" %>
    <%@ page import="java.util.List" %>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
            <% String adminName=(String) session.getAttribute("admin_name"); if (adminName==null ||
                adminName.trim().isEmpty()) { response.sendRedirect(request.getContextPath() + "/admin/admin_login.html"
                ); return; } List<Book> books = (List<Book>) request.getAttribute("books");
                    String searchText = (String) request.getAttribute("searchText");
                    String error = (String) request.getAttribute("error");
                    %>
                    <html lang="zh-CN">

                    <head>
                        <title>图书管理系统 - 图书管理</title>
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
                            <div class="search-container">
                                <h2>图书管理</h2>
                                <form action="admin_book_search.do" method="get" class="search-box">
                                    <input type="text" name="search_text" placeholder="按书名、作者、ISBN搜索..."
                                        value="<%= searchText != null ? searchText : "" %>">
                                    <button type="submit">搜索</button>
                                </form>
                            </div>

                            <% if (error !=null) { %>
                                <div class="error-message">
                                    <%= error %>
                                </div>
                                <% } %>

                                    <div class="table-container">
                                        <table>
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
                                            <% if (books !=null && !books.isEmpty()) { for (Book book : books) { %>
                                                <tr>
                                                    <td>
                                                        <%= book.getBookId() %>
                                                    </td>
                                                    <td>
                                                        <%= book.getBookName() %>
                                                    </td>
                                                    <td>
                                                        <%= book.getAuthor() %>
                                                    </td>
                                                    <td>
                                                        <%= book.getPublisher() %>
                                                    </td>
                                                    <td>
                                                        <%= book.getPublishDate() %>
                                                    </td>
                                                    <td>
                                                        <%= book.getIsbn() %>
                                                    </td>
                                                    <td>
                                                        <%= book.getTypeId() %>
                                                    </td>
                                                    <td>
                                                        <%= book.getStock() %>
                                                    </td>
                                                    <td>
                                                        <form action="admin_book_search.do" method="post"
                                                            style="display: inline;">
                                                            <input type="hidden" name="action" value="edit">
                                                            <input type="hidden" name="book_id"
                                                                value="<%= book.getBookId() %>">
                                                            <input type="hidden" name="search_text"
                                                                value="<%= searchText != null ? searchText : "" %>">
                                                            <button type="submit" class="edit-link">修改</button>
                                                        </form>
                                                        <form action="admin_book_search.do" method="post"
                                                            style="display: inline;">
                                                            <input type="hidden" name="action" value="delete">
                                                            <input type="hidden" name="book_id"
                                                                value="<%= book.getBookId() %>">
                                                            <input type="hidden" name="search_text"
                                                                value="<%= searchText != null ? searchText : "" %>">
                                                            <button type="submit" class="delete-link"
                                                                onclick="return confirm('确定要删除该图书吗？');">删除</button>
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
                                    </div>
                        </div>
                    </body>

                    </html>