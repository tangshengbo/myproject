package com.tangshengbo.design.flyweight.sign;

/**
 * Created by TangShengBo on 2017-10-22.
 */
public class SignInfo {

    private String id;
    //考试地点
    private String location;
    //考试科目
    private String subject;
    //邮寄地址
    private String postAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    @Override
    public String toString() {
        return "SignInfo{" +
                "id='" + id + '\'' +
                ", location='" + location + '\'' +
                ", subject='" + subject + '\'' +
                ", postAddress='" + postAddress + '\'' +
                '}';
    }
}
