package com.MyBlog.BlogApplication.service;

import com.MyBlog.BlogApplication.dto.AuthRequest;
import com.MyBlog.BlogApplication.dto.AuthResponse;
import com.MyBlog.BlogApplication.dto.RegisterRequest;

public interface AuthService {


    void register(RegisterRequest registerRequest);
}
