package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dataAdmin.Subject;
import com.example.theprojectfinal.R;

import java.util.ArrayList;

public class Adapter_Subject_Add_Student extends RecyclerView.Adapter<Adapter_Subject_Add_Student.myHolder> {

    Context context;
    ArrayList<Subject> dataSubject;
    isClicked isClicked;

    public Adapter_Subject_Add_Student(Context context, ArrayList<Subject> dataSubject, isClicked isClicked) {
        this.context = context;
        this.dataSubject = dataSubject;
        this.isClicked = isClicked;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Subject> getDataSubject() {
        return dataSubject;
    }

    public void setDataSubject(ArrayList<Subject> dataSubject) {
        this.dataSubject = dataSubject;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.iteam_add_recycle_add_student_1, parent, false);

        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {
        holder.txSubject.setText(dataSubject.get(holder.getAdapterPosition()).getName());
        holder.ckBoxSubj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isChecked = holder.ckBoxSubj.isChecked();
                dataSubject.get(holder.getAdapterPosition()).setChecked(isChecked); // تحديث حالة التحقق في قائمة المواد

                if (isClicked != null) {
                    isClicked.onCheckBoxClick(holder.getAdapterPosition());
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataSubject.size();
    }

    public class myHolder extends RecyclerView.ViewHolder {
        TextView txSubject;
        CheckBox ckBoxSubj;

        public myHolder(@NonNull View itemView) {
            super(itemView);
            txSubject = itemView.findViewById(R.id.txSubject);
            ckBoxSubj = itemView.findViewById(R.id.ckBoxSubj);
        }
    }

    public interface isClicked {
        void onCheckBoxClick(int position);
    }
}
