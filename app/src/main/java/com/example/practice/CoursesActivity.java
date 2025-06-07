package com.example.practice;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CoursesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        Fragment firstFragment = new FirstFragment();
        Fragment secondFragment = new SecondFragment();
        Fragment thirdFragment = new thirdFragment();

        setCurrentFragment(firstFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if(item.getItemId() == R.id.courses){
                setCurrentFragment(firstFragment);
            } else {
                if(item.getItemId() == R.id.profile)
                {
                    setCurrentFragment(secondFragment);
                } else {
                    if(item.getItemId() == R.id.setting){
                        setCurrentFragment(thirdFragment);
                    }
                }
            }
            return true;
        });
    }

    private void setCurrentFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flFragment, fragment)
                .commit();
    }
}