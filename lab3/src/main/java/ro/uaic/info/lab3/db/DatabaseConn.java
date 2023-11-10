package ro.uaic.info.lab3.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseConn {

    private static final Logger LOGGER = Logger.getLogger(DatabaseConn.class.getName());
    private static final String URL = "jdbc:postgresql://localhost:5433/tjlabs";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";

    public static Connection getConnection() {
        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("jdbc/postgres");
            return dataSource.getConnection();
        } catch (NamingException  | SQLException e){
            LOGGER.severe(e.getMessage());
            throw new RuntimeException("Database connection error.", e);
        }
    }
}
