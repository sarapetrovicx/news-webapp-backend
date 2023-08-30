package rs.raf.wpbackend.repositories.posts;

import rs.raf.wpbackend.entities.Comment;
import rs.raf.wpbackend.entities.Post;
import rs.raf.wpbackend.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySqlPostRepository extends MySqlAbstractRepository implements PostRepository {

    @Override
    public Post add(Post post) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            post.setDate(String.valueOf(LocalDate.now()));

            preparedStatement = connection.prepareStatement("INSERT INTO posts (title, content, date, author, categoryId, numOfVisits) VALUES(?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setString(3, post.getDate());
            preparedStatement.setString(4, post.getAuthor());
            preparedStatement.setInt(5, post.getCategoryId());
            preparedStatement.setInt(6, post.getNumOfVisits());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                post.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return post;
    }

    @Override
    public List<Post> allPosts() {
        List<Post> posts = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from posts");
            while (resultSet.next()) {
                posts.add(new Post(resultSet.getInt("id"),  resultSet.getString("title"), resultSet.getString("content"),
                        resultSet.getString("date"), resultSet.getString("author"), resultSet.getInt("categoryId"), resultSet.getInt("numOfVisits")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return posts;
    }

    @Override
    public Post find(Integer id) {
        Post post = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM posts where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int postId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                String author = resultSet.getString("author");
                String date = resultSet.getString("date");
                int categoryId = resultSet.getInt("categoryId");
                int numOfVisits = resultSet.getInt("numOfVisits");
                post = new Post(postId, title, content, date, author, categoryId, numOfVisits);
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

        return post;
    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM posts where id = ?");
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
    public List<Post> byCategory(Integer id) {
        List<Post> posts = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM posts where categoryId = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int postId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String content = resultSet.getString("content");
                String date = resultSet.getString("date");
                int categoryId = resultSet.getInt("categoryId");
                int numOfVisits = resultSet.getInt("numOfVisits");

                posts.add(new Post(postId, title, content, date,author, categoryId, numOfVisits));
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

        return posts;
    }

    @Override
    public Post edit(Post post, Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE posts SET title = ?, content = ?, date = ?, author = ?, categoryId = ? WHERE id = ?");
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setString(3, post.getDate());
            preparedStatement.setString(4, post.getAuthor());
            preparedStatement.setInt(5, post.getCategoryId());
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();

            post.setId(id);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return post;
    }


    @Override
    public Post visit(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Post post = find(id);
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};


            preparedStatement = connection.prepareStatement("UPDATE posts SET numOfVisits = ? WHERE id = ?", generatedColumns);
            preparedStatement.setInt(1, post.getNumOfVisits()+1);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            post.setId(id);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return post;
    }
}
