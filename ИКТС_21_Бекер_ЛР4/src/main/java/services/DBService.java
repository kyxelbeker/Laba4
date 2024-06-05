package services;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBService {

    private static final Logger LOGGER = Logger.getLogger(DBService.class.getName());

    private Connection getConnect() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "PostgreSQL Driver not found", e);
        }

        final String url = "jdbc:postgresql://localhost:5433/LocalBase";
        final String user = "postgres";
        final String password = "Bb060404";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Connection to the database failed", e);
        }
        return conn;
    }

    public void insert(String sql, Object... params) {
        try (Connection connection = getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Insert operation failed", e);
        }
    }

    public void delete(String sql, Object... params) {
        try (Connection connection = getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Delete operation failed", e);
        }
    }

    public void update(String sql, Object... params) {
        try (Connection connection = getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Update operation failed", e);
        }
    }

    public ResultSet select(String sql) {
        try {
            Connection connection = getConnect();
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Select operation failed", e);
            return null;
        }
    }
}
