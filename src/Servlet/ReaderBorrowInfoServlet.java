package Servlet;

import Entity.Reader;
import Entity.ViewBorrowInfo;
import Service.ReaderService;
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

@WebServlet("/borrow_info.do")
public class ReaderBorrowInfoServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Reader reader = (Reader) req.getSession().getAttribute("reader");
        String readerName = reader.getReaderName();
        ServletContext context = getServletContext();
        Map<String,Object> container = (Map<String,Object>) context.getAttribute("container");
        ReaderService readerService = (ReaderService) container.get("readerService");
        try {
            List<ViewBorrowInfo> borrowInfoList = readerService.getBorrowInfo(readerName);
            req.setAttribute("borrowInfoList", borrowInfoList);
            req.getRequestDispatcher("reader_borrow_info.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
