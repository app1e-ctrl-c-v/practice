package com.example.practice;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.Adapters.CoursesAdapters;
import com.example.practice.Models.Courses;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    RecyclerView list_corses;
    TextView numberText;
    EditText searchCourses;
    CoursesAdapters coursesAdapters;
    List<Courses> coursesList;
    SupabaseClient supabaseClient = new SupabaseClient();
    Button cancelButton;
    Intent intent;
    Context context;
    CoursesAdapters.OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        searchCourses = findViewById(R.id.search);
        list_corses = findViewById(R.id.courses_list);
        cancelButton = findViewById(R.id.button_cancel);
        numberText = findViewById(R.id.number_title);
        context = this;
        try {
            getAllCourses();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        onRecyclerViewItemClickListener = new CoursesAdapters.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(Courses courses, int position) {

                intent = new Intent(SearchActivity.this, CoursesDetailActivity.class);
                intent.putExtra("id", courses.getId());
                intent.putExtra("name", courses.getName());
                intent.putExtra("description", courses.getDescription());
                intent.putExtra("duration", courses.getDuration());
                intent.putExtra("price", courses.getPrice());
                intent.putExtra("image", courses.getCover_name());
                startActivity(intent);
            }
        };
        searchCourses.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(searchCourses.getText().length() == 0){
                    coursesAdapters.restore_list();
                    numberText.setText(""+coursesAdapters.getLength());
                } else
                {
                    coursesAdapters.filter(searchCourses.getText().toString());
                    numberText.setText(""+coursesAdapters.getLength());
                }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SearchActivity.this, CoursesActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getAllCourses() throws IOException {
        supabaseClient.fetchAllCourses (new SupabaseClient.SBC_Callback() {
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
                        Type type = new TypeToken<List<Courses>>(){}.getType();
                        coursesList = gson.fromJson(responseBody, type);
                        coursesAdapters = new CoursesAdapters(context, coursesList, onRecyclerViewItemClickListener);
                        list_corses.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                        list_corses.setAdapter(coursesAdapters);

                });
            }
        });

    }
}
