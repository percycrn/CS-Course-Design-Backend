package com.usst.lostandfound.entity;

import javax.persistence.*;

// @JoinColumn(name = "found_phone", referencedColumnName = "phone")
// @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
// private User user;

@Entity
@Table(name = "found")
public class Found {
    @Id @Column(length = 4) @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer foundId;
    @Column(length = 11, nullable = false) private String foundPhone;
    @Column(length = 10, nullable = false) private String name;
    @Column(length = 30, nullable = false) private String location;
    @Column(nullable = false) private Long time;
    @Column(length = 50) private String pic;
    @Column(length = 30, nullable = false) private String outline;
    @Column(length = 50, nullable = false) private String storage;
    @Column(length = 11) private String lostPhone;

    public Found() {
    }

    public Integer getFoundId() {
        return foundId;
    }

    public void setFoundId(Integer foundId) {
        this.foundId = foundId;
    }

    public String getFoundPhone() {
        return foundPhone;
    }

    public void setFoundPhone(String foundPhone) {
        this.foundPhone = foundPhone;
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

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getLostPhone() {
        return lostPhone;
    }

    public void setLostPhone(String lostPhone) {
        this.lostPhone = lostPhone;
    }

    @Override
    public String toString() {
        return "Found{" +
                "foundId=" + foundId +
                ", foundPhone='" + foundPhone + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", time=" + time +
                ", pic='" + pic + '\'' +
                ", outline='" + outline + '\'' +
                ", storage='" + storage + '\'' +
                ", lostPhone='" + lostPhone + '\'' +
                '}';
    }
}
