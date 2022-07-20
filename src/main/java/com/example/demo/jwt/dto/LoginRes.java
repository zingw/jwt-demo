package com.example.demo.jwt.dto;

public class LoginRes {

    private String message;
    private Boolean isSuccess;
    private String token;

    public LoginRes(String message, Boolean isSuccess, String token) {
        this.message = message;
        this.isSuccess = isSuccess;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
