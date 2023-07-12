package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OnDemandBookingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_demand_booking);
        Toolbar toolbar = findViewById(R.id.toolbar);

        AutoCompleteTextView genderView = findViewById(R.id.odm_gender);
        genderView.setThreshold(1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new String[]{"Male", "Female"});
        genderView.setAdapter(adapter);
        genderView.setOnClickListener(b -> genderView.showDropDown());
        genderView.setOnFocusChangeListener((view, hasFocus) -> {
            if(hasFocus){
                genderView.showDropDown();
            }
        });

        AutoCompleteTextView languageView = findViewById(R.id.odm_language);
        languageView.setThreshold(1);
        ArrayAdapter<String> languageAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Utils.languages);
        languageView.setAdapter(languageAdapter);
        languageView.setOnClickListener(b -> languageView.showDropDown());
        languageView.setOnFocusChangeListener((view, hasFocus) -> {
            if(hasFocus){
                languageView.showDropDown();
            }
        });

        EditText serviceUserNumberView = findViewById(R.id.odm_service_user_number);


        AutoCompleteTextView briefTimeView = findViewById(R.id.odm_brief_time);
        briefTimeView.setThreshold(1);
        ArrayAdapter<String> briefTimeAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Utils.briefTime);
        briefTimeView.setAdapter(briefTimeAdapter);
        briefTimeView.setOnClickListener(b -> briefTimeView.showDropDown());
        briefTimeView.setOnFocusChangeListener((view, hasFocus) -> {
            if(hasFocus){
                briefTimeView.showDropDown();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        TextView addGuestView = findViewById(R.id.toolbar_title1);
        addGuestView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(OnDemandBookingActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }


}