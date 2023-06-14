package com.example.theprojectfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Adapter.Adapter_AllStudent_Home;
import com.example.dataAdmin.DatabaseHelper;
import com.example.dataAdmin.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class All_Students extends AppCompatActivity {
    Adapter_AllStudent_Home allStudentHome;
    RecyclerView rcAllStudent;
    EditText edSearch;
    ArrayList<Student> students;
    ArrayList<Student> filteredStudents; // قائمة الطلاب المصفاة بناءً على نتائج البحث
    DatabaseHelper databaseHelper;
    boolean isSortByName = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_students);

        rcAllStudent = findViewById(R.id.rcAllStudent);
        edSearch = findViewById(R.id.edSearch);
        databaseHelper = new DatabaseHelper(this); // تعريف كائن لقاعدة البانات

        students = databaseHelper.getStudents(); // تعريف اري لست من نوع Student بقوم باستعلام الطلاب الموجودين بالقاعدة
        filteredStudents = new ArrayList<>(students); // تهيئة القائمة المصفاة لتحتوي على جميع الطلاب في البداية

        allStudentHome = new Adapter_AllStudent_Home(All_Students.this, filteredStudents, new Adapter_AllStudent_Home.isClicked() {
            @Override
            public void isDeleted(int id, int position) {
                if (databaseHelper.deleteStudent(String.valueOf(id))) { // حذف الطالب على حسب المعرف الخاص به
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
                        student.getLastName().toLowerCase().contains(searchQuery) || student.getFullName().toLowerCase().contains(searchQuery.toLowerCase()) || student.getFullName().equalsIgnoreCase(searchQuery)) {
                    filteredStudents.add(student); // يتم إضافة الطلاب الذين يطابقون سلسلة البحث إلى القائمة المصفاة
                }
            }
        }

        allStudentHome.notifyDataSetChanged(); // يتم إعلام الـ Adapter بتغييرات البيانات
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_students, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuFilterName:
                isSortByName = !isSortByName; // تبديل حالة الفرز بين تصاعدي وتنازلي
                sortStudentsByName(); // استدعاء طريقة الفرز حسب الاسم
                return true;
            case R.id.menuFilterDate:
                Toast.makeText(this, "فرز حسب تاريخ الاضافة", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sortStudentsByName() {
        if (isSortByName) {
            // قائمة مؤقتة للاحتفاظ بالطلاب قبل الفرز
            ArrayList<Student> tempStudents = new ArrayList<>(filteredStudents);

            // تنفيذ عملية الفرز حسب الاسم
            Collections.sort(tempStudents, new Comparator<Student>() {
                @Override
                public int compare(Student student1, Student student2) {
                    return student1.getFullName().compareToIgnoreCase(student2.getFullName());
                }
            });

            filteredStudents.clear();
            filteredStudents.addAll(tempStudents);
            allStudentHome.notifyDataSetChanged(); // يتم إعلام الـ Adapter بالتغييرات في البيانات
        } else {
            // إعادة تهيئة القائمة المصفاة لتحتوي على ا لطلاب بنفس ترتيب البداية
            filteredStudents.clear();
            filteredStudents.addAll(students);
            allStudentHome.notifyDataSetChanged(); // يتم إعلام الـ Adapter بالتغييرات في البيانات
        }
    }




}
