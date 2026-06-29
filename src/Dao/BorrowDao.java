package Dao;


import Entity.Borrow;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface BorrowDao {

    boolean insertBorrowRecord(Borrow borrow) throws SQLException;
    List<Borrow> getBorrowRecordsByReaderId(int readerId) throws SQLException;
    boolean updateBorrow(int borrowId) throws SQLException;
    Borrow getBorrowById(int borrowId) throws SQLException;
}