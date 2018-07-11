package com.usst.lostandfound.entity;

import javax.persistence.*;

@Entity
@Table(name = "application")
public class Application {
    @Id @Column(length = 4) @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer appId;
    @Column(length = 4) private Integer foundId;
    @Column(nullable = false) private Long time;
    @Column(length = 11) private String phone;
    @Column(length = 1) private Integer State; // 0 PENDING 1 PASS -1 DENY

    public Application() {
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getFoundId() {
        return foundId;
    }

    public void setFoundId(Integer foundId) {
        this.foundId = foundId;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getState() {
        return State;
    }

    public void setState(Integer state) {
        State = state;
    }
}
