package Dao.Impl;

import Dao.BookDao;
import JDBC.DBUtil;
import Entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    @Override
    public List<Book> searchBook(String keyword) throws SQLException {
        List<Book> list = new ArrayList<>();
        Book book = null;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        // 获取用户输入的搜索关键词
        String sql = "SELECT book.*, (SELECT type_name FROM book_type WHERE book_type.type_id = book.type_id) AS type_name FROM book WHERE book_name LIKE ? OR author LIKE ? OR isbn LIKE ?";
        conn= DBUtil.getConnection();
        pstmt=conn.prepareStatement(sql);
        String pattern = "%" + keyword + "%";
        pstmt.setString(1, pattern);
        pstmt.setString(2, pattern);
        pstmt.setString(3, pattern);
        rs=pstmt.executeQuery();
        while(rs.next()){
            book=new Book();
            book.setStock(rs.getInt("stock"));
            book.setBookId(rs.getInt("book_id"));
            book.setBookName(rs.getString("book_name"));
            book.setAuthor(rs.getString("author"));
            book.setTypeName(rs.getString("type_name"));
            book.setPublisher(rs.getString("publisher"));
            book.setPublishDate(rs.getDate("publish_date"));
            book.setIsbn(rs.getString("isbn"));
            book.setTypeId(rs.getInt("type_id"));
            list.add(book);
        }
        return list;
    }

    @Override
    public Book selectBookById(int bookId) throws SQLException {
        Book book = null;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        // 获取用户输入的搜索关键词
        String sql = "SELECT book.*, (SELECT type_name FROM book_type WHERE book_type.type_id = book.type_id) AS type_name FROM book WHERE book_id = ?";
        conn= DBUtil.getConnection();
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1, bookId);
        rs=pstmt.executeQuery();
        if(rs.next()){
            book=new Book();
            book.setStock(rs.getInt("stock"));
            book.setBookId(rs.getInt("book_id"));
            book.setBookName(rs.getString("book_name"));
            book.setAuthor(rs.getString("author"));
            book.setTypeName(rs.getString("type_name"));
            book.setPublisher(rs.getString("publisher"));
            book.setPublishDate(rs.getDate("publish_date"));
            book.setIsbn(rs.getString("isbn"));
            book.setTypeId(rs.getInt("type_id"));
        }
        return book;
    }

    @Override
    public List<Book> searchBooks(String keyword) throws SQLException {
        List<Book> list = new ArrayList<>();
        Book book = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        String sql = "SELECT book.*, (SELECT type_name FROM book_type WHERE book_type.type_id = book.type_id) AS type_name FROM book WHERE book_name LIKE ? OR author LIKE ? OR isbn LIKE ?";
        conn = DBUtil.getConnection();
        pstmt = conn.prepareStatement(sql);
        String pattern = "%" + keyword + "%";
        pstmt.setString(1, pattern);
        pstmt.setString(2, pattern);
        pstmt.setString(3, pattern);
        rs = pstmt.executeQuery();
        
        while (rs.next()) {
            book = new Book();
            book.setStock(rs.getInt("stock"));
            book.setBookId(rs.getInt("book_id"));
            book.setBookName(rs.getString("book_name"));
            book.setAuthor(rs.getString("author"));
            book.setTypeName(rs.getString("type_name"));
            book.setPublisher(rs.getString("publisher"));
            book.setPublishDate(rs.getDate("publish_date"));
            book.setIsbn(rs.getString("isbn"));
            book.setTypeId(rs.getInt("type_id"));
            list.add(book);
        }
        
        DBUtil.close(conn, pstmt, rs);
        return list;
    }

    @Override
    public boolean updateBook(Book book) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        String sql = "UPDATE book SET book_name = ?, author = ?, publisher = ?, publish_date = ?, isbn = ?, type_id = ?, stock = ? WHERE book_id = ?";
        conn = DBUtil.getConnection();
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, book.getBookName());
        pstmt.setString(2, book.getAuthor());
        pstmt.setString(3, book.getPublisher());
        pstmt.setDate(4, book.getPublishDate());
        pstmt.setString(5, book.getIsbn());
        pstmt.setInt(6, book.getTypeId());
        pstmt.setInt(7, book.getStock());
        pstmt.setInt(8, book.getBookId());
        
        int rows = pstmt.executeUpdate();
        DBUtil.close(conn, pstmt, null);
        return rows > 0;
    }

    @Override
    public boolean deleteBook(int bookId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        String sql = "DELETE FROM book WHERE book_id = ?";
        conn = DBUtil.getConnection();
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, bookId);
        
        int rows = pstmt.executeUpdate();
        DBUtil.close(conn, pstmt, null);
        return rows > 0;
    }

    @Override
    public boolean insertBook(Book book) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        String sql = "INSERT INTO book (book_name, author, publisher, publish_date, isbn, type_id, stock) VALUES (?, ?, ?, ?, ?, ?, ?)";
        conn = DBUtil.getConnection();
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, book.getBookName());
        pstmt.setString(2, book.getAuthor());
        pstmt.setString(3, book.getPublisher());
        pstmt.setDate(4, book.getPublishDate());
        pstmt.setString(5, book.getIsbn());
        pstmt.setInt(6, book.getTypeId());
        pstmt.setInt(7, book.getStock());
        
        int rows = pstmt.executeUpdate();
        DBUtil.close(conn, pstmt, null);
        return rows > 0;
    }
}
