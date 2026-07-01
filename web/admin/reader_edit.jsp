<%@ page import="Entity.Reader" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% 
    String adminName = (String) session.getAttribute("admin_name"); 
    if (adminName == null || adminName.trim().isEmpty()) { 
        response.sendRedirect(request.getContextPath() + "/admin/admin_login.html"); 
        return; 
    } 
    Reader reader = (Reader) request.getAttribute("reader"); 
    String error = (String) request.getAttribute("error"); 
    String searchText = request.getParameter("searchText");
    request.setAttribute("searchText", searchText); 
%>
<html lang="zh-CN">
<head>
    <title>图书管理系统 - 修改读者信息</title>
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
            <h2>修改读者信息</h2>
            
            <% if (error != null) { %>
            <div class="error-message"><%= error %></div>
            <% } %>
            
            <% if (reader != null) { %>
            <form action="admin_reader_update.do" method="post">
                <input type="hidden" name="reader_id" value="<%= reader.getReaderId() %>">
                <input type="hidden" name="search_text" value='<%= request.getAttribute("search_text") != null ? request.getAttribute("search_text") : "" %>'>
                
                <label for="reader_name">姓名：</label>
                <input type="text" id="reader_name" name="reader_name" value="<%= reader.getReaderName() %>" required>
                
                <label for="reader_password">密码：</label>
                <input type="password" id="reader_password" name="reader_password" value="<%= reader.getReaderPassword() %>" required>
                
                <div class="radio-group">
                    <label>性别：</label>
                    <input type="radio" id="gender_male" name="gender" value="男" <%= "男".equals(reader.getGender()) ? "checked" : "" %> required>
                    <label for="gender_male">男</label>
                    <input type="radio" id="gender_female" name="gender" value="女" <%= "女".equals(reader.getGender()) ? "checked" : "" %>>
                    <label for="gender_female">女</label>
                </div>
                
                <label for="phone">电话：</label>
                <input type="text" id="phone" name="phone" value="<%= reader.getPhone() %>" required>
                
                <input type="submit" value="保存">
                
                <div class="form-links">
                    <a href="admin_reader_search.do">取消</a>
                </div>
            </form>
            <% } else { %>
            <p>未找到读者信息</p>
            <a href="admin_reader_search.do" class="btn">返回</a>
            <% } %>
        </div>
    </div>
</body>
</html>