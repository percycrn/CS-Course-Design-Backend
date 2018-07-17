package com.usst.lostandfound.response;

import org.springframework.web.multipart.MultipartFile;

public class UploadPic {
    private MultipartFile pic;
    private Integer id;
    private Integer type;

    public UploadPic(MultipartFile pic, Integer id, Integer type) {
        this.pic = pic;
        this.id = id;
        this.type = type;
    }

    public UploadPic() {
    }

    public MultipartFile getPic() {
        return pic;
    }

    public void setPic(MultipartFile pic) {
        this.pic = pic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
