package com.example.practice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.practice.Adapters.CoursesAdapters;
import com.example.practice.Adapters.ThemesAdapter;
import com.example.practice.Models.Courses;
import com.example.practice.Models.CoursesStatic;
import com.example.practice.Models.Theme;
import com.example.practice.Models.ThemeStatic;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ThemesActivity  extends AppCompatActivity {
    TextView nameTheme, titleTheme, descriptionTheme;
    RecyclerView list_theme;
    ImageView course_image;
    Intent intent;
    Button buttonCancel;
    int id, size;
    String name, title, description, image;
    SupabaseClient supabaseClient = new SupabaseClient();
    Context context;
    List<Theme> themesList;
    ThemesAdapter.OnRecyclerViewThemeClickListener onRecyclerViewItemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_themes);
        nameTheme = findViewById(R.id.name_courses_title);
        titleTheme = findViewById(R.id.title_courses);
        descriptionTheme = findViewById(R.id.short_description_courses);
        list_theme = findViewById(R.id.list_theme);
        course_image = findViewById(R.id.courses_image);
        buttonCancel = findViewById(R.id.button_cancel);
        name = CoursesStatic.getName();
        title = CoursesStatic.getTitle();
        description = CoursesStatic.getDescription();
        image = CoursesStatic.getCover_name();
        id = CoursesStatic.getId();
        String url = "https://psziqhddgczahqmabysn.supabase.co/storage/v1/object/public/imagecourses//";
        Glide.with(this)
                .load(url + image)
                .placeholder (R.drawable.illustration)
                .error(R.drawable.illustration5)
                .into(course_image);
        nameTheme.setText(name);
        titleTheme.setText(name);
        descriptionTheme.setText(title);
        context = this;
        try {
            getThemes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        onRecyclerViewItemClickListener = new ThemesAdapter.OnRecyclerViewThemeClickListener() {
            @Override
            public void onClick(Theme theme, int position) {
                ThemeStatic.setId(id);
                ThemeStatic.setSize(size);
                ThemeStatic.setPosition(position);
                intent = new Intent(ThemesActivity.this, LessonsActivity.class);
                startActivity(intent);
            }
        };
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ThemesActivity.this, UserCoursesActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getThemes() throws IOException {
        supabaseClient.fetchTheme (id, new SupabaseClient.SBC_Callback() {
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
                    Type type = new TypeToken<List<Theme>>(){}.getType();
                    themesList = gson.fromJson(responseBody, type);
                    ThemesAdapter themesAdapter = new ThemesAdapter(context, themesList, onRecyclerViewItemClickListener);
                    list_theme.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                    list_theme.setAdapter(themesAdapter);
                    size = themesList.size();
                });
            }
        });

    }
}
