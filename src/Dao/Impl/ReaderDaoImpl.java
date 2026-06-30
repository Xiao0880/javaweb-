package Dao.Impl;

import Dao.ReaderDao;
import Entity.Reader;
import JDBC.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderDaoImpl implements ReaderDao {

    @Override
    public boolean login(String name, String password) throws SQLException {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="select * from reader where reader_name=? AND reader_password=?";
        conn= DBUtil.getConnection();
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,name);
        pstmt.setString(2,password);
        rs=pstmt.executeQuery();
        return rs.next();
    }

    private Reader getReader(ResultSet rs, Reader reader) throws SQLException {
        if(rs.next()){
            reader =new Reader();
            reader.setReaderId(rs.getInt("reader_id"));
            reader.setReaderName(rs.getString("reader_name"));
            reader.setReaderPassword(rs.getString("reader_password"));
            reader.setGender(rs.getString("gender"));
            reader.setPhone(rs.getString("phone"));
            reader.setRegisterDate(rs.getDate("register_date"));
        }
        return reader;
    }




    @Override
    public Reader selectReaderbyName(String name) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from reader where reader_name=?";
        conn = DBUtil.getConnection();
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        rs = pstmt.executeQuery();
        Reader reader = null;
        return getReader(rs, reader);
    }

    @Override
    public boolean insertReader(Reader reader) throws SQLException {
        Connection conn=null;
        PreparedStatement pstmt=null;

        String sql="insert into reader(reader_name,reader_password,gender,phone,register_date) " +
                "values(?,?,?,?,?)";
        conn= DBUtil.getConnection();
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,reader.getReaderName());
        pstmt.setString(2,reader.getReaderPassword());
        pstmt.setString(3,reader.getGender());
        pstmt.setString(4,reader.getPhone());
        pstmt.setDate(5,reader.getRegisterDate());
        return pstmt.executeUpdate()>0;
    }

    @Override
    public List<Reader> searchReaders(String keyword) throws SQLException {
        List<Reader> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM reader WHERE reader_name LIKE ? OR phone LIKE ? OR reader_id = ?";
        conn = DBUtil.getConnection();
        pstmt = conn.prepareStatement(sql);
        String pattern = "%" + keyword + "%";
        pstmt.setString(1, pattern);
        pstmt.setString(2, pattern);
        try {
            pstmt.setInt(3, Integer.parseInt(keyword));
        } catch (NumberFormatException e) {
            pstmt.setInt(3, -1);
        }
        rs = pstmt.executeQuery();
        
        while (rs.next()) {
            Reader reader = new Reader();
            reader.setReaderId(rs.getInt("reader_id"));
            reader.setReaderName(rs.getString("reader_name"));
            reader.setReaderPassword(rs.getString("reader_password"));
            reader.setGender(rs.getString("gender"));
            reader.setPhone(rs.getString("phone"));
            reader.setRegisterDate(rs.getDate("register_date"));
            list.add(reader);
        }
        
        DBUtil.close(conn, pstmt, rs);
        return list;
    }

    @Override
    public boolean updateReader(Reader reader) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        String sql = "UPDATE reader SET reader_name = ?, reader_password = ?, gender = ?, phone = ? WHERE reader_id = ?";
        conn = DBUtil.getConnection();
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, reader.getReaderName());
        pstmt.setString(2, reader.getReaderPassword());
        pstmt.setString(3, reader.getGender());
        pstmt.setString(4, reader.getPhone());
        pstmt.setInt(5, reader.getReaderId());
        
        int rows = pstmt.executeUpdate();
        DBUtil.close(conn, pstmt, null);
        return rows > 0;
    }

    @Override
    public boolean deleteReader(int readerId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        String sql = "DELETE FROM reader WHERE reader_id = ?";
        conn = DBUtil.getConnection();
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, readerId);
        
        int rows = pstmt.executeUpdate();
        DBUtil.close(conn, pstmt, null);
        return rows > 0;
    }

    @Override
    public Reader selectReaderById(int readerId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Reader reader = null;
        
        String sql = "SELECT * FROM reader WHERE reader_id = ?";
        conn = DBUtil.getConnection();
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, readerId);
        rs = pstmt.executeQuery();
        
        if (rs.next()) {
            reader = new Reader();
            reader.setReaderId(rs.getInt("reader_id"));
            reader.setReaderName(rs.getString("reader_name"));
            reader.setReaderPassword(rs.getString("reader_password"));
            reader.setGender(rs.getString("gender"));
            reader.setPhone(rs.getString("phone"));
            reader.setRegisterDate(rs.getDate("register_date"));
        }
        
        DBUtil.close(conn, pstmt, rs);
        return reader;
    }

}