package db;


import data.DatabaseElement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class DBManager {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/cs511";

    static final String USERNAME = "root";
    static final String PASSWORD = "welcome";

    private Connection mConn;

    public DBManager() {
        start();
    }

    public boolean executeWriteStatement(String statement) {
        try {
            return mConn.createStatement().execute(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<DatabaseElement> query(String query) {
        try {
            ResultSet resultSet = mConn.createStatement().executeQuery(query);
            return parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void start() {
        try {
            Class.forName(JDBC_DRIVER);
            mConn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void finish() {
        try {
            mConn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<DatabaseElement> parseResultSet(ResultSet resultSet) throws SQLException {
        List<DatabaseElement> elements = new ArrayList<>();
        while (resultSet.next()) {
            int num = resultSet.getInt(1);
            int tRank = resultSet.getInt(2);
            DatabaseElement element = new DatabaseElement(num, tRank);
            elements.add(element);
        }
        return elements;
    }


}