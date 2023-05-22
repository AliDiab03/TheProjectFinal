package com.example.theprojectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class signup extends AppCompatActivity {
    EditText edTextSignUpUserName ,edTextSignUpEmail ,edTextSignupPassword ;
    Button btnSignUp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        edTextSignUpUserName = findViewById(R.id.edTextSignUpUserName);
        edTextSignUpEmail = findViewById(R.id.edTextSignUpEmail);
        edTextSignupPassword = findViewById(R.id.edTextSignupPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
    }
}