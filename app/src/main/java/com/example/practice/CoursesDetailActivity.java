package com.example.practice;

import static com.example.practice.Models.DataBinding.saveBearerToken;
import static com.example.practice.Models.DataBinding.saveUuidUser;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.practice.Models.CoursesStatic;
import com.example.practice.Models.DataBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CoursesDetailActivity extends AppCompatActivity {
    Button buttonCancel, buttonAdd;
    TextView coursesTitle, coursesPrice, coursesDescription, coursesDuration;
    ImageView coursesImage;
    String title, name, price, description, duration, image;
    int id;
    Intent intent;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_courses_detail);
        coursesTitle = findViewById(R.id.name_courses_title);
        coursesPrice = findViewById(R.id.price_courses);
        coursesDescription = findViewById(R.id.about_courses_text);
        coursesDuration = findViewById(R.id.duration_text);
        buttonCancel = findViewById(R.id.button_cancel);
        buttonAdd = findViewById(R.id.add_button);
        coursesImage = findViewById(R.id.courses_illustration);
        name = CoursesStatic.getName();
        title = CoursesStatic.getTitle();
        description = CoursesStatic.getDescription();
        image = CoursesStatic.getCover_name();
        id = CoursesStatic.getId();
        double time = CoursesStatic.getDuration();
        double pr = CoursesStatic.getPrice();
        int hour = (int) Math.floor(time);
        double fractionalPart = time - hour;
        int min = (int) Math.round(fractionalPart * 60);
        duration = hour+" h "+min+" min";
        price = "$ "+pr;
        coursesTitle.setText(name);
        coursesDescription.setText(description);
        coursesDuration.setText(duration);
        coursesPrice.setText(price);
        String url = "https://psziqhddgczahqmabysn.supabase.co/storage/v1/object/public/imagecourses//";
        Glide.with(this)
                .load(url + image)
                .placeholder (R.drawable.illustration)
                .error(R.drawable.illustration5)
                .into(coursesImage);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(CoursesDetailActivity.this, CoursesActivity.class);
                startActivity(intent);
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddTask().execute("");
                buttonAdd.setText(getResources().getText(R.string.button_add_update));
            }
        });
    }
    class AddTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{ \"user\": \""+DataBinding.getUuidUser()+"\", \"course\": \""+id+"\" }");
            Request request = new Request.Builder()
                    .url("https://psziqhddgczahqmabysn.supabase.co/rest/v1/save_course")
                    .method("POST", body)
                    .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                    .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Prefer", "return=minimal")
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
            Toast.makeText(getApplicationContext(), getResources().getText(R.string.add_toast), Toast.LENGTH_SHORT).show();
        }
    }
}
