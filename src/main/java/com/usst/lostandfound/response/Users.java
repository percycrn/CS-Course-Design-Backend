package com.usst.lostandfound.response;

public class Users {
    private Integer userId;
    private String phone;

    public Users(Integer userId, String phone) {
        this.userId = userId;
        this.phone = phone;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
