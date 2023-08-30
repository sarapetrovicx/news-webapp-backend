package rs.raf.wpbackend.repositories;

import java.sql.*;
import java.util.Optional;

abstract public class MySqlAbstractRepository {

    public MySqlAbstractRepository() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//    jdbc:mariadb://localhost:3306/user-service?useSSL=false
    protected Connection newConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mariadb://" + this.getHost() + ":" + this.getPort() + "/" + this.getDatabaseName(), this.getUsername(), null
        );
    }

    protected String getHost() {
        return "localhost";
    }

    protected int getPort() {
        return 3306;
    }

    protected String getDatabaseName() {
        return "projekat_schema";
    }

    protected String getUsername() {
        return "root";
    }

    protected String getPassword() {
        return "root";
    }

    protected void closeStatement(Statement statement) {
        try {
            Optional.of(statement).get().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void closeResultSet(ResultSet resultSet) {
        try {
            Optional.of(resultSet).get().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void closeConnection(Connection connection) {
        try {
            Optional.of(connection).get().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
