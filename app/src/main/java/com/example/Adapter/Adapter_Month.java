package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theprojectfinal.Month;
import com.example.theprojectfinal.R;

import java.util.ArrayList;

public class Adapter_Month extends RecyclerView.Adapter<Adapter_Month.myHolder> {
    Context context ;
     String[] months;

    public Adapter_Month(Context context, String[] months) {
        this.context = context;
        this.months = months;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_activity_month,parent,false);
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {
        String month = months[position];
        holder.monthTextView.setText(month);
        holder.cardMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked to" +month, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return months.length;
    }

    public  class  myHolder extends RecyclerView.ViewHolder{
        TextView monthTextView;
        CardView cardMonth ;
        public myHolder(@NonNull View itemView) {
            super(itemView);
            monthTextView = itemView.findViewById(R.id.txMonth);
          cardMonth =  itemView.findViewById(R.id.cardMonth);
        }
    }
}
