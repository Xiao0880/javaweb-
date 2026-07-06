<%@ page import="Entity.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <title>图书管理系统 - 图书详情</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        .detail-container {
            background: white;
            border-radius: 16px;
            box-shadow: 0 8px 24px rgba(0, 0, 0, 0.08);
            padding: 40px;
            max-width: 700px;
            margin: 0 auto;
            border: 1px solid #e8e8e8;
        }
        
        .detail-header {
            text-align: center;
            margin-bottom: 40px;
            padding-bottom: 24px;
            border-bottom: 2px solid #1e3a5f;
        }
        
        .detail-header h2 {
            margin: 0;
            font-size: 28px;
            color: #1e3a5f;
            border: none;
            padding: 0;
        }
        
        .detail-row {
            display: flex;
            padding: 16px 0;
            border-bottom: 1px solid #f0f0f0;
        }
        
        .detail-label {
            width: 120px;
            font-weight: 600;
            color: #555;
            font-size: 15px;
            flex-shrink: 0;
        }
        
        .detail-value {
            flex: 1;
            color: #333;
            font-size: 15px;
            padding-left: 16px;
        }
        
        .action-buttons {
            margin-top: 32px;
            display: flex;
            gap: 16px;
            justify-content: center;
        }
        
        .btn {
            display: inline-block;
            padding: 12px 32px;
            border-radius: 8px;
            font-size: 15px;
            font-weight: 600;
            text-decoration: none;
            transition: all 0.2s ease;
            cursor: pointer;
            border: none;
        }
        
        .btn-primary {
            background-color: #1e3a5f;
            color: white;
        }
        
        .btn-primary:hover {
            background-color: #2d4a6f;
            text-decoration: none;
            transform: translateY(-1px);
            box-shadow: 0 4px 12px rgba(30, 58, 95, 0.3);
        }
        
        .btn-success {
            background-color: #52c41a;
            color: white;
        }
        
        .btn-success:hover {
            background-color: #389e0d;
            text-decoration: none;
            transform: translateY(-1px);
            box-shadow: 0 4px 12px rgba(82, 196, 26, 0.3);
        }
        
        .btn-secondary {
            background-color: #f5f5f5;
            color: #666;
        }
        
        .btn-secondary:hover {
            background-color: #e8e8e8;
            text-decoration: none;
        }
        
        .stock-available {
            color: #52c41a;
            font-weight: 600;
        }
        
        .stock-unavailable {
            color: #ff4d4f;
            font-weight: 600;
        }
    </style>
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
        <div class="detail-container">
            <% 
                Book book = (Book) request.getAttribute("book");
                if (book != null) {
            %>
            <div class="detail-header">
                <h2><%= book.getBookName() %></h2>
                <p style="color: #666; margin-top: 8px;">图书详情</p>
            </div>
            
            <div class="detail-row">
                <span class="detail-label">图书编号</span>
                <span class="detail-value"><%= book.getBookId() %></span>
            </div>
            
            <div class="detail-row">
                <span class="detail-label">作者</span>
                <span class="detail-value"><%= book.getAuthor() %></span>
            </div>
            
            <div class="detail-row">
                <span class="detail-label">类型</span>
                <span class="detail-value"><%= book.getTypeName() != null ? book.getTypeName() : book.getTypeId() %></span>
            </div>
            
            <div class="detail-row">
                <span class="detail-label">出版社</span>
                <span class="detail-value"><%= book.getPublisher() %></span>
            </div>
            
            <div class="detail-row">
                <span class="detail-label">出版日期</span>
                <span class="detail-value"><%= book.getPublishDate() %></span>
            </div>
            
            <div class="detail-row">
                <span class="detail-label">ISBN</span>
                <span class="detail-value"><%= book.getIsbn() %></span>
            </div>
            
            <div class="detail-row">
                <span class="detail-label">库存状态</span>
                <span class="detail-value">
                    <% if (book.getStock() > 0) { %>
                        <span class="stock-available">可借阅（剩余 <%= book.getStock() %> 本）</span>
                    <% } else { %>
                        <span class="stock-unavailable">已借出（暂无库存）</span>
                    <% } %>
                </span>
            </div>
            
            <div class="action-buttons">
                <% if (book.getStock() > 0) { %>
                    <a href="borrow.do?book_id=<%= book.getBookId() %>" class="btn btn-success">借阅图书</a>
                <% } %>
                <a href="book_search_results.jsp" class="btn btn-secondary">返回列表</a>
            </div>
            <% } else { %>
                <div style="text-align: center; padding: 40px;">
                    <p style="color: #666; font-size: 16px;">未找到该图书信息</p>
                    <a href="reader_index.jsp" class="btn btn-primary" style="margin-top: 20px;">返回首页</a>
                </div>
            <% } %>
        </div>
    </div>
</body>
</html>