package Servlet;

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

@WebServlet("/reader_login")
public class ReaderLoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String reader_name = request.getParameter("reader_name");
        String reader_password = request.getParameter("reader_password");
        String remember_me = request.getParameter("remember_me");
        ServletContext context = getServletContext();
        Map<String,Object> container = (Map<String,Object>) context.getAttribute("container");
        ReaderService readerService = (ReaderService) container.get("readerService");
        try {
            if (readerService.login(reader_name, reader_password)) {
                request.getSession().setAttribute("reader", readerService.getReaderInfo(reader_name));
                
                if ("true".equals(remember_me)) {
                    Cookie nameCookie = new Cookie("reader_name", reader_name);
                    Cookie pwdCookie = new Cookie("reader_password", reader_password);
                    nameCookie.setMaxAge(7 * 24 * 60 * 60);
                    pwdCookie.setMaxAge(7 * 24 * 60 * 60);
                    nameCookie.setPath(request.getContextPath());
                    pwdCookie.setPath(request.getContextPath());
                    response.addCookie(nameCookie);
                    response.addCookie(pwdCookie);
                }
                
                response.sendRedirect("reader_index.jsp");
            } else {
                response.sendRedirect("error/reader_login_error.html");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
