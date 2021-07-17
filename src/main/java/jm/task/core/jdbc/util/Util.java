package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String url = "jdbc:mysql://localhost:3306/user_schema";
    private static final String login = "root";
    private static final String password = "rooot";

    public static Connection getMySQLConnection() {

        Connection connection = null;

        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(url, login, password);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }
}


