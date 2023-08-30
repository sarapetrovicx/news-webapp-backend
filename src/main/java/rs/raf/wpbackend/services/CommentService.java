package rs.raf.wpbackend.services;

import rs.raf.wpbackend.entities.Comment;
import rs.raf.wpbackend.repositories.comments.CommentRepository;

import javax.inject.Inject;
import java.util.List;

public class CommentService {

    @Inject
    private CommentRepository commentRepository;

    public Comment add(Comment comment) {
        return this.commentRepository.add(comment);
    }

    public List<Comment> all() {
        return this.commentRepository.allComments();
    }

    public Comment find(Integer id) {
        return this.commentRepository.find(id);
    }
    public List<Comment> findByPost(Integer id) {
        return this.commentRepository.findByPost(id);
    }

    public void delete(Integer id) {
        this.commentRepository.delete(id);
    }
}
