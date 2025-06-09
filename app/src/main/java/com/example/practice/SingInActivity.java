package com.example.practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SingInActivity  extends AppCompatActivity {
    Button buttonSingUp, buttonSingIn;
    EditText editTextEmail, editTextPassword;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        buttonSingUp = findViewById(R.id.singup_button);
        buttonSingIn = findViewById(R.id.login_button);
        editTextPassword = findViewById(R.id.password_edittext);
        editTextEmail = findViewById(R.id.email_edittext);
        intent = new Intent(SingInActivity.this, SingUpActivity.class);
        buttonSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}
