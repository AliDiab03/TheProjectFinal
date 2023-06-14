package com.example.theprojectfinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.Adapter.Adapter_Student_Registered;
import com.example.dataAdmin.DatabaseHelper;
import com.example.dataAdmin.Student;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;

public class Students extends AppCompatActivity {
    RecyclerView rvAllSujStd;
    DatabaseHelper databaseHelper;
    Button attendanceBtn;
    int subjectId;
    String monthName, dayName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        rvAllSujStd = findViewById(R.id.rvAllSujStd);
        databaseHelper = new DatabaseHelper(this);
        attendanceBtn = findViewById(R.id.attendanceBtn);

        Intent intent = getIntent(); // استقبال البيانات المرسلة
        subjectId = intent.getIntExtra("subjectId", 0);
        monthName = intent.getStringExtra("monthName");
        dayName = intent.getStringExtra("dayName");
        showSubject(subjectId);

        attendanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Student> students = ((Adapter_Student_Registered) rvAllSujStd.getAdapter()).getSelectedStudents();
                if (students.isEmpty()) {
                    // إذا لم يتم اختيار أي طالب، قم بعرض رسالة الخطأ
                    Toast.makeText(Students.this, "يرجى تحديد طالب واحد على الأقل", Toast.LENGTH_SHORT).show();
                    return;
                }


                saveAttendance(students); // دالة تسجيل حضور للطالب
                AlertDialog.Builder builder = new AlertDialog.Builder(Students.this);
                builder.setTitle("تاكيد الحضور");
                builder.setMessage("هل تريد تاكيد حضور للطلاب الذين تم اختيارهم");
                builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish(); // هنا لو ضغط على نعم سيتم تسجيل حضور وانهاء الشاشة
                    }
                });
                builder.setNegativeButton("لا", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setCancelable(false);
                builder.show();

            }
        });
    }

    private void showSubject(int subjectID) {  // دالة لاستعلام الطالب المرتبطين بالمادة
        ArrayList<Student> students = databaseHelper.getStudentsBySubject(subjectID); // تم تعريف اري لست من student لاستعلام الطلاب المخزنين في قاعدة البيانات
        rvAllSujStd.setLayoutManager(new LinearLayoutManager(this));
        Adapter_Student_Registered adapter_students = new Adapter_Student_Registered(this, students); // تهيئة الادابتر للريسايكل فيو
        rvAllSujStd.setAdapter(adapter_students);
    }

    private void saveAttendance(ArrayList<Student> students) { // قمت بتعريف دالة لاضافة الحضور للطالب
        for (Student student : students) { // عمل حلقةة تكرارية للطلاب المخزنين
            databaseHelper.insertPresence(monthName, dayName, student.getId(), subjectId);
        }

    }


}

