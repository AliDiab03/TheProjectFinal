package com.example.theprojectfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;

public class signup extends AppCompatActivity {
    EditText edTextSignUpUserName, edTextSignUpEmail, edTextSignupPassword;
    Button btnSignUp;
    RelativeLayout coordinatorRelative2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        edTextSignUpUserName = findViewById(R.id.edTextSignUpUserName);
        edTextSignUpEmail = findViewById(R.id.edTextSignUpEmail);
        edTextSignupPassword = findViewById(R.id.edTextSignupPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        coordinatorRelative2 = findViewById(R.id.coordinatorRelative2);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isClicked = true;
                String userName = edTextSignUpUserName.getText().toString();
                String email = edTextSignUpEmail.getText().toString();
                String password = edTextSignupPassword.getText().toString();

                if (userName.isEmpty()) {
                    edTextSignUpUserName.setError("الرجاء ادخال اسم المستخدم");
                    isClicked = false;
                } else if (userName.length() <= 4) {
                    edTextSignUpUserName.setError("يجب ان لا يكون اسم المسنخدم اقل من 4");
                    isClicked = false;
                }
                if (email.isEmpty()) {
                    edTextSignUpEmail.setError("الرجاء ادخال البريد الالكتروني");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    edTextSignUpEmail.setError("الرجاء ادخال البريد الالكتروني بشكل صحيح");
                    isClicked = false;
                }
                if (password.isEmpty()){
                    edTextSignupPassword.setError("الرجاء ادخال كلمة مرور قوية");
                    isClicked = false ;
                }else if (password.length()<=4){
                    edTextSignupPassword.setError("يجب ان لا تكون كلمة المرور اقل من 4");
                    isClicked = false ;
                }


                if (isClicked){
                    Intent intent = new Intent(signup.this,Login.class);
                    startActivity(intent);
                    finish();
                }else {
                    Snackbar snackbar = Snackbar.make(coordinatorRelative2, "الرجاء ادخال البيانات بشكل صحيح", Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(signup.this, R.color.teal_700));
                    snackbar.show();
                    snackbar.setTextColor(ContextCompat.getColor(signup.this, R.color.white));
                }


            }
        });

    }
}