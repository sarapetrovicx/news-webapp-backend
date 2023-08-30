package rs.raf.wpbackend.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {
    private Integer id;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String email;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String first_name;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String last_name;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String role;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String status;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String hashed_password;


    public User() {
    }

    public User(String email, String first_name, String last_name, String role, String status, String hashedPassword) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.role = role;
        this.status = status;
        this.hashed_password = hashedPassword;
    }

    public User(Integer id, String email, String first_name, String last_name, String role, String status, String hashed_password) {
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.role = role;
        this.status = status;
        this.hashed_password = hashed_password;
    }

    public String getHashedPassword() {
        return hashed_password;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashed_password = hashedPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
