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
import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/admin/admin_book_update.do")
public class BookUpdateServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        Map<String, Object> container = (Map<String, Object>) context.getAttribute("container");
        BookService bookService = (BookService) container.get("bookService");
        String searchText = req.getParameter("search_text");
        try {
            Book book = new Book();
            book.setBookId(Integer.parseInt(req.getParameter("book_id")));
            book.setBookName(req.getParameter("book_name"));
            book.setAuthor(req.getParameter("author"));
            book.setPublisher(req.getParameter("publisher"));
            book.setPublishDate(Date.valueOf(req.getParameter("publish_date")));
            book.setIsbn(req.getParameter("isbn"));
            book.setTypeId(Integer.parseInt(req.getParameter("type_id")));
            book.setStock(Integer.parseInt(req.getParameter("stock")));
            
            if (bookService.updateBook(book)) {
                String encodedText = searchText != null ? URLEncoder.encode(searchText, StandardCharsets.UTF_8) : "";
                resp.sendRedirect(req.getContextPath() + "/admin/admin_book_search.do?search_text=" + encodedText);
            } else {
                req.setAttribute("error", "修改图书信息失败");
                req.setAttribute("book", book);
                req.getRequestDispatcher("book_edit.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
