package web.dao;
import web.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsers();
    void saveUser(User user);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
    User getUser(int id);
}

