package com.example.practice;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.practice.Models.CoursesStatic;
import com.example.practice.Models.DataBinding;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CoursesDetailActivity2 extends AppCompatActivity {
    Button buttonCancel, buttonDelete, buttonBuy;
    TextView coursesTitle, coursesPrice, coursesDescription, coursesDuration;
    ImageView coursesImage;
    String name, title, price, description, duration, image;
    int id;
    Intent intent;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_courses_detail2);
        coursesTitle = findViewById(R.id.name_courses_title);
        coursesPrice = findViewById(R.id.price_courses);
        coursesDescription = findViewById(R.id.about_courses_text);
        coursesDuration = findViewById(R.id.duration_text);
        buttonCancel = findViewById(R.id.button_cancel);
        buttonDelete = findViewById(R.id.delete_button);
        buttonBuy = findViewById(R.id.buy_button);
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
                intent = new Intent(CoursesDetailActivity2.this, SaveCoursesActivity.class);
                startActivity(intent);
            }
        });
        buttonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(CoursesDetailActivity2.this, UserPaymentActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DeleteTask().execute("");
                intent = new Intent(CoursesDetailActivity2.this, CoursesActivity.class);
                startActivity(intent);
            }
        });
    }
    class DeleteTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("https://psziqhddgczahqmabysn.supabase.co/rest/v1/save_course?course=eq."+id+"&user=eq."+DataBinding.getUuidUser())
                    .method("DELETE", body)
                    .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                    .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
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

        }
    }
}
