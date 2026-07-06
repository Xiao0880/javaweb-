package Dao.Impl;

import Dao.BookTypeDao;
import JDBC.DBUtil;
import Entity.BookType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookTypeDaoImpl implements BookTypeDao {
    @Override
    public List<BookType> getAllBookTypes() throws SQLException {
        List<BookType> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM book_type";
        conn = DBUtil.getConnection();
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        
        while (rs.next()) {
            BookType type = new BookType();
            type.setTypeId(rs.getInt("type_id"));
            type.setTypeName(rs.getString("type_name"));
            list.add(type);
        }
        
        DBUtil.close(conn, pstmt, rs);
        return list;
    }
}