package com.example.theprojectfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.Adapter.Adapter_Month;

public class Month extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);

        Intent intent = getIntent();
        String subjectName = intent.getStringExtra("subjectName");

        // عرض البيانات المستلمة في واجهة المستخدم

        TextView textViewName = findViewById(R.id.txTitleSubject);
        textViewName.setText(subjectName);



    }
}