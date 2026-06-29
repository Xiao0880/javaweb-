package Service;

import Dao.BookDao;
import Dao.BorrowDao;

import java.sql.SQLException;

public interface BorrowService {
    void setBorrowDao(BorrowDao borrowDao);
    void setBookDao(BookDao bookDao);
    boolean borrowBook(int readerId, int bookId) throws SQLException;

}
