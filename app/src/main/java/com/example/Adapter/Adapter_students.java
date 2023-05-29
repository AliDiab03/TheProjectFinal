package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dataAdmin.Student;
import com.example.theprojectfinal.R;

import java.util.ArrayList;

public class Adapter_students extends RecyclerView.Adapter<Adapter_students.myHolder> {

    Context context ;
    ArrayList<Student>students ;

    public Adapter_students(Context context, ArrayList<Student> students) {
        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //iteam  تذكر غير
        View view = LayoutInflater.from(context).inflate(R.layout.iteam_all_student,parent,false);

        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {
holder.nameStudent.setText(students.get(holder.getAdapterPosition()).getFirstName());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class myHolder extends RecyclerView.ViewHolder{
        TextView nameStudent ;
        public myHolder(@NonNull View itemView) {
            super(itemView);
            nameStudent =  itemView.findViewById(R.id.txSubject);
        }
    }
}
