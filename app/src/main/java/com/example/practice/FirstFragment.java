package com.example.practice;
import static java.lang.Character.getType;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.TokenWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.practice.Adapters.CategoryAdapter;
import com.example.practice.Adapters.CoursesAdapters;
import com.example.practice.Models.Category;
import com.example.practice.Models.Courses;
import com.example.practice.Models.CoursesStatic;
import com.example.practice.Models.Profile;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class FirstFragment extends Fragment {

    TextView name_user, categoryCSS, categoryUX, categoryhtml, categoryUI;
    RecyclerView list_corses, list_category;
    SupabaseClient supabaseClient = new SupabaseClient();
    List<Courses> coursesList;
    EditText searchCourses;
    CoursesAdapters coursesAdapters;
    int click = 0;
    CoursesAdapters.OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    Intent intent;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        name_user = view.findViewById(R.id.name_user_title);
        categoryCSS = view.findViewById(R.id.name_category1);
        categoryUX = view.findViewById(R.id.name_category2);
        categoryhtml = view.findViewById(R.id.name_category3);
        categoryUI = view.findViewById(R.id.name_category4);
        searchCourses = view.findViewById(R.id.search);
//        list_category = view.findViewById(R.id.list_category);
        list_corses = view.findViewById(R.id.courses_list);
        try {
//            getAllCategoty();
            getUser();
            getAllCourses();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.name_category1) {
                    if(click==0) {
                        coursesAdapters.filter(1);
                        click++;
                    } else {
                        coursesAdapters.restore_list();
                        click = 0;
                    }
                } else {
                    if (view.getId() == R.id.name_category2) {
                        if(click==0) {
                            coursesAdapters.filter(4);
                            click++;
                        } else {
                            coursesAdapters.restore_list();
                            click = 0;
                        }
                     } else {
                          if (view.getId() == R.id.name_category3) {
                              if(click==0) {
                                  coursesAdapters.filter(2);
                                  click++;
                              } else {
                                  coursesAdapters.restore_list();
                                  click = 0;
                              }
                              } else {
                                 if (view.getId() == R.id.name_category4) {
                                     if(click==0) {
                                         coursesAdapters.filter(3);
                                         click++;
                                     } else {
                                         coursesAdapters.restore_list();
                                         click = 0;
                                     }
                          }

                     }
                  }
                }
            }
        };
        categoryCSS.setOnClickListener (onClickListener);
        categoryUX.setOnClickListener (onClickListener);
        categoryhtml.setOnClickListener (onClickListener);
        categoryUI.setOnClickListener (onClickListener);
        searchCourses.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
            intent = new Intent(FirstFragment.this.getActivity(), SearchActivity.class);
            startActivity(intent);
            }

        });
        onRecyclerViewItemClickListener = new CoursesAdapters.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(Courses courses, int position) {
                CoursesStatic.setId(courses.getId());
                CoursesStatic.setName(courses.getName());
                CoursesStatic.setDescription(courses.getDescription());
                CoursesStatic.setDuration(courses.getDuration());
                CoursesStatic.setTitle(courses.getTitle());
                CoursesStatic.setCover_name(courses.getCover_name());
                CoursesStatic.setPrice(courses.getPrice());
                intent = new Intent(FirstFragment.this.getActivity(), CoursesDetailActivity.class);
                startActivity(intent);
            }
        };
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
                coursesList = gson.fromJson(responseBody, type);
                            coursesAdapters = new CoursesAdapters(getContext(), coursesList, onRecyclerViewItemClickListener);
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

    private void getUser() throws IOException {
        supabaseClient.fetchUser(new SupabaseClient.SBC_Callback() {
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
                        Type type = new TypeToken<List<Profile>>() {
                        }.getType();
                        List<Profile> profiles = gson.fromJson(responseBody, type);
                        if (profiles != null && !profiles.isEmpty()) {
                            Profile profile = profiles.get(0);
                            name_user.setText(profile.getUsername());
                        }
                    }
                });
            }
        });
    }
}