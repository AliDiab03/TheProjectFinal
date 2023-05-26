package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dataAdmin.Subject;
import com.example.theprojectfinal.R;

import java.util.ArrayList;

public class Adapter_AddSubject extends RecyclerView.Adapter<Adapter_AddSubject.myHolder> {
Context context ;
ArrayList<Subject>data ;
onItemClick onItemClick ;

    public Adapter_AddSubject(Context context, ArrayList<Subject> data, Adapter_AddSubject.onItemClick onItemClick) {
        this.context = context;
        this.data = data;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.iteam_subject_recycle,parent,false);

        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {
        holder.iteamTxSubject.setText(data.get(position).getName());
        holder.iteamDeleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION){
                    onItemClick.onDelete(data.get(adapterPosition).getId(),adapterPosition);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class myHolder extends RecyclerView.ViewHolder{

        TextView iteamTxSubject ;
        ImageView iteamDeleteStudent ;

        public myHolder(@NonNull View itemView) {
            super(itemView);
                   iteamTxSubject = itemView.findViewById(R.id.iteamTxSubject);
            iteamDeleteStudent =  itemView.findViewById(R.id.iteamDeleteStudent);
        }
    }

    public void setData(ArrayList<Subject> newData) {
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();
    }

    public interface onItemClick{
void onDelete (int id , int position);
    }
}
