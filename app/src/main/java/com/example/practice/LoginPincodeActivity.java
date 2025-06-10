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
    SharedPreferences preferences = getSharedPreferences("pin_code", Context.MODE_PRIVATE);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_pincode);
        pincodeText = findViewById(R.id.pincode_edittext);
        singinButton = findViewById(R.id.login_button);
        singupButton = findViewById(R.id.singup_button);
        backButton = findViewById(R.id.button_cancel);
        int pin_code = preferences.getInt("code", 0);
        singinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPinCode(pincodeText.getText().toString())){
                    int code = Integer.parseInt(pincodeText.getText().toString());
                    if(code == pin_code){
                        intent = new Intent(LoginPincodeActivity.this, SingInActivity.class);
                        startActivity(intent);
                    }
                    else Toast.makeText(getApplicationContext(), getResources().getText(R.string.error_pin_code), Toast.LENGTH_LONG).show();
                } else Toast.makeText(getApplicationContext(), getResources().getText(R.string.error_validation), Toast.LENGTH_LONG).show();
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
