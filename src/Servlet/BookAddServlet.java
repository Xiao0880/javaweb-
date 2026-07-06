package Servlet;

import Entity.Book;
 import Entity.BookType;
import Service.BookService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/admin/admin_book_add.do")
public class BookAddServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        Map<String, Object> container = (Map<String, Object>) context.getAttribute("container");
        BookService bookService = (BookService) container.get("bookService");
        try {
            List<BookType> bookTypes = bookService.getAllBookTypes();
            req.setAttribute("bookTypes", bookTypes);
            req.getRequestDispatcher("book_add.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "获取图书类型失败");
            req.getRequestDispatcher("book_add.jsp").forward(req, resp);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookName = req.getParameter("book_name");
        String author = req.getParameter("author");
        String publisher = req.getParameter("publisher");
        String publishDate = req.getParameter("publish_date");
        String isbn = req.getParameter("isbn");
        String typeIdStr = req.getParameter("type_id");
        String stockStr = req.getParameter("stock");
        
        ServletContext context = getServletContext();
        Map<String, Object> container = (Map<String, Object>) context.getAttribute("container");
        BookService bookService = (BookService) container.get("bookService");
        
        try {
            Book book = new Book();
            book.setBookName(bookName);
            book.setAuthor(author);
            book.setPublisher(publisher);
            book.setPublishDate(Date.valueOf(publishDate));
            book.setIsbn(isbn);
            book.setTypeId(Integer.parseInt(typeIdStr));
            book.setStock(Integer.parseInt(stockStr));
            
            if (bookService.insertBook(book)) {
                resp.sendRedirect(req.getContextPath() + "/admin/admin_book_search.do");
            } else {
                req.setAttribute("error", "添加图书失败");
                req.getRequestDispatcher("book_add.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "添加图书失败：" + e.getMessage());
            req.getRequestDispatcher("book_add.jsp").forward(req, resp);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            req.setAttribute("error", "输入数据格式错误");
            req.getRequestDispatcher("book_add.jsp").forward(req, resp);
        }
    }
}