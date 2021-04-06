package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.testapp.application.HomeApplication;
import com.example.testapp.security.JwtSecurityService;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prifile);

        JwtSecurityService jwtService = (JwtSecurityService)HomeApplication.getInstance();

        TextView tv = findViewById(R.id.textView2);
        tv.setText(jwtService.getToken());
    }
}