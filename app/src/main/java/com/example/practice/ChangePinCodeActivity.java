package com.example.practice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practice.Models.DataBinding;

import java.time.LocalDate;

public class ChangePinCodeActivity extends AppCompatActivity {

    Button buttonCreate, buttonBack;
    EditText pincodeEdit, confirmEdit;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_pincode);
        buttonBack = findViewById(R.id.button_cancel);
        buttonCreate = findViewById(R.id.confirm_button);
        pincodeEdit = findViewById(R.id.pincode_edittext);
        confirmEdit = findViewById(R.id.pincode_confirm_edittext);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ChangePinCodeActivity.this, thirdFragment.class);
                startActivity(intent);
            }
        });
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if(pincodeEdit.length() == 4 && confirmEdit.length()==4){
                    if(Integer.parseInt(pincodeEdit.getText().toString())==(Integer.parseInt(confirmEdit.getText().toString()))){
                        SharedPreferences sharedPreferences = getSharedPreferences("app_preferences", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("presence of pin code", true);
                        editor.putString("pin_code", pincodeEdit.getText().toString()   );
                        editor.apply();
                        intent = new Intent(ChangePinCodeActivity.this, CoursesActivity.class);
                        startActivity(intent);
                    } else Toast.makeText(getApplicationContext(), getResources().getText(R.string.error_pin_code), Toast.LENGTH_LONG).show();
                } else Toast.makeText(getApplicationContext(), getResources().getText(R.string.error_validation), Toast.LENGTH_LONG).show();
            }
        });

    }
}
