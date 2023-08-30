package rs.raf.wpbackend.services;

import rs.raf.wpbackend.entities.Post;
import rs.raf.wpbackend.repositories.posts.PostRepository;

import javax.inject.Inject;
import java.util.List;

public class PostService {
    @Inject
    private PostRepository postRepository;

    public Post add(Post post) {
        return this.postRepository.add(post);
    }

    public List<Post> all() {
        return this.postRepository.allPosts();
    }

    public Post find(Integer id) {
        return this.postRepository.find(id);
    }

    public void delete(Integer id) {
        this.postRepository.delete(id);
    }

    public List<Post> byCategory(Integer id) {
        return this.postRepository.byCategory(id);
    }

    public Post edit(Post post, Integer id){
        return this.postRepository.edit(post, id);
    }

    public Post visit(Integer id){
        return this.postRepository.visit(id);
    }


}
