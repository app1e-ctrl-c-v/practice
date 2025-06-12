package com.example.practice;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

import javax.xml.validation.Validator;

public class SingInActivity  extends AppCompatActivity {
    Button buttonSingUp, buttonSingIn;
    EditText editTextEmail, editTextPassword;
    Intent intent;
    TextView pincodeText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        buttonSingUp = findViewById(R.id.singup_button);
        buttonSingIn = findViewById(R.id.login_button);
        editTextPassword = findViewById(R.id.password_edittext);
        editTextEmail = findViewById(R.id.email_edittext);
        pincodeText = findViewById(R.id.login_pincode_text);
        pincodeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("app_preferences", MODE_PRIVATE);
                boolean booleanValue = sharedPreferences.getBoolean("presence of pin code", false);
            if(booleanValue){
                intent = new Intent(SingInActivity.this, LoginPincodeActivity.class);
                startActivity(intent);
            } else{
                Toast.makeText(getApplicationContext(), getResources().getText(R.string.error_pin_code), Toast.LENGTH_LONG).show();
            }
            }
        });
        buttonSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SingInActivity.this, SingUpActivity.class);
                startActivity(intent);
            }
        });
        buttonSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(isValidEmail(editTextEmail.getText().toString()) && isPassword(editTextPassword.getText())){
                    intent = new Intent(SingInActivity.this, CoursesActivity.class);
                    startActivity(intent);

//                } else Toast.makeText(getApplicationContext(), getResources().getText(R.string.error_validation), Toast.LENGTH_LONG).show();
            }
        });
    }



    private boolean isValidEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }
    public boolean isPassword(CharSequence target) {
        return (!TextUtils.isEmpty(target) && target.length()>8 && target.length()<20);
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
