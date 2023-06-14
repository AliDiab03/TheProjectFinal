
package com.example.theprojectfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dataAdmin.DatabaseHelper;
import com.google.android.material.snackbar.Snackbar;

public class Login extends AppCompatActivity {
    EditText edTextSignInUserName, edTextSignInPassword;
    CheckBox ckBtnRemember;
    Button btnLogin;
    TextView txSignup;
    RelativeLayout coordinatorRelative1;
    DatabaseHelper databaseHelper;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edTextSignInUserName = findViewById(R.id.edTextSignInUserName);
        edTextSignInPassword = findViewById(R.id.edTextSignInPassword);
        ckBtnRemember = findViewById(R.id.ckBtnRemember);
        btnLogin = findViewById(R.id.btnLogin);
        txSignup = findViewById(R.id.txSignup1);
        coordinatorRelative1 = findViewById(R.id.coordinatorRelative1);
        databaseHelper = new DatabaseHelper(this);
        sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);

        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        if (isLoggedIn) {
            Intent intent = new Intent(Login.this, Home.class);
            startActivity(intent);
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edTextSignInUserName.getText().toString();
                String password = edTextSignInPassword.getText().toString();

                if (databaseHelper.getAuthenticateUser(userName, password)) {
                    // تسجيل الدخول بنجاح
                    Toast.makeText(Login.this, "تسجيل الدخول بنجاح", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Login.this, Home.class);
                    startActivity(intent);
                    finish();

                    if (ckBtnRemember.isChecked()) {
                        // حفظ بيانات المستخدم في SharedPreferences عند تسجيل الدخول
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("isLoggedIn", true);
                        editor.putString("userName", userName);
                        editor.putString("password", password);
                        editor.apply();
                    }
                } else {
                    // فشل تسجيل الدخول
                    Toast.makeText(Login.this, "فشل تسجيل الدخول", Toast.LENGTH_SHORT).show();
                }

                if (userName.isEmpty()) {
                    edTextSignInUserName.setError("الرجاء ادخال اسم المستخدم");
                } else if (userName.length() <= 4) {
                    edTextSignInUserName.setError("الرجاء ادخال اسم المستخدم");
                }

                if (password.isEmpty()) {
                    edTextSignInPassword.setError("الرجاء ادخال كلمة المرور بشكل صحيح");
                } else if (password.length() <= 4) {
                    edTextSignInPassword.setError("الرجاء ادخال كلمة المرور بشكل صحيح");
                }
            }
        });

        edTextSignInPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edTextSignInPassword.length() >= 5) {
                    edTextSignInPassword.setTextColor(ContextCompat.getColor(Login.this, R.color.black));
                } else if (edTextSignInPassword.length() <= 5) {
                    edTextSignInPassword.setTextColor(ContextCompat.getColor(Login.this, R.color.red));
                }
            }
        });

        txSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), signup.class); // عند النقل عليه يرسله الى واجهة انشاء حساب
                startActivity(intent);
                finish();
            }
        });
    }
}
