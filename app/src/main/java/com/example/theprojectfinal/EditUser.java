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
    EditText edTextEditEmailUserName, edTextEditEmail, edTextEditPassword;
    Button btnEdit;
    Admin admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_user);
        edTextEditEmailUserName = findViewById(R.id.edTextEditEmailUserName);
        edTextEditEmail = findViewById(R.id.edTextEditEmail);
        edTextEditPassword = findViewById(R.id.edTextEditPassword);
        btnEdit = findViewById(R.id.btnEdit);


        // عرض البيانات في الحقول التحريرية هنا
        Intent intent = getIntent(); // يتم استخدامها لاستدعاء الانتينت  والبيانات المرفقة بالانتينت
        String userName = intent.getStringExtra("userName"); // تم استرجاع قيمة اليوزر وتخزينه
        String email = intent.getStringExtra("email"); // نتم استرجاع قيمى الايميل وتخزينه
        String password = intent.getStringExtra("password"); // تم استرجاع قيمة الباسورد وتخزينه
        edTextEditEmailUserName.setText(userName); // يتم تعين نص بالايديت اليوزر
        edTextEditEmail.setText(email); // يتم تعين نص بالايديت الايمييل
        edTextEditPassword.setText(password); // يتم تعين نص بالايديت الباسوررد

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUserName = edTextEditEmailUserName.getText().toString().trim(); //   الايديت تيكس اللي بداخله يتم جلبه وتحويله كنص والtrim تقوم بازالة المسافات
                String newEmail = edTextEditEmail.getText().toString().trim(); //   الايديت تيكس اللي بداخله يتم جلبه وتحويله كنص والtrim تقوم بازالة المسافات
                String newPassword = edTextEditPassword.getText().toString().trim(); //   الايديت تيكس اللي بداخله يتم جلبه وتحويله كنص والtrim تقوم بازالة المسافات


                ///////////////////////////////////////////////////////////////////////////////////////

                boolean isClicked = true; // قيمة اختيارية استخدمتها حتى اذا كان في عنصر ناقص لا يسمح بعمل وظيفة


                if (newUserName.isEmpty()) {
                    edTextEditEmailUserName.setError("الرجاء تعبئة اسم المستخدم");
                    isClicked = false;
                } else if (newUserName.length() <= 3) {
                    edTextEditEmailUserName.setError("الرجاء ادخال اسم مستخدم صالح");
                    isClicked = false;
                }
                if (newEmail.isEmpty()) {
                    edTextEditEmail.setError("الرحاء تعبئة البريد الالكتروني");
                    isClicked = false;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()) {
                    edTextEditEmail.setError("الرجاء ادخال بريد صالج");
                    isClicked = false;
                }
                if (newPassword.isEmpty()) {
                    edTextEditPassword.setError("الرجاء تعبئة كلمة مرور");
                    isClicked = false;
                } else if (newPassword.length() <= 5) {
                    edTextEditPassword.setError("الرحاء ادخال كلمة مرور صالحة");
                    isClicked = false;
                }


                if (isClicked) { // هون لو كانت جميع الاوامر true ينفذ لي الشرط

                    // قم بتحديث بيانات الـ Admin في قاعدة البيانات
                    DatabaseHelper databaseHelper = new DatabaseHelper(EditUser.this); // تعريف كائن لقاعدة البيانات
                    Admin admin = databaseHelper.getAdmin(); // تعريف كائن من نوع الادمن واستعلام عن قاعدة البيانات الادمن
                    if (admin != null) { // لو كان الادمن ليس فارغا يعمل لي الشرط
                        admin.setUserName(newUserName); // يتم تخزين قيمة الادمن الجديدة في اليوزر
                        admin.setEmail(newEmail); // يتم تخزين قيمة الايميل الجديدة
                        admin.setPassword(newPassword); // يتم تخزين الباسورد الجديدة
                        // بعد ان يخزن القيم بالادمن
                        boolean isUpdated = databaseHelper.updateAdmin(admin); // بعمل استدعء لتحديث بيانات الادمن وبخزنه بنوع boolean
                        if (isUpdated) { // هنا يتم فحص التحديث اذا كان ناجح يعرص لي رسالة بانه العملية نجحت
                            // عرض رسالة تأكيد التعديل وإغلاق الصفحة
                            Toast.makeText(EditUser.this, "تم تحديث البيانات بنجاح", Toast.LENGTH_SHORT).show();
                            finish(); // بعد ان يتم التحديث ينهي الواجهة الحالية
                        } else { // غير ذلك
                            // عرض رسالة خطأ في حالة عدم تمكن من تحديث البيانات
                            Toast.makeText(EditUser.this, "حدث خطأ أثناء تحديث البيانات", Toast.LENGTH_SHORT).show();
                        }

                    }


                }
            }
        });

    }


}
