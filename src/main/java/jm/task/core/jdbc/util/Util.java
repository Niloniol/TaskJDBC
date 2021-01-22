package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Util {

    private static final Logger log = Logger.getLogger(Util.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mydb?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Yusdawg123ss";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            //log.info("Database connection success");
        } catch (ClassNotFoundException | SQLException e){
            //log.warning("Database connection failure");
        }
        return connection;
    }
}
