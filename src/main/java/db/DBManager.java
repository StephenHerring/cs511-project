package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */
public abstract class DBManager<T> {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/EMP";

    static final String USERNAME = "username";
    static final String PASSWORD = "password";

    private Connection mConn;

    public DBManager() {
        try {
            Class.forName("com.mysql.jdbs.Driver");
            mConn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public abstract T executeQuery(String query);
}
