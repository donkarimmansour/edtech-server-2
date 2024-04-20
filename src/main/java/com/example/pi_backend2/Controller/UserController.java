package com.example.pi_backend2.Controller;

import com.example.pi_backend2.Entity.User;
import com.example.pi_backend2.Service.LoginRequest;
import com.example.pi_backend2.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    // Endpoint to update a user by username
    @PutMapping("/update/{userName}")
    public ResponseEntity<User> updateUser(@PathVariable String userName, @RequestBody User updatedUser) {
        User updated = userService.updateUser(userName, updatedUser);
        return new ResponseEntity<>(updated, HttpStatus.OK);

    }

    // Endpoint to delete a user by username
    @DeleteMapping("/delete/{userName}")
    public ResponseEntity<String> deleteUser(@PathVariable String userName) {

        userService.deleteUser(userName);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);

    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUserName();
        String password = loginRequest.getPassword();

      
        User user = userService.authenticate(username, password);

        if (user != null) {

            return ResponseEntity.ok("Login successful! " + user.getUserName());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
