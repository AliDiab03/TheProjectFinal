package com.example.theprojectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Add_student_1 extends AppCompatActivity {

    TextView tvFullName ,tvDateOfBirth ,tvAge ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student1);

        tvFullName = findViewById(R.id.tvFullName);
        tvDateOfBirth = findViewById(R.id.tvDateOfBirth);
        tvAge = findViewById(R.id.tvAge);


        Intent intent = getIntent();
        if (intent != null) {
            int id = intent.getIntExtra("id", 0);
            String firstName = intent.getStringExtra("firstName");
            String lastName = intent.getStringExtra("lastName");
            String birthdate = intent.getStringExtra("birthdate");

            tvFullName.setText(firstName + " " + lastName);
            tvDateOfBirth.setText(birthdate);


      // لا تنسى دالة حساب العنر

        }}


}