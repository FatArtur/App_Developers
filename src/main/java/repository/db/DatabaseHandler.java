package repository.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseHandler {
    private static DatabaseHandler databaseHandler;
    private static PreparedStatement statement;
    private static Properties property;
    private static String DB_USER;
    private static String DB_PASSWORD;
    private static String DB_HOST;
    private static Connection dbConnection;

    private DatabaseHandler() throws SQLException{
        dbConnection = DriverManager.getConnection(DB_HOST, DB_USER, DB_PASSWORD);
    }


    static {
        FileInputStream fis;
        property = new Properties();
        try {
            fis = new FileInputStream("src/main/resources/application.properties");
            property.load(fis);
            DB_HOST = property.getProperty("DB_HOST");
            DB_USER = property.getProperty("DB_USER");
            DB_PASSWORD = property.getProperty("DB_PASSWORD");
            fis.close();
        } catch (IOException e) {
            System.out.println("ОШИБКА: Файл свойств отсуствует!");
        }
    }

    public static PreparedStatement getStatement(String query) throws SQLException {
        if (databaseHandler == null) {
            databaseHandler = new DatabaseHandler();
        }
        statement = dbConnection.prepareStatement(query);
        return statement;
    }
}
