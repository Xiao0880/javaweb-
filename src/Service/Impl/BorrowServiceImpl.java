package Service.Impl;

import Dao.BookDao;
import Dao.BorrowDao;
import Entity.Book;
import Entity.Borrow;
import Service.BorrowService;

import java.sql.Date;
import java.sql.SQLException;

public class BorrowServiceImpl implements BorrowService {

    private BorrowDao borrowDao;
    private BookDao bookDao;

    @Override
    public void setBorrowDao(BorrowDao borrowDao) {
        this.borrowDao = borrowDao;
    }
    @Override
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
    @Override
    public boolean borrowBook(int readerId, int bookId) throws SQLException {
        // 查询库存
        Book book = bookDao.selectBookById(bookId);
        if (book.getStock() <= 0) {
            return false;
        }
        // 添加借阅记录（触发器会自动减少库存）
        Borrow borrow = new Borrow();
        borrow.setReaderId(readerId);
        borrow.setBookId(bookId);
        borrow.setBorrowDate(new Date(System.currentTimeMillis()));
        borrow.setReturnDate(null);
        borrow.setLimitDate(new Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000));
        return borrowDao.insertBorrowRecord(borrow);
    }


}