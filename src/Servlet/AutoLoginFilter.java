package Servlet;

import Service.AdminService;
import Service.ReaderService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebFilter("/*")
public class AutoLoginFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);
        
        if (session != null) {
            if (session.getAttribute("reader") != null && uri.contains("reader_login")) {
                resp.sendRedirect(req.getContextPath() + "/reader_index.jsp");
                return;
            }
            if (session.getAttribute("admin_name") != null && uri.contains("admin_login")) {
                resp.sendRedirect(req.getContextPath() + "/admin/admin_statistics.do");
                return;
            }
        }
        
        if (uri.endsWith("reader_login.html") || uri.endsWith("reader_login")) {
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                String readerName = null;
                String readerPassword = null;
                for (Cookie cookie : cookies) {
                    if ("reader_name".equals(cookie.getName())) {
                        readerName = cookie.getValue();
                    } else if ("reader_password".equals(cookie.getName())) {
                        readerPassword = cookie.getValue();
                    }
                }
                if (readerName != null && readerPassword != null) {
                    ServletContext context = req.getServletContext();
                    Map<String, Object> container = (Map<String, Object>) context.getAttribute("container");
                    ReaderService readerService = (ReaderService) container.get("readerService");
                    try {
                        if (readerService.login(readerName, readerPassword)) {
                            req.getSession().setAttribute("reader", readerService.getReaderInfo(readerName));
                            resp.sendRedirect(req.getContextPath() + "/reader_index.jsp");
                            return;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        
        if (uri.endsWith("admin_login.html") || uri.endsWith("/admin/admin_login")) {
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                String adminName = null;
                String adminPassword = null;
                for (Cookie cookie : cookies) {
                    if ("admin_name".equals(cookie.getName())) {
                        adminName = cookie.getValue();
                    } else if ("admin_password".equals(cookie.getName())) {
                        adminPassword = cookie.getValue();
                    }
                }
                if (adminName != null && adminPassword != null) {
                    ServletContext context = req.getServletContext();
                    Map<String, Object> container = (Map<String, Object>) context.getAttribute("container");
                    AdminService adminService = (AdminService) container.get("adminService");
                    try {
                        if (adminService.login(adminName, adminPassword)) {
                            req.getSession().setAttribute("admin_name", adminName);
                            resp.sendRedirect(req.getContextPath() + "/admin/admin_statistics.do");
                            return;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        
        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {
    }
}