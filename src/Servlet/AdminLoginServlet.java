package Servlet;

import Service.AdminService;
import Service.ReaderService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
@WebServlet("/admin/admin_login")
public class AdminLoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String admin_name = request.getParameter("admin_name");
        String admin_password = request.getParameter("admin_password");
        String remember_me = request.getParameter("remember_me");
        ServletContext context = getServletContext();
        System.out.println(admin_name);
        System.out.println(admin_password);
        Map<String,Object> container = (Map<String,Object>) context.getAttribute("container");
        AdminService adminService = (AdminService) container.get("adminService");
        try {
            if (adminService.login(admin_name, admin_password)) {
                request.getSession().setAttribute("admin_name", admin_name);
                
                if ("true".equals(remember_me)) {
                    Cookie nameCookie = new Cookie("admin_name", admin_name);
                    Cookie pwdCookie = new Cookie("admin_password", admin_password);
                    nameCookie.setMaxAge(7 * 24 * 60 * 60);
                    pwdCookie.setMaxAge(7 * 24 * 60 * 60);
                    nameCookie.setPath(request.getContextPath());
                    pwdCookie.setPath(request.getContextPath());
                    response.addCookie(nameCookie);
                    response.addCookie(pwdCookie);
                }
                
                response.sendRedirect("admin_statistics.do");
            } else {
                response.sendRedirect("admin_login_error.html");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
