package com.example.practice.Models;

import java.util.Date;
public class Profile {
    public  String id;
    public  String username;
    public  String avatar_url;
    public  int payplament;
    public int saved_courses;
    public int pay_courses;
    public static String email;

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Profile.email = email;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPay_courses() {
        return pay_courses;
    }

    public void setPay_courses(int pay_courses) {
        this.pay_courses = pay_courses;
    }

    public int getPayplament() {
        return payplament;
    }

    public void setPayplament(int payplament) {
        this.payplament = payplament;
    }

    public int getSaved_courses() {
        return saved_courses;
    }

    public void setSaved_courses(int saved_courses) {
        this.saved_courses = saved_courses;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
