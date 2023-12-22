package org.example.lab8.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.NotSupportedException;
import org.example.lab8.entities.User;
import org.example.lab8.entities.UserRole;
import org.example.lab8.repositories.UserRepository;

import java.util.List;

@Named("UserService")
@ApplicationScoped
public class UserServiceImpl implements UserService {
    @Inject
    private UserRepository userRepository;

    @Override
    public List<User> getAll(){
        return userRepository.getAll();
    }

    @Override
    public void register(User user) {
        if (userRepository.getByUsername(user.getUsername()).isPresent()) {
            throw new NotSupportedException("User already exists");
        }
        if (user.getRole() == null)
            user.setRole(UserRole.TEACHER);
        userRepository.save(user);
    }

    public boolean validateUser(User user) {
        User usr = userRepository.getByUsername(user.getUsername()).orElseThrow(
                () -> new NotFoundException("User not found"));
        return user.getPassword().equals(usr.getPassword());
    }
}
