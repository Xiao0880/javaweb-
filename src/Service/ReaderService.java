package Service;

import Dao.BorrowDao;
import Dao.ReaderDao;
import Dao.ViewBorrowInfoDao;
import Entity.Reader;
import Entity.ViewBorrowInfo;

import java.sql.SQLException;
import java.util.List;

public interface ReaderService {
    void setReaderDao(ReaderDao readerDao);
    void setViewBorrowInfoDao(ViewBorrowInfoDao ViewBorrowInfoDao);
    void setBorrowDao(BorrowDao borrowDao);

    boolean login(String name, String password) throws SQLException;
    boolean register(String name, String password,String gender,String phone,java.sql.Date registerDate) throws SQLException;
    List<ViewBorrowInfo> getBorrowInfo(String readerName) throws SQLException;
    Reader getReaderInfo(String name) throws SQLException;
    boolean returnBook(int borrowId) throws SQLException;
    boolean isOverdue(int borrowId) throws SQLException;

}