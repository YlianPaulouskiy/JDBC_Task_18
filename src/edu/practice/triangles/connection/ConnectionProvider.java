package edu.practice.triangles.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

import static edu.practice.triangles.constant.Constant.*;

/**
 * Get DB connection
 */
public class ConnectionProvider {


    /**
     * Get connection to database
     *
     * @return Optional of connection
     */
    public Optional<Connection> connection() {
        try {
            return Optional.of(DriverManager.getConnection(URl, USERNAME, PASSWORD));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return Optional.empty();
    }


}
