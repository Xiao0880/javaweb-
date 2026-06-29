package Service.Impl;

import Dao.BookDao;
import Entity.Book;
import Service.BookService;

import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    @Override
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<Book> searchBook(String keyword) throws SQLException {
        return bookDao.searchBook(keyword);
    }
}
