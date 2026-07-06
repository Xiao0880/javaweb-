package Service;

import Dao.BookDao;
import Dao.BookTypeDao;
import Entity.Book;
import Entity.BookType;

import java.sql.SQLException;
import java.util.List;

public interface BookService {
    void setBookDao(BookDao bookDao);
    void setBookTypeDao(BookTypeDao bookTypeDao);
    List<Book> searchBook(String keyword) throws SQLException;
    List<Book> searchBooks(String keyword) throws SQLException;
    boolean updateBook(Book book) throws SQLException;
    boolean deleteBook(int bookId) throws SQLException;
    boolean insertBook(Book book) throws SQLException;
    Book selectBookById(int bookId) throws SQLException;
    List<BookType> getAllBookTypes() throws SQLException;
}
