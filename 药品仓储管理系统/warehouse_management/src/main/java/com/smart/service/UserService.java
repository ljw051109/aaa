package com.smart.service;

import com.smart.endity.User;
import com.smart.dao.UserDao;

import java.util.List;

public class UserService {
    private List<User> users;
    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
        users = userDao.readUsersFromFile();
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) {
        users.add(user);
        userDao.writeUserToFile(user);
    }

    public void updateUser(User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(updatedUser.getId())) {
                users.set(i, updatedUser);
                userDao.writeUsersToFile(users);
                return;
            }
        }
    }

    public void deleteUser(String id) {
        users.removeIf(user -> user.getId().equals(id));
        userDao.writeUsersToFile(users);
    }
}