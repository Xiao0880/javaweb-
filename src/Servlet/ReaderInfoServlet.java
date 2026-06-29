package Servlet;
import Entity.Reader;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/reader_info.do")
public class ReaderInfoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Reader reader = (Reader) session.getAttribute("reader");
        request.setAttribute("reader",reader);
        request.getRequestDispatcher("reader_info.jsp").forward(request, response);
    }
}
