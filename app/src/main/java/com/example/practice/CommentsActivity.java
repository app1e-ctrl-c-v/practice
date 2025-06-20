package com.example.practice;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.Adapters.CommentsAdapter;
import com.example.practice.Adapters.CoursesAdapters;
import com.example.practice.Models.Comment;
import com.example.practice.Models.Courses;
import com.example.practice.Models.DataBinding;
import com.example.practice.Models.ThemeStatic;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CommentsActivity extends AppCompatActivity {
    TextView nameCourseText, nameLessonText, numberLessonsText;
    Button buttonCancel, buttonTests, buttonDiscuss, buttonLessons, buttonSend;
    int id, size, position;
    EditText commentText, ratingText;
    Context context;
    RecyclerView commentsList;
    List<Comment> list_comments;
    Intent intent;
    SupabaseClient supabaseClient = new SupabaseClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_discuss);
        nameCourseText = findViewById(R.id.name_courses_title);
        nameLessonText = findViewById(R.id.name_lessons_title);
        numberLessonsText = findViewById(R.id.number_lessons_title);
        buttonCancel = findViewById(R.id.button_cancel);
        buttonTests = findViewById(R.id.tests_button);
        buttonDiscuss = findViewById(R.id.discuss_button);
        buttonLessons = findViewById(R.id.lessons_button);
        buttonSend = findViewById(R.id.button_send);
        commentText = findViewById(R.id.comments_edit);
        ratingText = findViewById(R.id.rating);
        commentsList = findViewById(R.id.list_comments);
        id = ThemeStatic.getId();
        size = ThemeStatic.getSize();
        position = ThemeStatic.getPosition();
        nameCourseText.setText(ThemeStatic.getName_course());
        nameLessonText.setText(ThemeStatic.getName_lessions());
        context = this;
        numberLessonsText.setText(position+" "+getResources().getString(R.string.of)+" "+size+" "+getResources().getString(R.string.lessons));
        try {
            getComments();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commentText.getText().length()>0&&ratingText.getText().length()>0){
                    new AddTask().execute("");
                } else Toast.makeText(getApplicationContext(), getResources().getText(R.string.error_validation), Toast.LENGTH_LONG).show();
            }
        });
        buttonLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(CommentsActivity.this, LessonsActivity.class);
                startActivity(intent);
            }
        });
        buttonTests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(CommentsActivity.this, TestsActicity.class);
                startActivity(intent);
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(CommentsActivity.this, UserCoursesActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getComments() throws IOException {
        supabaseClient.fetchComments (id, new SupabaseClient.SBC_Callback() {
            @Override
            public void onFailure (IOException e) {
                runOnUiThread(() -> {
                    Log.e("getAllOrders: onFailure", e.getLocalizedMessage());
                });
            }
            @Override
            public void onResponse (String responseBody) {
                runOnUiThread(()-> {
                    Log.e("getAllOrders: onResponse",responseBody);
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Comment>>(){}.getType();
                    list_comments = gson.fromJson(responseBody, type);
                    CommentsAdapter commentsAdapter = new CommentsAdapter(context, list_comments);
                    commentsList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                    commentsList.setAdapter(commentsAdapter);
                });
            }
        });

    }
    class AddTask extends AsyncTask<String, Void, String> {

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{ \"id_theme\": \""+id+"\", \"content\": \""+commentText.getText().toString()+"\", \"date\": \""+ LocalDateTime.now() +"\", \"rating\": \""+ratingText.getText().toString()+"\", \"user\": \""+DataBinding.getUuidUser()+"\"}");
            Request request = new Request.Builder()
                    .url("https://psziqhddgczahqmabysn.supabase.co/rest/v1/comments")
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
            try {
                getComments();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
