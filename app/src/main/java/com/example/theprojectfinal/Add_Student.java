package com.example.theprojectfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Adapter.Adapter_Subject_Add_Student;
import com.example.dataAdmin.DatabaseHelper;
import com.example.dataAdmin.Subject;

import java.util.ArrayList;
import java.util.Calendar;

public class Add_Student extends AppCompatActivity {

    // تعريفهم عالميا كي اصلهم من اي مكان
    EditText edTextFirstName, edTextLastName;
    Button btnAddStudent;
    RecyclerView rcSubject1;
    int year, month, day;
    TextView edTextDateOfBirth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);


        edTextFirstName = findViewById(R.id.edTextFirstName);
        edTextLastName = findViewById(R.id.edTextLastName);
        edTextDateOfBirth = findViewById(R.id.edTextDateOfBirth);
        btnAddStudent = findViewById(R.id.btnAddStudent);
        rcSubject1 = findViewById(R.id.rcSubject1);
        DatabaseHelper databaseHelper = new DatabaseHelper(this); // تعريف قاعدة البيانات اللي حعمل علسها

        ArrayList<Subject> subjects = databaseHelper.getSubjects(); // تعريف اري لست من نوع Subject وبضيف لها من قاعدة بيانات استعلام للمواد المخزنة


        Adapter_Subject_Add_Student adapterSubjectAddStudent = new Adapter_Subject_Add_Student(this, subjects, new Adapter_Subject_Add_Student.isClicked() { // تعريف مهيئ الريسايكل
            @Override
            public void onCheckBoxClick(int position) {
                Toast.makeText(Add_Student.this, ""+subjects.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        rcSubject1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)); // شكل تصميم الريسايكل
        rcSubject1.setAdapter(adapterSubjectAddStudent); // عرض المهيئ بالريسايكل ليتم عرض المعلومات فيه


        edTextDateOfBirth.setOnClickListener(new View.OnClickListener() { // هنا لو ضغط عليها بيفتح لي picker التاريح
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Add_Student.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                TextView selectedDateTextView = findViewById(R.id.edTextDateOfBirth);
                                selectedDateTextView.setText(String.format("%d/%d/%d", year, month + 1, dayOfMonth));
                                // Do something with the selected date
                            }
                        },
                        year, month, day);
                datePickerDialog.show();


            }
        });


        btnAddStudent.setOnClickListener(new View.OnClickListener() { // اعطيت للزر وظيفة النقر
            @Override
            public void onClick(View v) {

                String firstName = edTextFirstName.getText().toString(); // استخراج البيانات من الايديت وتحويلها كنص
                String lastName = edTextLastName.getText().toString();// استخراج البيانات من الايديت وتحويلها كنص
                String date = edTextDateOfBirth.getText().toString();// استخراج البيانات من الايديت وتحويلها كنص


                boolean isClickAddStd = true;

                if (firstName.isEmpty()) {
                    edTextFirstName.setError("الرجاء ادخال الاسم الاول للطالب");
                    isClickAddStd = false;
                } else if (firstName.length() >= 20) {
                    edTextFirstName.setError("الرجاء ادخال اسم طالب صالح");
                    isClickAddStd = false;
                }
                if (lastName.isEmpty()) {
                    edTextLastName.setError("الرجاء ادخال اسم الاخير للطالب");
                    isClickAddStd = false;
                } else if (lastName.length() >= 20) {
                    edTextLastName.setError("الرجاء ادخال الاسم الاخير بشكل صحيح");
                    isClickAddStd = false;
                }
                if (date.isEmpty()) {
                    edTextDateOfBirth.setError("الرجاء ادخال تاريخ الميلاد");
                    isClickAddStd = false;
                }

                // الاكواد التالية تتعامل مع اضافة طالب جديد في قاعد البيانات
                if (isClickAddStd) { // اذا كانت الققيمة true ينفذ الاوامر التالية
                    DatabaseHelper databaseHelper = new DatabaseHelper(Add_Student.this); // انشاء كائنن لقاعدة بيانات
                    long studentId = databaseHelper.insertStudent(firstName, lastName, date); // يتم استدعاء الكائن وتمرير insert لاضافة معلومات الطالب
                    if (studentId > 0) { // هنا يوجد شرط لة كان معرف الطالب اكبر من صفر فالعملية تمت بنجاح
                        ArrayList<Subject> selectedSubjects = new ArrayList<>(); // يتم تعريف اري لست لتخزين المواد المحددة
                        for (Subject subject : adapterSubjectAddStudent.getDataSubject()) { // يتم عمل حلقة تكرارية للمواد المعروضة عن طريق الادابتر
                            if (subject.isChecked()) { // يوجد شرط اذا حدد مادة
                                selectedSubjects.add(subject); //يتم اضافة المادة المحددة في قاعدى بيانات المادة
                            }
                        }

                        for (Subject subject : selectedSubjects) { // حلقة تكرارية للمواد المخزنة في جدول المادة
                            boolean isSuccess = databaseHelper.insertStudent_Subject((int) studentId, subject.getId()); // استرداد كائن قاعدة بيانات ويتم فيها اضافة معرف الطالب والمادة في الجدول الوسيط
                            if (!isSuccess) { // لو لم يتم الاضافة يظهر الشرط
                                // حدث خطأ في تسجيل المادة للطالب
                                Toast.makeText(Add_Student.this, "حدث خطأ في تسجيل المادة للطالب", Toast.LENGTH_SHORT).show();
                            }
                        }

                        Toast.makeText(Add_Student.this, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(Add_Student.this, "فشل اضافة طالب", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });


    }
}