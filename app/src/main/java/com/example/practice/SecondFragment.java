package com.example.practice;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.practice.Adapters.CategoryAdapter;
import com.example.practice.Models.Category;
import com.example.practice.Models.Profile;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class SecondFragment extends Fragment {
    TextView textLogOut;
    Button buttonCancel, buttonCourses, buttonSaved, buttonPayment;
    ImageView avatar;
    SupabaseClient supabaseClient = new SupabaseClient();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        textLogOut = view.findViewById(R.id.log_out_text);
        buttonCancel = view.findViewById(R.id.button_cancel);
        buttonCourses = view.findViewById(R.id.button_courses_user);
        buttonPayment = view.findViewById(R.id.button_payment_user);
        buttonSaved = view.findViewById(R.id.button_saved_user);
        avatar = view.findViewById(R.id.avatar_icon);
        try {
            getUser();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.log_out_text) {
                    try {
                        LogOut();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Intent intent = new Intent(SecondFragment.this.getActivity(), SingInActivity.class);
                    startActivity(intent);
                } else {
                    if(view.getId() == R.id.button_cancel) {
                        Intent intent = new Intent(SecondFragment.this.getActivity(), FirstFragment.class);
                        startActivity(intent);
                    } else {
                        if(view.getId() == R.id.button_courses_user) {
                            Intent intent = new Intent(SecondFragment.this.getActivity(), UserCoursesActivity.class);
                            startActivity(intent);
                        } else {
                            if(view.getId() == R.id.button_payment_user) {
                                Intent intent = new Intent(SecondFragment.this.getActivity(), PaymentActivity.class);
                                startActivity(intent);
                            } else {
                                if(view.getId() == R.id.button_saved_user) {
                                    Intent intent = new Intent(SecondFragment.this.getActivity(), SaveCoursesActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }
                    }
                }
            }
        };
        textLogOut.setOnClickListener (onClickListener);
        return view;
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
                            String url = "https://psziqhddgczahqmabysn.supabase.co/storage/v1/object/sign/avatars/";
                            Glide.with(getContext())
                                    .load(url + profile.getAvatar_url())
                                    .placeholder(R.drawable.illustration)
                                    .error(R.drawable.illustration5)
                                    .into(avatar);
                        }
                    }
                });
            }
        });
    }

    private void LogOut() throws IOException {
        supabaseClient.fetchLogOut(new SupabaseClient.SBC_Callback() {
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
                });
            }
        });
    }
}