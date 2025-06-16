package com.example.practice;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class CoursesDetailActivity extends AppCompatActivity {
    Button buttonCancel, buttonAdd;
    TextView coursesTitle, coursesPrice, coursesDescription, coursesDuration;
    ImageView coursesImage;
    String title, price, description, duration, image;
    int id;
    Intent intent;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_courses_detail);
        coursesTitle = findViewById(R.id.name_courses_title);
        coursesPrice = findViewById(R.id.price_courses);
        coursesDescription = findViewById(R.id.about_courses_text);
        coursesDuration = findViewById(R.id.duration_text);
        buttonCancel = findViewById(R.id.button_cancel);
        buttonAdd = findViewById(R.id.add_button);
        coursesImage = findViewById(R.id.courses_illustration);
        Bundle arguments = getIntent().getExtras();
        title = arguments.get("name").toString();
        price = arguments.get("price").toString();
        description = arguments.get("description").toString();
        double time = arguments.getDouble("duration");
        int hour = (int) Math.floor(time);
        double fractionalPart = time - hour;
        int min = (int) Math.round(fractionalPart * 60);
        duration = hour+" h "+min+" min";
        image = arguments.get("image").toString();
        id = arguments.getInt("id");
        coursesTitle.setText(title);
        coursesDescription.setText(description);
        coursesDuration.setText(duration);
        coursesPrice.setText(price);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(CoursesDetailActivity.this, CoursesActivity.class);
                startActivity(intent);
            }
        });
    }
}
