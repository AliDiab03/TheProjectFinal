package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dataAdmin.DatabaseHelper;
import com.example.dataAdmin.Subject;
import com.example.theprojectfinal.R;

import java.util.List;
import java.util.Locale;

public class Adapter_Subject_Registered extends RecyclerView.Adapter<Adapter_Subject_Registered.myHolder> {

    Context context ;
    List<Subject>subjects ;
    DatabaseHelper databaseHelper ;
    int studentId ;

    public Adapter_Subject_Registered(Context context, List<Subject> subjects , int studentId ) {
        this.context = context;
        this.subjects = subjects;
        this.studentId = studentId;
        databaseHelper = new DatabaseHelper(context);

    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.iteam_show_student_subject,parent,false);
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {
        Subject subject = subjects.get(position);
        holder.txSubj.setText(subject.getName());
        float attendancePercentage = databaseHelper.calculateAttendancePercentage(studentId, subject.getId());
        holder.txShowTotalSubj.setText(String.format(Locale.getDefault(), "%.2f%%", attendancePercentage));

        if (attendancePercentage >= 85) {
            holder.txShowTotalSubj.setTextColor(ContextCompat.getColor(context, R.color.green));
        } else if (attendancePercentage >= 50) {
            holder.txShowTotalSubj.setTextColor(ContextCompat.getColor(context, R.color.black));
        } else if (attendancePercentage < 50) {
            holder.txShowTotalSubj.setTextColor(ContextCompat.getColor(context, R.color.red));
        }
    }


    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public class myHolder extends RecyclerView.ViewHolder{
        TextView txSubj  , txShowTotalSubj ;

        public myHolder(@NonNull View itemView) {
            super(itemView);
            txSubj =itemView.findViewById(R.id.txShowSubject);
            txShowTotalSubj = itemView.findViewById(R.id.txShowTotalSubj);
        }
    }




}
