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
import java.util.List;
import java.util.Map;

@WebServlet("/search_book")
public class SearchBookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search_text=request.getParameter("search_text");
        ServletContext context = getServletContext();
        Map<String,Object> container = (Map<String,Object>) context.getAttribute("container");
        BookService bookService = (BookService) container.get("bookService");
        List<Book> books = null;
        try {
            books = bookService.searchBook(search_text);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getSession().setAttribute("books", books);
        request.getSession().setAttribute("searchText", search_text);
        response.sendRedirect("book_search_results.jsp");

    }
}