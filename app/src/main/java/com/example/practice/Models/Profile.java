package com.example.practice.Models;

import java.util.Date;
public class Profile {
    public static String id;
    public static String username;
    public static String avatar_url;
    public static int payplament;

    public static String getId() {
        return id;
    }

    public static void saveId(String id) {
        Profile.id = id;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Profile.username = username;
    }

    public static String getAvatar_url() {
        return avatar_url;
    }

    public static void setAvatar_url(String avatar_url) {
        Profile.avatar_url = avatar_url;
    }

    public static int getPayplament() {
        return payplament;
    }

    public static void setPayplament(int payplament) {
        Profile.payplament = payplament;
    }
}
