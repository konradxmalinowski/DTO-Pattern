package org.example.dtopattern.User;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserRecord> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            users = userRepository.findAll();
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }

        if (users.isEmpty()) {
            System.out.println("No users found");
            return Collections.emptyList();
        }
        return users.stream().map((user -> new UserRecord(user.getId(), user.getUsername(), user.getEmail()))).toList();
    }

    public Optional<UserRecord> getUserById(int id) {
        Optional<User> user = Optional.empty();
        try {
            user = userRepository.findById(id);

        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }

        if (user.isEmpty()) {
            System.out.println("User not found");
            return Optional.empty();
        }

        return Optional.of(new UserRecord(user.get().getId(), user.get().getUsername(), user.get().getEmail()));
    }
}
