package Service;

import Dao.BookDao;
import Dao.ReaderDao;
import Entity.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookService {
    void setBookDao(BookDao bookDao);
    List<Book> searchBook(String keyword) throws SQLException;
}
