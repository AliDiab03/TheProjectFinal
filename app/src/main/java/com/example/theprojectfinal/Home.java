package com.example.theprojectfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.Adapter.Adapter_AddSubject;
import com.example.dataAdmin.Admin;
import com.example.dataAdmin.DatabaseHelper;
import com.example.dataAdmin.Subject;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    TextView txPersonName, txEmailAdmin, txAddSubject;
    DatabaseHelper databaseHelper;
    RelativeLayout layoutPersonName;
    Admin admin;
    Adapter_AddSubject addSubject;
    RecyclerView rcSubject;
    ImageView imgAddSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ;

        txPersonName = findViewById(R.id.txPersonName);
        txEmailAdmin = findViewById(R.id.txEmailAdmin);
        layoutPersonName = findViewById(R.id.layoutPersonName);
        rcSubject = findViewById(R.id.rcSubject);
        imgAddSubject = findViewById(R.id.imgAddSubject);
        txAddSubject = findViewById(R.id.txAddSubject);
        databaseHelper = new DatabaseHelper(this);

        // استدعاء الوظيفة للحصول على بيانات الـ Admin من قاعدة البيانات
        Admin admin = databaseHelper.getAdmin();


        layoutPersonName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditUser.class);
                intent.putExtra("userName", admin.getUserName());
                intent.putExtra("email", admin.getEmail());
                startActivity(intent);
            }
        });

        View.OnClickListener addSubjectClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Add_Subject.class);
                startActivity(intent);
            }
        };

        imgAddSubject.setOnClickListener(addSubjectClickListener);
        txAddSubject.setOnClickListener(addSubjectClickListener);


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

        // استدعاء الوظيفة للحصول على بيانات المواد من قاعدة البيانات
        ArrayList<Subject> subjects = databaseHelper.getSubjects();

        // تحديث قائمة البيانات في الـ Adapter إذا كان مهيأً بالفعل
        if (addSubject != null) {
            addSubject.setData(subjects);
        } else {
            // تهيئة Adapter_AddSubject إذا لم يكن مهيأً بالفعل
            addSubject = new Adapter_AddSubject(this, subjects, new Adapter_AddSubject.onItemClick() {
                @Override
                public void onDelete(int id, int position) {
                    if (databaseHelper.deleteSubject(String.valueOf(id))) {
                        subjects.remove(position);
                        addSubject.notifyItemRemoved(position);
                    }
                }
            });

            // تهيئة RecyclerView بناءً على احتياجات العرض المطلوبة (LinearLayoutManager أو GridLayoutManager)
            rcSubject.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2)); // أو يمكنك استخدام GridLayoutManager حسب الاحتياجات

            // إعادة تعيين الـ Adapter المحدث إلى RecyclerView
            rcSubject.setAdapter(addSubject);
        }
    }


}


