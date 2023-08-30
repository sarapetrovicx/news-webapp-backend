package rs.raf.wpbackend.repositories.user;

import org.apache.commons.codec.digest.DigestUtils;
import rs.raf.wpbackend.entities.User;
import rs.raf.wpbackend.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserRepository extends MySqlAbstractRepository implements UserRepository {

    @Override
    public User add(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            user.setHashedPassword(DigestUtils.sha256Hex(user.getHashedPassword()));

            if (doesUserExist(connection, user.getEmail())) {
                throw new IllegalArgumentException("User with the same email already exists");
            }

            preparedStatement = connection.prepareStatement("INSERT INTO users (email, first_name, last_name, role, status, hashed_password) VALUES(?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirst_name());
            preparedStatement.setString(3, user.getLast_name());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getStatus());
            preparedStatement.setString(6, user.getHashedPassword());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return user;
    }

    private boolean doesUserExist(Connection connection, String email) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM users WHERE email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
        }

        return false;
    }


    @Override
    public List<User> all() {
        List<User> users = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"),  resultSet.getString("email"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("role"),
                        resultSet.getString("status"), resultSet.getString("hashed_password")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return users;
    }

    @Override
    public User findUser(String email) {
        User user = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM users where email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int userId = resultSet.getInt("id");

                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String role = resultSet.getString("role");
                String status = resultSet.getString("status");
                String hashed_password = resultSet.getString("hashed_password");
                user = new User(userId, email, first_name, last_name, role, status, hashed_password);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return user;
    }

    @Override
    public User edit(User user, Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            if (doesUserExist(connection, user.getEmail())) {
                throw new IllegalArgumentException("User with the same email already exists");
            }

            preparedStatement = connection.prepareStatement("UPDATE users SET email = ?, first_name = ?, " +
                    "last_name = ?, role = ?, status = ?, hashed_password = ? WHERE id = ?");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirst_name());
            preparedStatement.setString(3, user.getLast_name());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getStatus());
            preparedStatement.setString(6, user.getHashedPassword());
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();

            user.setId(id);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return user;
    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM users where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public void activate(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("UPDATE users SET status = ? WHERE id = ?", generatedColumns);
            preparedStatement.setString(1, "active");
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public void deactivate(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("UPDATE users SET status = ? WHERE id = ?", generatedColumns);
            preparedStatement.setString(1, "inactive");
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }


}
