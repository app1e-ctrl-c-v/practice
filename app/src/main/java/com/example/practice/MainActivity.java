package com.example.practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button buttonNext;
    TextView textTitle;
    ImageView imageIllistration, imageView;
    int click = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intro);
        buttonNext = findViewById(R.id.button_next);
        textTitle = findViewById(R.id.title_intro);
        imageIllistration = findViewById(R.id.image_icon);
        imageView = findViewById(R.id.image_draw);
        Intent intent = new Intent(MainActivity.this, SingInActivity.class);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (click){
                    case 0: textTitle.setText(R.string.intro2_title); imageIllistration.setImageResource(R.drawable.illustration1); imageView.setImageResource(R.drawable.pagination1); break;
                    case 1: textTitle.setText(R.string.intro3_title); imageIllistration.setImageResource(R.drawable.illustration2); imageView.setImageResource(R.drawable.pagination2); buttonNext.setText(R.string.button_lets); break;
                    case 2: startActivity(intent); finish(); break;
                }
                click++;
            }
        });

    }
}