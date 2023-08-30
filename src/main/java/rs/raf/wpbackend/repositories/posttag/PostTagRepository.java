package rs.raf.wpbackend.repositories.posttag;

import rs.raf.wpbackend.entities.PostTag;

import java.util.List;

public interface PostTagRepository {

    public List<Integer> allTagsForPost(Integer postId);
    public List<Integer> allPostsForTag(Integer tagId);

    public PostTag add(PostTag postTag);

}
