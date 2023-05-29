package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dataAdmin.Student;
import com.example.dataAdmin.Subject;
import com.example.theprojectfinal.R;

import java.util.ArrayList;

public class Adapter_AllStudent_Home extends RecyclerView.Adapter<Adapter_AllStudent_Home.myHolder> {
    Context context ;
    ArrayList<Student>students ;
    isClicked isClicked ;

    public Adapter_AllStudent_Home(Context context, ArrayList<Student> students, Adapter_AllStudent_Home.isClicked isClicked) {
        this.context = context;
        this.students = students;
        this.isClicked = isClicked;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.iteam_all_student_home,parent,false);

        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {

        Student student = students.get(position);
        String fullName = student.getFirstName() + " " + student.getLastName();
        holder.txStudent.setText(fullName);

       holder.imgDeleteStd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               isClicked.isDeleted(students.get(holder.getAdapterPosition()).getId(), holder.getAdapterPosition());
           }
       });

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class myHolder extends RecyclerView.ViewHolder{
        TextView txStudent ;
        ImageView imgDeleteStd ;
        public myHolder(@NonNull View itemView) {
            super(itemView);
            txStudent = itemView.findViewById(R.id.txStudent);
            imgDeleteStd = itemView.findViewById(R.id.imgDeleteStd);
        }
    }

    public void setData(ArrayList<Student> newData) {
        students.clear();
        students.addAll(newData);
        notifyDataSetChanged();
    }

  public   interface isClicked{
        void isDeleted (int id , int position);
    }

}
