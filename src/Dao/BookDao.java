package Dao;

import Entity.Book;

import java.sql.SQLException;
import java.util.List;


public interface BookDao {



    List<Book> searchBook(String book_name) throws SQLException;


    Book selectBookById(int bookId) throws SQLException;
    List<Book> searchBooks(String keyword) throws SQLException;
    boolean updateBook(Book book) throws SQLException;
    boolean deleteBook(int bookId) throws SQLException;
    boolean insertBook(Book book) throws SQLException;
}