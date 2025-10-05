package com.MyBlog.BlogApplication.controller;


import com.MyBlog.BlogApplication.model.User;
import com.MyBlog.BlogApplication.security.JwtUtil;
import com.MyBlog.BlogApplication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = userService.registerUser(user);

        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginData) {
        System.out.println("Controller hit: login request received for " + loginData.getUsername());

        try {
            String token = userService.loginUser(loginData.getUsername(), loginData.getPassword());
            return ResponseEntity.ok(Map.of("token", token,"username",loginData.getUsername()));  // 👈 return JSON object
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

    }
}
