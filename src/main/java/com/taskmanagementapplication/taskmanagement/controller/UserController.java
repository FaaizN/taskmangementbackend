package com.taskmanagementapplication.taskmanagement.controller;

import com.taskmanagementapplication.taskmanagement.model.User;
import com.taskmanagementapplication.taskmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired

    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){

        // Check to see if the username has already been taken, if so give a bad request
        if (userService.existsByUserName(user.getUserName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already in use");
        }

        // Save the userName
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully");
    }
}
