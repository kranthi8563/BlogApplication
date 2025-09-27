package com.MyBlog.BlogApplication.service;


import com.MyBlog.BlogApplication.model.User;
import com.MyBlog.BlogApplication.repository.UserRepository;
import org.springframework.stereotype.Service;



@Service
public class UserService {


    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User registerUser(User user){
        return userRepository.save(user);
    }


    public boolean loginUser(String username, String password) {
        return userRepository.findByUsername(username)
                .map((user) -> user.getPassword().equals(password)) // basic check
                .orElse(false);
    }
}
