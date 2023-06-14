package com.example.theprojectfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Adapter.Adapter_Subject_Registered;
import com.example.dataAdmin.DatabaseHelper;
import com.example.dataAdmin.Subject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Add_student_1 extends AppCompatActivity {

    TextView tvFullName, tvDateOfBirth, tvAge;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student1);

        tvFullName = findViewById(R.id.tvFullName);
        tvDateOfBirth = findViewById(R.id.tvDateOfBirth);
        tvAge = findViewById(R.id.tvAge);
        databaseHelper = new DatabaseHelper(this);


        Intent intent = getIntent(); // حتى نستقبل البيانات المرسلة
        if (intent != null) {
            int id = intent.getIntExtra("id", 0);
            String firstName = intent.getStringExtra("firstName");
            String lastName = intent.getStringExtra("lastName");
            String birthdate = intent.getStringExtra("birthdate");

            tvFullName.setText(firstName + " " + lastName);
            tvDateOfBirth.setText(birthdate);

            // استدعاء الدالة لجلب المواد المسجلة للطالب وعرضها
            showRegisteredSubjects(id);
        }


    }

    private void showRegisteredSubjects(int studentId) {
        ArrayList<Subject> registeredSubjects = databaseHelper.getSubjectsByStudent(studentId); // تعريف اري لست لاستعلام الموادالمسجلة للطالب

        RecyclerView recyclerView = findViewById(R.id.rvStdSignSubj);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // لاضافة شكل التصميم
        Adapter_Subject_Registered adapter = new Adapter_Subject_Registered(Add_student_1.this, registeredSubjects , studentId );//تهيئة الريسايكل

        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_students,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
          case   R.id.menuFilterName:
              return true;

            case R.id.menuFilterDate:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}