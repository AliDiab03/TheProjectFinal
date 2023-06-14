package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dataAdmin.Student;
import com.example.theprojectfinal.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Student_Registered extends RecyclerView.Adapter<Adapter_Student_Registered.ViewHolder> {
    private Context context; // يحتوي على السياق الحالي للاكتفيتي
    private List<Student> students; //قائمة تحتوي على بيانات الطلاب
    private List<Student> selectedStudents; // قائمة تحتوي على الطلاب المحددين

    public Adapter_Student_Registered(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
        this.selectedStudents = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //  تستخدم لإنشاء ViewHolder جديد لعنصر واجهة المستخدم في RecyclerView. تقوم بتهيئة عنصر واجهة المستخدم وإرجاعه
        View view = LayoutInflater.from(context).inflate(R.layout.iteam_students_subjects, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // تستخدم لربط بيانات الطالب مع عنصر واجهة المستخدم في RecyclerView. تعيين اسم الطالب واستماع حدث تحديد/عدم تحديد المربع.
        Student student = students.get(position);
        String fullName = student.getFirstName() + " " + student.getLastName();
        holder.tvStudentName.setText(fullName);

        // تعيين مربع الاختيار واستماع حدث التحديد/عدم التحديد
        holder.checkBox.setChecked(student.isSelected());
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            student.setSelected(isChecked);
            if (isChecked) {
                selectedStudents.add(student);
            } else {
                selectedStudents.remove(student);
            }
        });
    }

    @Override
    public int getItemCount() { // تستخدم للحصول على عدد العناصر في القائمة.
        return students.size();
    }

    public ArrayList<Student> getSelectedStudents() { // تستخدم للحصول على الطلاب المحددين
        return new ArrayList<>(selectedStudents);
    }

    public class ViewHolder extends RecyclerView.ViewHolder { // تحتوي على مراجع لعناصر واجهة المستخدم داخل عنصر واجهة المستخدم في RecyclerView.
        TextView tvStudentName;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStudentName = itemView.findViewById(R.id.txSubject);
            checkBox = itemView.findViewById(R.id.ckBoxStd);
        }
    }
}


