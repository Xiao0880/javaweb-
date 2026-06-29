package Dao;

import Entity.ViewBorrowInfo;

import java.sql.SQLException;
import java.util.List;

public interface ViewBorrowInfoDao {
    List<ViewBorrowInfo> selectBorrowInfoByReaderName(String readerName) throws SQLException;
    List<ViewBorrowInfo> selectBorrowInfoByBorrowId(int borrowId) throws SQLException;
    List<ViewBorrowInfo> selectAllBorrowInfo() throws SQLException;
    boolean insertBorrowInfo(ViewBorrowInfo borrowInfo) throws SQLException;

}