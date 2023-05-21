package com.example.theprojectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    EditText edTextSignInUserName , edTextSignInPassword ;
    CheckBox ckBtnRemember ;
    Button btnLogin ;
    TextView txSignup ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edTextSignInUserName = findViewById(R.id.edTextSignInUserName);
        edTextSignInPassword = findViewById(R.id.edTextSignInPassword);
        ckBtnRemember = findViewById(R.id.ckBtnRemember);
        btnLogin = findViewById(R.id.btnLogin);
        txSignup = findViewById(R.id.txSignup1);




    }
}