package Servlet;

import Service.AdminService;
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
@WebServlet("/admin_login")
public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String admin_name = request.getParameter("admin_name");
        String admin_password = request.getParameter("admin_password");
        ServletContext context = getServletContext();
        System.out.println(admin_name);
        System.out.println(admin_password);
        Map<String,Object> container = (Map<String,Object>) context.getAttribute("container");
        AdminService adminService = (AdminService) container.get("adminService");
        try {
            if (adminService.login(admin_name, admin_password)) {
                // 登录成功
                request.getSession().setAttribute("admin_name", admin_name);
                response.sendRedirect("admin_index.jsp");
            } else {
                // 登录失败
                response.sendRedirect("error/admin_login_error.html");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
