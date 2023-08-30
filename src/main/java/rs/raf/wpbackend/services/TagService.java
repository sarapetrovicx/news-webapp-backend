package rs.raf.wpbackend.services;

import rs.raf.wpbackend.entities.Tag;
import rs.raf.wpbackend.repositories.tags.TagRepository;

import javax.inject.Inject;
import java.util.List;

public class TagService {

    @Inject
    private TagRepository tagRepository;

    public Tag add(Tag tag) {
        return this.tagRepository.add(tag);
    }

    public List<Tag> all() {
        return this.tagRepository.all();
    }

    public Tag find(Integer id) {
        return this.tagRepository.find(id);
    }

    public void delete(Integer id) {
        this.tagRepository.delete(id);
    }
}
