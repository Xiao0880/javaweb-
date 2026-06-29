package Dao.Impl;

import Dao.ReaderDao;
import Entity.Reader;
import JDBC.DBUtil;

import java.sql.*;

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

}