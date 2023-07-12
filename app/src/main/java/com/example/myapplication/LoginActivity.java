package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
                String email = emailView.getText().toString();
                String password = passwordView.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    return;
                }
                signIn(email, password);
            }
        });
    }

    private void signIn(String email, String password) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Signin Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), OnDemandBookingActivity.class);
                            startActivity(intent);
                            finish();
                        } else {

                            Toast.makeText(LoginActivity.this, "Signin Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void launchSignUpActivity() {
        Intent signupIntent = new Intent(this, SignupActivity.class);
        startActivity(signupIntent);
    }
}