package com.MyBlog.BlogApplication.controller;

import com.MyBlog.BlogApplication.dto.AuthRequest;
import com.MyBlog.BlogApplication.model.User;
import com.MyBlog.BlogApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User loginRequest) {
        boolean success = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        return success ? "Login successful" : "Invalid username or password";
    }
}
