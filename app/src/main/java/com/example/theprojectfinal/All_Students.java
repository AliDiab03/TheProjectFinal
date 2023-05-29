package com.example.theprojectfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.Adapter.Adapter_AllStudent_Home;
import com.example.dataAdmin.DatabaseHelper;
import com.example.dataAdmin.Student;

import java.util.ArrayList;

public class All_Students extends AppCompatActivity {
    Adapter_AllStudent_Home allStudentHome;
    RecyclerView rcAllStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_students);

        rcAllStudent = findViewById(R.id.rcAllStudent);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        ArrayList<Student> students = databaseHelper.getStudents();

        allStudentHome = new Adapter_AllStudent_Home(All_Students.this, students, new Adapter_AllStudent_Home.isClicked() {
            @Override
            public void isDeleted(int id, int position) {
                if (databaseHelper.deleteStudent(String.valueOf(id))) {
                    students.remove(position);
                    allStudentHome.notifyItemRemoved(position);
                }
            }
        });

        rcAllStudent.setLayoutManager(new LinearLayoutManager(this));
        rcAllStudent.setAdapter(allStudentHome);
    }
}
