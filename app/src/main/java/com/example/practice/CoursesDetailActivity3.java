package com.example.practice;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.practice.Models.CoursesStatic;
import com.example.practice.Models.DataBinding;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CoursesDetailActivity3 extends AppCompatActivity {
    Button buttonCancel, buttonOpen;
    TextView coursesTitle, coursesPrice, coursesDescription, coursesDuration;
    ImageView coursesImage;
    String name, title, description, duration, image;
    int id;
    Intent intent;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_courses_detail3);
        coursesTitle = findViewById(R.id.name_courses_title);
        coursesPrice = findViewById(R.id.price_courses);
        coursesDescription = findViewById(R.id.about_courses_text);
        coursesDuration = findViewById(R.id.duration_text);
        buttonCancel = findViewById(R.id.button_cancel);
        buttonOpen = findViewById(R.id.open_button);
        coursesImage = findViewById(R.id.courses_illustration);
        name = CoursesStatic.getName();
        title = CoursesStatic.getTitle();
        description = CoursesStatic.getDescription();
        image = CoursesStatic.getCover_name();
        id = CoursesStatic.getId();
        double time = CoursesStatic.getDuration();
        int hour = (int) Math.floor(time);
        double fractionalPart = time - hour;
        int min = (int) Math.round(fractionalPart * 60);
        duration = hour+" h "+min+" min";
        coursesTitle.setText(name);
        coursesDescription.setText(description);
        coursesDuration.setText(duration);
        String url = "https://psziqhddgczahqmabysn.supabase.co/storage/v1/object/public/imagecourses//";
        Glide.with(this)
                .load(url + image)
                .placeholder (R.drawable.illustration)
                .error(R.drawable.illustration5)
                .into(coursesImage);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(CoursesDetailActivity3.this, UserCoursesActivity.class);
                startActivity(intent);
            }
        });
        buttonOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(CoursesDetailActivity3.this, ThemesActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                intent.putExtra("title", title);
                intent.putExtra("description", description);
                intent.putExtra("image", image);
                startActivity(intent);
            }
        });
    }
}