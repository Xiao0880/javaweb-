package Servlet;

import Entity.ViewAdminStatistics;
import JDBC.DBUtil;
import Service.AdminService;
import Service.BookService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/admin/admin_statistics.do")
public class  AdminStatisticsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> statistics = new HashMap<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ServletContext context = getServletContext();
        Map<String, Object> container = (Map<String, Object>) context.getAttribute("container");
        AdminService adminService = (AdminService) container.get("adminService");
        try {
            ViewAdminStatistics viewAdminStatistics = adminService.getAdminStatistics();
            req.setAttribute("ViewAdminStatistics", viewAdminStatistics);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*
        try {
            conn = DBUtil.getConnection();
            
            // 今日借阅数
            String sql1 = "SELECT COUNT(*) FROM borrow WHERE DATE(borrow_date) = CURDATE()";
            pstmt = conn.prepareStatement(sql1);
            rs = pstmt.executeQuery();
            if (rs.next()) statistics.put("todayBorrowCount", rs.getInt(1));
            rs.close();
            pstmt.close();
            
            // 今日归还数
            String sql2 = "SELECT COUNT(*) FROM borrow WHERE DATE(return_date) = CURDATE()";
            pstmt = conn.prepareStatement(sql2);
            rs = pstmt.executeQuery();
            if (rs.next()) statistics.put("todayReturnCount", rs.getInt(1));
            rs.close();
            pstmt.close();
            
            // 借阅人数（当前借阅中）
            String sql3 = "SELECT COUNT(DISTINCT reader_id) FROM borrow WHERE return_date IS NULL";
            pstmt = conn.prepareStatement(sql3);
            rs = pstmt.executeQuery();
            if (rs.next()) statistics.put("currentBorrowReaderCount", rs.getInt(1));
            rs.close();
            pstmt.close();
            
            // 库存总量
            String sql4 = "SELECT COALESCE(SUM(stock), 0) FROM book";
            pstmt = conn.prepareStatement(sql4);
            rs = pstmt.executeQuery();
            if (rs.next()) statistics.put("totalStock", rs.getInt(1));
            
            // 读者总数
            String sql5 = "SELECT COUNT(*) FROM reader";
            rs.close();
            pstmt.close();
            pstmt = conn.prepareStatement(sql5);
            rs = pstmt.executeQuery();
            if (rs.next()) statistics.put("totalReaderCount", rs.getInt(1));
            
            // 图书总数
            String sql6 = "SELECT COUNT(*) FROM book";
            rs.close();
            pstmt.close();
            pstmt = conn.prepareStatement(sql6);
            rs = pstmt.executeQuery();
            if (rs.next()) statistics.put("totalBookCount", rs.getInt(1));
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        */

        req.getRequestDispatcher("admin_index.jsp").forward(req, resp);
    }
}
