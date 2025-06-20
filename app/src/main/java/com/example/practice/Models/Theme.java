package com.example.practice.Models;

public class Theme {
    private int id;
    private int id_courses;
    private String name_lessions;
    private String lecture_name;
    private String description;
    private String headline;

    public Theme(String description, String headline, int id, int id_courses, String lecture_name, String name_lessions) {
        this.description = description;
        this.headline = headline;
        this.id = id;
        this.id_courses = id_courses;
        this.lecture_name = lecture_name;
        this.name_lessions = name_lessions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_courses() {
        return id_courses;
    }

    public void setId_courses(int id_courses) {
        this.id_courses = id_courses;
    }

    public String getLecture_name() {
        return lecture_name;
    }

    public void setLecture_name(String lecture_name) {
        this.lecture_name = lecture_name;
    }

    public String getName_lessions() {
        return name_lessions;
    }

    public void setName_lessions(String name_lessions) {
        this.name_lessions = name_lessions;
    }
}
