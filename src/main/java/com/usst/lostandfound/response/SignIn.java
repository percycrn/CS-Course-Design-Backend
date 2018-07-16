package com.usst.lostandfound.response;

public class SignIn {
    private String message;
    private Integer status;
    private Users users;

    public SignIn(String message, Integer status, Users users) {
        this.message = message;
        this.status = status;
        this.users = users;
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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
