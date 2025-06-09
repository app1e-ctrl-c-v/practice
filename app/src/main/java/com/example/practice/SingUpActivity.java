package com.example.practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SingUpActivity  extends AppCompatActivity {
    Button buttonSingUp, buttonSingIn;
    ImageButton buttonCancel;
    EditText editTextName, editTextEmail, editTextPassword;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_singup);
        buttonSingUp = findViewById(R.id.singup_button);
        buttonSingIn = findViewById(R.id.login_button);
        buttonCancel = findViewById(R.id.button_cancel);
        editTextName = findViewById(R.id.name_edittext);
        editTextPassword = findViewById(R.id.password_edittext);
        editTextEmail = findViewById(R.id.email_edittext);
        intent = new Intent(SingUpActivity.this, SingInActivity.class);
        buttonSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}

