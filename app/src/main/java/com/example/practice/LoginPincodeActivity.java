package com.example.practice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginPincodeActivity extends AppCompatActivity {
    EditText pincodeText;
    Button singupButton, singinButton;
    ImageButton backButton;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_pincode);
        pincodeText = findViewById(R.id.pincode_edittext);
        singinButton = findViewById(R.id.login_button);
        singupButton = findViewById(R.id.singup_button);
        backButton = findViewById(R.id.button_cancel);
        singupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("app_preferences", MODE_PRIVATE);
                String save_pin_code = sharedPreferences.getString("pin_code", "");
            if(pincodeText.length() == 4){
                String pin_code = pincodeText.getText().toString();
                if(pin_code.equals(save_pin_code)){
                    intent = new Intent(LoginPincodeActivity.this, CoursesActivity.class);
                    startActivity(intent);
                }else Toast.makeText(getApplicationContext(), getResources().getText(R.string.error_pin_code), Toast.LENGTH_LONG).show();
            } else Toast.makeText(getApplicationContext(), getResources().getText(R.string.error_validation), Toast.LENGTH_LONG).show();
            }
        });
        singinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(LoginPincodeActivity.this, SingInActivity.class);
                startActivity(intent);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(LoginPincodeActivity.this, SingInActivity.class);
                startActivity(intent);
            }
        });
    }
    public boolean isPinCode(CharSequence target) {
        return (!TextUtils.isEmpty(target) && target.length()==4);
    }
}
