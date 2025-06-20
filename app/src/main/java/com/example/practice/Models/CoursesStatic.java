package com.example.practice.Models;

public class CoursesStatic {
    private static int id;
    private static String name;
    private static int price;
    private static int category;
    private static String description;
    private static double duration;
    private static String cover_name;
    private static String date_creation;
    private static String author;
    private static String title;


    public static String getAuthor() {
        return author;
    }

    public static void setAuthor(String author) {
        CoursesStatic.author = author;
    }

    public static int getCategory() {
        return category;
    }

    public static void setCategory(int category) {
        CoursesStatic.category = category;
    }

    public static String getCover_name() {
        return cover_name;
    }

    public static void setCover_name(String cover_name) {
        CoursesStatic.cover_name = cover_name;
    }

    public static String getDate_creation() {
        return date_creation;
    }

    public static void setDate_creation(String date_creation) {
        CoursesStatic.date_creation = date_creation;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        CoursesStatic.description = description;
    }

    public static double getDuration() {
        return duration;
    }

    public static void setDuration(double duration) {
        CoursesStatic.duration = duration;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        CoursesStatic.id = id;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        CoursesStatic.name = name;
    }

    public static int getPrice() {
        return price;
    }

    public static void setPrice(int price) {
        CoursesStatic.price = price;
    }

    public static String getTitle() {
        return title;
    }

    public static void setTitle(String title) {
        CoursesStatic.title = title;
    }
}
