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

@WebServlet("/reader_register")
public class ReaderRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reader_name = request.getParameter("reader_name");
        String reader_password = request.getParameter("reader_password");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        java.sql.Date registerDate = java.sql.Date.valueOf(java.time.LocalDate.now());
        ServletContext context = getServletContext();
        Map<String, Object> container = (Map<String, Object>) context.getAttribute("container");
        ReaderService readerService = (ReaderService) container.get("readerService");
        try {
            if (readerService.register(reader_name, reader_password, gender, phone,registerDate)) {
                // 注册成功
                response.sendRedirect("success/register_success.html");
            } else {
                // 注册失败
                response.sendRedirect("error/error.html");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
