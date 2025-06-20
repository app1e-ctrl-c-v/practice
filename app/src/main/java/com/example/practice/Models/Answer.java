package com.example.practice.Models;

import java.util.ArrayList;

public class Answer {
    private int id;
    private int id_tests;
    private String text;
    private ArrayList<String> answer_text;
    private String correct;
    private String name_cover;

    public Answer(ArrayList<String> answer_text, String correct, int id, int id_tests, String name_cover, String text) {
        this.answer_text = answer_text;
        this.correct = correct;
        this.id = id;
        this.id_tests = id_tests;
        this.name_cover = name_cover;
        this.text = text;
    }

    public ArrayList<String> getAnswer_text() {
        return answer_text;
    }

    public void setAnswer_text(ArrayList<String> answer_text) {
        this.answer_text = answer_text;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_tests() {
        return id_tests;
    }

    public void setId_tests(int id_tests) {
        this.id_tests = id_tests;
    }

    public String getName_cover() {
        return name_cover;
    }

    public void setName_cover(String name_cover) {
        this.name_cover = name_cover;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
