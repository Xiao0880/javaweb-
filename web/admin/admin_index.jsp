<%@ page import="java.util.Map" %>
<%@ page import="Entity.ViewAdminStatistics" %>
<%-- Created by IntelliJ IDEA. User: xlw Date: 2026/6/29 Time: 15:58 To change this template use File | Settings |
    File Templates. --%>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
            <% String adminName=(String) session.getAttribute("admin_name"); if (adminName==null ||
                adminName.trim().isEmpty()) { response.sendRedirect(request.getContextPath() + "/admin/admin_login.html"
                ); return; }
                ViewAdminStatistics viewAdminStatistics = (ViewAdminStatistics) request.getAttribute("ViewAdminStatistics");
                    if (viewAdminStatistics == null) {
                        viewAdminStatistics = new ViewAdminStatistics();
                        viewAdminStatistics.setCurrentBorrowReaderCount(0);
                        viewAdminStatistics.setTotalStock(0);
                        viewAdminStatistics.setTodayBorrowCount(0);
                        viewAdminStatistics.setTodayReturnCount(0);
                    }
                        %>
                        <html>

                        <head>
                            <title>管理员首页</title>
                        </head>

                        <body>
                            <h1>欢迎，<%= adminName %> - 管理员</h1>

                            <div class="navbar">
                                <a href="admin_statistics.do">首页</a>
                                <a href="reader_management.jsp">读者管理</a>
                                <a href="book_management.jsp">图书管理</a>
                            </div>

                            <h2>今日统计</h2>
                            <table border="1">
                                <tr>
                                    <th>今日借阅数</th>
                                    <th>今日归还数</th>
                                    <th>当前借阅人数</th>
                                    <th>图书库存总量</th>
                                </tr>
                                <tr>
                                    <td>
                                        <%= viewAdminStatistics.getTodayBorrowCount() %>
                                    </td>
                                    <td>
                                        <%= viewAdminStatistics.getTodayReturnCount() %>
                                    </td>
                                    <td>
                                        <%= viewAdminStatistics.getCurrentBorrowReaderCount() %>
                                    </td>
                                    <td>
                                        <%= viewAdminStatistics.getTotalStock() %>
                                    </td>

                                </tr>
                            </table>
                        </body>

                        </html>