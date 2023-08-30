package rs.raf.wpbackend.repositories.comments;

import rs.raf.wpbackend.entities.Comment;
import rs.raf.wpbackend.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySqlCommentRepository extends MySqlAbstractRepository implements CommentRepository {

    @Override
    public Comment add(Comment comment) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            comment.setDate(String.valueOf(LocalDate.now()));

            preparedStatement = connection.prepareStatement("INSERT INTO comments (author, comment, date, postId) VALUES(?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, comment.getAuthor());
            preparedStatement.setString(2, comment.getComment());
            preparedStatement.setString(3, comment.getDate());
            preparedStatement.setInt(4, comment.getPostId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                comment.setId(resultSet.getInt(1));
            }

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
    public List<Comment> allComments() {
        List<Comment> comments = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from comments");
            while (resultSet.next()) {
                comments.add(new Comment(resultSet.getInt("id"),  resultSet.getString("author"), resultSet.getString("comment"),
                       resultSet.getString("date"),  resultSet.getInt("postId")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comments;
    }

    @Override
    public Comment find(Integer id) {
        Comment comment = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM comments where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int comId = resultSet.getInt("id");
                String author = resultSet.getString("author");
                String date = resultSet.getString("date");
                String content = resultSet.getString("comment");
                int postId = resultSet.getInt("postId");
                comment = new Comment(comId, author, content, date, postId);
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

    public List<Comment> findByPost(Integer id){
        List<Comment> comments = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM comments where postId = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int comId = resultSet.getInt("id");
                String author = resultSet.getString("author");
                String content = resultSet.getString("comment");
                String date = resultSet.getString("date");
                int postId = resultSet.getInt("postId");
                comments.add(new Comment(comId, author, content, date, postId));
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

        return comments;
    }
    @Override
    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM comments where id = ?");
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
}
