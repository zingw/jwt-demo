package com.example.demo.jwt.service;

import com.example.demo.jwt.dto.LoginRes;
import com.example.demo.jwt.entities.User;
import com.example.demo.jwt.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginServiceImpl implements  LoginService{
    @Autowired
    UserRepository userRepository;

    public LoginRes checkLogin(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isEmpty()) return new LoginRes("username nhập không đúng !", false,null);

        User user = userOptional.get();
        String pw = user.getPassword();
        if(!BCrypt.checkpw(password,pw)) return new LoginRes("sai mật khẩu",false,null);
        return new LoginRes("đăng nhập thành công",true,tokenGenerator(user));
    }

    private String tokenGenerator(User user) {
        Map<String,Object> userMap = new HashMap<>();
        String name = user.getUsername();
        String authors = user.getAuthor();
        List<String> authorList = List.of(authors.split(","));
        userMap.put(name,authorList);
        return TokenService.createToken(userMap);
    }
}
