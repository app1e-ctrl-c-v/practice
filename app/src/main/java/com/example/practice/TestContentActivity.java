package com.example.practice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.practice.Adapters.TestAdapter;
import com.example.practice.Adapters.ThemesAdapter;
import com.example.practice.Models.Answer;
import com.example.practice.Models.CoursesStatic;
import com.example.practice.Models.Test;
import com.example.practice.Models.Theme;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TestContentActivity extends AppCompatActivity {
    TextView nameAnswer, numberAnswer;
    ImageView answer_image;
    Intent intent;
    Button buttonCancel, buttonAnswer1, buttonAnswer2, buttonAnswer3, buttonContinue;
    int id, size, i = 0, correctAnswer = 0;
    String name, number;
    SupabaseClient supabaseClient = new SupabaseClient();
    Context context;
    List<Answer> answerList;
    Answer answer;
    ArrayList<String> variable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_content);
        buttonCancel = findViewById(R.id.button_cancel);
        nameAnswer = findViewById(R.id.name_lessons_title);
        numberAnswer = findViewById(R.id.number_lessons_title);
        buttonAnswer1 = findViewById(R.id.answer1_button);
        buttonAnswer2 = findViewById(R.id.answer2_button);
        buttonAnswer3 = findViewById(R.id.answer3_button);
        buttonContinue = findViewById(R.id.continue_button);
        answer_image = findViewById(R.id.image_question);
        Bundle arguments = getIntent().getExtras();
        id = arguments.getInt("id_test");
        try {
            getAnswers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(TestContentActivity.this, UserCoursesActivity.class);
                startActivity(intent);
            }
        });
        buttonAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(variable.get(0).contains(answer.getCorrect())){
                    correctAnswer++;
                    buttonAnswer1.setEnabled(false);
                    buttonAnswer2.setEnabled(false);
                    buttonAnswer3.setEnabled(false);
                } else{
                    Toast.makeText(getApplicationContext(), getResources().getText(R.string.incorrect_toast), Toast.LENGTH_LONG).show();
                    buttonAnswer1.setEnabled(false);
                    buttonAnswer2.setEnabled(false);
                    buttonAnswer3.setEnabled(false);
                }
            }
        });
        buttonAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(variable.get(1).contains(answer.getCorrect())){
                    correctAnswer++;
                    buttonAnswer1.setEnabled(false);
                    buttonAnswer2.setEnabled(false);
                    buttonAnswer3.setEnabled(false);
                } else{
                    Toast.makeText(getApplicationContext(), getResources().getText(R.string.incorrect_toast), Toast.LENGTH_LONG).show();
                    buttonAnswer1.setEnabled(false);
                    buttonAnswer2.setEnabled(false);
                    buttonAnswer3.setEnabled(false);
                }
            }
        });
        buttonAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(variable.get(2).contains(answer.getCorrect())){
                    correctAnswer++;
                    buttonAnswer1.setEnabled(false);
                    buttonAnswer2.setEnabled(false);
                    buttonAnswer3.setEnabled(false);
                } else{
                    Toast.makeText(getApplicationContext(), getResources().getText(R.string.incorrect_toast), Toast.LENGTH_LONG).show();
                    buttonAnswer1.setEnabled(false);
                    buttonAnswer2.setEnabled(false);
                    buttonAnswer3.setEnabled(false);
                }
            }
        });
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if(i<size){
                answer = answerList.get(i);
                variable = answer.getAnswer_text();
                nameAnswer.setText(answer.getText());
                numberAnswer.setText((i+1)+" "+getResources().getString(R.string.of)+" "+size);
                buttonAnswer1.setText("A.   "+variable.get(0));
                buttonAnswer2.setText("B.   "+variable.get(1));
                buttonAnswer3.setText("C.   "+variable.get(2));
                buttonAnswer1.setEnabled(true);
                buttonAnswer2.setEnabled(true);
                buttonAnswer3.setEnabled(true);
                } else {
                    intent = new Intent(TestContentActivity.this, ResultTestActivity.class);
                    intent.putExtra("correctAnswer", correctAnswer);
                    intent.putExtra("size", size);
                    startActivity(intent);
                }

            }
        });

    }
    private void getAnswers() throws IOException {
        supabaseClient.fetchAnswer(id, new SupabaseClient.SBC_Callback() {
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
                    Type type = new TypeToken<List<Answer>>() {
                    }.getType();
                    answerList = gson.fromJson(responseBody, type);
                    size = answerList.size();
                    answer = answerList.get(i);
                    variable = answer.getAnswer_text();
                    nameAnswer.setText(answer.getText());
                    numberAnswer.setText((i+1)+" "+getResources().getString(R.string.of)+" "+size);
                    buttonAnswer1.setText("A.   "+variable.get(0));
                    buttonAnswer2.setText("B.   "+variable.get(1));
                    buttonAnswer3.setText("C.   "+variable.get(2));
                });
            }
        });
    }
}
