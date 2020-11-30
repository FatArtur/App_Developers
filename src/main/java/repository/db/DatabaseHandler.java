package repository.db;

import java.sql.*;

public class DatabaseHandler {
    private final static String connectionString = "jdbc:mysql://localhost/CRUD_schema" +
            "?useTimezone=true&serverTimezone=UTC";
    private final static String DB_USER = "root";
    private final static String DB_PASSWORD = "fotya2227";
    private static Connection dbConnection;

    public static Connection getDbConnection() throws SQLException {
        dbConnection = DriverManager.getConnection(connectionString, DB_USER, DB_PASSWORD);
        return dbConnection;
    }

    public static ResultSet readDBase(String query) throws SQLException {
        Statement statement = getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }
    public static void writeDBase(String query) throws SQLException{
        Statement statement = getDbConnection().createStatement();
        statement.executeQuery(query);
    }
}
