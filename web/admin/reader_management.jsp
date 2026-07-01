<%@ page import="Entity.Reader" %>
    <%@ page import="java.util.List" %>
        <%-- Created by IntelliJ IDEA. User: xlw Date: 2026/6/29 Time: 17:30 To change this template use File | Settings
            | File Templates. --%>
            <%@ page contentType="text/html;charset=UTF-8" language="java" %>
                <% String adminName=(String) session.getAttribute("admin_name"); if (adminName==null ||
                    adminName.trim().isEmpty())
                { response.sendRedirect(request.getContextPath()
                    + "/admin/admin_login.html" );
                    return; } List<Reader> readers = (List<Reader>)
                        request.getAttribute("readers");
                        String searchText = (String) request.getAttribute("searchText");
                        String error = (String) request.getAttribute("error");
                        %>
                        <html>

                        <head>
                            <title>读者管理</title>
                        </head>

                        <body>
                            <h1>欢迎，<%= adminName %> - 管理员</h1>

                            <div class="navbar">
                                <a href="admin_statistics.do">首页</a>
                                <a href="reader_management.jsp">读者管理</a>
                                <a href="book_management.jsp">图书管理</a>
                            </div>

                            <h2>读者管理</h2>

                            <form action="admin_reader_search.do" method="get">
                                <input type="text" name="search_text"
                                    value="<%= searchText != null ? searchText : "" %>" placeholder="按姓名、电话、ID搜索...">
                                <button type="submit">搜索</button>
                            </form>

                            <% if (error !=null) { %>
                                <p style="color: red;">
                                    <%= error %>
                                </p>
                                <% } %>

                                    <table border="1">
                                        <tr>
                                            <th>读者ID</th>
                                            <th>姓名</th>
                                            <th>密码</th>
                                            <th>性别</th>
                                            <th>电话</th>
                                            <th>注册日期</th>
                                            <th>操作</th>
                                        </tr>
                                        <% if (readers !=null && !readers.isEmpty()) { for (Reader reader : readers) {
                                            %>
                                            <tr>
                                                <td>
                                                    <%= reader.getReaderId() %>
                                                </td>
                                                <td>
                                                    <%= reader.getReaderName() %>
                                                </td>
                                                <td>
                                                    <%= reader.getReaderPassword() %>
                                                </td>
                                                <td>
                                                    <%= reader.getGender() %>
                                                </td>
                                                <td>
                                                    <%= reader.getPhone() %>
                                                </td>
                                                <td>
                                                    <%= reader.getRegisterDate() %>
                                                </td>
                                                <td>
                                                    <form action="admin_reader_search.do" method="post"
                                                        style="display: inline;">
                                                        <input type="hidden" name="action" value="edit">
                                                        <input type="hidden" name="reader_id"
                                                            value="<%= reader.getReaderId() %>">
                                                        <input type="hidden" name="search_text"
                                                            value="<%= searchText != null ? searchText : "" %>">
                                                        <button type="submit">修改</button>
                                                    </form>
                                                    <form action="admin_reader_search.do" method="post"
                                                        style="display: inline;">
                                                        <input type="hidden" name="action" value="delete">
                                                        <input type="hidden" name="reader_id"
                                                            value="<%= reader.getReaderId() %>">
                                                        <input type="hidden" name="search_text"
                                                            value="<%= searchText != null ? searchText : "" %>">
                                                        <button type="submit"
                                                            onclick="return confirm('确定要删除该读者吗？');">删除</button>
                                                    </form>
                                                </td>
                                            </tr>
                                            <% } %>
                                                <% } else { %>
                                                    <tr>
                                                        <td colspan="7">暂无搜索结果</td>
                                                    </tr>
                                                    <% } %>
                                    </table>
                        </body>

                        </html>