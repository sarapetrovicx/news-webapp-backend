package rs.raf.wpbackend.services;

import rs.raf.wpbackend.entities.Category;
import rs.raf.wpbackend.entities.Comment;
import rs.raf.wpbackend.entities.Post;
import rs.raf.wpbackend.repositories.categories.CategoryRepository;

import javax.inject.Inject;
import java.util.List;

public class CategoryService {

    @Inject
    private CategoryRepository categoryRepository;

    public Category add(Category category) {
        return this.categoryRepository.add(category);
    }

    public List<Category> all() {
        return this.categoryRepository.all();
    }

    public Category find(Integer id) {
        return this.categoryRepository.find(id);
    }

    public void delete(Integer id) {
        this.categoryRepository.delete(id);
    }

    public Category edit(Category category, Integer id){
        return this.categoryRepository.edit(category, id);
    }

}
