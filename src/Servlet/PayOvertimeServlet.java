package Servlet;

import Service.ReaderService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/pay_overtime.do")
public class PayOvertimeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String borrowId = req.getParameter("borrow_id");
        ServletContext context = getServletContext();
        Map<String, Object> container = (Map<String, Object>) context.getAttribute("container");
        ReaderService readerService = (ReaderService) container.get("readerService");
        
        try {
            // 执行归还操作
            if (readerService.returnBook(Integer.parseInt(borrowId))) {
                // 归还成功，跳转到借阅信息页面
                req.setAttribute("borrow_id", borrowId);
                resp.sendRedirect("borrow_info.do");
            } else {
                // 归还失败
                req.getRequestDispatcher("error/reader_return_error.html").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}