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

@WebServlet("/return.do")
public class ReaderReturnServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String borrowId = req.getParameter("borrow_id");
        String payOvertime = req.getParameter("pay_overtime");
        ServletContext context = getServletContext();
        Map<String,Object> container = (Map<String,Object>) context.getAttribute("container");
        ReaderService readerService = (ReaderService) container.get("readerService");
        try {
            int id = Integer.parseInt(borrowId);
            // 检测是否超时，但如果是支付逾期费用则跳过检测
            if (readerService.isOverdue(id) && !"true".equals(payOvertime)) {
                // 超时且不是支付逾期费用，跳转到超时页面
                req.setAttribute("borrow_id", borrowId);
                req.getRequestDispatcher("error/overtime/overtime.jsp").forward(req, resp);
            } else {
                // 未超时 或 已支付逾期费用，执行归还
                if (readerService.returnBook(id)) {
                    resp.sendRedirect("borrow_info.do");
                } else {
                    req.getRequestDispatcher("error/reader_return_error.html").forward(req, resp);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}