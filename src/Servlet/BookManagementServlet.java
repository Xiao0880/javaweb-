package Servlet;

import Entity.Book;
import Service.BookService;
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

@WebServlet("/admin/admin_book_search.do")
public class BookManagementServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("search_text");
        ServletContext context = getServletContext();
        Map<String, Object> container = (Map<String, Object>) context.getAttribute("container");
        BookService bookService = (BookService) container.get("bookService");
        req.setAttribute("searchText", keyword);
        try {
            List<Book> books = bookService.searchBooks(keyword);
            req.setAttribute("books", books);
            req.setAttribute("searchText", keyword);
            req.getRequestDispatcher("book_management.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        ServletContext context = getServletContext();
        Map<String, Object> container = (Map<String, Object>) context.getAttribute("container");
        BookService bookService = (BookService) container.get("bookService");
        
        try {
            if ("delete".equals(action)) {
                int bookId = Integer.parseInt(req.getParameter("book_id"));
                if (bookService.deleteBook(bookId)) {
                    String searchText = req.getParameter("search_text");
                    String encodedText = URLEncoder.encode(searchText != null ? searchText : "", StandardCharsets.UTF_8);
                    resp.sendRedirect(req.getContextPath() + "/admin/admin_book_search.do?search_text=" + encodedText);
                } else {
                    req.setAttribute("error", "删除图书失败");
                    req.getRequestDispatcher("book_management.jsp").forward(req, resp);
                }
            } else if ("edit".equals(action)) {
                int bookId = Integer.parseInt(req.getParameter("book_id"));
                Book book = bookService.selectBookById(bookId);
                req.setAttribute("book", book);
                req.setAttribute("search_text", req.getParameter("search_text"));
                req.getRequestDispatcher("book_edit.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
