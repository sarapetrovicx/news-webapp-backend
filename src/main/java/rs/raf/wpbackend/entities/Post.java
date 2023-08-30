package rs.raf.wpbackend.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Post {

    private Integer id;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String title;
    @NotNull(message = "Content field is required")
    @NotEmpty(message = "Content field is required")
    private String content;
    @NotNull(message = "Date field is required")
    @NotEmpty(message = "Date field is required")
    private String date;
    @NotNull(message = "Author field is required")
    @NotEmpty(message = "Author field is required")
    private String author;
    @NotNull(message = "Category field is required")
    private int categoryId;
    private int numOfVisits;

    public Post() {
    }

    public Post(String title, String content, String date, String author, int categoryId) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.author = author;
        this.categoryId = categoryId;
    }

    public Post(Integer id, String title, String content, String date, String author, int categoryId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.author = author;
        this.categoryId = categoryId;
    }

    public Post(Integer id, String title, String content, String date, String author, int categoryId, int numOfVisits) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.author = author;
        this.categoryId = categoryId;
        this.numOfVisits = numOfVisits;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getNumOfVisits() {
        return numOfVisits;
    }

    public void setNumOfVisits(int numOfVisits) {
        this.numOfVisits = numOfVisits;
    }
}
