package org.example.lab8.services;

import org.example.lab8.entities.User;

import java.util.List;

public interface UserService {
    void register(User user);
    List<User> getAll();
    boolean validateUser(User user);
}
