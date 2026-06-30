package Dao;
import Entity.Reader;
import Entity.Reader;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ReaderDao {
    boolean login(String name, String password) throws SQLException;
    Reader selectReaderbyName(String name) throws SQLException;
    boolean insertReader(Reader reader) throws SQLException;
    List<Reader> searchReaders(String keyword) throws SQLException;
    boolean updateReader(Reader reader) throws SQLException;
    boolean deleteReader(int readerId) throws SQLException;
    Reader selectReaderById(int readerId) throws SQLException;
}

