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

import com.bumptech.glide.Glide;
import com.example.practice.Adapters.ThemesAdapter;
import com.example.practice.Models.CoursesStatic;
import com.example.practice.Models.Theme;
import com.example.practice.Models.ThemeStatic;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class LessonsActivity extends AppCompatActivity {
    TextView nameCourseText, nameLessonText, numberLessonsText, titleLessonText, textLessonText;
    Button buttonCancel, buttonTests, buttonDiscuss, buttonLessons;
    ImageView imageLesson;
    int id, size, position;
    SupabaseClient supabaseClient = new SupabaseClient();
    Context context;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activty_lessons);
        nameCourseText = findViewById(R.id.name_courses_title);
        nameLessonText = findViewById(R.id.name_lessons_title);
        numberLessonsText = findViewById(R.id.number_lessons_title);
        titleLessonText = findViewById(R.id.title_lessons);
        textLessonText = findViewById(R.id.content_lessons);
        buttonCancel = findViewById(R.id.button_cancel);
        buttonTests = findViewById(R.id.tests_button);
        buttonDiscuss = findViewById(R.id.discuss_button);
        buttonLessons = findViewById(R.id.lessons_button);
        imageLesson = findViewById(R.id.image_lessons);
        id = ThemeStatic.getId();
        size = ThemeStatic.getSize();
        position = ThemeStatic.getPosition();
        context = this;
        ThemeStatic.setName_course(nameCourseText.getText().toString());
        ThemeStatic.setName_lessions(nameLessonText.getText().toString());
        try {
            getThemes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(LessonsActivity.this, UserCoursesActivity.class);
                startActivity(intent);
            }
        });
        buttonTests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(LessonsActivity.this, TestsActicity.class);
                startActivity(intent);
            }
        });
        buttonDiscuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(LessonsActivity.this, CommentsActivity.class);
                startActivity(intent);
            }
        });

    }
    private void getThemes() throws IOException {
        supabaseClient.fetchLessons (id, new SupabaseClient.SBC_Callback() {
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
                    Type type = new TypeToken<List<Theme>>(){}.getType();
                    List<Theme> themesList = gson.fromJson(responseBody, type);
                    Theme theme = themesList.get(0);
                    nameCourseText.setText(CoursesStatic.getName());
                    nameLessonText.setText(theme.getName_lessions());
                    numberLessonsText.setText(position+" "+getResources().getString(R.string.of)+" "+size+" "+getResources().getString(R.string.lessons));
                    titleLessonText.setText(theme.getHeadline());
                    textLessonText.setText(theme.getDescription());
                    String url = "https://psziqhddgczahqmabysn.supabase.co/storage/v1/object/public/imagelessons//";
                    Glide.with(context)
                            .load(url + theme.getLecture_name())
                            .placeholder (R.drawable.illustration)
                            .error(R.drawable.illustration5)
                            .into(imageLesson);
                });
            }
        });

    }
}
