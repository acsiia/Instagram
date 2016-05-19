package by.alex.bsuir.instagram.dao.user;

import by.alex.bsuir.instagram.entity.User;

import java.util.List;

public interface UserDao {
    long saveUser(User user);
    void updateUser(User user);
    User getUser(long id);
    User getUserByName(String login);
    User getUserByEmail(String email);
    List<User> getListOfUsers();
    void deleteUserById(long id);
    List<User> findByPattern(String pattern);
}
