package rs.raf.wpbackend.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Comment {

    private Integer id;
    @NotNull(message = "Author field is required")
    @NotEmpty(message = "Author field is required")
    private String author;
    @NotNull(message = "Comment field is required")
    @NotEmpty(message = "Comment field is required")
    private String comment;
    @NotNull(message = "Date field is required")
    @NotEmpty(message = "Date field is required")
    private String date;
    @NotNull(message = "PostId field is required")
    private int postId;

    public Comment() {
    }

    public Comment(String author, String comment, String date, int postId) {
        this.author = author;
        this.comment = comment;
        this.date = date;
        this.postId = postId;
    }

    public Comment(Integer id, String author, String comment, String date, int postId) {
        this.id = id;
        this.author = author;
        this.comment = comment;
        this.date = date;
        this.postId = postId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
