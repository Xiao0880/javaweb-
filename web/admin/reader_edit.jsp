<%@ page import="Entity.Reader" %>
    <%-- Created by IntelliJ IDEA. User: xlw Date: 2026/6/29 Time: 17:40 To change this template use File | Settings |
        File Templates. --%>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
            <% String adminName=(String) session.getAttribute("admin_name"); if (adminName==null ||
                adminName.trim().isEmpty()) { response.sendRedirect(request.getContextPath() + "/admin/admin_login.html"
                ); return; } Reader reader=(Reader) request.getAttribute("reader"); String error=(String)
                request.getAttribute("error"); String searchText=request.getParameter("searchText");
                request.setAttribute("searchText", searchText); %>
                <html>

                <head>
                    <title>修改读者信息</title>
                </head>

                <body>
                    <h1>欢迎，<%= adminName %> - 管理员</h1>

                    <div class="navbar">
                        <a href="admin_statistics.do">首页</a>
                        <a href="reader_management.jsp">读者管理</a>
                        <a href="book_management.jsp">图书管理</a>
                    </div>

                    <h2>修改读者信息</h2>

                    <% if (error !=null) { %>
                        <p style="color: red;">
                            <%= error %>
                        </p>
                        <% } %>

                            <% if (reader !=null) { %>
                                <form action="admin_reader_update.do" method="post">
                                    <input type="hidden" name="reader_id" value="<%= reader.getReaderId() %>">
                                    <input type="hidden" name="search_text"
                                        value='<%= request.getAttribute("search_text") != null ? request.getAttribute("search_text") : "" %>'>

                                    <label for="reader_name">姓名：</label>
                                    <input type="text" id="reader_name" name="reader_name"
                                        value="<%= reader.getReaderName() %>" required><br>

                                    <label for="reader_password">密码：</label>
                                    <input type="text" id="reader_password" name="reader_password"
                                        value="<%= reader.getReaderPassword() %>" required><br>

                                    <label>性别：</label>
                                    <input type="radio" id="gender_male" name="gender" value="男" <%="男"
                                        .equals(reader.getGender()) ? "checked" : "" %> required>
                                    <label for="gender_male">男</label>
                                    <input type="radio" id="gender_female" name="gender" value="女" <%="女"
                                        .equals(reader.getGender()) ? "checked" : "" %>>
                                    <label for="gender_female">女</label><br>

                                    <label for="phone">电话：</label>
                                    <input type="text" id="phone" name="phone" value="<%= reader.getPhone() %>"
                                        required><br>

                                    <button type="submit">保存</button>
                                    <a href="reader_management.jsp">取消</a>
                                </form>
                                <% } else { %>
                                    <p>未找到读者信息</p>
                                    <a href="reader_management.jsp">返回</a>
                                    <% } %>
                </body>

                </html>