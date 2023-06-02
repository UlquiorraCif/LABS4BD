package models;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    // Получение JDBC драйвера и установка соедиения с сервером
    public static Connection connect() {
        Connection connection = null;

        try {
            Driver driver =  new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

            String username = "root";
            String password = "root";
            String url = "jdbc:mysql://localhost:3306/tests?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";

            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.getStackTrace();
        }

        return connection;
    }
}