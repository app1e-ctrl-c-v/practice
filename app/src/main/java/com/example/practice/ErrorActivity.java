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

public class ErrorActivity extends AppCompatActivity {
    Button buttonCancel, buttonContinue;
    TextView errorTitle, errorDescription, errorDescriptionTitle;
    ImageView errorImage;
    String error;
    Intent intent;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_error);
        errorTitle = findViewById(R.id.name_courses_title);
        errorDescriptionTitle = findViewById(R.id.about_courses_title);
        errorDescription = findViewById(R.id.about_courses_text);
        buttonCancel = findViewById(R.id.button_cancel);
        buttonContinue = findViewById(R.id.add_button);
        errorImage = findViewById(R.id.courses_illustration);
        Bundle arguments = getIntent().getExtras();
        error = arguments.getString("error");
        switch (error){
            case "Saved":
                errorTitle.setText(getResources().getText(R.string.button_add_update));
                errorDescriptionTitle.setText(getResources().getText(R.string.error_saved3));
                errorDescription.setText(getResources().getText(R.string.error_saved4));
                errorImage.setImageResource(R.drawable.illustration8);
                break;
            case "notSaved":
                errorTitle.setText(getResources().getText(R.string.button_add_update));
                errorDescriptionTitle.setText(getResources().getText(R.string.error_saves1));
                errorDescription.setText(getResources().getText(R.string.error_saves2));
                errorImage.setImageResource(R.drawable.illustration9);
                break;
            case "Payment":
                errorTitle.setText(getResources().getText(R.string.button_add_update));
                errorDescriptionTitle.setText(getResources().getText(R.string.error_payment3));
                errorDescription.setText(getResources().getText(R.string.error_payment4));
                errorImage.setImageResource(R.drawable.illustration6);
                break;
            case "notPayment":
                errorTitle.setText(getResources().getText(R.string.button_add_update));
                errorDescriptionTitle.setText(getResources().getText(R.string.error_payment1));
                errorDescription.setText(getResources().getText(R.string.error_payment2));
                errorImage.setImageResource(R.drawable.illustration7);
                break;
        }
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ErrorActivity.this, CoursesActivity.class);
                startActivity(intent);
            }
        });
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ErrorActivity.this, CoursesActivity.class);
                startActivity(intent);
            }
        });


    }
}
