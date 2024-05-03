package com.example.pi_backend2.Controller;

import com.example.pi_backend2.Entity.Prof;

import com.example.pi_backend2.Entity.User;
import com.example.pi_backend2.Repository.TeacherRepository;

import com.example.pi_backend2.Service.LoginRequest;
import com.example.pi_backend2.Service.LoginRequestTeacher;
import com.example.pi_backend2.Service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    public Optional<Prof> getUserByUserName(@PathVariable String userName) {
        return teacherRepository.findByUserName(userName);
    }


    // Endpoint to update a user by username
    @PutMapping("/update/{userName}")
    public ResponseEntity<Prof> updateUser(@PathVariable String userName, @RequestBody Prof updatedProf) {
        Prof updated = userService.updateProf(userName, updatedProf);
        return new ResponseEntity<>(updated, HttpStatus.OK);

    }


    @PostMapping("/add")
    @JsonIgnore
    public ResponseEntity<Prof> addTeacher(@RequestBody Prof teacher) {


        Prof savedTeacher = teacherRepository.save(teacher);
        return new ResponseEntity<>(savedTeacher, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUserName();
        String password = loginRequest.getPassword();


        Prof user = userService.authenticateTeacher(username, password);

        if (user != null) {

            return ResponseEntity.ok("Login successful! " + user.getUserName());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
