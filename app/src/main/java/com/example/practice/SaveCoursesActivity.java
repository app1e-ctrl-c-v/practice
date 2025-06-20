package com.example.practice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.practice.Adapters.CoursesAdapters;
import com.example.practice.Models.Courses;
import com.example.practice.Models.CoursesStatic;
import com.example.practice.Models.CoursesUser;
import com.example.practice.Models.DataBinding;
import com.example.practice.Models.Profile;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SaveCoursesActivity extends AppCompatActivity {
    Button cancelButton, addButton;
    RecyclerView courses_list;
    List<Courses> coursesList = new ArrayList<>();
    SupabaseClient supabaseClient = new SupabaseClient();
    Context context;
    CoursesAdapters.OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    Intent intent;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_save_courses);
        cancelButton = findViewById(R.id.button_cancel);
        addButton = findViewById(R.id.button_add);
        courses_list = findViewById(R.id.saves_courses_list);
        context = this;
        try {
            getCourses();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        onRecyclerViewItemClickListener = new CoursesAdapters.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(Courses courses, int position) {
                CoursesStatic.setId(courses.getId());
                CoursesStatic.setName(courses.getName());
                CoursesStatic.setDescription(courses.getDescription());
                CoursesStatic.setDuration(courses.getDuration());
                CoursesStatic.setTitle(courses.getTitle());
                CoursesStatic.setCover_name(courses.getCover_name());
                CoursesStatic.setPrice(courses.getPrice());
                intent = new Intent(SaveCoursesActivity.this, CoursesDetailActivity2.class);
                startActivity(intent);
            }
        };
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SaveCoursesActivity.this, CoursesActivity.class);
                startActivity(intent);
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SaveCoursesActivity.this, CoursesActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getCourses() throws IOException {
        supabaseClient.fetchSaveCoursesId(new SupabaseClient.SBC_Callback() {
            @Override
            public void onFailure (IOException e) {
                runOnUiThread(() -> {
                    Log.e("getAllOrders: onFailure", e.getLocalizedMessage());
                });
            }
            @Override
            public void onResponse (String responseBody) {
                runOnUiThread(()-> {
                    Log.e("getAllOrders: onResponse",responseBody);
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<CoursesUser>>(){}.getType();
                    List<CoursesUser> courses = gson.fromJson(responseBody, type);
                    for(int i = 0; i < courses.size(); i++){
                        CoursesUser course = courses.get(i);
                        id = course.getCourse();
                        supabaseClient.fetchSaveCoursesUser (id, new SupabaseClient.SBC_Callback() {
                            @Override
                            public void onFailure (IOException e) {
                                runOnUiThread(() -> {
                                    Log.e("getAllOrders: onFailure", e.getLocalizedMessage());
                                });
                            }
                            @Override
                            public void onResponse (String responseBody) {
                                runOnUiThread(()-> {
                                    Log.e("getAllOrders: onResponse",responseBody);
                                    Gson gson = new Gson();
                                    Type type = new TypeToken<List<Courses>>(){}.getType();
                                    List<Courses> courses = gson.fromJson(responseBody, type);
                                    coursesList.addAll(courses);
                                    CoursesAdapters coursesAdapters = new CoursesAdapters(context, coursesList, onRecyclerViewItemClickListener);
                                    courses_list.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                                    courses_list.setAdapter(coursesAdapters);
                                });
                            }
                        });

                    }
                });
            }
        });

    }

}
