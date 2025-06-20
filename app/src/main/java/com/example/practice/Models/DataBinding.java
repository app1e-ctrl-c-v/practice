package com.example.practice.Models;

public class DataBinding {
    private static String bearerToken;
    private static String uuidUser;
    private static int saved_courses;
    private static int pay_courses;
    private static int payment;

    public static int getSaved_courses() {
        return saved_courses;
    }

    public static int getPay_courses() {
        return pay_courses;
    }

    public static void setPay_courses(int pay_courses) {
        DataBinding.pay_courses = pay_courses;
    }

    public static void setSaved_courses(int saved_courses) {
        DataBinding.saved_courses = saved_courses;
    }

    public static String getBearerToken() {
        return bearerToken;
    }

    public static void saveBearerToken(String bearerToken) {
        DataBinding.bearerToken = bearerToken;
    }

    public static String getUuidUser() {
        return uuidUser;
    }

    public static void saveUuidUser(String uuidUser) {
        DataBinding.uuidUser = uuidUser;
    }

    public static int getPayment() {
        return payment;
    }

    public static void setPayment(int payment) {
        DataBinding.payment = payment;
    }
}
