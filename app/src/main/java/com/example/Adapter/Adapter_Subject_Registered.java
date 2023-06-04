package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dataAdmin.Subject;
import com.example.theprojectfinal.R;

import java.util.List;

public class Adapter_Subject_Registered extends RecyclerView.Adapter<Adapter_Subject_Registered.myHolder> {

    Context context ;
    List<Subject>subjects ;
    isClick isClick ;
    public Adapter_Subject_Registered(Context context, List<Subject> subjects , isClick isClick) {
        this.context = context;
        this.subjects = subjects;
        this.isClick = isClick;
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
        holder.coordinatorTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClick.isClicked(subjects.get(holder.getAdapterPosition()).getId(), holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public class myHolder extends RecyclerView.ViewHolder{
        TextView txSubj ;
        RelativeLayout coordinatorTx ;
        public myHolder(@NonNull View itemView) {
            super(itemView);
            txSubj =itemView.findViewById(R.id.txShowSubject);
            coordinatorTx =  itemView.findViewById(R.id.coordinatorTx);
        }
    }
    public interface isClick {
        void isClicked(int id , int position);
    }
}
