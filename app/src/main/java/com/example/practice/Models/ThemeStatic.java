package com.example.practice.Models;

public class ThemeStatic {
    private static int id;
    private static int id_courses;
    private static int size;
    private static int position;
    private static String name_lessions;
    private static String name_course;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        ThemeStatic.id = id;
    }

    public static int getId_courses() {
        return id_courses;
    }

    public static void setId_courses(int id_courses) {
        ThemeStatic.id_courses = id_courses;
    }

    public static String getName_course() {
        return name_course;
    }

    public static void setName_course(String name_course) {
        ThemeStatic.name_course = name_course;
    }

    public static String getName_lessions() {
        return name_lessions;
    }

    public static void setName_lessions(String name_lessions) {
        ThemeStatic.name_lessions = name_lessions;
    }

    public static int getPosition() {
        return position;
    }

    public static void setPosition(int position) {
        ThemeStatic.position = position;
    }

    public static int getSize() {
        return size;
    }

    public static void setSize(int size) {
        ThemeStatic.size = size;
    }
}
