package com.example.practice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practice.Models.Answer;

import java.util.ArrayList;
import java.util.List;

public class ResultTestActivity extends AppCompatActivity {
    TextView resultText;
    Intent intent;
    Button buttonCancel;
    int size, correctAnswer, res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_result);
        buttonCancel = findViewById(R.id.button_cancel);
        resultText = findViewById(R.id.result_title);
        Bundle arguments = getIntent().getExtras();
        size = arguments.getInt("size");
        correctAnswer = arguments.getInt("correctAnswer");
        if((size-correctAnswer)>size/2){
            resultText.setText(getResources().getText(R.string.incorrect_toast)+" "+(size-correctAnswer)+"\n"+getResources().getText(R.string.correct_toast)+" "+correctAnswer+"\n"+getResources().getText(R.string.failed));
        } else{
            if(size==correctAnswer){
                resultText.setText(getResources().getText(R.string.error_result2));
            }else {
                resultText.setText(getResources().getText(R.string.incorrect_toast)+" "+(size-correctAnswer)+"\n"+getResources().getText(R.string.correct_toast)+" "+correctAnswer+"\n"+getResources().getText(R.string.passed));
            }
        }
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ResultTestActivity.this, UserCoursesActivity.class);
                startActivity(intent);
            }
        });
    }
}
