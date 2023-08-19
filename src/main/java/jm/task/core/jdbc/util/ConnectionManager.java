package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/jdbc";
    static final String USER = "postgres";
    static final String password = "";
private ConnectionManager(){

}
public static Connection open(){
        try {
            return DriverManager.getConnection(DB_URL, USER, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
