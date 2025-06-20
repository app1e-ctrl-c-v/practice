package com.example.practice;

import androidx.annotation.NonNull;

import com.example.practice.Models.DataBinding;
import com.example.practice.Models.Profile;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SupabaseClient {
    public interface SBC_Callback{
        public void onFailure(IOException e);
        public void onResponse(String responseBody);
    }
    public static String DOMAIN_NAME = "https://psziqhddgczahqmabysn.supabase.co/";
    public static String REST_PATH = "rest/v1/";
    public static String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA";
    OkHttpClient client = new OkHttpClient();
    public void fetchAllCourses(final SBC_Callback callback) throws IOException {
        Request request = new Request.Builder()
                .url(DOMAIN_NAME+REST_PATH+"courses?select=*")
                .addHeader("apikey", API_KEY)
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
             if(response.isSuccessful()){
                String responseBody = response.body().string();
                callback.onResponse(responseBody);
             } else callback.onFailure(new IOException("Server error: "+response));
            }
        });
    }
    public void fetchAllPayment(final SBC_Callback callback) throws IOException {
        Request request = new Request.Builder()
                .url(DOMAIN_NAME+REST_PATH+"payplament?select=*")
                .addHeader("apikey", API_KEY)
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                } else callback.onFailure(new IOException("Server error: "+response));
            }
        });
    }

    public void fetchAllCategory(final SBC_Callback callback) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://psziqhddgczahqmabysn.supabase.co/rest/v1/category?select=*")
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                } else callback.onFailure(new IOException("Server error: "+response));
            }
        });
    }

//    public void fetchSingUp(final SBC_Callback callback) throws IOException {
//        OkHttpClient client1 = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "{\n  \"email\": \"test2@mail.com\",\n  \"password\": \"11111111111\"\n}");
//        Request request = new Request.Builder()
//                .url("https://psziqhddgczahqmabysn.supabase.co/auth/v1/signup")
//                .method("POST", body)
//                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
//                .addHeader("Content-Type", "application/json")
//                .build();
//        client1.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                callback.onFailure(e);
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                if(response.isSuccessful()){
//                    String responseBody = response.body().string();
//                    callback.onResponse(responseBody);
//                } else callback.onFailure(new IOException("Server error: "+response));
//            }
//        });
//    }
    public void fetchLogin(final SBC_Callback callback) throws IOException {
        Request request = new Request.Builder()
                .url("https://psziqhddgczahqmabysn.supabase.co/auth/v1/token?grant_type=password")
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .addHeader("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                } else callback.onFailure(new IOException("Server error: "+response));
            }
        });
    }
    public void fetchSaveCoursesId(final SBC_Callback callback){
        Request request = new Request.Builder()
                .url("https://psziqhddgczahqmabysn.supabase.co/rest/v1/save_course?select=*&user=eq."+DataBinding.getUuidUser())
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                } else callback.onFailure(new IOException("Server error: "+response));
            }
        });
    }

    public void fetchSaveCoursesUser(int id, final SBC_Callback callback){
        Request request = new Request.Builder()
                .url("https://psziqhddgczahqmabysn.supabase.co/rest/v1/courses?select=*&id=eq."+id)
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                } else callback.onFailure(new IOException("Server error: "+response));
            }
        });
    }
    public void fetchPayCoursesUserId(final SBC_Callback callback){
        Request request = new Request.Builder()
                .url("https://psziqhddgczahqmabysn.supabase.co/rest/v1/buy_course?select=*&user=eq."+DataBinding.getUuidUser())
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                } else callback.onFailure(new IOException("Server error: "+response));
            }
        });
    }

    public void fetchUser(final SBC_Callback callback){
        Request request = new Request.Builder()
                .url("https://psziqhddgczahqmabysn.supabase.co/rest/v1/profiles?select=*&id=eq."+DataBinding.getUuidUser())
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                } else callback.onFailure(new IOException("Server error: "+response));
            }
        });
    }
    public void fetchTheme(int id, final SBC_Callback callback){
        Request request = new Request.Builder()
                .url("https://psziqhddgczahqmabysn.supabase.co/rest/v1/contents?select=*&id_courses=eq."+id)
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                } else callback.onFailure(new IOException("Server error: "+response));
            }
        });
    }
    public void fetchLessons(int id, final SBC_Callback callback){
        Request request = new Request.Builder()
                .url("https://psziqhddgczahqmabysn.supabase.co/rest/v1/contents?select=*&id=eq."+id)
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                } else callback.onFailure(new IOException("Server error: "+response));
            }
        });
    }
    public void fetchLogOut(final SBC_Callback callback) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://psziqhddgczahqmabysn.supabase.co/auth/v1/logout")
                .method("POST", body)
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer USER_TOKEN")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                } else callback.onFailure(new IOException("Server error: "+response));
            }
        });
    }
    public void fetchUserPaymentId(final SBC_Callback callback) throws IOException {
        Request request = new Request.Builder()
                .url("https://psziqhddgczahqmabysn.supabase.co/rest/v1/payplament_user?select=*&user=eq."+DataBinding.getUuidUser())
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                } else callback.onFailure(new IOException("Server error: "+response));
            }
        });
    }
    public void fetchComments(int id, final SBC_Callback callback) throws IOException {
        Request request = new Request.Builder()
                .url("https://psziqhddgczahqmabysn.supabase.co/rest/v1/comments?select=*,profiles(*)&id_theme=eq."+id)
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                } else callback.onFailure(new IOException("Server error: "+response));
            }
        });
    }
    public void fetchUserPayment(int id, final SBC_Callback callback) throws IOException {
        Request request = new Request.Builder()
                .url("https://psziqhddgczahqmabysn.supabase.co/rest/v1/payplament?select=*&id=eq."+id)
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                } else callback.onFailure(new IOException("Server error: "+response));
            }
        });
    }
    public void fetchTests(int id, final SBC_Callback callback) throws IOException {
        Request request = new Request.Builder()
                .url("https://psziqhddgczahqmabysn.supabase.co/rest/v1/tests?select=*&id_lessons=eq."+id)
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                } else callback.onFailure(new IOException("Server error: "+response));
            }
        });
    }
    public void fetchAnswer(int id, final SBC_Callback callback) throws IOException {
        Request request = new Request.Builder()
                .url("https://psziqhddgczahqmabysn.supabase.co/rest/v1/questions?select=*&id_tests=eq."+id)
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                } else callback.onFailure(new IOException("Server error: "+response));
            }
        });
    }

}
