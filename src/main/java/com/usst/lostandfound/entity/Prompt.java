package com.usst.lostandfound.entity;

import javax.persistence.*;

@Entity
@Table(name = "prompt")
public class Prompt {
    @Id
    @Column(length = 4)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer promptId;
    @Column(length = 4, nullable = false)
    private Integer lostId;
    @Column(length = 11, nullable = false)
    private String lostPhone;
    @Column(length = 11, nullable = false)
    private String foundPhone;
    @Column(length = 11)
    private Integer foundId;

    public Prompt() {
    }

    public Integer getPromptId() {
        return promptId;
    }

    public void setPromptId(Integer promptId) {
        this.promptId = promptId;
    }

    public Integer getLostId() {
        return lostId;
    }

    public void setLostId(Integer lostId) {
        this.lostId = lostId;
    }

    public String getLostPhone() {
        return lostPhone;
    }

    public void setLostPhone(String lostPhone) {
        this.lostPhone = lostPhone;
    }

    public String getFoundPhone() {
        return foundPhone;
    }

    public void setFoundPhone(String foundPhone) {
        this.foundPhone = foundPhone;
    }

    public Integer getFoundId() {
        return foundId;
    }

    public void setFoundId(Integer foundId) {
        this.foundId = foundId;
    }
}
