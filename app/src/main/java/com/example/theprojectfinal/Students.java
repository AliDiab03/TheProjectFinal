package com.example.theprojectfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.Adapter.Adapter_students;
import com.example.dataAdmin.DatabaseHelper;
import com.example.dataAdmin.Student;

import java.util.ArrayList;

public class Students extends AppCompatActivity {
    RecyclerView rvAllSujStd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        rvAllSujStd = findViewById(R.id.rvAllSujStd);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);






    }
}