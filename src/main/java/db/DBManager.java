package db;


import data.Row;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *  Establishes connection to database, executes all statements, and parses query results
 */
public abstract class DBManager {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/cs511";

    static final String USERNAME = "root";
    static final String PASSWORD = "root";

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


    public List<Row> query(String query) {
        try {
            ResultSet resultSet = mConn.createStatement().executeQuery(query);
            return parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int query_max(String query) {
        try {
            ResultSet resultSet = mConn.createStatement().executeQuery(query);
            while(resultSet.next()) {
                int pos = resultSet.getInt(1);
                System.out.println("mx index:" + pos);
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
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

    private List<Row> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Row> elements = new ArrayList<>();
        while (resultSet.next()) {
            //System.out.println("Result set size:" + resultSet.getInt(1) + resultSet.getString(2) + resultSet.getFloat(3) + resultSet.getLong(4));
            int num = resultSet.getInt(1);
            String name = resultSet.getString(2);
            float gpa = resultSet.getFloat(3);
            long student_id = resultSet.getLong(4);
            Row element = new Row(num, name, gpa, student_id);
            elements.add(element);
        }
        return elements;
    }


}
