package com.example.theprojectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditUser extends AppCompatActivity {
    EditText edTextEditEmailUserName , edTextEditEmail ,edTextEditPassword ;
    Button btnEdit ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        edTextEditEmailUserName = findViewById(R.id.edTextEditEmailUserName);
        edTextEditEmail = findViewById(R.id.edTextEditEmail);
        edTextEditPassword = findViewById(R.id.edTextEditPassword);
        btnEdit = findViewById(R.id.btnEdit);
    }
}