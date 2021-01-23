package core.basesyntax.util;

import core.basesyntax.exception.DataProcessingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't find postgres driver " + e);
        }
    }

    public static Connection getConnection() {
        Properties dbProperties = new Properties();
        dbProperties.put("user", "yedzzkerctojhf");
        dbProperties.put("password",
                "56629467b6bf25aa8f82dd3711c2a52c20f67665c46af89effaf2f77a260c7d2");
        String url = "jdbc:postgresql://ec2-34-251-118-151.eu-west-1.compute.amazonaws.com:5432"
                + "/dbj8lruo1jle2q";
        try {
            return DriverManager.getConnection(url, dbProperties);
        } catch (SQLException throwables) {
            throw new DataProcessingException("ERROR: Connection to DB has been failed!",
                    throwables);
        }
    }
}
