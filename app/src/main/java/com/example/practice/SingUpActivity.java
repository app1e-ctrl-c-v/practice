package com.example.practice;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

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
        buttonSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SingUpActivity.this, SingInActivity.class);
                startActivity(intent);
            }
        });
        buttonSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValidEmail(editTextEmail.getText().toString()) && isPassword(editTextPassword.getText())){
                    intent = new Intent(SingUpActivity.this, CoursesActivity.class);
                    startActivity(intent);

                } else Toast.makeText(getApplicationContext(), getResources().getText(R.string.error_validation), Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean isValidEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }
    public boolean isPassword(CharSequence target) {
        return (!TextUtils.isEmpty(target) && target.length()>8 && target.length()<20);
    }
    public boolean isName(CharSequence target) {
        return (!TextUtils.isEmpty(target)&& target.length()<20);
    }
    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );
}

