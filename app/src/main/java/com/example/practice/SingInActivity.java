package com.example.practice;

import static androidx.core.content.ContextCompat.startActivity;

import static com.example.practice.Models.DataBinding.saveBearerToken;
import static com.example.practice.Models.DataBinding.saveUuidUser;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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

import com.example.practice.Models.Profile;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.xml.validation.Validator;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SingInActivity  extends AppCompatActivity {
    Button buttonSingUp, buttonSingIn;
    EditText editTextEmail, editTextPassword;
    Intent intent;
    TextView pincodeText, forgotpasswordText;
    String email, password, name, bearerToken, uuidUser;
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
        forgotpasswordText = findViewById(R.id.forgot_passwors_text);
        forgotpasswordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SingInActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
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
                if(isValidEmail(editTextEmail.getText().toString()) && isPassword(editTextPassword.getText())){
                email = editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();
                new LoginTask().execute("");
                } else Toast.makeText(getApplicationContext(), getResources().getText(R.string.error_validation), Toast.LENGTH_LONG).show();
            }
        });
    }

    class LoginTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n  \"email\": \""+email+"\",\n  \"password\": \""+password+"\"\n}");
            Request request = new Request.Builder()
                    .url("https://psziqhddgczahqmabysn.supabase.co/auth/v1/token?grant_type=password")
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

        @Override
        protected void onPostExecute(String answer) {
            try {
                JSONObject profilies = new JSONObject(answer);
                bearerToken = profilies.getString("access_token");
                JSONObject user = profilies.getJSONObject("user");
                uuidUser = user.getString("id");
                saveUuidUser(uuidUser);
                saveBearerToken(bearerToken);
                intent = new Intent(SingInActivity.this, CoursesActivity.class);
                startActivity(intent);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean isPassword(CharSequence target) {
        return (!TextUtils.isEmpty(target) && target.length()>8 && target.length()<20);
    }
    private boolean isValidEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
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
