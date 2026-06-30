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

    @Override
    public List<Book> searchBooks(String keyword) throws SQLException {
        return bookDao.searchBooks(keyword);
    }

    @Override
    public boolean updateBook(Book book) throws SQLException {
        return bookDao.updateBook(book);
    }

    @Override
    public boolean deleteBook(int bookId) throws SQLException {
        return bookDao.deleteBook(bookId);
    }

    @Override
    public boolean insertBook(Book book) throws SQLException {
        return bookDao.insertBook(book);
    }

    @Override
    public Book selectBookById(int bookId) throws SQLException {
        return bookDao.selectBookById(bookId);
    }
}
