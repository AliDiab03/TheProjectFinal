package com.example.theprojectfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
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

import com.google.android.material.snackbar.Snackbar;

public class Login extends AppCompatActivity {
    EditText edTextSignInUserName, edTextSignInPassword;
    CheckBox ckBtnRemember;
    Button btnLogin;
    TextView txSignup;
    RelativeLayout coordinatorRelative1;


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


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isClicked = true;

                String userName = edTextSignInUserName.getText().toString();
                String password = edTextSignInPassword.getText().toString();

                if (userName.isEmpty()) {
                    edTextSignInUserName.setError("الرجاء ادخال اسم المستخدم");
                    isClicked = false;

                } else if (userName.length() <= 4) {
                    edTextSignInUserName.setError("الرجاء ادخال اسم المستخدم");
                    isClicked = false;

                }
                if (password.isEmpty()) {
                    edTextSignInPassword.setError("الرجاء ادخال كلمة المرور بشكل صحيح");
                    isClicked = false;
                } else if (password.length() <= 4) {
                    edTextSignInPassword.setError("الرجاء ادخال كلمة المرور بشكل صحيح");
                    isClicked = false;
                }

                if (ckBtnRemember.isChecked()){

                }

                if (isClicked) {
                    Intent intent = new Intent(Login.this, Home.class);
                    startActivity(intent);
                } else {
                    Snackbar snackbar = Snackbar.make(coordinatorRelative1, "الرجاء ادخال البيانات بشكل صحيح", Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(Login.this, R.color.teal_700));
                    snackbar.show();
                    snackbar.setTextColor(ContextCompat.getColor(Login.this, R.color.white));
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
                Intent intent = new Intent(getApplicationContext(), signup.class);
                startActivity(intent);
                finish();
            }
        });


    }
}