package com.usst.lostandfound.entity;

import javax.persistence.*;

@Entity
@Table(name = "lost")
public class Lost {
    @Id @Column(length = 4) @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer lostId;
    @Column(length = 11, nullable = false) private String lostPhone;
    @Column(length = 30, nullable = false) private String name;
    @Column(length = 30, nullable = false) private String location;
    @Column(nullable = false) private Long time;
    @Column(length = 60) private String pic;
    @Column(length = 100, nullable = false) private String outline;
    @Column(length = 1) private Integer found; // 0未找到 1找到

    public Lost() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public Integer getFound() {
        return found;
    }

    public void setFound(Integer found) {
        this.found = found;
    }
}
