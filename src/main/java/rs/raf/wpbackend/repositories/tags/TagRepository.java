package rs.raf.wpbackend.repositories.tags;

import rs.raf.wpbackend.entities.Tag;

import java.util.List;

public interface TagRepository {

    public Tag add(Tag tag);
    public List<Tag> all();
    public Tag find(Integer id);
    public void delete(Integer id);
}
