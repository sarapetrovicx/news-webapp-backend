package rs.raf.wpbackend.repositories.categories;

import rs.raf.wpbackend.entities.Category;
import rs.raf.wpbackend.entities.Post;

import java.util.List;

public interface CategoryRepository {

    public Category add(Category category);
    public List<Category> all();
    public Category find(Integer id);
    public void delete(Integer id);
    public Category edit(Category category, Integer id);

}
