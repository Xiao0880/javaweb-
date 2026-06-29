package Servlet;

import Entity.Book;
import Entity.Reader;
import Service.BookService;
import Service.BorrowService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/borrow.do")
public class ReaderBorrowServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Reader reader = (Reader) req.getSession().getAttribute("reader");
        int bookId = Integer.parseInt(req.getParameter("book_id"));
        int readerId = reader.getReaderId();

        ServletContext context = getServletContext();
        Map<String,Object> container = (Map<String,Object>) context.getAttribute("container");
        BorrowService borrowService = (BorrowService) container.get("borrowService");

        try {
            if(borrowService.borrowBook(readerId,bookId)){
                String searchText = (String) req.getSession().getAttribute("searchText");
                BookService bookService = (BookService) container.get("bookService");
                List<Book> books = bookService.searchBook(searchText);
                req.getSession().setAttribute("books", books);
                
                resp.sendRedirect("book_search_results.jsp");
            }
            else{
                resp.sendRedirect("error/borrow_failed.html");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}