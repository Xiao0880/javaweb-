package Service;

import Dao.ViewAdminStatisticsDao;
import Entity.ViewAdminStatistics;

import java.sql.SQLException;

public interface AdminService {
    void setViewAdminStatisticsDao(ViewAdminStatisticsDao viewAdminStatisticsDao);
    boolean login(String name, String password) throws SQLException;
    ViewAdminStatistics getAdminStatistics() throws SQLException;
}
