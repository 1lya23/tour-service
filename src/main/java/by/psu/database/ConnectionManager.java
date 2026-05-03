package by.psu.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/tour-service";

    private static final String USER =
            "admin";

    private static final String PASSWORD =
            "admin";

    public static Connection open()
            throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}