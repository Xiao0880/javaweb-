package Dao.Impl;

import Dao.ViewAdminStatisticsDao;
import Entity.ViewAdminStatistics;
import JDBC.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewAdminStatisticsDaoImpl implements ViewAdminStatisticsDao {
    @Override
    public ViewAdminStatistics getStatistics() throws SQLException {
        ViewAdminStatistics viewAdminStatistics = new ViewAdminStatistics();
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="select * from v_admin_statistics";
        conn= DBUtil.getConnection();
        pstmt=conn.prepareStatement(sql);
        rs=pstmt.executeQuery();
        if(rs.next()){
            viewAdminStatistics.setCurrentBorrowReaderCount(rs.getInt("current_borrow_reader_count"));
            viewAdminStatistics.setTodayBorrowCount(rs.getInt("today_borrow_count"));
            viewAdminStatistics.setTodayReturnCount(rs.getInt("today_return_count"));
            viewAdminStatistics.setTotalStock(rs.getInt("total_stock"));
        }
        return viewAdminStatistics;
    }
}
