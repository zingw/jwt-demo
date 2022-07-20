package com.example.demo.jwt.service;

import com.example.demo.jwt.dto.LoginRes;

public interface LoginService {
    LoginRes checkLogin(String username, String password);
}
