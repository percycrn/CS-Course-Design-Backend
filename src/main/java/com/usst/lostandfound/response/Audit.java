package com.usst.lostandfound.response;

public class Audit {
    private Integer state;

    public Audit(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
