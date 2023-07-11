package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView signup = findViewById(R.id.signup);
        Button singinBtn = findViewById(R.id.signin_btn);
        TextView emailView = findViewById(R.id.login_email);
        TextView passwordView = findViewById(R.id.login_password);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSignUpActivity();
            }
        });

        singinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    private void signIn() {

        Intent intent = new Intent(this, OnDemandBookingActivity.class);
        startActivity(intent);
    }

    private void launchSignUpActivity() {
        Intent signupIntent = new Intent(this, SignupActivity.class);
        startActivity(signupIntent);
    }
}