package rs.raf.wpbackend.repositories.posttag;

import rs.raf.wpbackend.entities.PostTag;
import rs.raf.wpbackend.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlPostTagRepository extends MySqlAbstractRepository implements PostTagRepository {


    @Override
    public List<Integer> allTagsForPost(Integer postId) {
        List<Integer> tags = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM posts_tags where postId = ?");
            preparedStatement.setInt(1, postId);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int tagId = resultSet.getInt("tagId");
                tags.add(tagId);
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

        return tags;
    }

    @Override
    public List<Integer> allPostsForTag(Integer tagId) {
        List<Integer> posts = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM posts_tags where tagId = ?");
            preparedStatement.setInt(1, tagId);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int postId = resultSet.getInt("postId");
                posts.add(postId);
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
    public PostTag add(PostTag postTag) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};


            preparedStatement = connection.prepareStatement("INSERT INTO posts_tags (postId, tagId) VALUES(?, ?)", generatedColumns);
            preparedStatement.setInt(1, postTag.getPostId());
            preparedStatement.setInt(2, postTag.getTagId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                postTag.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return postTag;
    }
}
