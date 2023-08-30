package rs.raf.wpbackend.entities;

import javax.validation.constraints.NotNull;

public class PostTag {
    private Integer id;
    @NotNull(message = "PostId field is required")
    private int postId;
    @NotNull(message = "TagId field is required")
    private int tagId;

    public PostTag() {
    }

    public PostTag(int postId, int tagId) {
        this.postId = postId;
        this.tagId = tagId;
    }

    public PostTag(Integer id, int postId, int tagId) {
        this.id = id;
        this.postId = postId;
        this.tagId = tagId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
