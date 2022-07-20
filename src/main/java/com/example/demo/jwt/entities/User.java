package com.example.demo.jwt.entities;

import org.hibernate.internal.build.AllowPrintStacktrace;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name ="username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "author")
    private String author;

    public User(){

    }

    public User(String username, String password, String author) {
        this.username = username;
        this.password = password;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


