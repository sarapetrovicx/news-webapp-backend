package rs.raf.wpbackend.repositories.posts;

import rs.raf.wpbackend.entities.Post;

import java.util.List;

public interface PostRepository {

    public Post add(Post post);
    public List<Post> allPosts();
    public Post find(Integer id);
    public void delete(Integer id);
    public List<Post> byCategory(Integer id);
    public Post edit(Post post, Integer id);
    public Post visit(Integer id);

}
