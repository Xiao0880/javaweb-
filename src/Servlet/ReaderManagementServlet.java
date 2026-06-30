package Servlet;

import Entity.Reader;
import Service.ReaderService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/admin/admin_reader_search.do")
public class ReaderManagementServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("search_text");
        ServletContext context = getServletContext();
        Map<String, Object> container = (Map<String, Object>) context.getAttribute("container");
        ReaderService readerService = (ReaderService) container.get("readerService");
        
        try {
            List<Reader> readers = readerService.searchReaders(keyword);
            req.setAttribute("readers", readers);
            req.setAttribute("searchText", keyword);
            req.getRequestDispatcher("reader_management.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        ServletContext context = getServletContext();
        Map<String, Object> container = (Map<String, Object>) context.getAttribute("container");
        ReaderService readerService = (ReaderService) container.get("readerService");
        
        try {
            if ("delete".equals(action)) {
                int readerId = Integer.parseInt(req.getParameter("reader_id"));
                if (readerService.deleteReader(readerId)) {
                    String searchText = req.getParameter("search_text");
                    String encodedText = URLEncoder.encode(searchText != null ? searchText : "", StandardCharsets.UTF_8);
                    resp.sendRedirect(req.getContextPath() + "/admin/admin_reader_search.do?search_text=" + encodedText);
                } else {
                    req.setAttribute("error", "删除读者失败");
                    req.getRequestDispatcher("reader_management.jsp").forward(req, resp);
                }
            } else if ("edit".equals(action)) {
                int readerId = Integer.parseInt(req.getParameter("reader_id"));
                Reader reader = readerService.getReaderById(readerId);
                req.setAttribute("reader", reader);
                req.setAttribute("search_text", req.getParameter("search_text"));
                req.getRequestDispatcher("reader_edit.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
