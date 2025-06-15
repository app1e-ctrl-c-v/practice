package com.example.practice;

import static com.example.practice.Models.DataBinding.saveBearerToken;
import static com.example.practice.Models.DataBinding.saveUuidUser;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practice.Models.Profile;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OTPActivity extends AppCompatActivity {
    Button buttonConfirm;
    EditText editCode;
    Intent intent;
    int otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_code);
        buttonConfirm = findViewById(R.id.confirm_button);
        editCode = findViewById(R.id.code_edittext);
        Bundle arguments = getIntent().getExtras();
        String email = arguments.get("email").toString();
        Random random = new Random();
        otp = random.nextInt(1000000);
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, email);
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getResources().getText(R.string.otp_code_title)+" "+otp);
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, getResources().getText(R.string.otp_code_text)+" "+otp);
        this.startActivity(Intent.createChooser(emailIntent,
                getResources().getText(R.string.send_email)));
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValidOTR(editCode.getText().toString(), otp)){
                    intent = new Intent(OTPActivity.this, ChangePasswordActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean isValidOTR(String otr, int saveOTR) {
        return (!TextUtils.isEmpty(otr) && Integer.parseInt(otr)==saveOTR);
    }


}
