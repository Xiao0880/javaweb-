<%@ page import="java.util.Map" %>
    <%@ page import="Entity.ViewAdminStatistics" %>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
            <% String adminName=(String) session.getAttribute("admin_name"); if (adminName==null ||
                adminName.trim().isEmpty()) { response.sendRedirect(request.getContextPath() + "/admin/admin_login.html"
                ); return; } ViewAdminStatistics viewAdminStatistics=(ViewAdminStatistics)
                request.getAttribute("ViewAdminStatistics"); if (viewAdminStatistics==null) { viewAdminStatistics=new
                ViewAdminStatistics(); viewAdminStatistics.setCurrentBorrowReaderCount(0);
                viewAdminStatistics.setTotalStock(0); viewAdminStatistics.setTodayBorrowCount(0);
                viewAdminStatistics.setTodayReturnCount(0); } %>
                <html lang="zh-CN">

                <head>
                    <title>图书管理系统 - 管理员首页</title>
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
                        <h2>欢迎，<%= adminName %>
                        </h2>

                        <div class="statistics-grid">
                            <div class="stat-card">
                                <div class="stat-value">
                                    <%= viewAdminStatistics.getTodayBorrowCount() %>
                                </div>
                                <div class="stat-label">今日借阅数</div>
                            </div>
                            <div class="stat-card">
                                <div class="stat-value">
                                    <%= viewAdminStatistics.getTodayReturnCount() %>
                                </div>
                                <div class="stat-label">今日归还数</div>
                            </div>
                            <div class="stat-card">
                                <div class="stat-value">
                                    <%= viewAdminStatistics.getCurrentBorrowReaderCount() %>
                                </div>
                                <div class="stat-label">当前借阅人数</div>
                            </div>
                            <div class="stat-card">
                                <div class="stat-value">
                                    <%= viewAdminStatistics.getTotalStock() %>
                                </div>
                                <div class="stat-label">图书库存总量</div>
                            </div>
                        </div>
                    </div>
                </body>

                </html>