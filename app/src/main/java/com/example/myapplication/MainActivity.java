package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent loginActivityIntent = new Intent(getApplicationContext(), OnDemandBookingActivity.class);
        startActivity(loginActivityIntent);
        finish();

        setContentView(R.layout.activity_main);
    }
}