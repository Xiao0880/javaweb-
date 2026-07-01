package Dao.Impl;

import Dao.BorrowDao;
import Entity.Borrow;
import JDBC.DBUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BorrowDaoImpl implements BorrowDao {
    private BorrowDao borrowDao;
    @Override
    public boolean insertBorrowRecord(Borrow borrow) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        conn = DBUtil.getConnection();

        String sql = "INSERT INTO borrow (reader_id, book_id, borrow_date, limit_date) VALUES (?, ?, ?, ?)";

        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, borrow.getReaderId());
        pstmt.setInt(2, borrow.getBookId());
        pstmt.setDate(3, borrow.getBorrowDate());
        pstmt.setDate(4, borrow.getLimitDate());

        int rows = pstmt.executeUpdate();
        DBUtil.close(conn, pstmt, null);
        return rows > 0;
    }
    @Override
    public List<Borrow> getBorrowRecordsByReaderId(int readerId) throws SQLException {
        return List.of();
    }
    @Override
    public boolean updateBorrow(int borrowId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBUtil.getConnection();
            Date returnDate = new Date(System.currentTimeMillis());
            String sql = "UPDATE borrow SET return_date = ? WHERE borrow_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1, returnDate);
            pstmt.setInt(2, borrowId);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } finally {
            DBUtil.close(conn, pstmt, null);
        }
    }
    @Override
    public Borrow getBorrowById(int borrowId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM borrow WHERE borrow_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, borrowId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Borrow borrow = new Borrow();
                borrow.setBorrowId(rs.getInt("borrow_id"));
                borrow.setReaderId(rs.getInt("reader_id"));
                borrow.setBookId(rs.getInt("book_id"));
                borrow.setBorrowDate(rs.getDate("borrow_date"));
                borrow.setReturnDate(rs.getDate("return_date"));
                borrow.setLimitDate(rs.getDate("limit_date"));
                return borrow;
            }
            return null;
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }
}