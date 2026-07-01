package Dao;
import Entity.ViewAdminStatistics;

import java.sql.SQLException;

public interface ViewAdminStatisticsDao {
    ViewAdminStatistics getStatistics() throws SQLException;

}
