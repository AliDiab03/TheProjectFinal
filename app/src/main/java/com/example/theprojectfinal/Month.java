package com.example.theprojectfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.Adapter.Adapter_Month;

public class Month extends AppCompatActivity {
    RecyclerView rvMonth ;
    String months[] = {"يناير", "فبراير", "مارس", "أبريل", "مايو", "يونيو", "يوليو", "أغسطس", "سبتمبر", "أكتوبر", "نوفمبر", "ديسمبر"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);
        rvMonth =findViewById(R.id.rvMonth);

        Intent intent = getIntent();
        String subjectName = intent.getStringExtra("subjectName");

        // عرض البيانات المستلمة في واجهة المستخدم

        TextView textViewName = findViewById(R.id.txTitleSubject);
        textViewName.setText(subjectName);

Adapter_Month adapter_month = new Adapter_Month(this,months);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvMonth.setLayoutManager(layoutManager);
        rvMonth.setAdapter(adapter_month);



    }
}