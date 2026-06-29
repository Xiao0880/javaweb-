package JDBC;

import java.sql.*;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/librarys?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USER = "root";
    private static final String PWD = "123456";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 获取连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PWD);
    }

    // 关闭资源
    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try { if(rs != null) rs.close(); } catch (SQLException e){}
        try { if(pstmt != null) pstmt.close(); } catch (SQLException e){}
        try { if(conn != null) conn.close(); } catch (SQLException e){}
    }
}