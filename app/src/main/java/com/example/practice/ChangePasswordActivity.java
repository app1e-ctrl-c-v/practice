package com.example.practice;

import static com.example.practice.Models.DataBinding.saveBearerToken;
import static com.example.practice.Models.DataBinding.saveUuidUser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practice.Models.Profile;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChangePasswordActivity extends AppCompatActivity {
    String email;
    Button buttonConfirm;
    EditText editPassword, editConfirmPassword;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_password);
        buttonConfirm = findViewById(R.id.confirm_button);
        editPassword = findViewById(R.id.pincode_edittext);
        editConfirmPassword = findViewById(R.id.pincode_confirm_edittext);
        Bundle arguments = getIntent().getExtras();
        email = arguments.get("email").toString();
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editPassword.length() >0 && editConfirmPassword.length()>0){
                    if(Integer.parseInt(editPassword.getText().toString())==(Integer.parseInt(editConfirmPassword.getText().toString()))){
                        new UpdatePassword().execute("");
                        intent = new Intent(ChangePasswordActivity.this, SingInActivity.class);
                        startActivity(intent);
                    } else Toast.makeText(getApplicationContext(), getResources().getText(R.string.error_validation), Toast.LENGTH_LONG).show();
                } else Toast.makeText(getApplicationContext(), getResources().getText(R.string.error_validation), Toast.LENGTH_LONG).show();
            }
        });
    }
    class UpdatePassword extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            MediaType mediaType = MediaType.parse("application/json");
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            RequestBody body = RequestBody.create(mediaType, "{\n  \"email\": \"" + email + "\",\n  \"password\": \"" + editPassword.getText().toString() + "\"\n}");
            Request request = new Request.Builder()
                    .url("https://psziqhddgczahqmabysn.supabase.co/functions/v1/update-password")
                    .method("POST", body)
                    .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                    .addHeader("Content-Type", "application/json")
                    .build();

            try{
                Response response = client.newCall(request).execute();
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code" + response);
                return response.body().string();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


}
