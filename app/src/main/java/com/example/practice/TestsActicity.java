package com.example.practice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.Adapters.CoursesAdapters;
import com.example.practice.Adapters.TestAdapter;
import com.example.practice.Models.Courses;
import com.example.practice.Models.CoursesStatic;
import com.example.practice.Models.Test;
import com.example.practice.Models.Theme;
import com.example.practice.Models.ThemeStatic;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class TestsActicity extends AppCompatActivity {
    TextView nameCourseText, nameLessonText, numberLessonsText;
    Button buttonCancel, buttonTests, buttonDiscuss, buttonLessons;
    int id, size, position;
    RecyclerView list_tests;
    SupabaseClient supabaseClient = new SupabaseClient();
    Context context;
    Intent intent;
    List<Test> testList;
    TestAdapter.OnRecyclerViewTestClickListener onRecyclerViewItemClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test);
        nameCourseText = findViewById(R.id.name_courses_title);
        nameLessonText = findViewById(R.id.name_lessons_title);
        numberLessonsText = findViewById(R.id.number_lessons_title);
        buttonCancel = findViewById(R.id.button_cancel);
        buttonTests = findViewById(R.id.tests_button);
        buttonDiscuss = findViewById(R.id.discuss_button);
        buttonLessons = findViewById(R.id.lessons_button);
        list_tests = findViewById(R.id.list_tests);
        id = ThemeStatic.getId();
        size = ThemeStatic.getSize();
        position = ThemeStatic.getPosition();
        nameCourseText.setText(ThemeStatic.getName_course());
        nameLessonText.setText(ThemeStatic.getName_lessions());
        context = this;
        numberLessonsText.setText(position+" "+getResources().getString(R.string.of)+" "+size+" "+getResources().getString(R.string.lessons));
        try {
            getTest();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        onRecyclerViewItemClickListener = new TestAdapter.OnRecyclerViewTestClickListener () {
            @Override
            public void onClick(Test tests, int position) {
                intent = new Intent(TestsActicity.this, TestContentActivity.class);
                intent.putExtra("id_test", tests.getId());
                startActivity(intent);
            }
        };
        buttonLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(TestsActicity.this, LessonsActivity.class);
                startActivity(intent);
            }
        });
        buttonDiscuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(TestsActicity.this, CommentsActivity.class);
                startActivity(intent);
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(TestsActicity.this, UserCoursesActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getTest() throws IOException {
        supabaseClient.fetchTests(id, new SupabaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() -> {
                    Log.e("getAllOrders: onFailure", e.getLocalizedMessage());
                });
            }

            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(() -> {
                    Log.e("getAllOrders: onResponse", responseBody);
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Test>>() {
                    }.getType();
                    testList = gson.fromJson(responseBody, type);
                    TestAdapter testAdapters = new TestAdapter(context, testList, onRecyclerViewItemClickListener);
                    list_tests.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                    list_tests.setAdapter(testAdapters);
                });
            }
        });
    }
}