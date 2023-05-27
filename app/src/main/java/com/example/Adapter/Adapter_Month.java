package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theprojectfinal.Month;
import com.example.theprojectfinal.R;

import java.util.ArrayList;

public class Adapter_Month extends RecyclerView.Adapter<Adapter_Month.myHolder> {
    private String[] months;

    public Adapter_Month(String[] months) {
        this.months = months;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_activity_month,parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {
        String month = months[position];

    }

    @Override
    public int getItemCount() {
        return months.length;
    }

    public  class  myHolder extends RecyclerView.ViewHolder{
        TextView monthTextView;
        public myHolder(@NonNull View itemView) {
            super(itemView);
            monthTextView = itemView.findViewById(R.id.txMonth);
        }
    }
}
