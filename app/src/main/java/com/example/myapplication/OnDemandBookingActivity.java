package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapters.GuestsAdapter;

import java.util.List;

public class OnDemandBookingActivity extends AppCompatActivity implements GuestsListListner {

    TextView guestsCountView;
    ListView guestsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_demand_booking);
        Toolbar toolbar = findViewById(R.id.toolbar);

        guestsCountView = findViewById(R.id.odm_guests_count);
        AutoCompleteTextView genderView = findViewById(R.id.odm_gender);
        genderView.setThreshold(1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new String[]{"Male", "Female"});
        genderView.setAdapter(adapter);

        genderView.setOnClickListener(b -> genderView.showDropDown());
        genderView.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                genderView.showDropDown();
            }
        });

        AutoCompleteTextView languageView = findViewById(R.id.odm_language);
        languageView.setThreshold(1);
        ArrayAdapter<String> languageAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Utils.languages);
        languageView.setAdapter(languageAdapter);
        languageView.setOnClickListener(b -> languageView.showDropDown());
        languageView.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
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
            if (hasFocus) {
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

        View view = this.getLayoutInflater().inflate(R.layout.add_guest_dialog_layout, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(OnDemandBookingActivity.this, R.style.dialogStyle);
        builder.setView(view);

        AlertDialog dialog = builder.create();

        addGuestView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//                dialog.getWindow().setDimAmount(0.88f);
                dialog.show();

            }
        });
        GuestsAdapter guestsAdapter = new GuestsAdapter(this);
        TextView guestNumberTextView = view.findViewById(R.id.dialog_guest_number);

        Button cancelBtn = view.findViewById(R.id.dialog_cancel_btn);
        cancelBtn.setOnClickListener(v -> {
            dialog.dismiss();
            guestNumberTextView.setText("");
        });

        Button submitBtn = view.findViewById(R.id.dialog_submit_btn);

        submitBtn.setOnClickListener(v -> onSubmitButtonPressed(guestNumberTextView, guestsAdapter, dialog));


        guestsListView = findViewById(R.id.odm_listView);
//        for (int i = 0; i < 10; i++) {
//            guestsAdapter.addGuest("" + i);
//
//        }
        guestsAdapter.notifyDataSetChanged();
        guestsListView.setAdapter(guestsAdapter);

        Utils.getListViewSize(guestsListView);


    }

    private void onSubmitButtonPressed(TextView guestNumberTextView, GuestsAdapter adapter, AlertDialog dialog) {
        String guestNumber = guestNumberTextView.getText().toString();
        if (!guestNumber.isEmpty()) {

            if (adapter.getCount() > 4) {
                Toast.makeText(this, "Only 5 guests allowed", Toast.LENGTH_SHORT).show();
                return;

            }

            adapter.addGuest(guestNumber);
            adapter.notifyDataSetChanged();

            guestNumberTextView.setText("");
            dialog.dismiss();
        }


    }


    @Override
    public void onListSizeChange(int newSize) {
        guestsCountView.setText(newSize + "/5");
        Utils.getListViewSize(guestsListView);

    }

}