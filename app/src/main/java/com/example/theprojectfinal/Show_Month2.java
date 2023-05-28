package com.example.theprojectfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.Adapter.Adapter_Show_Day_Month;

public class Show_Month2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_month2);

        Intent intent = getIntent();
        String monthName = intent.getStringExtra("monthName");
        TextView textViewName = findViewById(R.id.tvSeeMonth);
        textViewName.setText(monthName);

        String[] days = getDaysOfMonth(monthName);
        RecyclerView rcSeeDayMonth = findViewById(R.id.rcSeeDayMonth);

        Adapter_Show_Day_Month adapter_show_day_month = new Adapter_Show_Day_Month(getApplicationContext(),days);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,5,RecyclerView.VERTICAL,false);
        rcSeeDayMonth.setLayoutManager(layoutManager);
        rcSeeDayMonth.setAdapter(adapter_show_day_month);


    }
    private String[] getDaysOfMonth(String monthName) {
        String[] days;

        if (monthName.equals("يناير")) {
            days = new String[31];
            for (int i = 1; i <= 31; i++) {
                days[i - 1] = String.valueOf(i);
            }
        } else if (monthName.equals("فبراير")) {
            days = new String[28];
            for (int i = 1; i <= 28; i++) {
                days[i - 1] = String.valueOf(i);
            }
        } else if (monthName.equals("مارس")) {
            days = new String[31];
            for (int i = 1; i <= 31; i++) {
                days[i - 1] = String.valueOf(i);
            }
        } else if (monthName.equals("أبريل")) {
            days = new String[30];
            for (int i = 1; i <= 30; i++) {
                days[i - 1] = String.valueOf(i);
            }
        } else if (monthName.equals("مايو")) {
            days = new String[31];
            for (int i = 1; i <= 31; i++) {
                days[i - 1] = String.valueOf(i);
            }
        } else if (monthName.equals("يونيو")) {
            days = new String[30];
            for (int i = 1; i <= 30; i++) {
                days[i - 1] = String.valueOf(i);
            }
        } else if (monthName.equals("يوليو")) {
            days = new String[31];
            for (int i = 1; i <= 31; i++) {
                days[i - 1] = String.valueOf(i);
            }
        } else if (monthName.equals("أغسطس")) {
            days = new String[31];
            for (int i = 1; i <= 31; i++) {
                days[i - 1] = String.valueOf(i);
            }
        } else if (monthName.equals("سبتمبر")) {
            days = new String[30];
            for (int i = 1; i <= 30; i++) {
                days[i - 1] = String.valueOf(i);
            }
        } else if (monthName.equals("أكتوبر")) {
            days = new String[31];
            for (int i = 1; i <= 31; i++) {
                days[i - 1] = String.valueOf(i);
            }
        } else if (monthName.equals("نوفمبر")) {
            days = new String[30];
            for (int i = 1; i <= 30; i++) {
                days[i - 1] = String.valueOf(i);
            }
        } else if (monthName.equals("ديسمبر")) {
            days = new String[31];
            for (int i = 1; i <= 31; i++) {
                days[i - 1] = String.valueOf(i);
            }
        } else {
            days = new String[0]; // شهر غير صالح
        }

        return days;
    }


}