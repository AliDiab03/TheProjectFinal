package com.example.theprojectfinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    TextView txPersonName, txEmailAdmin, txAddSubject, txAddStudent;
    DatabaseHelper databaseHelper;
    RelativeLayout layoutPersonName;
    Admin admin;
    Adapter_AddSubject addSubject;
    RecyclerView rcSubject;
    ImageView imgAddSubject, imgAddStudent;
    Button btnClickAllStd;

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
        txAddStudent = findViewById(R.id.txAddStudent);
        imgAddStudent = findViewById(R.id.imgAddStudent);
        btnClickAllStd = findViewById(R.id.btnClickAllStd);
        databaseHelper = new DatabaseHelper(this);


        btnClickAllStd.setOnClickListener(new View.OnClickListener() { // يعمل هذا الاجراء عند النقر عليه ينقله الى واجهة عرض الطلاب
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Home.this, All_Students.class);
                startActivity(intent);

            }
        });

        // استدعاء الوظيفة للحصول على بيانات الـ Admin من قاعدة البيانات
        Admin admin = databaseHelper.getAdmin();


        layoutPersonName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditUser.class); // النتقال الى واجهة التعديل
                intent.putExtra("userName", admin.getUserName()); // ارسال بيانات اليوزر
                intent.putExtra("email", admin.getEmail()); // ارسال بيانات الايميل
                intent.putExtra("password",admin.getPassword()); // ارسال بيانات الباسوورد
                startActivity(intent);
            }
        });

        View.OnClickListener addSubjectClickListener = new View.OnClickListener() { // تعريف كائن من نوع view.clicked
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Add_Subject.class);
                startActivity(intent);
            }
        };
       // هنا عرفت ميثود واجخلتهم داخل البراميتر ليتم الانتقال
        imgAddSubject.setOnClickListener(addSubjectClickListener);
        txAddSubject.setOnClickListener(addSubjectClickListener);

        View.OnClickListener addStudentClickListener = new View.OnClickListener() { // تعريف كائن من نوع view.clicked
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Home.this, Add_Student.class);
                startActivity(intent);

            }
        };
// هنا عرفت ميثود واجخلتهم داخل البراميتر ليتم الانتقال
        imgAddStudent.setOnClickListener(addStudentClickListener);
        txAddStudent.setOnClickListener(addStudentClickListener);


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
            addSubject = new Adapter_AddSubject(this, subjects, new Adapter_AddSubject.onItemClick() {
                @Override
                public void onDelete(int id, int position) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                    builder.setTitle("حذف المادة");
                    builder.setMessage("هل تريد تاكيد حذف المادة");
                    builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (databaseHelper.deleteSubject(String.valueOf(id))) { // اذا تم حذف المادة بنجاح يتحقق الشرط :
                                subjects.remove(position); // يتم ازالتها من موقعها على حسب position
                                addSubject.notifyItemRemoved(position); // يتم ازالة العنصر الذي تم حذفه وترتيب العناصر الاخرى
                            }
                        }
                    });
                    builder.setNegativeButton("لا", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });


                    builder.setCancelable(false);
                    builder.show(); // لعرض الدايلوغ


                }

                @Override
                public void isClicked(Subject subject) {
                    Intent intent = new Intent(Home.this, Month.class); // الانتقال الى الاشهر
                    intent.putExtra("subjectName", subject.getName()); // ارسال بيانات اسم المادة التي يتم الضغط عليها
                    intent.putExtra("subjectId", subject.getId()); // ارسال بيانات معرف المادة التي يتم الضغط عليها
                    startActivity(intent);
                }
            });

            // تهيئة RecyclerView بناءً على احتياجات العرض المطلوبة (LinearLayoutManager أو GridLayoutManager)
            rcSubject.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2)); // أو يمكنك استخدام GridLayoutManager حسب الاحتياجات

            // إعادة تعيين الـ Adapter المحدث إلى RecyclerView
            rcSubject.setAdapter(addSubject);
        }
    }


}


