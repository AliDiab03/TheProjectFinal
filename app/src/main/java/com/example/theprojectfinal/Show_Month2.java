package com.example.theprojectfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.Adapter.Adapter_Show_Day_Month;
import com.example.dataAdmin.DatabaseHelper;

public class Show_Month2 extends AppCompatActivity {
    DatabaseHelper databaseHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_month2);

        Intent intent = getIntent();
        String monthName = intent.getStringExtra("monthName");
        int subjectId = intent.getIntExtra("subjectId", 0);
        TextView textViewName = findViewById(R.id.tvSeeMonth);
        TextView txShowTotalMonth  = findViewById(R.id.txShowTotalMonth);
        textViewName.setText(monthName);
         databaseHelper = new DatabaseHelper(this);

        String[] days = getDaysOfMonth(monthName);
        RecyclerView rcSeeDayMonth = findViewById(R.id.rcSeeDayMonth);

        Adapter_Show_Day_Month adapter_show_day_month = new Adapter_Show_Day_Month(getApplicationContext(), days, new Adapter_Show_Day_Month.itemClick() {
            @Override
            public void isClicked(int position) {
                Intent intent1 = new Intent(Show_Month2.this, Students.class);
                intent1.putExtra("monthName", monthName);
                intent1.putExtra("dayName", days[position]);
                intent1.putExtra("subjectId", subjectId);
                startActivity(intent1);
            }
        });
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 5, RecyclerView.VERTICAL, false);
        rcSeeDayMonth.setLayoutManager(layoutManager);
        rcSeeDayMonth.setAdapter(adapter_show_day_month);


        float attendancePercentage = calculateAttendancePercentage(subjectId, monthName);
        TextView textViewPercentage = findViewById(R.id.txShowTotalMonth);
        textViewPercentage.setText(String.format("%.2f%%", attendancePercentage));


    }

    private float calculateAttendancePercentage(int subjectId, String monthName) {
        int totalDays = getDaysOfMonth(monthName).length;
        int attendedDays = databaseHelper.getAttendedDays(subjectId, monthName);
        float attendancePercentage = ((float) attendedDays / totalDays) * 100;

        if (attendancePercentage > 100.0f) {
            attendancePercentage = 100.0f;
        }
        return attendancePercentage;
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