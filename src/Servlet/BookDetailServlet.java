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
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/book_detail.do")
public class BookDetailServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookIdStr = req.getParameter("book_id");
        if (bookIdStr == null || bookIdStr.trim().isEmpty()) {
            resp.sendRedirect("reader_index.jsp");
            return;
        }
        
        ServletContext context = getServletContext();
        Map<String, Object> container = (Map<String, Object>) context.getAttribute("container");
        BookService bookService = (BookService) container.get("bookService");
        
        try {
            int bookId = Integer.parseInt(bookIdStr);
            Book book = bookService.selectBookById(bookId);
            req.setAttribute("book", book);
            req.getRequestDispatcher("book_detail.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            resp.sendRedirect("reader_index.jsp");
        }
    }
}