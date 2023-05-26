package com.example.theprojectfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Adapter.Adapter_Subject_Add_Student;
import com.example.dataAdmin.DatabaseHelper;
import com.example.dataAdmin.Subject;

import java.util.ArrayList;

public class Add_Student extends AppCompatActivity {

    EditText edTextFirstName ,edTextDateOfBirth , edTextLastName;
    Button btnAddStudent ;
    RecyclerView rcSubject1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);


        edTextFirstName = findViewById(R.id.edTextFirstName);
        edTextLastName = findViewById(R.id.edTextLastName);
        edTextDateOfBirth =findViewById(R.id.edTextDateOfBirth);
        btnAddStudent = findViewById(R.id.btnAddStudent);
        rcSubject1 = findViewById(R.id.rcSubject1);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        ArrayList<Subject>subjects = databaseHelper.getSubjects();

        Adapter_Subject_Add_Student adapterSubjectAddStudent = new Adapter_Subject_Add_Student(this,subjects);
        rcSubject1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcSubject1.setAdapter(adapterSubjectAddStudent);



        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              String firstName =  edTextFirstName.getText().toString();
              String lastName =   edTextLastName.getText().toString();
               String date = edTextDateOfBirth.getText().toString();




               boolean isClickAddStd = true ;

               if (firstName.isEmpty()){
                   edTextFirstName.setError("الرجاء ادخال الاسم الاول للطالب");
                   isClickAddStd = false ;
               }else if (firstName.length()>=10){
                   edTextFirstName.setError("الرجاء ادخال اسم طالب صالح");
                   isClickAddStd = false ;
               }if (lastName.isEmpty()){
                   edTextLastName.setError("الرجاء ادخال اسم الاخير للطالب");
                   isClickAddStd = false ;
                }else if (lastName.length()>=10){
                   edTextLastName.setError("الرجاء ادخال الاسم الاخير بشكل صحيح");
                   isClickAddStd = false ;
                }if (date.isEmpty()){
                   edTextDateOfBirth.setError("الرجاء ادخال تاريخ الميلاد");
                   isClickAddStd = false ;
                }

               if (isClickAddStd){

                   DatabaseHelper databaseHelper = new DatabaseHelper(Add_Student.this);
                   databaseHelper.insertStudent(firstName,lastName,date);
                   Toast.makeText(Add_Student.this, "تم الاضافة بنجاح", Toast.LENGTH_SHORT).show();
               }else {
                   Toast.makeText(Add_Student.this, "فشل اضافة طالب", Toast.LENGTH_SHORT).show();
               }




            }
        });




    }
}