package com.example.practice;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.practice.Models.DataBinding;
import com.example.practice.Models.Profile;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Quota;

public class thirdFragment extends Fragment {
SupabaseClient supabaseClient = new SupabaseClient();
    TextView textName, textEmail, textPassword, textPinCode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        Button buttonName = (Button) view.findViewById(R.id.button_setting);
        Button buttonEmail = (Button) view.findViewById(R.id.button_setting1);
        Button buttonPassword = (Button) view.findViewById(R.id.button_setting2);
        Button buttonPinCode = (Button) view.findViewById(R.id.button_setting3);
        textName = view.findViewById(R.id.text_description);
        textEmail = view.findViewById(R.id.text_description1);
        textPassword = view.findViewById(R.id.text_description2);
        textPinCode = view.findViewById(R.id.text_description3);
        try {
            getUser();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.button_setting3) {
                    Intent intent = new Intent(thirdFragment.this.getActivity(), ChangePinCodeActivity.class);
                    startActivity(intent);
               }
            }
        };
        buttonName.setOnClickListener (onClickListener);
        buttonEmail.setOnClickListener (onClickListener);
        buttonPassword.setOnClickListener (onClickListener);
        buttonPinCode.setOnClickListener (onClickListener);
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

            @RequiresApi(api = Build.VERSION_CODES.O)
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
                            textName.setText(profile.getUsername());
                            textEmail.setText(Profile.getEmail());
                        }
                    }
                });
            }
        });
    }
}