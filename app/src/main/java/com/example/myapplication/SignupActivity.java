package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        TextView signinTextBtn = findViewById(R.id.signin);
        signinTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSignInActivity();
            }
        });

        ImageButton backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        TextView nameView = findViewById(R.id.signup_name);
        TextView emailView = findViewById(R.id.signup_email);
        TextView passwordView = findViewById(R.id.signup_password);
        TextView confirmPasswordView = findViewById(R.id.signup_password1);


        Button signupBtn = findViewById(R.id.signup_btn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFields(nameView, emailView, passwordView, confirmPasswordView)) {
                    String name = nameView.getText().toString();
                    String email = emailView.getText().toString();
                    String password = passwordView.getText().toString();
                    FirebaseAuth.getInstance().
                            createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser firebaseUser = task.getResult().getUser();
                                        if (firebaseUser != null) {
                                            String uid = firebaseUser.getUid();
                                            User user = new User(name, email, password, uid);
                                            uploadUserInfo(user);
                                        }
                                        Toast.makeText(SignupActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), OnDemandBookingActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {

                                        Toast.makeText(SignupActivity.this, "SignUp Failed " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

    }

    private void uploadUserInfo(User user) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("Users");
        Map<String, Object> map = new HashMap<>();
        map.put(user.getUid(), user);
        usersRef.updateChildren(map, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error == null) {
                    Toast.makeText(SignupActivity.this, "UserInfo uploaded", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignupActivity.this, "UserInfo uploading failed : "+error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean validateFields(TextView nameView, TextView emailView, TextView passwordView, TextView confirmPasswordView) {
        boolean isInputValid = true;
        String password = passwordView.getText().toString();
        String confirmPassword = confirmPasswordView.getText().toString();
        String name = passwordView.getText().toString();
        String email = passwordView.getText().toString();
        if (!(password.isEmpty() || confirmPassword.isEmpty()) && !password.equals(confirmPassword)) {
            confirmPasswordView.setError("Didn't match");
            return false;
        }
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill all field correctly", Toast.LENGTH_SHORT).show();
            return false;
        }

        return isInputValid;
    }

    private void launchSignInActivity() {
        Intent signinIntent = new Intent(this, LoginActivity.class);
        startActivity(signinIntent);
        finish();
    }
}