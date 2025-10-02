package com.MyBlog.BlogApplication.service;


import com.MyBlog.BlogApplication.exception.BadRequestException;
import com.MyBlog.BlogApplication.exception.ResourceNotFoundException;
import com.MyBlog.BlogApplication.model.User;
import com.MyBlog.BlogApplication.repository.UserRepository;
import com.MyBlog.BlogApplication.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {


    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    public String loginUser(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            // Check password with BCrypt
            if (passwordEncoder.matches(password, user.getPassword())) {
                // Generate token
               return jwtUtil.generateToken(user.getUsername());

            }
        }

        throw new RuntimeException("Invalid Username or Password");
    }
}
