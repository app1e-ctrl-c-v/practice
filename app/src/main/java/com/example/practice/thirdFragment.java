package com.example.practice;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class thirdFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        final Button buttonName = (Button) view.findViewById(R.id.button_setting);
        final Button buttonEmail = (Button) view.findViewById(R.id.button_setting1);
        final Button buttonPassword = (Button) view.findViewById(R.id.button_setting2);
        final Button buttonPinCode = (Button) view.findViewById(R.id.button_setting3);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.button_setting) {
                } else{
                    if(view.getId() == R.id.button_setting1) {
                    } else{
                        if(view.getId() == R.id.button_setting2) {
                            Intent intent = new Intent(thirdFragment.this.getActivity(), ForgotPasswordActivity.class);
                            startActivity(intent);
                        } else{
                            if(view.getId() == R.id.button_setting3) {
                                Intent intent = new Intent(thirdFragment.this.getActivity(), ChangePinCodeActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                }
            }
        };
        buttonName.setOnClickListener (onClickListener);
        buttonEmail.setOnClickListener (onClickListener);
        buttonPassword.setOnClickListener (onClickListener);
        buttonPinCode.setOnClickListener (onClickListener);
        return view;
    }
}