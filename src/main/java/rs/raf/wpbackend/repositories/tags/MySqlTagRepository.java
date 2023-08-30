package rs.raf.wpbackend.repositories.tags;

import rs.raf.wpbackend.entities.Category;
import rs.raf.wpbackend.entities.Tag;
import rs.raf.wpbackend.repositories.MySqlAbstractRepository;
import rs.raf.wpbackend.repositories.categories.CategoryRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySqlTagRepository extends MySqlAbstractRepository implements TagRepository {

    @Override
    public Tag add(Tag tag) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

//            SELECT `id` FROM `tags` WHERE `keyword` = 'Fudbal'
            preparedStatement = connection.prepareStatement("SELECT id FROM tags WHERE keyword = ?");
            preparedStatement.setString(1, tag.getKeyword());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Tag vec postoji
                tag.setId(resultSet.getInt("id"));
            } else {
                String[] generatedColumns = {"id"};

                preparedStatement = connection.prepareStatement("INSERT INTO tags (keyword) VALUES(?)", generatedColumns);
                preparedStatement.setString(1, tag.getKeyword());
                preparedStatement.executeUpdate();
                resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {
                    tag.setId(resultSet.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tag;
    }


    @Override
    public List<Tag> all() {
        return null;
    }

    @Override
    public Tag find(Integer id) {
        Tag tag = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM tags where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int comId = resultSet.getInt("id");
                String name = resultSet.getString("keyword");
                tag = new Tag(comId, name);
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

        return tag;
    }

    @Override
    public void delete(Integer id) {

    }

//    @Override
//    public List<Category> all() {
//        List<Category> categories = new ArrayList<>();
//
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = this.newConnection();
//
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery("select * from categories");
//            while (resultSet.next()) {
//                categories.add(new Category(resultSet.getInt("id"),  resultSet.getString("name"), resultSet.getString("description")));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            this.closeStatement(statement);
//            this.closeResultSet(resultSet);
//            this.closeConnection(connection);
//        }
//
//        return categories;
//    }
//
//    @Override
//    public Category find(Integer id) {
//        Category comment = null;
//
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = this.newConnection();
//
//            preparedStatement = connection.prepareStatement("SELECT * FROM categories where id = ?");
//            preparedStatement.setInt(1, id);
//            resultSet = preparedStatement.executeQuery();
//
//            if(resultSet.next()) {
//                int comId = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                String description = resultSet.getString("description");
//                comment = new Category(comId, name, description);
//            }
//
//            resultSet.close();
//            preparedStatement.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            this.closeStatement(preparedStatement);
//            this.closeResultSet(resultSet);
//            this.closeConnection(connection);
//        }
//
//        return comment;
//    }
//
//    @Override
//    public void delete(Integer id) {
//
//    }
//
//    @Override
//    public Category edit(Category category, Integer id) {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = this.newConnection();
//
//            String[] generatedColumns = {"id"};
//
//            preparedStatement = connection.prepareStatement("UPDATE categories SET name = ?, description = ? WHERE id = ?", generatedColumns);
//            preparedStatement.setString(1, category.getName());
//            preparedStatement.setString(2, category.getDescription());
//            preparedStatement.setInt(3, id);
//            preparedStatement.executeUpdate();
//            resultSet = preparedStatement.getGeneratedKeys();
//
//            if (resultSet.next()) {
//                category.setId(resultSet.getInt(1));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            this.closeStatement(preparedStatement);
//            this.closeResultSet(resultSet);
//            this.closeConnection(connection);
//        }
//
//        return category;
//    }

}
