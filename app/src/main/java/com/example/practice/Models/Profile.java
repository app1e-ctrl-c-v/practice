package com.example.practice.Models;

import java.util.Date;
public class Profile {
    public  String id;
    public  String username;
    public  String avatar_url;
    public  int payplament;

    public  String getId() {
        return id;
    }

    public  void saveId(String id) {
        this.id = id;
    }

    public  String getUsername() {
        return username;
    }

    public  void setUsername(String username) {
        this.username = username;
    }

    public  String getAvatar_url() {
        return avatar_url;
    }

    public  void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public  int getPayplament() {
        return payplament;
    }

    public  void setPayplament(int payplament) {
        this.payplament = payplament;
    }
}
