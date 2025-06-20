package com.example.practice.Models;

public class Progress {
    public int id;
    public String id_user;
    public int id_test;
    public int id_lessons;

    public Progress(int id, int id_lessons, int id_test, String id_user) {
        this.id = id;
        this.id_lessons = id_lessons;
        this.id_test = id_test;
        this.id_user = id_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_lessons() {
        return id_lessons;
    }

    public void setId_lessons(int id_lessons) {
        this.id_lessons = id_lessons;
    }

    public int getId_test() {
        return id_test;
    }

    public void setId_test(int id_test) {
        this.id_test = id_test;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
}
