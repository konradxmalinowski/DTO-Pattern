package org.example.dtopattern.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public ResponseEntity<List<UserRecord>> getUsers() {
        List<UserRecord> users;
        try {
            users = userService.getUsers();

            if (users.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            System.out.println("Exception in UserController.getUsers()");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRecord> getUser(@PathVariable int id) {
        Optional<UserRecord> user;
        try {
            user = userService.getUserById(id);
            if (user.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }
}
