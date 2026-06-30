package Service.Impl;
import Dao.BorrowDao;
import Dao.ReaderDao;
import Dao.ViewBorrowInfoDao;

import Entity.Borrow;
import Entity.Reader;
import Entity.ViewBorrowInfo;
import Service.ReaderService;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class ReaderServiceImpl implements ReaderService {
    private ReaderDao readerDao;
    private ViewBorrowInfoDao ViewBorrowInfoDao;
    private BorrowDao borrowDao;
    @Override
    public void setReaderDao(ReaderDao readerDao) {
        this.readerDao = readerDao;
    }

    @Override
    public void setBorrowDao(BorrowDao borrowDao) {
        this.borrowDao = borrowDao;
    }

    @Override
    public void setViewBorrowInfoDao(ViewBorrowInfoDao ViewBorrowInfoDao) {
        this.ViewBorrowInfoDao = ViewBorrowInfoDao;
    }
    @Override
    public boolean login(String name, String password) throws SQLException {
        return readerDao.login(name, password);
    }

    @Override
    public boolean register(String name, String password, String gender, String phone,java.sql.Date registerDate) throws SQLException {
        Reader reader = new Reader();
        reader.setReaderName(name);
        reader.setReaderPassword(password);
        reader.setGender(gender);
        reader.setPhone(phone);
        reader.setRegisterDate(registerDate);
        return readerDao.insertReader(reader);
    }

    @Override
    public List<ViewBorrowInfo> getBorrowInfo(String readerName) throws SQLException {
        return ViewBorrowInfoDao.selectBorrowInfoByReaderName(readerName);
    }


    @Override
    public Reader getReaderInfo(String name) throws SQLException {
        return readerDao.selectReaderbyName(name);
    }

    @Override
    public boolean returnBook(int borrowId) throws SQLException {
        return borrowDao.updateBorrow(borrowId);
    }

    @Override
    public boolean isOverdue(int borrowId) throws SQLException {
        Borrow borrow = borrowDao.getBorrowById(borrowId);
        if (borrow == null) {
            return false;
        }
        Date limitDate = borrow.getLimitDate();
        Date currentDate = new Date(System.currentTimeMillis());
        // 如果当前日期超过截止日期，说明超时
        return currentDate.after(limitDate);
    }

    @Override
    public List<Reader> searchReaders(String keyword) throws SQLException {
        return readerDao.searchReaders(keyword);
    }

    @Override
    public boolean updateReader(Reader reader) throws SQLException {
        return readerDao.updateReader(reader);
    }

    @Override
    public boolean deleteReader(int readerId) throws SQLException {
        return readerDao.deleteReader(readerId);
    }

    @Override
    public Reader getReaderById(int readerId) throws SQLException {
        return readerDao.selectReaderById(readerId);
    }
}