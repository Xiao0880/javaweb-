package Dao;

import Entity.Book;

import java.sql.SQLException;
import java.util.List;


public interface BookDao {



    List<Book> searchBook(String book_name) throws SQLException;


    Book selectBookById(int bookId) throws SQLException;
}