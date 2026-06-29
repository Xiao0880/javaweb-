package Servlet;

import JDBC.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.*;



import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/t")
public class tServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String sql = "SELECT * FROM books WHERE book_name = '活着'";
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);


            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                PrintWriter ps = new PrintWriter(resp.getOutputStream());
                ps.print(rs.getString("book_name"));
            }

            DBUtil.close(conn, pstmt, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
