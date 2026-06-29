package Service;

import java.sql.SQLException;

public interface AdminService {
    public boolean login(String name, String password) throws SQLException;
}
