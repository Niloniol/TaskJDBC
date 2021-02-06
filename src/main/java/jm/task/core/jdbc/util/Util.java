package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class Util {

    private static final Logger log = Logger.getLogger(Util.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mydb?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Yusdawg123ss";
    private static SessionFactory sessionFactory;

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

    public static SessionFactory getSessionFactory() {
        try {
            Configuration configuration = new Configuration();

            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            settings.put(Environment.URL, "jdbc:mysql://localhost:3306/mydb?useSSL=false");
            settings.put(Environment.USER, "root");
            settings.put(Environment.PASS, "Yusdawg123ss");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

            settings.put(Environment.SHOW_SQL, "true");

            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

            settings.put(Environment.HBM2DDL_AUTO, "create-drop");

            configuration.setProperties(settings);
            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e){
            e.printStackTrace();
        }

        return sessionFactory;
    }
}
