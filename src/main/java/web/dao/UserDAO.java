package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsersList();

    User getUser(int id);

    void addUser(User user);

    void deleteUser(int id);

    void editUser(User user);

}
