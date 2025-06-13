package com.example.practice;
import static java.lang.Character.getType;

import android.content.Context;
import android.os.Bundle;
import android.os.TokenWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.Adapters.CategoryAdapter;
import com.example.practice.Adapters.CoursesAdapters;
import com.example.practice.Models.Category;
import com.example.practice.Models.Courses;
import com.example.practice.Models.Profile;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    TextView name_user;
    RecyclerView list_corses, list_category;
    SupabaseClient supabaseClient = new SupabaseClient();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        name_user = view.findViewById(R.id.name_user_title);
//        list_category = view.findViewById(R.id.list_category);
        list_corses = view.findViewById(R.id.courses_list);
        name_user.setText(Profile.getUsername());
        try {
//            getAllCategoty();
            getAllCourses();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return view;
    }
    private void getAllCourses() throws IOException {
        supabaseClient.fetchAllCourses (new SupabaseClient.SBC_Callback() {
            @Override
            public void onFailure (IOException e) {
                getActivity().runOnUiThread(() -> {
                    Log.e("getAllOrders: onFailure", e.getLocalizedMessage());
                });
            }
            @Override
            public void onResponse (String responseBody) {
                getActivity().runOnUiThread(()-> {
                    Log.e("getAllOrders: onResponse",responseBody);
                        if(isAdded() && getContext() != null) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<Courses>>(){}.getType();
                List<Courses> coursesList = gson.fromJson(responseBody, type);
                        CoursesAdapters coursesAdapters = new CoursesAdapters(getContext(), coursesList);
                        list_corses.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                        list_corses.setAdapter(coursesAdapters);
                    }
                });
            }
        });

    }

    private void getAllCategoty() throws IOException {
        supabaseClient.fetchAllCategory(new SupabaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                getActivity().runOnUiThread(() -> {
                    Log.e("getAllOrders: onFailure", e.getLocalizedMessage());
                });
            }

            @Override
            public void onResponse(String responseBody) {
                getActivity().runOnUiThread(() -> {
                    Log.e("getAllOrders: onResponse", responseBody);
                    if (isAdded() && getContext() != null) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Category>>() {
                        }.getType();
                        List<Category> category = gson.fromJson(responseBody, type);
                        CategoryAdapter categoryAdapter = new CategoryAdapter(category, getContext());
                        list_corses.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                        list_corses.setAdapter(categoryAdapter);
                    }
                });
            }
        });
    }
}