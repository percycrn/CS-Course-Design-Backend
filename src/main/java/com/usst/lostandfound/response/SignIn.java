package com.usst.lostandfound.response;

public class SignIn {
    private String message;
    private Integer status;
    private Integer userId;

    public SignIn(String message, Integer status, Integer userId) {
        this.message = message;
        this.status = status;
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
