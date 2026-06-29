package Dao;
import Entity.Reader;
import Entity.Reader;

import java.sql.Date;
import java.sql.SQLException;

public interface ReaderDao {
    boolean login(String name, String password) throws SQLException;
    Reader selectReaderbyName(String name) throws SQLException;
    boolean insertReader(Reader reader) throws SQLException;


}

