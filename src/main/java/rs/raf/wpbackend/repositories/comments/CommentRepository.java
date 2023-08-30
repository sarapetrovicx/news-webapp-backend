package rs.raf.wpbackend.repositories.comments;

import rs.raf.wpbackend.entities.Comment;

import java.util.List;

public interface CommentRepository {
    public Comment add(Comment comment);
    public List<Comment> allComments();
    public Comment find(Integer id);
    public List<Comment> findByPost(Integer id);
    public void delete(Integer id);
}
