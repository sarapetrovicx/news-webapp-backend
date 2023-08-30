package rs.raf.wpbackend.repositories.user;

import rs.raf.wpbackend.entities.User;

import java.util.List;

public interface UserRepository {

    public User add(User user);
    public List<User> all();
    public User findUser(String email);
    public User edit(User user, Integer id);
    public void delete(Integer id);
    public void activate(Integer id);
    public void deactivate(Integer id);

}
