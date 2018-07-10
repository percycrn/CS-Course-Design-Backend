package com.usst.lostandfound.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(length = 4)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;
    @Column(length = 11, nullable = false)
    private String phone;
    @Column(length = 20, nullable = false)
    private String password;
    @Column(length = 10, nullable = false)
    private String name;
    @Column(length = 30)
    private String email;
    @Column(length = 50)
    private String address;
    @Column(length = 20, nullable = false)
    private String IDCard;
    @Column(length = 4, nullable = false)
    private Integer credit;

    public User() {
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }
}
