package rs.raf.wpbackend.repositories.categories;

import rs.raf.wpbackend.entities.Category;
import rs.raf.wpbackend.entities.Comment;
import rs.raf.wpbackend.entities.Post;
import rs.raf.wpbackend.repositories.MySqlAbstractRepository;
import rs.raf.wpbackend.repositories.posts.PostRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySqlCategoryRepository extends MySqlAbstractRepository implements CategoryRepository {

    @Override
    public Category add(Category category) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            if (doesCategoryExist(connection, category.getName())) {
                throw new IllegalArgumentException("Category with the same name already exists");
            }

            preparedStatement = connection.prepareStatement("INSERT INTO categories (name, description) VALUES(?, ?)", generatedColumns);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                category.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return category;
    }

    private boolean doesCategoryExist(Connection connection, String name) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM categories WHERE name = ?");
            preparedStatement.setString(1, name);
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
    public List<Category> all() {
        List<Category> categories = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from categories");
            while (resultSet.next()) {
                categories.add(new Category(resultSet.getInt("id"),  resultSet.getString("name"), resultSet.getString("description")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return categories;
    }

    @Override
    public Category find(Integer id) {
        Category comment = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM categories where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int comId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                comment = new Category(comId, name, description);
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

        return comment;
    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM categories where id = ?");
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
    public Category edit(Category category, Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();


            if (doesCategoryExist(connection, category.getName())) {
                throw new IllegalArgumentException("Category with the same name already exists");
            }

            preparedStatement = connection.prepareStatement("UPDATE categories SET name = ?, description = ? WHERE id = ?");
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();

            category.setId(id);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return category;
    }

}
