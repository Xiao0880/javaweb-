package Dao;

import Entity.BookType;

import java.sql.SQLException;
import java.util.List;

public interface BookTypeDao {
    List<BookType> getAllBookTypes() throws SQLException;
}
