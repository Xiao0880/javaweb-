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
import java.util.Map;

@WebServlet("/admin/admin_reader_update.do")
public class ReaderUpdateServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        Map<String, Object> container = (Map<String, Object>) context.getAttribute("container");
        ReaderService readerService = (ReaderService) container.get("readerService");
        String searchText = req.getParameter("search_text");
        
        try {
            Reader reader = new Reader();
            reader.setReaderId(Integer.parseInt(req.getParameter("reader_id")));
            reader.setReaderName(req.getParameter("reader_name"));
            reader.setReaderPassword(req.getParameter("reader_password"));
            reader.setGender(req.getParameter("gender"));
            reader.setPhone(req.getParameter("phone"));
            
            if (readerService.updateReader(reader)) {
                String encodedText = searchText != null ? URLEncoder.encode(searchText, StandardCharsets.UTF_8) : "";
                resp.sendRedirect(req.getContextPath() + "/admin/admin_reader_search.do?search_text=" + encodedText);
            } else {
                req.setAttribute("error", "修改读者信息失败");
                req.setAttribute("reader", reader);
                req.getRequestDispatcher("reader_edit.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
