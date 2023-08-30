package rs.raf.wpbackend.services;

import rs.raf.wpbackend.entities.Post;
import rs.raf.wpbackend.entities.PostTag;
import rs.raf.wpbackend.entities.Tag;
import rs.raf.wpbackend.repositories.posts.PostRepository;
import rs.raf.wpbackend.repositories.posttag.PostTagRepository;

import javax.inject.Inject;
import java.util.List;

public class PostTagService {
    @Inject
    private PostTagRepository postTagRepository;

    public PostTag add(PostTag postTag) {
        return this.postTagRepository.add(postTag);
    }

    public List<Integer> allPostsForTag(Integer id){
        return this.postTagRepository.allPostsForTag(id);
    }
    public List<Integer> allTagsForPost(Integer id){
        return this.postTagRepository.allTagsForPost(id);
    }


}
