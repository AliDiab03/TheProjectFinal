package com.example.theprojectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dataAdmin.DatabaseHelper;

public class Add_Subject extends AppCompatActivity {
    EditText edTextNameSubject;
    Button btnAddSubject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        edTextNameSubject = findViewById(R.id.edTextNameSubject);
        btnAddSubject = findViewById(R.id.btnAddSubject);


        DatabaseHelper databaseHelper = new DatabaseHelper(this); // تعريف كائن لقاعدة البيانات


        btnAddSubject.setOnClickListener(new View.OnClickListener() { // اعطي حاصية النقر على الزر
            @Override
            public void onClick(View v) {

                boolean isClickSub = true ;

              String nameSubject =  edTextNameSubject.getText().toString(); // استخراج القيمة الموجودة داخل الايديت وتحويلها الى نص وتخزيتها كنص


            if (nameSubject.isEmpty()){
                edTextNameSubject.setError("الرجاء ادخال مادة");
                isClickSub = false ;
            }

            if (isClickSub){ // التحقق انه القيمة تكون true
                boolean isSuccessInsertSubject =  databaseHelper.insertSubject(nameSubject); // استرداد من قاعدة البيانات لادخال المادة في  القاعدة وتخزينها في boolean
                if (isSuccessInsertSubject){ // التحقق من انه القيمة تم ادخالها بنجاح
                    finish(); // يتم انهاء الواجهة بعد الاضافة
                }else { // غير ذلك
                    Toast.makeText(Add_Subject.this, "لقد حدث خطا ما", Toast.LENGTH_SHORT).show();
                }
            }





            }
        });


    }
}