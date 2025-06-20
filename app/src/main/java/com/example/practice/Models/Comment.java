package com.example.practice.Models;

public class Comment{
    private int id;
    private int id_theme;
    private String content;
    private String date;
    private double rating;
    private String user;
    private Profile profiles;

    public Comment(String content, String date, int id, int id_theme, Profile profiles, double rating, String user) {
        this.content = content;
        this.date = date;
        this.id = id;
        this.id_theme = id_theme;
        this.profiles = profiles;
        this.rating = rating;
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getid_theme() {
        return id_theme;
    }

    public void setid_theme(int id_theme) {
        this.id_theme = id_theme;
    }

    public Profile getProfiles() {
        return profiles;
    }

    public void setProfiles(Profile profiles) {
        this.profiles = profiles;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
