package com.example.demo.jwt.controller;

import com.example.demo.jwt.dto.LoginRes;
import com.example.demo.jwt.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
public class HomeController {
    @Autowired
    LoginServiceImpl loginService;

    @GetMapping("/login")
    public LoginRes login(@RequestParam String username,
                           @RequestParam String password){
        return loginService.checkLogin(username,password);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin-page")
    public String getAdminContent(){
        return "only admin can see this";
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user-page")
    public String getUserContent(){
        return "only user can see this";
    }
    @PreAuthorize("hasAuthority('COMMON')")
    @GetMapping("/both-page")
    public String greeting(){
        return "both user can see this";
    }
}
