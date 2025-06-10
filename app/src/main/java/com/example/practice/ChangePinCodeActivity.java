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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ChangePinCodeActivity extends AppCompatActivity {
    ImageButton buttonBack;
    Button buttonCreate;
    EditText pincodeEdit, confirmEdit;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_code);
        buttonBack = findViewById(R.id.button_cancel);
        buttonCreate = findViewById(R.id.singup_button);
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
            @Override
            public void onClick(View view) {
                if(isPinCode(pincodeEdit.getText().toString()) == isPinCode(confirmEdit.getText())){
                    SharedPreferences preferences = getSharedPreferences("pin_code", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("code", Integer.parseInt(pincodeEdit.getText().toString()));
                    editor.apply();
                    intent = new Intent(ChangePinCodeActivity.this, thirdFragment.class);
                    startActivity(intent);

                } else Toast.makeText(getApplicationContext(), getResources().getText(R.string.error_validation), Toast.LENGTH_LONG).show();
            }
        });

    }
    public boolean isPinCode(CharSequence target) {
        return (!TextUtils.isEmpty(target) && target.length()==4);
    }
}
