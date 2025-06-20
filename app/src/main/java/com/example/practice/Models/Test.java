package com.example.practice.Models;

public class Test {
    private int id;
    private int id_courses;
    private int id_lessons;
    private String name;
    private String description;
    private String name_cover;

    public Test(String description, int id, int id_courses, int id_lessons, String name, String name_cover) {
        this.description = description;
        this.id = id;
        this.id_courses = id_courses;
        this.id_lessons = id_lessons;
        this.name = name;
        this.name_cover = name_cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getId_lessons() {
        return id_lessons;
    }

    public void setId_lessons(int id_lessons) {
        this.id_lessons = id_lessons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_cover() {
        return name_cover;
    }

    public void setName_cover(String name_cover) {
        this.name_cover = name_cover;
    }
}
