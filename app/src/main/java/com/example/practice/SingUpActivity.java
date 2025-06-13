package com.example.practice;

import static com.example.practice.Models.DataBinding.saveBearerToken;
import static com.example.practice.Models.DataBinding.saveUuidUser;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.practice.Adapters.CoursesAdapters;
import com.example.practice.Models.Courses;
import com.example.practice.Models.DataBinding;
import com.example.practice.Models.Profile;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SingUpActivity  extends AppCompatActivity {
    Button buttonSingUp, buttonSingIn;
    ImageButton buttonCancel;
    EditText editTextName, editTextEmail, editTextPassword;
    Intent intent;
    String email, password, name, bearerToken, uuidUser;
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
                    email = editTextEmail.getText().toString();
                    password = editTextPassword.getText().toString();
                    name = editTextName.getText().toString();
                    Profile.setUsername(name);
                new SingUpTask().execute("");
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


    class SingUpTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            MediaType mediaType = MediaType.parse("application/json");
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            RequestBody body = RequestBody.create(mediaType, "{\n  \"email\": \"" + email + "\",\n  \"password\": \"" + password + "\"\n}");
            Request request = new Request.Builder()
                    .url("https://psziqhddgczahqmabysn.supabase.co/auth/v1/signup")
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
                    Profile.saveId(uuidUser);


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
        }
    }
//    class UpdateProfile extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... strings) {
//            MediaType mediaType = MediaType.parse("application/json");
//            OkHttpClient client = new OkHttpClient().newBuilder()
//                    .build();
//            RequestBody body = RequestBody.create(mediaType, "{\n  \"email\": \"" + email + "\",\n  \"password\": \"" + password + "\"\n}");
//            Request request = new Request.Builder()
//                    .url("https://psziqhddgczahqmabysn.supabase.co/auth/v1/signup")
//                    .method("POST", body)
//                    .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
//                    .addHeader("Content-Type", "application/json")
//                    .build();
//
//            try{
//                Response response = client.newCall(request).execute();
//                if (!response.isSuccessful())
//                    throw new IOException("Unexpected code" + response);
//                return response.body().string();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        @Override
//        protected void onPostExecute(String answer) {
//            try {
//                JSONObject profilies = new JSONObject(answer);
//                bearerToken = profilies.getString("access_token");
//                JSONObject user = profilies.getJSONObject("user");
//                uuidUser = user.getString("id");
//                saveUuidUser(uuidUser);
//                saveBearerToken(bearerToken);
//                Profile.saveId(uuidUser);
//
//            } catch (JSONException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }


        //    private void postSingUp() throws IOException {
//        supabaseClient.fetchSingUp(new SupabaseClient.SBC_Callback(){
//            @Override
//            public void onFailure (IOException e) {
//                runOnUiThread(() -> {
//                    Log.e("getAllOrders: onFailure", e.getLocalizedMessage());
//                });
//            }
//            @Override
//            public void onResponse (String responseBody) {
//                runOnUiThread(()-> {
//                    Log.e("getAllOrders: onResponse",responseBody);
//                    Gson gson = new Gson();
//                    Type type = new TypeToken<List<JSONObject>>(){}.getType();
//                    List<JSONObject> arr = gson.fromJson(responseBody, type);
//                    JSONObject profilies = arr.get(0);
//                    try {
//                        bearerToken = profilies.getString("access_token");
//                        JSONObject user = profilies.getJSONObject("user");
//                        uuidUser = user.getString("id");
//                        saveUuidUser(uuidUser);
//                        saveBearerToken(bearerToken);
//                        profile.setId(uuidUser);
//                        profile.setUsername(name);
//                    } catch (JSONException e) {
//                        throw new RuntimeException(e);
//                    }
//
//                });
//            }
//        });
//
//    }

}

