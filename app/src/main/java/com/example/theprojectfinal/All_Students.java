package com.example.theprojectfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.Adapter.Adapter_AllStudent_Home;
import com.example.dataAdmin.DatabaseHelper;
import com.example.dataAdmin.Student;

import java.util.ArrayList;

public class All_Students extends AppCompatActivity {
    Adapter_AllStudent_Home allStudentHome;
    RecyclerView rcAllStudent;
    EditText edSearch;
    ArrayList<Student> students;
    ArrayList<Student> filteredStudents; // قائمة الطلاب المصفاة بناءً على نتائج البحث
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_students);

        rcAllStudent = findViewById(R.id.rcAllStudent);
        edSearch = findViewById(R.id.edSearch);
        databaseHelper = new DatabaseHelper(this);

        students = databaseHelper.getStudents();
        filteredStudents = new ArrayList<>(students); // تهيئة القائمة المصفاة لتحتوي على جميع الطلاب في البداية

        allStudentHome = new Adapter_AllStudent_Home(All_Students.this, filteredStudents, new Adapter_AllStudent_Home.isClicked() {
            @Override
            public void isDeleted(int id, int position) {
                if (databaseHelper.deleteStudent(String.valueOf(id))) {
                    students.remove(position);
                    filteredStudents.remove(position); // يجب أيضًا حذف الطالب من القائمة المصفاة
                    allStudentHome.notifyItemRemoved(position);
                }
            }

            @Override
            public void isClicked(int id, int position) {
                Intent intent = new Intent(All_Students.this, Add_student_1.class);
                Student student = filteredStudents.get(position); // يجب الحصول على الطالب من القائمة المصفاة
                intent.putExtra("id", student.getId());
                intent.putExtra("firstName", student.getFirstName());
                intent.putExtra("lastName", student.getLastName());
                intent.putExtra("birthdate", student.getBirthdate());
                startActivity(intent);
            }
        });

        rcAllStudent.setLayoutManager(new LinearLayoutManager(this));
        rcAllStudent.setAdapter(allStudentHome);

        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filterStudents(s.toString()); // يتم تنفيذ عملية البحث بعد تغيير النص في الـ EditText
            }
        });
    }

    private void filterStudents(String searchQuery) {
        filteredStudents.clear();

        if (searchQuery.isEmpty()) {
            filteredStudents.addAll(students); // إذا كانت سلسلة البحث فارغة، يتم إضافة جميع الطلاب إلى القائمة المصفاة
        } else {
            searchQuery = searchQuery.toLowerCase();

            for (Student student : students) {
                if (student.getFirstName().toLowerCase().contains(searchQuery) ||
                        student.getLastName().toLowerCase().contains(searchQuery)||student.getFullName().toLowerCase().contains(searchQuery.toLowerCase()) || student.getFullName().equalsIgnoreCase(searchQuery)) {
                    filteredStudents.add(student); // يتم إضافة الطلاب الذين يطابقون سلسلة البحث إلى القائمة المصفاة
                }
            }
        }

        allStudentHome.notifyDataSetChanged(); // يتم إعلام الـ Adapter بتغييرات البيانات
    }
}
