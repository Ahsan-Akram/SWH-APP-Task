package com.example.myapplication.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.GuestsListListner;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GuestsAdapter extends BaseAdapter {
    GuestsListListner guestsListListner;
    List<String> guestsList = new ArrayList<>();

    public GuestsAdapter(GuestsListListner guestsListListner) {
        this.guestsListListner = guestsListListner;
    }


    @Override
    public int getCount() {
        return guestsList.size();
    }

    @Override
    public String getItem(int i) {
        return guestsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.guests_listview_item, viewGroup, false);
        }

        TextView textView = view.findViewById(R.id.guests_textview);
        textView.setText(guestsList.get(i));

        Button removeBtn = view.findViewById(R.id.remove_guest_btn);
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeGuest(i);
                notifyDataSetChanged();
            }
        });

        return view;
    }

    public void addGuest(String guestName) {
        guestsList.add(guestName);
        guestsListListner.onListSizeChange(guestsList.size());
    }

    public void removeGuest(int position) {
        guestsList.remove(position);
        guestsListListner.onListSizeChange(guestsList.size());
    }
}
