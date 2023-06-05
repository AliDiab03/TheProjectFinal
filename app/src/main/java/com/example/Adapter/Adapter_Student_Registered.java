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
    private Context context;
    private List<Student> students;
    private List<Student> selectedStudents;

    public Adapter_Student_Registered(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
        this.selectedStudents = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.iteam_students_subjects, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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
    public int getItemCount() {
        return students.size();
    }

    public ArrayList<Student> getSelectedStudents() {
        return new ArrayList<>(selectedStudents);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvStudentName;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStudentName = itemView.findViewById(R.id.txSubject);
            checkBox = itemView.findViewById(R.id.ckBoxStd);
        }
    }
}


