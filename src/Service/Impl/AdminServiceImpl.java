package Service.Impl;

import Dao.ViewAdminStatisticsDao;
import Entity.ViewAdminStatistics;
import JDBC.DBUtil;
import Service.AdminService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminServiceImpl implements AdminService {
    private ViewAdminStatisticsDao viewAdminStatisticsDao;


    @Override
    public void setViewAdminStatisticsDao(ViewAdminStatisticsDao viewAdminStatisticsDao) {
        this.viewAdminStatisticsDao = viewAdminStatisticsDao;
    }

    @Override
    public boolean login(String name, String password) throws SQLException {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="select * from admin where admin_name=? AND admin_password=?";
        conn= DBUtil.getConnection();
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,name);
        pstmt.setString(2,password);
        rs=pstmt.executeQuery();
        return rs.next();
    }

    @Override
    public ViewAdminStatistics getAdminStatistics() throws SQLException {
        return viewAdminStatisticsDao.getStatistics();
    }
}
