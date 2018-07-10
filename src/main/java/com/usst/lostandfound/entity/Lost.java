package com.usst.lostandfound.entity;

import javax.persistence.*;

@Entity
@Table(name = "lost")
public class Lost {
    @Id
    @Column(length = 4)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lostID;

    public Lost() {
    }

    public Integer getLostID() {
        return lostID;
    }

    public void setLostID(Integer lostID) {
        this.lostID = lostID;
    }
}
