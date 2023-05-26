package com.example.theprojectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dataAdmin.Admin;
import com.example.dataAdmin.DatabaseHelper;

public class EditUser extends AppCompatActivity {
    EditText edTextEditEmailUserName , edTextEditEmail ,edTextEditPassword ;
    Button btnEdit ;
    Admin admin ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        edTextEditEmailUserName = findViewById(R.id.edTextEditEmailUserName);
        edTextEditEmail = findViewById(R.id.edTextEditEmail);
        edTextEditPassword = findViewById(R.id.edTextEditPassword);
        btnEdit = findViewById(R.id.btnEdit);


        // عرض البيانات في الحقول التحريرية هنا
        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        String email = intent.getStringExtra("email");
        edTextEditEmailUserName.setText(userName);
        edTextEditEmail.setText(email);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUserName = edTextEditEmailUserName.getText().toString().trim();
                String newEmail = edTextEditEmail.getText().toString().trim();
                String newPassword = edTextEditPassword.getText().toString().trim();




                    ///////////////////////////////////////////////////////////////////////////////////////

                    boolean isClicked = true;



                    if (newUserName.isEmpty()){
                        edTextEditEmailUserName.setError("الرجاء تعبئة اسم المستخدم");
                        isClicked = false ;
                    }else if (newUserName.length()<=3){
                        edTextEditEmailUserName.setError("الرجاء ادخال اسم مستخدم صالح");
                        isClicked = false ;
                    }if (newEmail.isEmpty()){
                        edTextEditEmail.setError("الرحاء تعبئة البريد الالكتروني");
                        isClicked = false ;
                    }else if (!Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()){
                        edTextEditEmail.setError("الرجاء ادخال بريد صالج");
                        isClicked = false ;
                    }if (newPassword.isEmpty()){
                        edTextEditPassword.setError("الرجاء تعبئة كلمة مرور");
                        isClicked = false ;
                    }else if (newPassword.length()<=5){
                        edTextEditPassword.setError("الرحاء ادخال كلمة مرور صالحة");
                        isClicked = false ;
                    }


                    if (isClicked){

                        // قم بتحديث بيانات الـ Admin في قاعدة البيانات
                        DatabaseHelper databaseHelper = new DatabaseHelper(EditUser.this);
                        Admin admin = databaseHelper.getAdmin();
                        if (admin != null) {
                            admin.setUserName(newUserName);
                            admin.setEmail(newEmail);
                            admin.setPassword(newPassword);

                            boolean isUpdated = databaseHelper.updateAdmin(admin);
                            if (isUpdated) {
                                // عرض رسالة تأكيد التعديل وإغلاق الصفحة
                                Toast.makeText(EditUser.this, "تم تحديث البيانات بنجاح", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                // عرض رسالة خطأ في حالة عدم تمكن من تحديث البيانات
                                Toast.makeText(EditUser.this, "حدث خطأ أثناء تحديث البيانات", Toast.LENGTH_SHORT).show();
                            }

                    }



                }
            }
        });

    }




}
