package com.example.theprojectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dataAdmin.Admin;
import com.example.dataAdmin.DatabaseHelper;

import org.w3c.dom.Text;

public class Home extends AppCompatActivity {

    TextView txPersonName , txEmailAdmin ;
    DatabaseHelper databaseHelper ;
    RelativeLayout layoutPersonName ;
    Admin admin ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ;

            txPersonName = findViewById(R.id.txPersonName);
            txEmailAdmin = findViewById(R.id.txEmailAdmin);
            layoutPersonName = findViewById(R.id.layoutPersonName);
            databaseHelper = new DatabaseHelper(this);

        // استدعاء الوظيفة للحصول على بيانات الـ Admin من قاعدة البيانات
        Admin admin = databaseHelper.getAdmin();






            layoutPersonName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),EditUser.class);
                    intent.putExtra("userName", admin.getUserName());
                    intent.putExtra("email", admin.getEmail());
                    startActivity(intent);
                }
            });



        }

    @Override
    protected void onResume() {
        super.onResume();

        // استدعاء الوظيفة للحصول على بيانات الـ Admin من قاعدة البيانات
        admin = databaseHelper.getAdmin();

        if (admin != null) {
            // عرض بيانات الـ Admin على واجهة المستخدم
            txPersonName.setText(admin.getUserName());
            txEmailAdmin.setText(admin.getEmail());
        }
    }


}